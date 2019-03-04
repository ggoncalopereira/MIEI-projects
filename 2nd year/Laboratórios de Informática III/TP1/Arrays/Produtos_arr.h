#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <time.h>

#ifndef CAT_PRODS
#define CAT_PRODS

#define SIZE_PRODUTOS 200000

/*Hide Produto as struct*/
/*Produto as to be unique; there can be no 2 Produtos at any one time - stor said legal???still have my doubts*/
typedef struct prods* CatProds;
typedef struct prod* Produto;
typedef struct conj_p* Conjunto_Prods;
typedef struct list_p* List_Prods;
#ifndef BOOLEAN
#define BOOLEAN
typedef char Boolean;
#endif

/*tipos Opacos para todos as livrarias importadas*/

/*Inits*/

CatProds initCatProds();
void removeCatProds(CatProds);
void removeConjuntoProds(Conjunto_Prods);



/*Transformações*/

void printConjunto(Conjunto_Prods cnj);
CatProds insereProduto(CatProds,Produto);
/*retorna NULL se produto não é válido*/
Produto criaProduto(const char *);
/*chamar após acabar as inserções*/
void sortCatProds(CatProds);



/*Lookups*/

int totalProdutos(CatProds);
int totalProdutosLetra(CatProds,unsigned char);
Conjunto_Prods prodsPorLetra(CatProds,unsigned char);
void printCatProdsToF(CatProds CatProds,FILE *fd);
Boolean existeProduto(CatProds,Produto);


#endif
