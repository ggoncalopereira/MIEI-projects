#include "Clientes_arr.h"

#define SIZE_CLIENTE 6
#define SIZE_ALPHA 26

struct clies {
	int fill[SIZE_ALPHA];
	int *codigos[SIZE_ALPHA];
};

struct clie {
	unsigned char index;
	int codigo;
};


static int cliecmp(const void *acmp,const void *valorcmp);
static int bsearch_cl(Clientes clientes,struct clie cliente,int min,int max);
static struct clie toCliente(Cliente);



Clientes initClientes() {
	int i;
	Clientes clientes;
	clientes = (Clientes)malloc(sizeof(struct clies));
	if(!clientes) {
		perror("Falha a alocar estrutura de Clientes principal");	
	}
	else {	
		for(i=0;i<SIZE_ALPHA && clientes;i++){
			clientes->fill[i]=0;
			clientes->codigos[i]=(int *)malloc(sizeof(int)
						*SIZE_CLIENTES/SIZE_ALPHA);

			if (!clientes->codigos[i]) {
				perror("Falha a alocar estruturas de Clientes");
				for(i--;i>0;i--)
					free(clientes->codigos[i]);
				free(clientes);
				clientes=NULL;
			}
		}	
	}
	return clientes;
}


void destroiClientes(Clientes clientes) {
	int i;
	for (i=0;i<SIZE_ALPHA;i++){
		free(clientes->codigos[i]);
	}
	free(clientes);
}



Clientes insereCliente(Clientes clientes,Cliente cliente){
	struct clie clienter=toCliente(cliente);
	clientes->codigos[clienter.index][clientes->fill[clienter.index]]
						=clienter.codigo;
	clientes->fill[clienter.index]++;

	return clientes;
}


/** função que assegura que o token tem o formato "AXXXX", em que A é uma maiúscula e XXXX um número
  * entre 0000 e 9999
**/
Boolean check_cliente(const char *cliente) {
	Boolean iscliente=1;
	int i;
	if (cliente) {
		for(i=0;i<1 && cliente[i]!='\0';i++)
			if (!isupper(cliente[i]))
				iscliente=0;
		for(;i<SIZE_CLIENTE-1 && cliente[i]!='\0';i++)
			if (!isdigit(cliente[i]))
				iscliente=0;
		if (i<SIZE_CLIENTE-1)
			iscliente=0;
	}
	else 
		iscliente=0;	
	return iscliente;
}




Boolean existeClientes(Clientes clientes,Cliente cliente){
	struct clie clienter=toCliente(cliente);
	return (bsearch_cl(clientes,clienter,0,clientes->fill[clienter.index])
							==clienter.codigo);
}



void sortClientes(Clientes clientes){
	int i;
	for(i=0;i<SIZE_ALPHA;i++)
		qsort(clientes->codigos[i],clientes->fill[i],sizeof(int),cliecmp);
}


int elementosClientes(Clientes clientes){
	int i,res;
	for (i=res=0;i<SIZE_ALPHA;i++)
		res+=clientes->fill[i];
	return res;
}

void printClientesToF(Clientes clientes,FILE *fd){
	int i,j;
	char c='A';
	for (i=0;i<SIZE_ALPHA;i++)
		for(j=0;j<clientes->fill[i];j++)
			fprintf(fd,"%c%d\n",(c+i),clientes->codigos[i][j]);
}


static int bsearch_cl(Clientes clientes,struct clie cliente,int min,int max) {
	int res=0,mid;
	do {
		mid=(min+max)/2;		
		res=clientes->codigos[cliente.index][mid];
		if(cliente.codigo<res) {
			max=mid-1;
			res=0;
		}
		else if (cliente.codigo>res) {
			min=mid+1;
			res=0;
		}

	} while (!res && mid>=min && mid<=max);
	return res;
}



static struct clie toCliente(Cliente scliente){
	struct clie cliente;
	cliente.index=scliente[0]-'A';
	cliente.codigo=strtol(scliente+1,NULL,10);
	return cliente;
}



static int cliecmp(const void *compv,const void *serav){
	int *comp=(int *)compv;
	int *sera=(int *)serav;
	if ((*comp)<(*sera))
		return -1;
	else 
		return (*comp)>(*sera);
}
