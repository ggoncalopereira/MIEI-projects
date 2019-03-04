#include "Produtos_arv.h"

#define SIZE_ALPHA 26
#define SIZE_PRODUTO 7
#define SIZE_MASK 1000000

struct prods {
	Arvore codigos[SIZE_ALPHA];
};

struct prod {
	unsigned char index;
	int codigo;
};



static int prodcmp(const void *acmp,const void *valorcmp, void* nada);
static int printProduto(void *produto,void *n,void *fd);
static struct prod toProduto(Produto);


Produtos initProdutos() {
	int i;
	Produtos produtos=(Produtos)malloc(sizeof(struct prods));
	for (i=0;i<SIZE_ALPHA;i++)
		produtos->codigos[i]=g_tree_new_full(prodcmp,NULL,free,NULL);	
	return produtos;
}


void destroiProdutos(Produtos produtos) {
	int i;
	for(i=0;i<SIZE_ALPHA;i++)
		g_tree_destroy(produtos->codigos[i]);
	free(produtos);
}




Produtos insereProduto(Produtos produtos,Produto produto){
	struct prod *produtor=(struct prod *)malloc(sizeof(struct prod));
	(*produtor)=toProduto(produto);
	g_tree_insert(produtos->codigos[produtor->index],produtor,NULL);

	return produtos;
}


/** função que assegura que o token tem o formato "AAXXXX", em que A são maiúsculas e XXXX um número
  * entre 0000 e 9999
**/
Boolean check_produto(const char *sproduto) {
	Boolean isproduto=1;
	int i;
	if (sproduto) {
		for(i=0;i<2 && sproduto[i]!='\0';i++)
			if (!isupper(sproduto[i]))
				isproduto=0;
		for(;i<SIZE_PRODUTO-1 && sproduto[i]!='\0';i++)
			if (!isdigit(sproduto[i]))
				isproduto=0;
		if (i<SIZE_PRODUTO-1)
			isproduto=0;
	}
	else 
		isproduto=0;
	return isproduto;
}


Boolean existeProduto(Produtos produtos,Produto produto){
	struct prod *produtor=(struct prod *)malloc(sizeof(struct prod));
	(*produtor)=toProduto(produto);	
	return (g_tree_lookup(produtos->codigos[produtor->index],produtor)!=NULL);
}



/**operacao insegura, verificar cons char* com check_produto primeiro**/
static struct prod toProduto(Produto sproduto){
	struct prod produtor;
	produtor.index=sproduto[0]-'A';
	produtor.codigo=(sproduto[1]*SIZE_MASK)+
					strtol(sproduto+2,NULL,10);
	return produtor;
}




int elementosProdutos(Produtos produtos){
	int i,res;
	for(i=res=0;i<SIZE_ALPHA;i++)
		res+=g_tree_nnodes(produtos->codigos[i]);
	return res;
}


void printProdutosToF(Produtos produtos,FILE *fd){
	int i;
	for(i=0;i<SIZE_ALPHA;i++)
		g_tree_foreach(produtos->codigos[i],printProduto,fd);
}

static int printProduto(void *produto,void *n,void *fd){
	int i=0;
	char c='A';
	struct prod *produtor=(struct prod *)produto;
	FILE *fde=(FILE *)fd;
	if (produto==NULL) 
		i++;	
	else
		fprintf(fde,"%c%c%d\n",c+produtor->index,produtor->codigo/SIZE_MASK,produtor->codigo%SIZE_MASK);
	return i;
}




static int prodcmp(const void *compv,const void *serav,void *n){
	struct prod *comp=(struct prod *)compv;
	struct prod *sera=(struct prod *)serav;
	if (comp->codigo<sera->codigo)
		return -1;
	else 
		return (comp->codigo>sera->codigo);
}

