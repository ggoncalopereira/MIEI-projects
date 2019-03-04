#include <string.h>
#include "Clientes_arv.h"
#include "Produtos_arv.h"

#define SIZE_BUFFER_CL 32
#define SIZE_BUFFER_PR 64

Clientes readclients(Clientes clientes,FILE *fd);
Produtos readproducts(Produtos produtos,FILE *fd);

/** main cria as estruturas, chama inicializaçoes, le dos ficheiros que lhe sao dados (Clientes seguido do ficheiro de Produtos seguido do ficheiro de Vendas) sorteia os clientes e os produtos, e depois imprime tudo**/

int main(int argc, char *argv[]) {
	clock_t before,after;
	int exitst=-1;
	Clientes clientes;
	Produtos produtos;
/*	Vendas vendas;
	Facturacao facturacao;
*/	FILE *fd;
	if(argc==4){
	clientes=initClientes();

	if (clientes) {
	produtos=initProdutos();

	if (produtos) {
/*	vendas=init_ve(&vendas);

	if (vendas.vendas) {
	facturacao=init_fact(&facturacao);

	if (facturacao.compras_pr) {*/
	fd=fopen(argv[1],"r");	
	
	if (fd) {
	before=clock();
	clientes=readclients(clientes,fd);
	after=clock();
	printf("Read C:%.2Lf\n",(long double)(after-before));
	fclose(fd);

	if (clientes) {
	fd=fopen(argv[2],"r");
	
	if (fd) {
	before=clock();
	produtos=readproducts(produtos,fd);
	after=clock();
	printf("Read P:%.2Lf\n",(long double)(after-before));
	fclose(fd);

	if (produtos) {
	fd=fopen("./Clientes_válidos.txt","w");

	if (fd) {
	before=clock();
	printClientesToF(clientes,fd);
	after=clock();
	printf("Print C:%.2Lf\n",(long double)(after-before));
	fclose(fd);
	fd=fopen("./Produtos_válidos.txt","w");

	if (fd) {
	before=clock();
	printProdutosToF(produtos,fd);
	after=clock();
	printf("Print P:%.2Lf\n",(long double)(after-before));
	fclose(fd);
	printf("\nClientes has %d elements\nProdutos has %d elements\n",elementosClientes(clientes),elementosProdutos(produtos));

	exitst=0;
/*	if (!exitst) {
	exitst=write_factura(&facturacao,&clientes,&produtos,&vendas);
	print_z5000(&vendas,"Z5000");
	print_af1184(&vendas,"AF1184");	
	destroy_facturacao(&facturacao);	
	}
	free(vendas.vendas);
*/
	}}
	destroiProdutos(produtos);
	}
	destroiClientes(clientes);
	}}}}}}
	else
		printf("Usage: (Clientes-path) (Produtos-path) (Vendas-path)\n");
	return exitst;
}


/*Secção 1: Inits*/



/**inicializa o array de estruturas Venda**/
/*Vendas init_ve(Vendas *vendas) {
	vendas->fill=0;
	vendas->vendas=(Venda *)malloc(sizeof(Venda)*SIZE_VENDAS);
	if (!vendas->vendas) {
		perror("Failed starting vending structure");
	}
	return (*vendas);
}
*/
/**inicializa a estrutura facturas**/
/*Facturacao init_fact(Facturacao *facturacao){
	facturacao->compras_cl=(int *)malloc(sizeof(*(facturacao->compras_cl))*SIZE_CLIENTES);
	if (facturacao->compras_cl) {
		facturacao->compras_pr=(int *)malloc(sizeof(*(facturacao->compras_pr))*SIZE_PRODUTOS);
		if (facturacao->compras_pr) {
			zeroarrayi(facturacao->compras_cl,SIZE_CLIENTES);
			zeroarrayi(facturacao->compras_pr,SIZE_PRODUTOS);
		}
		else {
			perror("Memória insuficiente para registar compras por produto");
			free(facturacao->compras_cl);
		}
	} else {
		perror("Memória insuficiente para registar compras por cliente");
	}
	if (facturacao->compras_pr) {
		facturacao->quant=0;
		facturacao->facturacao=0;
		zeroarrayi(facturacao->codigos_cl,26);
		zeroarrayi(facturacao->codigos_pr,26);
		zeroarrayd(facturacao->quant_mes,12);
		zeroarrayd(facturacao->fact_mes,12);
		zeroarrayd(facturacao->quant_filial,NUM_FILIAL);
		zeroarrayd(facturacao->fact_filial,NUM_FILIAL);
	}
	return (*facturacao);
}

void destroy_facturacao(Facturacao *facturacao) {
	free(facturacao->compras_pr);
	free(facturacao->compras_cl);
}

*/

