#pragma once
//must import stdlib from tinyxml2 first otherwise 
//exit redefinition from glut breaks everything
#include "tinyxml2.h"
#ifdef __APPLE__
#include <GL/glut.h>
#else
#include <GL/glew.h>
#include <GL/glut.h>
#include <IL/il.h>
#endif
#include <vector>
#include <unordered_map>
#include <string>
#include <iostream>
#include <fstream>

#define _USE_MATH_DEFINES
#include <math.h>
#include "VectorMath.h"

#define ANG2RAD M_PI/180.0

using std::vector;
using std::string;
using std::unordered_map;
using namespace tinyxml2;

class SceneTree;
class Component;
class ModelComponent;
class TextureComponent;
class AnimationComponent;
class GroupComponent;



enum Op : unsigned char 
{
	ID, TR, RT, SC, ANT, ANR
};

enum ActiveCamera : unsigned char 
{
	FP, TP
};


typedef struct fp_camera_struct
{
	fp_camera_struct() : alpha(M_PI), beta(0.0f), k(0.2), 
						 camX(15), camY(0), camZ(15) {}
	//spherical coordinates based on alpha and beta angles + radius
	float alpha, beta, k;
	float camX, camY, camZ;
} FirstPersonCamera;

typedef struct tp_camera_struct
{
	tp_camera_struct() : alpha(0.0f), beta(45.0f), radius(50.0f),
						 startX(0), startY(0), tracking(0), 
						 camX(0), camY(30), camZ(40) {}
	float alpha, beta, radius;
	int startX, startY, tracking;
	float camX, camY, camZ;
}ThirdPersonCamera;


class SceneTree 
{
	private:
		vector<Component *> elements;
		vector<int> staticLightsToDisable, globalLightsToDisable;
			
	public:
		//Constructor
		SceneTree(const char *file);
		//Destructor
		~SceneTree();

		void renderTree();
};

class Component 
{
	public:
		//boolean
		bool bIsGroupingComponent;
		Component(bool bIsGroupingComponent)
		{
			this->bIsGroupingComponent = bIsGroupingComponent;
		}
		~Component() {}

		virtual void renderComponent()=0;
};

class ModelComponent : public Component 
{
	private:
		string model;
		int bound_buffer_index;

		//Use as a giant pile of vertices you go through as an array
		int v_size;
		Vector3D* vertices;
		Vector3D* normals;
		Vector2D* tex_coords;
	
	public:
		ModelComponent(const char *model);
		ModelComponent(string model); 
		~ModelComponent();
		void renderComponent();
		void assignBuffer(int index);
};


class AnimationComponent : public Component
{
	friend GroupComponent;
private:
	float curve_time;
	float curve_step;
	vector<Vector3D> catmull_points;

public:
	AnimationComponent();
	bool getAnimFromPoints(float time, XMLElement *current);

	void renderComponent();
};


class GroupComponent : public Component
{
private:
	//Each group component can only have one transform and one rotate
	AnimationComponent animation;
	Vector3D translate;
	Vector3D scale;
	Vector3D rotate;
	vector<int> lightsToDisable;
	//rtt_angortime can function to store the rotation time for a 360� rotation
	//or a fixed rotation angle
	float rtt_angortime;

	//vector that holds which operation to do first, second and third.
	Op order_vector[3];

	//Each group can have subgroups, necessitates recursive handling
	//Or stack based iteration and manual pushing into its component vector from
	//outside the group component.
	vector<Component *> elements;
	
public:
	//Stack based?
	/*
	GroupComponent();
	GroupComponent PushElement(Component& element);
	*/
	//Recursive?
	GroupComponent(XMLElement *current);
	~GroupComponent();

	void renderComponent();
	void rotate_();
};

class TextureComponent : public Component 
{
private:
	unsigned int width, height, tex_index, il_index;
	unsigned char *texData;
	string texture;

public :
	TextureComponent(string path);

	void renderComponent();

	void assignBuffer(int index);
};

class MaterialComponent : public Component
{
	friend void processModelAttributes(vector<Component *>&elements, XMLElement *current);

	private:
		float diffuse[4];
		float ambient[4];
		float specular[4];
		float emissive[4];
		float shininess;

	public:
		MaterialComponent();
		MaterialComponent(float *RGB, float *specular, float *emissive, float *ambient, float shininess);

		void renderComponent();
};

class LightComponent : public Component
{
	friend void processLightsIntoVector(vector<int> lightsToDisable, vector<Component *>&elements,
										XMLElement *current, bool bResetLights);

	private:
		float pos[4];
		float diffuse[4];
		float specular[4];
		float ambient[4];

		//It is always the case that GL_LIGHTi = GL_LIGHT0+i.
		int light_index;

	public:
		LightComponent();
		LightComponent(float *pos,float *diffuse, float *specular, float *ambient, int index);

		void renderComponent();
};
