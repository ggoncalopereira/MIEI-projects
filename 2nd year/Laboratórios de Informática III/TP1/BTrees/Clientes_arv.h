#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <time.h>
#include <glib.h>

#define SIZE_CLIENTES 20000

#ifndef ARVORE
#define ARVORE
typedef GTree *Arvore;
#endif
typedef struct clies* Clientes;
typedef const char* Cliente;
#ifndef BOOLEAN
#define BOOLEAN
typedef char Boolean;
#endif

/*tipos Opacos para todos as livrarias importadas*/

/*Qualquer catálogo tem de ser destruído depois de usado*/
Clientes initClientes();
void destroiClientes(Clientes);


/*Transformações*/
Clientes insereCliente(Clientes,Cliente);

/*Lookups*/
Boolean existeCliente(Clientes,Cliente);
Boolean check_cliente(const char*);
void printClientesToF(Clientes clientes,FILE *fd);
int elementosClientes(Clientes);