/*Secção 2: prints*/



/**imprime todas as vendas segundo o formato que recebeu**/
/*int printve(Vendas *vendas) {
	int i=0,error=0,n=0;
	FILE *fd=fopen("./Vendas_validas.txt","w");
	if (fd) {
		for (;i<vendas->fill && !error;i++) {
			fprintf(fd,"%s ",vendas->vendas[i].produto);
			fprintf(fd,"%.2f ",vendas->vendas[i].preco);
			fprintf(fd,"%d ",vendas->vendas[i].quant);
			n=vendas->vendas[i].promo;		
			if (n)
				fprintf(fd,"P ");
			else
				fprintf(fd,"N ");
			fprintf(fd,"%s ",vendas->vendas[i].cliente);
			fprintf(fd,"%d ",vendas->vendas[i].mes);
			fprintf(fd,"%d \n",vendas->vendas[i].filial);
		}
	}
	else {
		error--;
		perror("Falha a criar ficheiro de texto de vendas válidos");
	}
	return error;
}
*/
/*
 *Imprime para ficheiro Facturacao na diretoria local informacoes de faturacao
*/
/*int print_facturacao(Facturacao *facturacao,Clientes *clientes,Produtos *produtos) {
	int error=0;	
	FILE *fd;
	fd=fopen("./Facturacao.txt","w");
	if (!fd) {
		error--;
		perror("Falha a criar ficheiro de texto de facturacao global");
	}
	else {
		fprintf(fd,"Quantidade Vendida Total: %G	Total Facturado: %G\n\n",facturacao->quant,facturacao->facturacao);
		print_fact_mes(fd,facturacao);
		print_fact_filial(fd,facturacao);
		print_fact_codigos(fd,facturacao);
		print_fact_compras_cl(fd,facturacao,clientes);
		print_fact_compras_pr(fd,facturacao,produtos);	
		fclose(fd);	
	}

	return error;
}



void print_fact_mes(FILE *fd,Facturacao *facturacao) {
	int i;	
	for(i=0;i<12;i++) {
		fprintf(fd,"Facturação em ");	
		escreve_mes(fd,facturacao,i);
		fprintf(fd,"%G		",facturacao->fact_mes[i]);
		fprintf(fd,"Quantidade vendidade em ");
		escreve_mes(fd,facturacao,i);
		fprintf(fd,"%G\n",facturacao->quant_mes[i]);
	}
	fprintf(fd,"\n\n");
}

void escreve_mes(FILE *fd,Facturacao *facturacao,int i) {
	switch (i) {
		case 0:fprintf(fd,"Janeiro: ");
		       break;		
		case 1:fprintf(fd,"Fevereiro: ");
		       break;
		case 2:fprintf(fd,"Março: ");
		       break;
		case 3:fprintf(fd,"Abril: ");
		       break;
		case 4:fprintf(fd,"Maio: ");
		       break;
		case 5:fprintf(fd,"Junho: ");
		       break;
		case 6:fprintf(fd,"Julho: ");
		       break;
		case 7:fprintf(fd,"Agosto: ");
		       break;
		case 8:fprintf(fd,"Setembro: ");
		       break;
		case 9:fprintf(fd,"Outubro: ");
		       break;
		case 10:fprintf(fd,"Novembro: ");
		       break;
		case 11:fprintf(fd,"Dezembro: ");
		       break;
		default:break;
	}
}

void print_fact_filial(FILE *fd,Facturacao *facturacao) {
	int i;	
	for (i=0;i<NUM_FILIAL;i++){
		fprintf(fd,"Facturacao na Filial %d: %G		",i+1,facturacao->fact_filial[i]);
		fprintf(fd,"Quantidade vendida na filial %d: %G\n",i+1,facturacao->quant_filial[i]);
	}
	fprintf(fd,"\n\n");
}

void print_fact_codigos(FILE *fd,Facturacao *facturacao) {
	int i;	
	for(i=0;i<26;i++)
		fprintf(fd,"Clientes começados por %c: %d\n",(char)(i+65),facturacao->codigos_cl[i]);
	fprintf(fd,"\n\n");	
	for(i=0;i<26;i++)
		fprintf(fd,"Produtos começados por %c: %d\n",(char)(i+65),facturacao->codigos_pr[i]);
	fprintf(fd,"\n\n");
}

void print_fact_compras_pr(FILE *fd,Facturacao *facturacao,Produtos *produtos) {
	int i;	
	for (i=0;i<produtos->fill;i++) {
		fprintf(fd,"Compras do produto %s: %d\n",produtos->produtos+i*SIZE_PRODUTO,facturacao->compras_pr[i]);
	}
	fprintf(fd,"\n\n");
}

void print_fact_compras_cl(FILE *fd,Facturacao *facturacao,Clientes *clientes) {
	int i;	
	for (i=0;i<clientes->fill;i++) {
		fprintf(fd,"Compras efetuadas pelo cliente %s: %d\n",clientes->clientes+i*SIZE_CLIENTE,facturacao->compras_cl[i]);
	}
	fprintf(fd,"\n\n");
}


*/

