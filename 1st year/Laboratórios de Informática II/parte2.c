#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "header.h"

/**
Esta função está responsável por desfazer o último comando quando este foi o p.

@param a função recebe a estrutura atual
@param para que possa recuperar a estrutura antiga, a função recebe também a stack.
*/
void undo_p (ESTRUTURA_BN *est_bn, stck *stack){
    int lin, col;
    char cmd;
    lin= (*stack)->estado.Dados.dadosp->l;
    col= (*stack)->estado.Dados.dadosp->c;
    cmd= (*stack)->estado.Dados.dadosp->ch;
     est_bn->tabuleiro[lin][col]=cmd;
    pop (stack); 
}

/**
Esta função está responsável por desfazer o último comando quando este foi o v.

@param a função recebe a estrutura atual
@param para que possa recuperar a estrutura antiga, a função recebe também a stack.
*/
void undo_v (ESTRUTURA_BN *est_bn, stck *stack){
    int i,numc;
    numc= (*stack)->estado.Dados.dadosvh->x;
     for (i=0;i<est_bn->numlinhas;i++){
          est_bn->tabuleiro[i][numc]=(*stack)->estado.Dados.dadosvh->ant[i];
     }
     pop (stack);
}

/**
Esta função está responsável por desfazer o último comando quando este foi o h.

@param a função recebe a estrutura atual
@param para que possa recuperar a estrutura antiga, a função recebe também a stack.
*/
void undo_h (ESTRUTURA_BN *est_bn, stck *stack){
    int i,numl;
    numl= (*stack)->estado.Dados.dadosvh->x;
     for (i=0;i<est_bn->numcolunas;i++){
        est_bn->tabuleiro[numl][i]=(*stack)->estado.Dados.dadosvh->ant[i];
     }
     pop (stack);
}

/**
Esta função está responsável por desfazer o último comando quando este foi o C, o L, ou qualquer das E's.

@param a função recebe a estrutura atual
@param para que possa recuperar a estrutura antiga, a função recebe também a stack.
*/
void undo_CLE (ESTRUTURA_BN *est_bn, stck *stack){
    int i, c, l;
    ESTRUTURA_BN *n;

    n=newDadosEst((*stack)->estado.Dados.dadosest);
    est_bn->numlinhas=n->numlinhas;
    est_bn->numcolunas=n->numcolunas;
    for(i=0;i<n->numlinhas;i++){
        est_bn->segmentos_linhas[i]=n->segmentos_linhas[i];
    }for(i=0;i<n->numcolunas;i++){
        est_bn->segmentos_colunas[i]=n->segmentos_colunas[i];
    }for(l=0;l<n->numlinhas;l++){
        for (c=0;c<n->numcolunas;c++){
            est_bn->tabuleiro[l][c]=n->tabuleiro[l][c];
        }
    }
    pop(stack);
}

/**
O comando D desfaz um comando e volta ao estado anterior.

@param A função desfaz a última alteração feita ao tabuleiro.
@param Esta é constituida por várias funções auxiliares pois cada comando é diferente na sua forma de alterar o tabuleiro.

@returns Não retorna nada apenas desfaz a última alteração feita ao tabuleiro. 

*/
void cmd_D (ESTRUTURA_BN *est_bn, stck *stack){
    char c=(*stack)->estado.com;
    switch (c){
        case 'p':
          undo_p(est_bn, stack);
        break;
        case 'v':
          undo_v(est_bn, stack);
        break;
        case 'h':
          undo_h(est_bn, stack);
        break;
        case 'c':
          undo_CLE(est_bn, stack);
        break;
        case 'l':
          undo_CLE(est_bn, stack);
        break;
        case 'E':
          undo_CLE(est_bn, stack);
        break;
        case 'R':
          undo_CLE(est_bn, stack);
        break;
      default:
        printf("\n");

    }
}

/**
Esta funcao acrescenta um elemento à stack

@param a função recebe a stack.
@param recebe o comando que foi utilizado.
@param recebe uma "union dados" que, dependendo do comando uzado será um apontador para tipos diferentes.

*/
void push (stck *stack, char cmd, union dados d){
    stck aux;
    aux = (stck) malloc (sizeof (STACK));
    aux->prox=(*stack);
    aux->estado.com=cmd;
    aux->estado.Dados=d;
    (*stack)=aux;
}

/**
Esta função retira um elemento da stack.

@param esta função recebe somente a stack.
*/
void pop (stck *stack){
    stck aux=*stack;
    (*stack)=(*stack)->prox;
    free(aux);
}

/** 
O comando l lê o tabuleiro a partir de um ficheiro .txt.

@param A função recebe um tabuleiro a partir do ficheiro .txt  escrito que pertence à mesma diretoria onde o projeto se encontra.

@returns Não retorna nada apenas lê o tabuleiro.
*/

