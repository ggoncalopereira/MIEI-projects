#include "leitura.h"


/** main cria as estruturas, chama inicializaçoes, le dos ficheiros que lhe sao dados (Clientes seguido do ficheiro de Produtos seguido do ficheiro de Vendas) sorteia os clientes e os produtos, e depois imprime tudo**/

int main(int argc, char *argv[]) {
	clock_t before,after;
	int exitst=0;
	Clientes clientes;
	Produtos produtos;
	Vendas vendas;
	Facturacao facturacao;
	FILE *fd;
	if(argc==4){
	clientes=init_cl(&clientes);

	if (clientes.clientes) {
	produtos=init_pr(&produtos);

	if (produtos.produtos) {
	vendas=init_ve(&vendas);

	if (vendas.vendas) {
	facturacao=init_fact(&facturacao);

	if (facturacao.compras_pr) {
	fd=fopen(argv[1],"r");	
	
	if (fd) {
	clientes=readclients(&clientes,fd);
	fclose(fd);

	if (!exitst) {
	exitst=readproducts(&produtos,argv[2]);

	if (!exitst) {
	before=clock();
	qsort(clientes.clientes,clientes.fill,sizeof(char)*SIZE_CLIENTE,cmpr_strcmp);
	qsort(produtos.produtos,produtos.fill,sizeof(char)*SIZE_PRODUTO,cmpr_strcmp);
	after=clock();
	printf("Sorts: %.2Lf\n",(long double)(after-before));
	readsales(&vendas,argv[3],&clientes,&produtos);

	if (!exitst) {		

	before=clock();
	exitst=printcl(&clientes);
	after=clock();
	printf("Print C:%.2Lf",(long double)(after-before));
	
	if (!exitst) {
	before=clock();
	exitst=printpr(&produtos);
	after=clock();
	printf("Print P:%.2Lf",(long double)(after-before));

	if (!exitst) {
	exitst=printve(&vendas);
	printf("\nClientes has %d elements\nProdutos has %d elements\nVendas has %d elements\n",clientes.fill,produtos.fill,vendas.fill);
	
	if (!exitst) {
	exitst=write_factura(&facturacao,&clientes,&produtos,&vendas);
	print_z5000(&vendas,"Z5000");
	print_af1184(&vendas,"AF1184");	
	destroy_facturacao(&facturacao);	
	}
	free(vendas.vendas);
	}
	free(produtos.produtos);
	}
	free(clientes.clientes);
	}}}}}}}}}
	else
		printf("Usage: (Clientes-path) (Produtos-path) (Vendas-path)\n");
	return exitst;
}


/*Secção 1: Inits*/


/**inicializa o array de strings de clientes**/
Clientes init_cl(Clientes *clientes) {
	clientes->fill=0;	
	clientes->clientes=(char *)malloc(sizeof(char)*SIZE_CLIENTES*SIZE_CLIENTE);
	if (!clientes->clientes) {
		perror(clientes->clientes);		
	}	
	return (*clientes);
}

/**inicializa o array de strings de produtos**/
Produtos init_pr(Produtos *produtos) {
	produtos->fill=0;	
	produtos->produtos=(char *)malloc(sizeof(char)*SIZE_PRODUTOS*SIZE_PRODUTO);
	if (!produtos->produtos) {
		perror(produtos->produtos);
	}	
	return (*produtos);
}

/**inicializa o array de estruturas Venda**/
Vendas init_ve(Vendas *vendas) {
	vendas->fill=0;
	vendas->vendas=(Venda *)malloc(sizeof(Venda)*SIZE_VENDAS);
	if (!vendas->vendas) {
		perror("Failed starting vending structure");
	}
	return (*vendas);
}

