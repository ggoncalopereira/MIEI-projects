#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
typedef struct Dados{
	char acao[8];
	char path[1024];
	int pidC;
}*dados;


char fn[512];

void s_handlerB(int sinal){
	switch(sinal){
		case SIGUSR1: printf("%s, foi copiado.\n",fn);
					  break;
		case SIGUSR2: printf("O ficheiro: %s, não existe, ou não foi possivel criar o ficheiro de backup.\n",fn);
					  _exit(1);
		default: printf("Erro desconhecido.\n");
				 _exit(1);		  			  
	}
}

void s_handlerR(int sinal){
	switch(sinal){
		case SIGUSR1: printf("%s, foi restaurado.\n",fn);
					  break;
		case SIGUSR2: printf("O ficheiro: %s, não existe em backup, ou não foi possivel restaurá-lo.\n",fn);
					  _exit(1);
		default: printf("Erro desconhecido.\n");
				 _exit(1);		  			  
	}
}

int main (int argc, char*argv[]){
	dados dadosCliente;
	int fd,f;
	char *ap;
	if((strcmp(argv[1],"backup"))==0){
		dadosCliente= (dados) malloc (sizeof(struct Dados));
		strcpy(dadosCliente->acao,"backup");
		fd=open("/home/goncalopereira96/backup/fifo",O_WRONLY);
		for(int i=2; i<argc; i++){
			signal(SIGUSR1,s_handlerB);
			signal(SIGUSR2,s_handlerB);
			getcwd(dadosCliente->path,sizeof(dadosCliente->path));
			strcat(dadosCliente->path,"/");
			strcpy(fn,argv[i]);
			strcat(dadosCliente->path,argv[i]);
			dadosCliente->pidC=getpid();
			write(fd,dadosCliente,sizeof(struct Dados));
			pause();
		}
		free(dadosCliente);
    }
    else{
    	if((strcmp(argv[1],"restore"))==0){
    	dadosCliente= (dados) malloc (sizeof(struct Dados));
		strcpy(dadosCliente->acao,"restore");
    	fd=open("/home/goncalopereira96/backup/fifo",O_WRONLY);
		for(int i=2; i<argc; i++){
			signal(SIGUSR1,s_handlerR);
			signal(SIGUSR2,s_handlerR);
			getcwd(dadosCliente->path,sizeof(dadosCliente->path));
			strcat(dadosCliente->path,"/");
			strcpy(fn,argv[i]);
			strcat(dadosCliente->path,argv[i]);
			dadosCliente->pidC=getpid();
			write(fd,dadosCliente,sizeof(struct Dados));
			pause();
   	 	}
   	 	}else printf("Argumento inválido");
    }
	return 0;
}