int cmd_l (ESTRUTURA_BN *est_bn,  char *path, stck *stack) {
    char linha [MAX_SIZE],restolinha [MAX_SIZE];
    int lin, col;
    union dados d;
    FILE *fp;
    
    d.dadosest=newDadosEst(est_bn);
    push(stack,'c',d);

    if ((fp=fopen(path,"r"))==NULL){
            printf ("Ficheiro não existe\n");
    }
      
    if(fgets (linha, MAX_SIZE, fp) == NULL) return 0;
    sscanf(linha, "%d %d", &est_bn -> numlinhas, &est_bn -> numcolunas);
    if(fgets (linha, MAX_SIZE, fp) == NULL) return 0;
    for(lin = 0; lin < est_bn -> numlinhas; lin++){
        sscanf (linha, "%d %[^\n]", &est_bn-> segmentos_linhas[lin], restolinha);
        strcpy (linha,restolinha);
    }
    if(fgets (linha, MAX_SIZE, fp) == NULL) return 0;
    for(col = 0; col < est_bn -> numcolunas; col++){
        sscanf (linha, "%d %[^\n]", &est_bn-> segmentos_colunas[col], restolinha);
        strcpy (linha,restolinha);
    }
    for (lin= 0; lin < est_bn -> numlinhas; lin++){
        if(fgets(linha,MAX_SIZE, fp) == NULL) return 0;
        for(col = 0; col < est_bn -> numcolunas; col++){
            est_bn -> tabuleiro[lin][col] = linha[col];
        }
    }
    fclose (fp);
    return 1;
}


/** 
O comando e escrever um tabuleiro num ficheiro .txt.

@param A função vai receber um tabuleiro a partir do qual vai escrever num dado ficheiro, para o qual teremos de escolher o nome e o path.

@returns Não retorna nada apenas escreve no ficheiro .txt.
*/
int cmd_e (ESTRUTURA_BN *est_bn, char *path){
    FILE *fp;
    int lin, col;

if ((fp=fopen(path,"w"))==NULL){
            printf ("Ficheiro não existe\n");
    }fprintf (fp,"%d %d\n",est_bn->numlinhas, est_bn->numcolunas);    
    for(lin=0;lin<(est_bn->numlinhas-1);lin++)
        fprintf(fp,"%d ",est_bn->segmentos_linhas[lin]);
    fprintf(fp,"%d",est_bn->segmentos_linhas[lin]);
        fprintf(fp,"\n");
    for(col=0;col<(est_bn->numcolunas-1);col++)
        fprintf(fp,"%d ",est_bn->segmentos_colunas[col]);
    fprintf(fp,"%d",est_bn->segmentos_colunas[col]);
        fprintf(fp,"\n");
    for(lin=0; lin<est_bn-> numlinhas;lin++){
        for(col=0; col<est_bn-> numcolunas;col++){
            fprintf(fp,"%c", est_bn-> tabuleiro[lin][col]);   
        }
        fprintf(fp,"\n");
    }
    return 1;
}

int verificaAgua(ESTRUTURA_BN *est_bn){/*verificar a regra da água*/
    int lin,col,r=0;

    for(lin=0;lin<est_bn->numlinhas;lin++){
        for(col=0;col<est_bn->numcolunas;col++){

            if(est_bn->tabuleiro[lin][col]=='<' && est_bn->tabuleiro[lin][col+1]=='~'){/*CASO <~... */
                            r=1;
            }else if(est_bn->tabuleiro[lin][col]=='>' && est_bn->tabuleiro[lin][col-1]=='~'){/*CASO ...~> */
                        r=1;
                    }else if(est_bn->tabuleiro[lin][col]=='^' && est_bn->tabuleiro[lin+1][col]=='~'){/*CASO ..^../..~.. */
                                r=1;
                            }else if(est_bn->tabuleiro[lin][col]=='v' && est_bn->tabuleiro[lin-1][col]=='~'){/*CASO ..~../..v.. */
                                        r=1;
                                    }else r=0;
        }
    }
    return r;
}

