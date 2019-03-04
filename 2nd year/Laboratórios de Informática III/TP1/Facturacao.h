#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>


typedef struct fact* Facturacao;
typedef int * Codigos;
#ifndef BOOLEAN
#define BOOLEAN
typedef char Boolean;
#endif

/*tipos Opacos para todos as livrarias importadas*/

/*Can we interface???*/
Produtos initFacturacao(Codigos clientes,Codigos produtos,Codigos ncl,Codigos npr);
void destroiProdutos(Produtos);


/*Transformações*/


/*Lookups*/

