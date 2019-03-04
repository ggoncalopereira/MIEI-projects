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

int main(int argc, char*argv[]){
	dados dadosCliente;
	int i,n,fd,fSha1sum,pidCliente;
	char *ap,*antap,linha[1024],linha2[1024],pathSha1sum[1024];
	dadosCliente=(dados) malloc(sizeof (struct Dados));
	i=mkdir("/home/goncalopereira96/backup/",0777);if(i==-1)perror("mkdir");
	i=mkdir("/home/goncalopereira96/backup/data",0777);if(i==-1)perror("mkdir");
	i=mkdir("/home/goncalopereira96/backup/metadata",0777);if(i==-1)perror("mkdir");
	i=mkfifo("/home/goncalopereira96/backup/fifo",0666);
	if(i==-1) perror("mkfifo");
	fd = open("/home/goncalopereira96/backup/fifo",O_RDONLY);
	while((n=read(fd,dadosCliente,sizeof(struct Dados)))>0){
    if((strcmp((dadosCliente->acao),"backup"))==0){
			pidCliente=dadosCliente->pidC;
			strcpy(linha,dadosCliente->path);
			ap=strtok(linha,"/");
			while(ap!=NULL){
				antap=ap;
				ap=strtok(NULL,"/");
			}
			strcpy(linha,"/home/goncalopereira96/backup/data/");
			strcat(linha,antap);
			if(fork()==0){
				execlp("cp","cp",dadosCliente->path,linha,NULL);
				perror("cp");
				kill(pidCliente,SIGUSR2);
				free(dadosCliente);
				return 1;
			}
			wait(NULL);
			if(fork()==0){
				char sha1[512];
				strcpy(sha1,"/home/goncalopereira96/backup/data/");
				strcat(sha1,antap);
				strcat(sha1,".sha1");
				execlp("sha1sum","sha1sum",linha,">",sha1,NULL);
				perror("sha1sum");
				return 1;
			}
			wait(NULL);
			strcpy(pathSha1sum,"/home/goncalopereira96/backup/data/");
			strcat(pathSha1sum,antap);
			strcat(pathSha1sum,".sha1");
			fSha1sum = open(pathSha1sum,O_RDONLY);
			read(fSha1sum,linha2,40);
			/*if(fork()==0){
				execlp("rm","rm",pathSha1sum,NULL);
				perror("rm");
				return 1;
			}
			wait(NULL);*/
			if(fork()==0){
				execlp("gzip","gzip",linha,NULL);
				perror("gzip");
				return 1;
			}
			wait(NULL);
			if(fork()==0){
				execlp("mv","mv",linha,linha2,NULL);
				perror("mv");
				return 1;
			}
			wait(NULL);
			kill(pidCliente,SIGUSR1);
    }else{  
			pidCliente=dadosCliente->pidC;
			strcpy(linha,dadosCliente->path);
			ap=strtok(linha,"/");
			while(ap!=NULL){
				antap=ap;
				ap=strtok(NULL,"/");
			}
			strcpy(linha,"/home/goncalopereira96/backup/");
			strcat(linha,antap);
			if(fork()==0){
				execlp("cp","cp",linha,dadosCliente->path,NULL);
				perror("cp");
				kill(pidCliente,SIGUSR2);
				free(dadosCliente);
				return 1;
			}
			wait(NULL);
			kill(pidCliente,SIGUSR1);
		}
	close(fd);
	fd = open("/home/goncalopereira96/backup/fifo",O_RDONLY);	
	}
	close (fd);
	free(dadosCliente);
	return 0;
}