/**inicializa a estrutura facturas**/
Facturacao init_fact(Facturacao *facturacao){
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



/*Secção 2: prints*/



/**imprime para o stdout informacoes relativas ao cliente recebido em cl**/
void print_z5000(Vendas *vendas,const char *cl) {
	double fact_mes_P[12],fact_mes_N[12],fact_cli=0;
	int quant_mes[12],vendas_mes[12],quant_cli=0,venda_cli=0,i;
	zeroarrayi(quant_mes,12);
	zeroarrayi(vendas_mes,12);
	zeroarrayd(fact_mes_P,12);
	zeroarrayd(fact_mes_N,12);
	for (i=0;i<vendas->fill;i++)
		if (!strcmp(cl,vendas->vendas[i].cliente)) {
			quant_cli+=vendas->vendas[i].quant;
			venda_cli++;
			fact_cli+=vendas->vendas[i].preco*vendas->vendas[i].quant;
			if (vendas->vendas[i].promo)		
				fact_mes_P[vendas->vendas[i].mes-1]+=vendas->vendas[i].preco*vendas->vendas[i].quant;
			else
				fact_mes_N[vendas->vendas[i].mes-1]+=vendas->vendas[i].preco*vendas->vendas[i].quant;
			quant_mes[vendas->vendas[i].mes-1]+=vendas->vendas[i].quant;
			vendas_mes[vendas->vendas[i].mes-1]++;
		}
	printf("Cliente %s vendas totais %d, facturacao total %G, quantidade total %d\n",cl,venda_cli,fact_cli,quant_cli);
	for(i=0;i<12;i++){
		printf("Vendas mes %d: facturacao P %G, facturacao N %G quantidade %d, vendas %d\n",i+1,fact_mes_P[i],fact_mes_N[i],quant_mes[i],vendas_mes[i]);
	}
}

/**imprime para o stdout informacoes relativas ao cliente recebido em cl**/
void print_af1184(Vendas *vendas,const char *pr) {
	double fact_mes_P[12],fact_mes_N[12],fact_cli=0;
	int quant_mes[12],vendas_mes[12],quant_cli=0,venda_cli=0,i;
	zeroarrayi(quant_mes,12);
	zeroarrayi(vendas_mes,12);
	zeroarrayd(fact_mes_N,12);
	zeroarrayd(fact_mes_P,12);
	for (i=0;i<vendas->fill;i++)
		if (!strcmp(pr,vendas->vendas[i].produto)) {
			quant_cli+=vendas->vendas[i].quant;
			venda_cli++;
			fact_cli+=vendas->vendas[i].preco*vendas->vendas[i].quant;
			if (vendas->vendas[i].promo)		
				fact_mes_P[vendas->vendas[i].mes-1]+=vendas->vendas[i].preco*vendas->vendas[i].quant;
			else
				fact_mes_N[vendas->vendas[i].mes-1]+=vendas->vendas[i].preco*vendas->vendas[i].quant;
			quant_mes[vendas->vendas[i].mes-1]+=vendas->vendas[i].quant;
			vendas_mes[vendas->vendas[i].mes-1]++;
		}
	printf("Produto %s vendas totais %d, facturacao total %G, quantidade total %d\n",pr,venda_cli,fact_cli,quant_cli);
	for(i=0;i<12;i++){
		printf("Vendas mes %d: facturacao P %G, facturacao N %G quantidade %d, vendas %d\n",i+1,fact_mes_P[i],fact_mes_N[i],quant_mes[i],vendas_mes[i]);
	}
}



/**imprime todos os clientes**/
int printcl(Clientes *clientes) {
	int error=0,i=0,n=0;
	FILE *fd=fopen("./Clientes_validos.txt","w");
	if (fd) {
		for (;i<clientes->fill && !error;i++) {	
			n=fprintf(fd,"%s\n",clientes->clientes+(i*SIZE_CLIENTE));
			if (!n) {
				error--;
				perror(clientes->clientes+(i*SIZE_CLIENTE));
			}
		}
	}
	else {
		error--;
		perror("Falha a criar ficheiro de texto de produtos válidos");
	}
	fprintf(fd,"\n");
	return error;
}

/**imprime todos os produtos**/
int printpr(Produtos *produtos) {
	int error=0,i=0,n=0;
	FILE *fd=fopen("./Produtos_validos.txt","w");
	if (fd) {
		for (;i<produtos->fill && !error;i++) {	
			n=fprintf(fd,"%s\n",produtos->produtos+(i*SIZE_PRODUTO));
			if (!n) {
				error--;
				perror(produtos->produtos+(i*SIZE_PRODUTO));
			}
		}
		fprintf(fd,"\n");
	}
	else {
		error--;
		perror("Falha a criar ficheiro de texto de produtos válidos");
	}
	return error;
}

/**imprime todas as vendas segundo o formato que recebeu**/
int printve(Vendas *vendas) {
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

/*
 *Imprime para ficheiro Facturacao na diretoria local informacoes de faturacao
*/
int print_facturacao(Facturacao *facturacao,Clientes *clientes,Produtos *produtos) {
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




/*Secção 3: Leitura de ficheiros/tokens*/



/**le os clientes a partir do ficheiro em path para o array de strings de clientes**/
Clientes readclients(Clientes *clientes,FILE *fd) {
	clock_t after,before=clock();
	int i=0;
	char buffer[SIZE_BUFFER_CL],*token=NULL,*sitio=NULL;
	/*Para os clientes e os produtos leio tudo o que estiver separado por whitespace*/
	/*Dai os dois fors seguidos, assumo que pode acontecer e ser válido um input*/
	/*A5667 B5687 C1234 D7689*/
	for(;i<SIZE_CLIENTES && fgets(buffer,SIZE_BUFFER_CL,fd);) {			
		/*strtok atualiza o token para o local do token a ler*/
		/*se for o ultimo token atualiza para NULL*/				
		token=strtok(buffer," \r\n");
		/*depois de ler o token verificar se o token é valido*/
		/*se nao for le-se a proxima linha sem atualizar nada na estrutura*/
		if (check_cliente(token)) {
			sitio=clientes->clientes+(i*SIZE_CLIENTE);
			strcpy(sitio,token);
			i++;
			clientes->fill++;			
		}
		for(;i<SIZE_CLIENTES && (token=strtok(NULL," \n\r"));) {
			if (check_cliente(token)){
				sitio=clientes->clientes+(i*SIZE_CLIENTE);
				strcpy(sitio,token);
				i++;
				clientes->fill++;
			}			
		}
	}	
	after=clock();
	printf("Read C:%.12Lf\n",(long double)(after-before));
	return (*clientes);
}

/**Praticamente igual a readclients**/
int readproducts(Produtos *produtos,char const *path) {
	clock_t after,before=clock();
	int error=0,i=0;
	char buffer[SIZE_BUFFER_PR],*token=NULL,*sitio=NULL;
	FILE *fd;
	fd=fopen(path,"r");
	if (fd) {
		for(;i<SIZE_PRODUTOS && fgets(buffer,SIZE_BUFFER_PR,fd);) {				
			token=strtok(buffer," \r\n");
			if (check_produto(token)) {			
				sitio=produtos->produtos+(i*SIZE_PRODUTO);
				strcpy(sitio,token);
				i++;
				produtos->fill++;
			}
			for(;i<SIZE_PRODUTOS && (token=strtok(NULL," \r\n"));) {
				if (check_produto(token)){
					sitio=produtos->produtos+(i*SIZE_PRODUTO);
					strcpy(sitio,token);
					i++;
					produtos->fill++;
				}			
			}
		}	
		fclose(fd);
	}
	else {
		perror(path);
		error--;
	}
	after=clock();
	printf("Read P:%.12Lf\n",(long double)(after-before));
	return error;
}

/**readsales trata so de abrir o ficheiro**/
int readsales(Vendas *vendas,char const *path,Clientes *clientes,Produtos *produtos) {
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


/** funcao que le as vendas, assume que cada linha lida tem de estar direita senao passa a proxima
  * para cada token converte para o formato e verifica a partir de cada funcao individual de ler tokens
  * se algum token for invalido passa à proxima linha 
**/
int read_line_vendas(Vendas *vendas,FILE *fd,Clientes *clientes,Produtos *produtos) {
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

/** função que verifica se o produto lido consta da lista de produtos atraves de um binary search no
 *  array previamente ordenado de produtos ordenado pelo qsort na main
**/
char read_token_pr(char *token,Vendas *vendas,int i,Produtos *produtos) {
	char error=-1;
	char *produto=bsearch_pr(token,produtos,0,produtos->fill-1);
	if (produto) {
		vendas->vendas[i].produto=produto;						
		error=0;	
	}
	return error;
}

/** funcao que verifica se o preço é positivo atraves da conversao para float do strtof()
**/
char read_token_preco(char *token,Vendas *vendas,int i) {
	float preco=0;char error=-1;	
	if ((preco=(float)strtod(token,NULL))>=0.0) {
		vendas->vendas[i].preco=preco;					
		error=0;	
	}
	return error;
}


/** função que verifica se a quantidade é positiva atraves da conversão para (long int) do strtol()
**/
char read_token_quant(char *token,Vendas *vendas,int i) {
	int error=(int)strtol(token,NULL,10);
	if (error<0)
		error--;
	else {
		vendas->vendas[i].quant=error;		
		error=0;
	}return (char)error;
}


/** função que verifica se o carater corresponde a venda normal ou promoção e  guarda como booleano
  * nas vendas
**/
char read_token_promo(char *token,Vendas *vendas,int i) {	
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


/** função praticamente igual a read_token_pr()
**/
char read_token_cl(char *token,Vendas *vendas,int i,Clientes *clientes) {
	char error=1;
	char *cliente=bsearch_cl(token,clientes,0,clientes->fill-1);
	if (cliente) {
		vendas->vendas[i].cliente=cliente;				
		error=0;
	}
	return error;
}


/** função que lê o mes e verifica se é válido, converte a string para (long int) pelo strtol()
**/
char read_token_mes(char *token,Vendas *vendas,int i) {
	int error=(int)strtol(token,NULL,10);
	if (error<13 && error>0) {
		vendas->vendas[i].mes=error;
		error=0;
	} 
	return (char)error;
}


/** função que lê a filial e verifica se é valida, converte a string para (long int) pelo strtol()
**/
char read_token_filial(char *token,Vendas *vendas,int i) {
	int error=(int)strtol(token,NULL,10);
	if (error<=NUM_FILIAL && error>=0) {
		vendas->vendas[i].filial=error;				
		error=0;
	}
	return (char)error;
}


/**função que trata de preencher a estrutura de facturação, a partir das outras**/
int write_factura(Facturacao *facturacao,Clientes *clientes,Produtos *produtos,Vendas *vendas) {
	/*Código de 'A' é 65 em ASCII, esta funcao vai buscar frequentemente o primeiro carater de um cliente ou produto e transforma-o num indice, para contar quantidades de produtos começados por dada letra*/	
	int error=0,i,quanti=0;
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



/*Secção 4: Checks*/



/** função que assegura que o token tem o formato "AXXXX", em que A é uma maiúscula e XXXX um número
  * entre 0000 e 9999
**/
char check_cliente(char *cliente) {
	char iscliente=1;
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


/** função que assegura que o token tem o formato "AAXXXX", em que A são maiúsculas e XXXX um número
  * entre 0000 e 9999
**/
char check_produto(char *produto) {
	char isproduto=1;
	int i;
	if (produto) {
		for(i=0;i<2 && produto[i]!='\0';i++)
			if (!isupper(produto[i]))
				isproduto=0;
		for(;i<SIZE_PRODUTO-1 && produto[i]!='\0';i++)
			if (!isdigit(produto[i]))
				isproduto=0;
		if (i<SIZE_PRODUTO-1)
			isproduto=0;
	}
	else 
		isproduto=0;	
	return isproduto;
}


/** binary search no array de codigos de clientes
**/
char *bsearch_cl(char *token,Clientes *clientes,int min,int max) {
	char *sitio=NULL;int mid;
	mid=(min+max)/2;
	if (mid>=min && mid<=max) {
		sitio=clientes->clientes+mid*SIZE_CLIENTE;
		if(strcmp(token,sitio)<0)
			sitio=bsearch_cl(token,clientes,min,mid-1);
		else if (strcmp(token,sitio)>0)
			sitio=bsearch_cl(token,clientes,mid+1,max);
	}
	return sitio;
}

/** binary search no array de codigos de produtos 
**/
char *bsearch_pr(char *token,Produtos *produtos,int min,int max) {
	char *sitio=NULL;int mid;
	mid=(min+max)/2;
	if (mid>=min && mid<=max) {
		sitio=produtos->produtos+mid*SIZE_PRODUTO;
		if(strcmp(token,sitio)<0)
			sitio=bsearch_pr(token,produtos,min,mid-1);
		else if (strcmp(token,sitio)>0)
			sitio=bsearch_pr(token,produtos,mid+1,max);	
	}
	return sitio;
}

/** pesquisa linear no array de codigos de clientes
  * muito pesada, inútil
**/
char *search_cl(char *token,Clientes *clientes) {
	char *sitio;
	int i,found;	
	for(i=found=0;i<clientes->fill && !found;i++) {
		sitio=clientes->clientes+i*SIZE_CLIENTE;
		if (!strcmp(sitio,token))
			found++;
	}
	if (found)
		return sitio;
	else
		return NULL;
}

/** pesquisa linear no array de codigos de produtos
  * muito pesada, inútil
**/
char *search_pr(char *token,Produtos *produtos) {
	char *sitio;
	int i,found;	
	for(i=found=0;i<produtos->fill && !found;i++) {
		sitio=produtos->produtos+i*SIZE_PRODUTO;	
		if (!strcmp(sitio,token))
			found++;
	}
	if (found)
		return sitio;
	else
		return NULL;
}


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


/** função que concretiza o strcmp para funcionar com a chamada nao-especifica de qsort()
  * qsort() especifica um parametro (int *)função(const void *,const void*) de comparação
  * esta função concretiza os voids para funcionar com o strcmp
**/
int cmpr_strcmp(const void *a, const void *b) { 
    const char *ia = (const char *)a;
    const char *ib = (const char *)b;
    return strcmp(ia, ib);
} 
