#pragma once
#include <fstream>
#include <string>
#define _USE_MATH_DEFINES
#include <math.h>
#define TOL 10E-6

using std::ofstream;
using std::ifstream;

void generatePlane(ofstream& fp, float length, float width);
void generateBox(ofstream& fp, float length, float height, float width, int divisions);
void generateBox(ofstream& fp, float length, float height, float width);
void generateSphere(ofstream& fp, float radius, int slices, int stacks);
void generateCone(ofstream& fp, float radius, float height, int slices, int stacks);
void generateCylinder(ofstream& fp,float radius,float height,int slices,int stacks); //optional
void generateFlatDiscus(ofstream& fp, float inner_radius, float outer_radius,
						int slices);
void generateFromPatches(ofstream& fp, ifstream &patchfile, int tessalation);