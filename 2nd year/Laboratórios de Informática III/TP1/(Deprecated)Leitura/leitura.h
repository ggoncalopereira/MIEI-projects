#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define SIZE_CLIENTES 20000
#define SIZE_CLIENTE 6
#define SIZE_PRODUTOS 200000
#define SIZE_PRODUTO 7
#define SIZE_VENDAS 2000000
#define SIZE_BUFFER_CL 32
#define SIZE_BUFFER_PR 64
#define SIZE_BUFFER_VE 128
#define NUM_FILIAL 3

typedef struct {
	int codigos_cl[26],codigos_pr[26],*compras_cl,*compras_pr;
	double quant_mes[12],fact_mes[12],quant_filial[NUM_FILIAL],fact_filial[NUM_FILIAL];	
	double facturacao,quant;
}Facturacao;

typedef struct {
	int quant,mes,filial;
	float preco;
	char promo,*cliente,*produto;
}Venda;

typedef struct {
	int fill;
	Venda *vendas;
}Vendas;

typedef struct {
	int fill;
	char *produtos;
}Produtos;

typedef struct {
	int fill;
	char *clientes;
}Clientes;


/*Secção 1: Inits*/
Clientes init_cl(Clientes *);
Produtos init_pr(Produtos *);
Vendas init_ve(Vendas *);
Facturacao init_fact(Facturacao *);
void destroy_facturacao(Facturacao *);


/*Secção 2: prints*/
int printcl(Clientes *);
int printpr(Produtos *);
int printve(Vendas *);
int print_facturacao(Facturacao *,Clientes *,Produtos *produtos);
void print_fact_mes(FILE *,Facturacao *);
void escreve_mes(FILE *,Facturacao *,int);
void print_fact_filial(FILE *,Facturacao *);
void print_fact_codigos(FILE *,Facturacao *);
void print_fact_compras_pr(FILE *,Facturacao *,Produtos *);
void print_fact_compras_cl(FILE *,Facturacao *,Clientes *);
void print_af1184(Vendas *,const char *);
void print_z5000(Vendas *,const char *);

/*Secção 3: reads */
Clientes readclients(Clientes *,FILE *);
int readproducts(Produtos *,const char *);
int readsales(Vendas *,const char *,Clientes *,Produtos *);
int read_line_vendas(Vendas *,FILE *,Clientes *,Produtos *);
char read_token_pr(char *,Vendas *,int,Produtos *);
char read_token_preco(char *,Vendas *,int);
char read_token_quant(char *,Vendas *,int);
char read_token_promo(char *,Vendas *,int);
char read_token_cl(char *,Vendas *,int,Clientes *);
char read_token_mes(char *,Vendas *,int);
char read_token_filial(char *,Vendas *,int);
int write_factura(Facturacao *,Clientes *,Produtos *,Vendas *);

/*Secção 4: checks*/
char check_cliente(char *);
char check_produto(char *);
char *search_cl(char *,Clientes *);
char *search_pr(char *,Produtos	 *);
char *bsearch_cl(char *,Clientes *,int,int);
char *bsearch_pr(char *,Produtos *,int,int);

/*Secção 5: Utils*/
void zeroarrayi(int *,int);
void zeroarrayd(double *,int);
int cmpr_strcmp(const void *, const void *);