int verificaCantosP(ESTRUTURA_BN *est_bn){/* verifica se tem peças isoladas e se os submarinos têm peças à sua volta*/
    int lin,col,i,r=0,countSub=0;
    char pecas[2]=".~";

    for(lin=0;lin<est_bn->numlinhas;lin++){
        for(col=0;col<est_bn->numcolunas;col++){
            
            if(est_bn->tabuleiro[lin][col]=='O'){
                countSub=countSub+1;
            }
            for(i=0;i<2;i++){
                if(est_bn->tabuleiro[lin][col]=='>'){
                    if(est_bn->tabuleiro[lin][col+1]!=pecas[i] || est_bn->tabuleiro[lin+1][col]!=pecas[i] || est_bn->tabuleiro[lin-1][col]!=pecas[i] || est_bn->tabuleiro[lin+1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col-1]!=pecas[i] || est_bn->tabuleiro[lin+1][col-1]!=pecas[i]){
                    r=1;
                    }
                }else if(est_bn->tabuleiro[lin][col]=='<'){
                            if(est_bn->tabuleiro[lin][col-1]!=pecas[i] || est_bn->tabuleiro[lin+1][col]!=pecas[i] || est_bn->tabuleiro[lin-1][col]!=pecas[i] || est_bn->tabuleiro[lin+1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col-1]!=pecas[i] || est_bn->tabuleiro[lin+1][col-1]!=pecas[i]){
                            r=1;
                            }
                        }else if(est_bn->tabuleiro[lin][col]=='^'){
                                    if(est_bn->tabuleiro[lin][col+1]!=pecas[i] || est_bn->tabuleiro[lin][col-1]!=pecas[i] || est_bn->tabuleiro[lin-1][col]!=pecas[i] || est_bn->tabuleiro[lin+1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col-1]!=pecas[i] || est_bn->tabuleiro[lin+1][col-1]!=pecas[i]){
                                    r=1;
                                    }
                                }else if(est_bn->tabuleiro[lin][col]=='v'){
                                            if(est_bn->tabuleiro[lin][col+1]!=pecas[i] || est_bn->tabuleiro[lin+1][col]!=pecas[i] || est_bn->tabuleiro[lin][col-1]!=pecas[i] || est_bn->tabuleiro[lin+1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col+1]!=pecas[i] || est_bn->tabuleiro[lin-1][col-1]!=pecas[i] || est_bn->tabuleiro[lin+1][col-1]!=pecas[i]){
                                            r=1;
                                            }
                                        }else if(verificaSUB(est_bn,pecas[i],lin,col)==1){
                                                r=1;
                                            }else r=0;
            }
        }
    }
    return r;
}

int verificaSUB(ESTRUTURA_BN *est_bn, char peca, int lin, int col){/* verifica se os submarinos têm peças à volta*/
    int r=0;

    if(est_bn->tabuleiro[0][0]=='O'){
        if(est_bn->tabuleiro[lin][col+1]!=peca || est_bn->tabuleiro[lin+1][col]!=peca || est_bn->tabuleiro[lin+1][col+1]!=peca){
            r=1;
            }
        }else if(est_bn->tabuleiro[0][est_bn->numcolunas-1]=='O'){
                    if(est_bn->tabuleiro[lin+1][col]!=peca || est_bn->tabuleiro[lin][col-1]!=peca || est_bn->tabuleiro[lin+1][col-1]!=peca){
                    r=1;
                    }
                }else if(est_bn->tabuleiro[est_bn->numlinhas-1][0]=='O'){
                            if(est_bn->tabuleiro[lin-1][col]!=peca || est_bn->tabuleiro[lin][col+1]!=peca || est_bn->tabuleiro[lin-1][col+1]!=peca){
                            r=1;
                            }
                        }else if(est_bn->tabuleiro[est_bn->numlinhas-1][est_bn->numcolunas-1]=='O'){
                                    if(est_bn->tabuleiro[lin][col-1]!=peca || est_bn->tabuleiro[lin-1][col]!=peca || est_bn->tabuleiro[lin-1][col-1]!=peca){
                                    r=1;
                                    }
                                }else if(est_bn->tabuleiro[lin][col]=='O'){
                                                if(est_bn->tabuleiro[lin+1][col]!=peca || est_bn->tabuleiro[lin-1][col]!=peca || est_bn->tabuleiro[lin][col+1]!=peca || est_bn->tabuleiro[lin][col-1]!=peca || est_bn->tabuleiro[lin+1][col+1]!=peca || est_bn->tabuleiro[lin+1][col-1]!=peca || est_bn->tabuleiro[lin-1][col-1]==peca || est_bn->tabuleiro[lin-1][col+1]==peca){
                                                r=1;
                                                }
                                        }else r=0;
    return r;
}

int verificaBarcos(ESTRUTURA_BN *est_bn){/* verifica se os barcos são válidos*/
    int lin,col,r=0;

    for(lin=0;lin<est_bn->numlinhas;lin++){
        for(col=0;col<est_bn->numcolunas;col++){

            if(est_bn->tabuleiro[lin][col]=='<' && est_bn->tabuleiro[lin][col+1]=='>'){
                        r=0;
            }else if(est_bn->tabuleiro[lin][col]=='^' && est_bn->tabuleiro[lin+1][col]=='v'){
                                r=0;
                    }else if(est_bn->tabuleiro[lin][col]=='<'){
                                if(est_bn->tabuleiro[lin][col+1]=='#'){
                                        while(est_bn->tabuleiro[lin][col]!='>')
                                            col++;
                                }
                    }else if(est_bn->tabuleiro[lin][col]=='^'){
                                if(est_bn->tabuleiro[lin+1][col]=='#'){
                                        while(est_bn->tabuleiro[lin][col]!='v')   
                                            lin++;
                                }
            }
        }
    }
    return r;
}


