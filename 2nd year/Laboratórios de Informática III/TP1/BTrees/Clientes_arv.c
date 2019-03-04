#include "Clientes_arv.h"

#define SIZE_CLIENTE 6
#define SIZE_ALPHA 26

struct clies {
	Arvore codigos[SIZE_ALPHA];
};

struct clie {
	unsigned char index;
	int codigo;
};


static int cliecmp(const void *acmp,const void *valorcmp, void *nada);
/*
static int bsearch_cl(Clientes clientes,struct clie cliente,int min,int max);
*/
static int printCliente(void *cliente,void *novalue,void *fd);
static struct clie toCliente(Cliente);



Clientes initClientes() {
	int i;
	Clientes clientes=(Clientes)malloc(sizeof(struct clies));
	for (i=0;i<SIZE_ALPHA;i++)
		clientes->codigos[i]=g_tree_new_full(cliecmp,NULL,free,NULL);	
	return clientes;
}


void destroiClientes(Clientes clientes) {
	int i;
	for(i=0;i<SIZE_ALPHA;i++)
		g_tree_destroy(clientes->codigos[i]);
	free(clientes);
}



Clientes insereCliente(Clientes clientes,Cliente cliente){
	struct clie *clienter=(struct clie *)malloc(sizeof(struct clie));
	(*clienter)=toCliente(cliente);
	g_tree_insert(clientes->codigos[clienter->index],clienter,NULL);

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




Boolean existeCliente(Clientes clientes,Cliente cliente){
	struct clie *clienter=(struct clie *)malloc(sizeof(struct clie));
	(*clienter)=toCliente(cliente);	
	return (g_tree_lookup(clientes->codigos[clienter->index],clienter)!=NULL);
}



int elementosClientes(Clientes clientes){
	int i,res;
	for (i=res=0;i<SIZE_ALPHA;i++)
		res+=g_tree_nnodes(clientes->codigos[i]);
	return res;
}

void printClientesToF(Clientes clientes,FILE *fd){
	int i;
	for (i=0;i<SIZE_ALPHA;i++)
		g_tree_foreach(clientes->codigos[i],printCliente,fd);
}

static int printCliente(void *cliente,void *n,void *fd){
	int i=0;
	char c='A';
	struct clie *clienter=(struct clie *)cliente;
	FILE *fde=(FILE *)fd;
	if (cliente==NULL) 
		i++;	
	else
		fprintf(fde,"%c%d\n",c+clienter->index,clienter->codigo);
	return i;
}



static struct clie toCliente(Cliente scliente){
	struct clie cliente;
	cliente.index=scliente[0]-'A';	
	cliente.codigo=strtol(scliente+1,NULL,10);
	return cliente;
}



static int cliecmp(const void *compv,const void *serav,void *n){
	struct clie *comp=(struct clie *)compv;
	struct clie *sera=(struct clie *)serav;
	if (comp->codigo<sera->codigo)
		return -1;
	else 
		return (comp->codigo>sera->codigo);
}
