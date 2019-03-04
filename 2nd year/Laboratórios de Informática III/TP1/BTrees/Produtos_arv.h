#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <time.h>
#include <glib.h>

#define SIZE_PRODUTOS 200000

#ifndef ARVORE
#define ARVORE
typedef GTree *Arvore;
#endif
typedef struct prods* Produtos;
typedef const char *Produto;
#ifndef BOOLEAN
#define BOOLEAN
typedef char Boolean;
#endif

/*tipos Opacos para todos as livrarias importadas*/

/*Qualquer Produto ou catálogo tem de ser destruído depois de usado*/
Produtos initProdutos();
void destroiProdutos(Produtos);


/*Transformações*/
Produtos insereProduto(Produtos,Produto);


/*Lookups*/
int elementosProdutos(Produtos);
Boolean check_produto(const char *);
void printProdutosToF(Produtos produtos,FILE *fd);
Boolean existeProdutos(Produtos,Produto);