int verificaPecaEx(ESTRUTURA_BN *est_bn){/* verifica extremos*/
    int lin,col,r=0;

    for(lin=0;lin<est_bn->numlinhas;lin++){
        for(col=0;col<est_bn->numcolunas;col++){
                if(est_bn->tabuleiro[lin][col]=='v' && lin==0){/*1ª LINHA*/
                        r=1;
                    }else if(est_bn->tabuleiro[lin][col]=='^' && lin==(est_bn->numlinhas-1)){/*ULTIMA LINHA*/
                                r=1;
                            }else if(est_bn->tabuleiro[lin][col]=='>' && col==0){/*1ª COLUNA*/
                                        r=1;
                                    }else if(est_bn->tabuleiro[lin][col]=='<' && col==(est_bn->numcolunas-1)){/*ULTIMA COLUNA*/
                                                r=1;
                                            }else if(est_bn->tabuleiro[lin][col]=='#'){
                                                        if(lin==0 && col==0){/*peças do tipo # no canto superior esquerdo*/
                                                        r=1;
                                                        }
                                                    }else if(est_bn->tabuleiro[lin][col]=='#'){
                                                                if(lin==0 && col==(est_bn->numcolunas-1)){/*peças do tipo # no canto superior direito*/
                                                                r=1;
                                                                }
                                                            }else if(est_bn->tabuleiro[lin][col]=='#'){
                                                                        if(col==0 && lin==(est_bn->numlinhas-1)){/*pecas do tipo # no canto inferior esquerdo*/
                                                                        r=1;
                                                                        }
                                                                    }else if(est_bn->tabuleiro[lin][col]=='#'){ 
                                                                            if(lin==(est_bn->numlinhas-1) && col==(est_bn->numcolunas-1)){/*pecas do tipo # no canto inferior direito*/
                                                                            r=1;
                                                                            }
                                                                        }else r=0;  
        }
    }
    return r;
}

int verificaSeg(ESTRUTURA_BN *est_bn){
    int lin,col,i,countPL=0,countPC=0,r=0;
    char pecas[7]="oO#^v<>";

    for(lin=0;lin<est_bn->numlinhas;lin++){
        for(col=0;col<est_bn->numcolunas;col++){
            for(i=0;i<7;i++){
                if(est_bn->tabuleiro[lin][col]==pecas[i])
                    countPL++;
            }
        }
    }
    for(col=0;col<est_bn->numcolunas;col++){
        for(lin=0;lin<est_bn->numlinhas;lin++){
            for(i=0;i<7;i++){
                if(est_bn->tabuleiro[lin][col]==pecas[i])
                    countPC++;
            }   
        }
    }
    if(countPC>est_bn->segmentos_colunas[col] || countPL>est_bn->segmentos_linhas[lin])
        r=1;
    return r;
}

/**O comando V verifica se a solução apresentada está correta.

@param A função é composta por várias funções auxiliares que conforme as regras da água, dos segmentos e dos barcos verifica se a solução apresentada está correta

@returns A função retorna SIM caso a solução seja a correta e retorna NAO caso seja incorreta. 

*/

int cmd_V(ESTRUTURA_BN *est_bn){
    int valido,p,s,a,b,c;

    s=verificaSeg(est_bn);
    p=verificaPecaEx(est_bn);
    a=verificaAgua(est_bn);
    b=verificaBarcos(est_bn);
    c=verificaCantosP(est_bn);

    if(s==0 && p==0 && a==0 && b==0 && c==0){
        valido=0;
        printf("SIM\n");
    }else{
        valido=1;
        printf("NAO\n");
    }

    return valido;
}

