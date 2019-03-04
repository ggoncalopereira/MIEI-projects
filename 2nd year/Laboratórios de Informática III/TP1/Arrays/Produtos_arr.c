#include "Produtos_arr.h"

#define SIZE_ALPHA 26
#define SIZE_PRODUTO 7
#define SIZE_MASK 1000000
#define PAGE_LEN 20


struct prods {
	int fill[SIZE_ALPHA];
	int *codigos[SIZE_ALPHA];
};


struct prod {
	unsigned char index;
	int codigo;
};


struct conj_p {
	unsigned char letra;	
	int fill,pages,remainder;
	int *codigos;
};


struct lista_p {
	int *fill;
	int **codigos;
};


static int prodcmp(const void *acmp,const void *valorcmp);
static Boolean check_produto(const char *sproduto);
static int bsearch_pr(CatProds catprods,Produto produto,int max);
static void printPageConjunto(Conjunto_Prods cnj,int page);


CatProds initCatProds() {
	int i;
	CatProds catprods;
	catprods = (CatProds)malloc(sizeof(struct prods));
	if(!catprods) {
		perror("Falha a alocar estrutura de CatProds principal");	
	}
	else {	
		for(i=0;i<SIZE_ALPHA && catprods;i++){
			catprods->fill[i]=0;			
			catprods->codigos[i]=(int *)malloc(sizeof(int)
							*SIZE_PRODUTOS/SIZE_ALPHA);
			if (!catprods->codigos[i]) {
				perror("Falha a alocar estruturas de CatProds");
				for(i--;i>0;i--)
					free(catprods->codigos[i]);			
				free(catprods);
				catprods=NULL;
			}
		}	
	}
	return catprods;
}



void removeCatProds(CatProds catprods) {
	int i;
	for (i=0;i<SIZE_ALPHA;i++){
		free(catprods->codigos[i]);
	}
	free(catprods);
}



void removeConjuntoProds(Conjunto_Prods cnj){
	free(cnj);
}



Produto criaProduto(const char *sproduto){	
	static struct prod produto;
	Produto prod=NULL;
	if (check_produto(sproduto)) {
		produto.index=sproduto[0]-'A';
		produto.codigo=(sproduto[1]*SIZE_MASK)+
						strtol(sproduto+2,NULL,10);
	}
	else
		return prod;
	prod=&produto;
	return prod;
}


CatProds insereProduto(CatProds catprods,Produto produto){
	catprods->codigos[produto->index]
				[catprods->fill[produto->index]]=produto->codigo;
	catprods->fill[produto->index]++;
	return catprods;
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


Boolean existeProduto(CatProds catprods,Produto produto){
	return (bsearch_pr(catprods,produto,
				catprods->fill[produto->index])
							==produto->codigo);
}



void sortCatProds(CatProds catprods){
	int i;
	for (i=0;i<SIZE_ALPHA;i++)	
		qsort(catprods->codigos[i],catprods->fill[i],
						sizeof(int),prodcmp);
}




int totalProdutos(CatProds catprods){
	int i,res;
	for(i=res=0;i<SIZE_ALPHA;i++)
		res+=catprods->fill[i];
	return res;
}

int totalProdutosLetra(CatProds catprods,unsigned char letra){
	letra-='A';
	return catprods->fill[letra];
} 


Conjunto_Prods prodsPorLetra(CatProds catprods,unsigned char letra){
	Conjunto_Prods cnj=(Conjunto_Prods)malloc(sizeof(struct conj_p));
	cnj->letra=letra;		
	letra-='A';
	cnj->fill=catprods->fill[letra];
	cnj->remainder=cnj->fill%PAGE_LEN;
	cnj->pages=cnj->fill/PAGE_LEN;
	if (cnj->remainder)
		cnj->pages++;
	cnj->codigos=catprods->codigos[letra];
	return cnj;
}


void printConjunto(Conjunto_Prods cnj){
	int page,n,stop=1;
	printf("Produtos começados por %c: %d\n",cnj->letra,cnj->fill);
	do {
		printf("\n%d páginas\nDigite um número de página:",cnj->pages);
		n=scanf("%d",&page);
		if (n || n!=EOF)
			printPageConjunto(cnj,page);
		else
			stop=0;
	} while(stop);
}


static void printPageConjunto(Conjunto_Prods cnj,int page){
	int i,j=PAGE_LEN;
	if (page==cnj->pages && cnj->remainder)
		j=cnj->remainder;
	for(i=0;i<j;i++){
		printf("%c%c%d\n",cnj->letra,cnj->codigos[page*PAGE_LEN+i]/SIZE_MASK,cnj->codigos[page*PAGE_LEN+i]%SIZE_MASK);
	}
}


static int bsearch_pr(CatProds catprods,Produto produto,int max) {
	int res=0,min=0,mid;
	do {
		mid=(min+max)/2;		
		res=catprods->codigos[produto->index][mid];
		if(produto->codigo<res) {
			max=mid-1;			
			res=0;
		}
		else if (produto->codigo>res) {
			min=mid+1;			
			res=0;
		}
	} while (!res && mid>=min && mid<=max); 
	return res;
}






static int prodcmp(const void *compv,const void *serav){
	int *comp=(int *)compv;
	int *sera=(int *)serav;
	if ((*comp)<(*sera))
		return -1;
	else 
		return (*comp)>(*sera);
}