/*Secção 3: Leitura de ficheiros/tokens*/



/**le os clientes a partir do ficheiro em path para o array de strings de clientes**/
Clientes readclients(Clientes clientes,FILE *fd) {	
	int i=0;
	char buffer[SIZE_BUFFER_CL],*token=NULL;
	/*Para os clientes e os produtos leio tudo o que estiver separado por whitespace*/
	/*Dai os dois fors seguidos, assumo que pode acontecer e ser válido um input*/
	/*A5667 B5687 C1234 D7689*/
	for(;i<SIZE_CLIENTES && fgets(buffer,SIZE_BUFFER_CL,fd);) {			
		/*strtok atualiza o token para o local do token a ler*/
		/*se for o ultimo token atualiza para NULL*/				
		token=strtok(buffer," \r\n");
		/*depois de ler o token verificar se o token é valido*/
		/*se nao for le-se a proxima linha sem atualizar nada na estrutura*/
		if (check_cliente(token)){		
			clientes=insereCliente(clientes,token);	
		}
		for(;i<SIZE_CLIENTES && (token=strtok(NULL," \n\r"));) {
			if (check_cliente(token)){				
				clientes=insereCliente(clientes,token);
			}		
		}
	}
	return clientes;
}

/**Praticamente igual a readclients**/
Produtos readproducts(Produtos produtos,FILE *fd) {
	int i=0;
	char buffer[SIZE_BUFFER_PR],*token=NULL;
	for(;i<SIZE_PRODUTOS && fgets(buffer,SIZE_BUFFER_PR,fd);) {				
		token=strtok(buffer," \r\n");
		if (check_produto(token)) {
			produtos=insereProduto(produtos,token);			
		}
		for(;i<SIZE_PRODUTOS && (token=strtok(NULL," \r\n"));) {
			if (check_produto(token)) {
				produtos=insereProduto(produtos,token);
			}
		}
	}
	return produtos;
}

/**readsales trata so de abrir o ficheiro**/
/*int readsales(Vendas *vendas,char const *path,Clientes *clientes,Produtos *produtos) {
	int error=0,i=0;
	FILE *fd;	
	fd=fopen(path,"r");
	if (fd) {
		i=read_line_vendas(vendas,fd,clientes,produtos);	
		if (!i) {
			perror("0 Vendas válidas\n");		
			error--;
		}
		fclose(fd);
	}
	else {
		perror(path);
		error--;
	}
	return error;
}

*/
/** funcao que le as vendas, assume que cada linha lida tem de estar direita senao passa a proxima
  * para cada token converte para o formato e verifica a partir de cada funcao individual de ler tokens
  * se algum token for invalido passa à proxima linha 
**/
/*int read_line_vendas(Vendas *vendas,FILE *fd,Clientes *clientes,Produtos *produtos) {
	int i,error;	
	char buffer[SIZE_BUFFER_VE],*token=NULL;
	for(i=error=0;i<SIZE_VENDAS && fgets(buffer,SIZE_BUFFER_VE,fd);) {					
			token=strtok(buffer," \r\n");
			error=read_token_pr(token,vendas,i,produtos);
			if (!error){
			token=strtok(NULL," \r\n");
			error=read_token_preco(token,vendas,i);			
			if (!error){
			token=strtok(NULL," \r\n");
			error=read_token_quant(token,vendas,i);
			if (!error){
			token=strtok(NULL," \r\n");
			error=read_token_promo(token,vendas,i);			
			if (!error){
			token=strtok(NULL," \r\n");
			error=read_token_cl(token,vendas,i,clientes);
			if (!error){
			token=strtok(NULL," \r\n");
			error=read_token_mes(token,vendas,i);
			if (!error){
			token=strtok(NULL," \r\n");
			error=read_token_filial(token,vendas,i);
			if (!error) {			
				i++;
				vendas->fill++;
			}}}}}}}	
	}
	return i;
}
*/
/** função que verifica se o produto lido consta da lista de produtos atraves de um binary search no
 *  array previamente ordenado de produtos ordenado pelo qsort na main
**/
/*char read_token_pr(char *token,Vendas *vendas,int i,Produtos *produtos) {
	char error=-1;
	char *produto=bsearch_pr(token,produtos,0,produtos->fill-1);
	if (produto) {
		vendas->vendas[i].produto=produto;						
		error=0;	
	}
	return error;
}
*/
/** funcao que verifica se o preço é positivo atraves da conversao para float do strtof()
**/
/*char read_token_preco(char *token,Vendas *vendas,int i) {
	float preco=0;char error=-1;	
	if ((preco=(float)strtod(token,NULL))>=0.0) {
		vendas->vendas[i].preco=preco;					
		error=0;	
	}
	return error;
}
*/