void E1_primlinha(ESTRUTURA_BN *est_bn){
    int lin, col;
    for(lin=0, col=0; col<est_bn->numcolunas;col++){ 
        if(est_bn->tabuleiro[lin][col]=='^'){ /*casos da primeira linha*/
            if(col==0){
                if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~'; /*peça ^ na pos CSE*/
                if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~';
            }
            else{
                if(col==((est_bn->numcolunas)-1)){ /*peça ^ na pos CSD*/
                    if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                    if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                }
                else{
                    if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~'; /*restantes posições em CS*/
                    if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~';
                    if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                    if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                }
            }
        }
        else{ 
            if(est_bn->tabuleiro[lin][col]=='<'){ 
                if(col==0){
                    if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~'; /*peça < na pos CSE*/
                    if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                }
                else{
                    if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~'; /*restantes posições em CS*/
                    if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                    if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                    if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                }
            }
            else{
                if(est_bn->tabuleiro[lin][col]=='>'){ /*peça > na pos CSE*/
                    if(col==((est_bn->numcolunas)-1)){
                        if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                        if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                    }
                    else{
                        if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~'; /*restantes posições em CS*/
                        if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                        if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                        if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~';
                    }
                }
                else{
                    if(est_bn->tabuleiro[lin][col]=='O'){ 
                        if(col==0){
                            if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~'; /*peça O em CSE*/
                            if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                            if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                        }
                        else{
                            if(col==((est_bn->numcolunas)-1)){ /*peça O em CSD*/
                                if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                                if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                                if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                            }
                            else{
                                if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~'; /*restantes posições em CS*/
                                if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                                if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                                if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~';
                                if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                            }
                        }
                    }
                    else{
                        if(est_bn->tabuleiro[lin][col]=='o' || est_bn-> tabuleiro[lin][col]=='#'){
                            if(col==0){
                                if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~'; /*peça o em CSE*/
                            }
                            else{
                                if(col==((est_bn->numcolunas)-1)){ /*peça o em CSD*/
                                    if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                                }
                                else{
                                    if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~'; /*restantes posições em CS*/
                                    if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                                    if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

void E1_filtraresto (ESTRUTURA_BN *est_bn){
    int lin, col;

    for(lin=1; lin<((est_bn->numlinhas)); lin++){
        for(col=0; col<est_bn->numcolunas; col++){
            if(est_bn->tabuleiro[lin][col]=='o'){
                if(est_bn->tabuleiro[lin-1][col-1]=='.') est_bn->tabuleiro[lin-1][col-1]='~';
                if(est_bn->tabuleiro[lin-1][col+1]=='.') est_bn->tabuleiro[lin-1][col+1]='~';
                if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
            }
            else{
                if(est_bn->tabuleiro[lin][col]=='v'){
                    if(est_bn->tabuleiro[lin-1][col-1]=='.') est_bn->tabuleiro[lin-1][col-1]='~';
                    if(est_bn->tabuleiro[lin-1][col+1]=='.') est_bn->tabuleiro[lin-1][col+1]='~';
                    if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                    if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~';
                    if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                    if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                    if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                }
                else{
                    if(est_bn->tabuleiro[lin][col]=='<'){
                        if(est_bn->tabuleiro[lin-1][col-1]=='.') est_bn->tabuleiro[lin-1][col-1]='~';
                        if(est_bn->tabuleiro[lin-1][col]=='.') est_bn->tabuleiro[lin-1][col]='~';
                        if(est_bn->tabuleiro[lin-1][col+1]=='.') est_bn->tabuleiro[lin-1][col+1]='~';
                        if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                        if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                        if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                        if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                    }
                    else{
                        if(est_bn->tabuleiro[lin][col]=='^'){
                            if(est_bn->tabuleiro[lin-1][col-1]=='.') est_bn->tabuleiro[lin-1][col-1]='~';
                            if(est_bn->tabuleiro[lin-1][col]=='.') est_bn->tabuleiro[lin-1][col]='~';
                            if(est_bn->tabuleiro[lin-1][col+1]=='.') est_bn->tabuleiro[lin-1][col+1]='~';
                            if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                            if(est_bn->tabuleiro[lin][col]=='.') est_bn->tabuleiro[lin][col]='~';
                            if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~';
                            if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                            if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                        }
                        else{
                            if(est_bn->tabuleiro[lin][col]=='>'){
                                if(est_bn->tabuleiro[lin-1][col-1]=='.')est_bn->tabuleiro[lin-1][col-1]='~';
                                if(est_bn->tabuleiro[lin][col+1]=='.')est_bn->tabuleiro[lin][col+1]='~';
                                if(est_bn->tabuleiro[lin-1][col]=='.')est_bn->tabuleiro[lin-1][col]='~';
                                if(est_bn->tabuleiro[lin-1][col+1]=='.')est_bn->tabuleiro[lin-1][col+1]='~';
                                if(est_bn->tabuleiro[lin+1][col-1]=='.')est_bn->tabuleiro[lin+1][col-1]='~';
                                if(est_bn->tabuleiro[lin+1][col]=='.')est_bn->tabuleiro[lin+1][col]='~';
                                if(est_bn->tabuleiro[lin+1][col+1]=='.')est_bn->tabuleiro[lin+1][col+1]='~';
                            }
                            else{
                                if(est_bn->tabuleiro[lin][col]=='O'){
                                    if(est_bn->tabuleiro[lin-1][col-1]=='.') est_bn->tabuleiro[lin-1][col-1]='~';
                                    if(est_bn->tabuleiro[lin-1][col]=='.') est_bn->tabuleiro[lin-1][col]='~';
                                    if(est_bn->tabuleiro[lin-1][col+1]=='.') est_bn->tabuleiro[lin-1][col+1]='~';
                                    if(est_bn->tabuleiro[lin][col-1]=='.') est_bn->tabuleiro[lin][col-1]='~';
                                    if(est_bn->tabuleiro[lin][col+1]=='.') est_bn->tabuleiro[lin][col+1]='~';
                                    if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                                    if(est_bn->tabuleiro[lin+1][col]=='.') est_bn->tabuleiro[lin+1][col]='~';
                                    if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                                }
                                else{
                                    if(est_bn->tabuleiro[lin][col]=='#'){
                                        if(est_bn->tabuleiro[lin-1][col-1]=='.') est_bn->tabuleiro[lin-1][col-1]='~';
                                        if(est_bn->tabuleiro[lin-1][col+1]=='.') est_bn->tabuleiro[lin-1][col+1]='~';
                                        if(est_bn->tabuleiro[lin+1][col-1]=='.') est_bn->tabuleiro[lin+1][col-1]='~';
                                        if(est_bn->tabuleiro[lin+1][col+1]=='.') est_bn->tabuleiro[lin+1][col+1]='~';
                                        if(est_bn->tabuleiro[lin+1][col]=='~') est_bn->tabuleiro[lin-1][col]='~';
                                        if(est_bn->tabuleiro[lin-1][col]=='~') est_bn->tabuleiro[lin+1][col]='~';
                                        if(est_bn->tabuleiro[lin][col+1]=='~') est_bn->tabuleiro[lin][col-1]='~';
                                        if(est_bn->tabuleiro[lin][col-1]=='~') est_bn->tabuleiro[lin][col+1]='~';
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

int contaaguas(ESTRUTURA_BN *est_bn){
    int lin,col,contaagua=0;
    for(lin=0; lin<est_bn->numlinhas;lin++){
        for(col=0;col<est_bn->numcolunas;col++){
            if(est_bn->tabuleiro[lin][col]=='~')
                contaagua++;
        }
    }
    return contaagua;
}

/**
O comando E1 é a estratégia 1.

@param A função recebe o tabuleiro e é responsável por colocar água onde se deduz que vai existir à volta de todos os segmentos de barcos já colocados.
@param É constituída por duas funções auxiliares chamadas E1_primlinha e E1_filtraresto que começam por preencher a primeira linha. Caso existam mais do que uma linha, esta preenchê-las-á.

@returns Retorna 1 se houverem alterações e 0 se não houverem.
*/
int cmd_E1(ESTRUTURA_BN *est_bn, stck *stack){
    union dados d;
    int r=0,a,dps;

    a=contaaguas(est_bn);
    d.dadosest=newDadosEst(est_bn);
    push(stack,'E',d);

    E1_primlinha(est_bn);
    if((est_bn->numlinhas)>0){
        E1_filtraresto(est_bn); 
    }

    dps=contaaguas(est_bn);

    if(a!=dps)
        r=1;
    return r;
}

int pertence (char c){
    int i,r=0;
    char pecasD[7]="oO#^v<>";
    for (i=0;i<7;i++){
        if (c==pecasD[i])
           r=1;
   }
    return r;   
}

void vaux (ESTRUTURA_BN *est_bn, int c){
int l;
    for(l=0;l<(est_bn->numlinhas);l++){
        if (est_bn->tabuleiro[l][c] == '.')
        est_bn->tabuleiro[l][c] = '~';
    }
}

int E2C (ESTRUTURA_BN *est_bn){
    int r=0,l,c,count=0;
    for (c=0;c<est_bn->numcolunas;c++){
        for (l=0;l<est_bn->numlinhas;l++){
            if ((pertence(est_bn->tabuleiro[l][c]))==1)
                count++;
        }
        if (count==est_bn->segmentos_colunas[c]){
            vaux (est_bn,c);
            r=1;
        }count=0;
    }
    return r;
}

void haux (ESTRUTURA_BN *est_bn, int l){
int c;
    for(c=0;est_bn->tabuleiro [l] [c] != '\0';c++){
        if (est_bn->tabuleiro [l][c] == '.')
        est_bn->tabuleiro [l][c] = '~';
    }
}

int E2L (ESTRUTURA_BN *est_bn){
    int r=0,l,c,count=0;
    for (l=0;l<est_bn->numlinhas;l++){
        for (c=0;c<est_bn->numcolunas;c++){
            if ((pertence(est_bn->tabuleiro[l][c]))==1)
                count++;
        }
        if (count==est_bn->segmentos_linhas[l]){
            haux (est_bn,l);
            r=1;
        }count=0;
    }
    return r;
}

/**
O comando E2 é a estratégia 2.

@param A função recebe o tabuleiro e é responsável por colocar água nas linhas e colunas em todos os segmentos de barcos que já foram colocados.

@returns retorna 1 se houverem alterações e 0 se não houverem.
*/
int cmd_E2 (ESTRUTURA_BN *est_bn, stck *stack){
    union dados d;
    int r=0,el,ec;

    el=contaaguas (est_bn);

    d.dadosest=newDadosEst(est_bn);
    push(stack,'E',d);

    E2L (est_bn);
    E2C (est_bn);

    ec=contaaguas(est_bn);

    if(ec!=el)
        r=1;
    
    return r;
}


int pertence3 (char c){
    int i,r=0;
    char pecasD[7]="O#^v<>o";
    for (i=0;i<7;i++){
        if (c==pecasD[i])
           r=1;
   }
    return r;   
}

int contasegl (ESTRUTURA_BN *est_bn, int l){
    int c,count=0;
        for (c=0;c<est_bn->numcolunas;c++){
            if ((pertence3(est_bn->tabuleiro[l][c]))==1)
                count++;
        }
    return count;
}

int contapl (ESTRUTURA_BN *est_bn, int l){
    int c,count=0;
        for (c=0;c<est_bn->numcolunas;c++){
            if (est_bn->tabuleiro[l][c]=='.')
                count++;
        }   
    return count;
}

void porosl (ESTRUTURA_BN *est_bn){
    int c=0,l,cs,cp;
    for (l=0;l<est_bn->numlinhas;l++){
        cs=contasegl (est_bn,l);
        cp=contapl (est_bn,l);
        if ((cp-cs)==est_bn->segmentos_linhas[l]){
            for (c=0;c<est_bn->numcolunas;c++){
              if (est_bn->tabuleiro [l][c] == '.')
                est_bn->tabuleiro [l][c] = 'o';  
            }
        }
    }
}    

int contasegc (ESTRUTURA_BN *est_bn, int c){
    int l,count=0;
        for (l=0;l<est_bn->numlinhas;l++){
            if ((pertence3(est_bn->tabuleiro[l][c]))==1)
                count++;
        }
    return count;
}

int contapc (ESTRUTURA_BN *est_bn, int c){
    int l,count=0;
        for (l=0;l<est_bn->numlinhas;l++){
            if (est_bn->tabuleiro[l][c]=='.')
                count++;
        }
    return count;
}

void porosc (ESTRUTURA_BN *est_bn){
    int c,l=0,cs,cp;
    for (c=0;c<est_bn->numcolunas;c++){
        cs=contasegc(est_bn,c);
        cp=contapc(est_bn,c);
        if (cs!=est_bn->segmentos_colunas[c]){
        if ((cp+cs)==est_bn->segmentos_colunas[c]){
            for (l=0;l<est_bn->numlinhas;l++){
                if (est_bn->tabuleiro [l][c] == '.')
                  est_bn->tabuleiro [l][c] = 'o';
                }
            }
        }
    }
}

void poros (ESTRUTURA_BN *est_bn){
    porosl (est_bn);
    porosc (est_bn);
}

int porsubs (ESTRUTURA_BN *est_bn){
    int l,c,r=0;
    for (l=0;l<est_bn->numlinhas;l++){
        for (c=0;c<est_bn->numcolunas;c++){
            if (est_bn->tabuleiro[l][c]=='o'){
                    r=1;
                    if (l==0 && c==0 && est_bn->tabuleiro[(l+1)][c]=='~' && est_bn->tabuleiro[l][(c+1)]=='~')
                        est_bn->tabuleiro[l][c]='O';
                        else if (l==0 && c==(est_bn->numcolunas-1) && est_bn->tabuleiro[(l+1)][c]=='~' && est_bn->tabuleiro[l][(c-1)]=='~')
                            est_bn->tabuleiro[l][c]='O';
                            else if (l==0 && est_bn->tabuleiro[(l+1)][c]=='~' && est_bn->tabuleiro[l][(c+1)]=='~' && est_bn->tabuleiro[l][(c-1)]=='~')
                                    est_bn->tabuleiro[l][c]='O';
                                else if (l==(est_bn->numlinhas-1) && c==0 && est_bn->tabuleiro[l][(c+1)]=='~' && est_bn->tabuleiro[(l-1)][c]=='~')
                                        est_bn->tabuleiro[l][c]='O';
                                    else if (l==(est_bn->numlinhas-1) && c==(est_bn->numcolunas-1) && est_bn->tabuleiro[(l-1)][c]=='~' && est_bn->tabuleiro[l][(c-1)]=='~')
                                            est_bn->tabuleiro[l][c]='O';
                                        else if (l==(est_bn->numlinhas-1) && est_bn->tabuleiro[(l-1)][c]=='~' && est_bn->tabuleiro[l][(c-1)]=='~' && est_bn->tabuleiro[l][(c+1)]=='~')
                                            est_bn->tabuleiro[l][c]='O';
                                            else if (c==0 && est_bn->tabuleiro[(l+1)][c]=='~' && est_bn->tabuleiro[(l-1)][c]=='~' && est_bn->tabuleiro[(l)][(c+1)]=='~' && est_bn->tabuleiro[(l-1)][(c+1)]=='~')
                                                    est_bn->tabuleiro[l][c]='O';
                                                else if (c==(est_bn->numcolunas-1) && est_bn->tabuleiro[(l+1)][c]=='~' && est_bn->tabuleiro[(l-1)][c]=='~' && est_bn->tabuleiro[l][(c-1)]=='~')
                                                    est_bn->tabuleiro[l][c]='O';
                                                    else if (est_bn->tabuleiro[(l+1)][c]=='~' && est_bn->tabuleiro[(l-1)][c]=='~' && est_bn->tabuleiro[l][(c+1)]=='~' && est_bn->tabuleiro[l][(c-1)]=='~')
                                                         est_bn->tabuleiro[l][c]='O';
                                                        else if (l==(est_bn->numlinhas-1) && est_bn->tabuleiro[(l-1)][c]=='~' && est_bn->tabuleiro[l][(c-1)]=='~' && est_bn->tabuleiro[l][(c+1)]=='.')
                                                            est_bn->tabuleiro[l][c]='O';
            }
        }
    }
    return r;
}

void poebarcosAuxl (ESTRUTURA_BN *est_bn, int l, int c){
    est_bn->tabuleiro[l][c]='<';
    do{
    est_bn->tabuleiro[l][(c+1)]='#';
    c++;
    }while ((c+2!=est_bn->numcolunas) && (est_bn->tabuleiro[l][c+2])!='~' && (est_bn->tabuleiro[l][c+1])!='>' && (est_bn->tabuleiro[l][c+1]!='.'));
    ++c;
    est_bn->tabuleiro[l][c]='>';
}

void casoEl (ESTRUTURA_BN *est_bn, int l, int c){
    for(;est_bn->tabuleiro[l][c+1]!='.' && est_bn->tabuleiro[l][c+1]!='~';c++){
        est_bn->tabuleiro[l][c]='#';
       }est_bn->tabuleiro[l][c]='>';
}

void casoEc (ESTRUTURA_BN *est_bn, int l, int c){
    for(;est_bn->tabuleiro[l+1][c]!='.' && est_bn->tabuleiro[l+1][c]!='~';l++){
        est_bn->tabuleiro[l][c]='#';
       }est_bn->tabuleiro[l][c]='v';
}       

void poebarcosAuxc (ESTRUTURA_BN *est_bn, int l, int c){
    est_bn->tabuleiro[l][c]='^';
    do{
    est_bn->tabuleiro[(l+1)][c]='#';
    l++;
    }while ((l+2!=est_bn->numlinhas) && (est_bn->tabuleiro[(l+2)][c])!='~' && (est_bn->tabuleiro[(l+1)][c])!='v');
    ++l;
    est_bn->tabuleiro[l][c]='v';
}


int porbarcos (ESTRUTURA_BN *est_bn){
    int l,c,r=0;
    for (l=0;l<est_bn->numlinhas;l++){
        for (c=0;c<est_bn->numcolunas;c++){
            if (est_bn->tabuleiro[l][c]=='o'){
                if (est_bn->tabuleiro[l][(c+1)]=='o'){
                    if (est_bn->tabuleiro[l][(c+2)]!='o' && est_bn->tabuleiro[l][c-1]!='<' && est_bn->tabuleiro[l][c-1]!='#' && est_bn->tabuleiro[l][c+2]!='>'){
                        est_bn->tabuleiro[l][c]='<';
                        est_bn->tabuleiro[l][c+1]='>';
                        r=1;
                    }else poebarcosAuxl (est_bn,l,c);
                          r=1;
                }
                else if (est_bn->tabuleiro[l+1][c]=='o'){
                        if (est_bn->tabuleiro[l+2][c]!='o' && est_bn->tabuleiro[l+2][c]!='v'){
                            est_bn->tabuleiro[l][c]='^';
                            est_bn->tabuleiro[l+1][c]='v';
                            r=1;
                        }else poebarcosAuxc (est_bn,l,c);
                              r=1;
                }
            }else if (est_bn->tabuleiro[l][c]=='<' && (est_bn->tabuleiro[l][c+1]=='o')){
                    casoEl(est_bn,l,(c+1));
                    r=1;
            }else if (est_bn->tabuleiro[l][c]=='^' && (est_bn->tabuleiro[l+1][c]=='o')){
                    casoEc(est_bn,(l+1),c);
                    r=1;
                }
        }
    }
    return r;
}

/**
Esta função substitui os o's que sejam possiveis substituir por segmentos de barcos.

@param a função recebe a estrutura com todos os o's.

@returns 1 ou zero consoante haja alterações
*/
int porsegs (ESTRUTURA_BN *est_bn){
    int ps,pb,r=0;
    ps=porsubs (est_bn);
    pb=porbarcos (est_bn);
    if (ps==1 || pb==1)
        r=1;
    return r;
}

void tiraros (ESTRUTURA_BN *est_bn){
    int l, c;
    for (l=0;l<est_bn->numlinhas;l++){
        for (c=0;c<est_bn->numcolunas;c++){
            if (est_bn->tabuleiro[l][c]=='o')
                est_bn->tabuleiro[l][c]='.';
        }
    } 
}

/**O comando E3 é a estratégia E3

@param A função recebe um tabuleiro quase completo, ou seja onde a água já esteja completamente preenchida, e coloca segmentos de barcos nas linhas e colunas nas quais todos os espaços vazios têm que conter segmentos de barcos para que o número correspondente seja respeitado.

@returns A funcao retorna 1 se houverem alterações e 0 se não houverem.
*/
int cmd_E3 (ESTRUTURA_BN *est_bn, stck *stack){
    union dados d;
    int r=0,ps;

    d.dadosest=newDadosEst(est_bn);
    push(stack,'E',d);

    poros (est_bn);
    ps=porsegs (est_bn);   
    tiraros (est_bn);
    if (ps==1)
        r=1;
    return r;
}