/** função que verifica se a quantidade é positiva atraves da conversão para (long int) do strtol()
**/
/*char read_token_quant(char *token,Vendas *vendas,int i) {
	int error=(int)strtol(token,NULL,10);
	if (error<0)
		error--;
	else {
		vendas->vendas[i].quant=error;		
		error=0;
	}return (char)error;
}
*/

/** função que verifica se o carater corresponde a venda normal ou promoção e  guarda como booleano
  * nas vendas
**/
/*char read_token_promo(char *token,Vendas *vendas,int i) {	
	char error=1;
	if (token && token[0]!='\0' && token[1]=='\0') {
		switch (token[0]) {
			case 'P':error=0;
				 vendas->vendas[i].promo=1;
				 break;
			case 'N':error=0;
				 vendas->vendas[i].promo=0;
				 break;
			default:break;
		}
	}	
	return error;
}
*/

/** função praticamente igual a read_token_pr()
**/
/*char read_token_cl(char *token,Vendas *vendas,int i,Clientes *clientes) {
	char error=1;
	char *cliente=bsearch_cl(token,clientes,0,clientes->fill-1);
	if (cliente) {
		vendas->vendas[i].cliente=cliente;				
		error=0;
	}
	return error;
}
*/

/** função que lê o mes e verifica se é válido, converte a string para (long int) pelo strtol()
**/
/*char read_token_mes(char *token,Vendas *vendas,int i) {
	int error=(int)strtol(token,NULL,10);
	if (error<13 && error>0) {
		vendas->vendas[i].mes=error;
		error=0;
	} 
	return (char)error;
}
*/

/** função que lê a filial e verifica se é valida, converte a string para (long int) pelo strtol()
**/
/*char read_token_filial(char *token,Vendas *vendas,int i) {
	int error=(int)strtol(token,NULL,10);
	if (error<=NUM_FILIAL && error>=0) {
		vendas->vendas[i].filial=error;				
		error=0;
	}
	return (char)error;
}
*/

/**função que trata de preencher a estrutura de facturação, a partir das outras**/
/*int write_factura(Facturacao *facturacao,Clientes *clientes,Produtos *produtos,Vendas *vendas) {
*/	/*Código de 'A' é 65 em ASCII, esta funcao vai buscar frequentemente o primeiro carater de um cliente ou produto e transforma-o num indice, para contar quantidades de produtos começados por dada letra*/	
/*	int error=0,i,quanti=0;
	double facti=0;
	for(i=0;i<vendas->fill;i++) {
		facti=(vendas->vendas[i].preco*vendas->vendas[i].quant);
		facturacao->facturacao+=facti;
		facturacao->fact_mes[vendas->vendas[i].mes-1]+=facti;
		facturacao->fact_filial[vendas->vendas[i].filial-1]+=facti;		
		quanti=vendas->vendas[i].quant;
		facturacao->quant+=quanti;
		facturacao->quant_mes[vendas->vendas[i].mes-1]+=quanti;
		facturacao->quant_filial[vendas->vendas[i].filial-1]+=quanti;		
		facturacao->compras_cl[(vendas->vendas[i].cliente-clientes->clientes)/SIZE_CLIENTE]++;
		facturacao->compras_pr[(vendas->vendas[i].produto-produtos->produtos)/SIZE_PRODUTO]++;
	}
	for(i=0;i<clientes->fill;i++)
		facturacao->codigos_cl[clientes->clientes[i*SIZE_CLIENTE]-65]++;
	for(i=0;i<produtos->fill;i++)
		facturacao->codigos_pr[produtos->produtos[i*SIZE_PRODUTO]-65]++;
	error=print_facturacao(facturacao,clientes,produtos);	
	return error;
}
*/



/*Secção 5: Utilidades*/


/**zerar um array de n inteiros**/
void zeroarrayi(int *arr,int n) {
	for(n--;n>=0;n--)
		arr[n]=0;
}


/**zerar um array de n doubles**/
void zeroarrayd(double *arr,int n) {
	for(n--;n>=0;n--)
		arr[n]=0;
}


