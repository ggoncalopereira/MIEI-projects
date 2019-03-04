#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "header.h"

/**
A função "newDadosEst" cria uma nova estrutura do tipo "ESTRUTURA_BN".

@param A função recebe como parametro somente uma estrutura, a qual vai copiar na integra.

@returns A nova estrutura.
*/
ESTRUTURA_BN *newDadosEst (ESTRUTURA_BN  *est_bn){
    int i, c, l;

    ESTRUTURA_BN *new= (ESTRUTURA_BN*) malloc (sizeof (ESTRUTURA_BN));
    new->numlinhas=est_bn->numlinhas;
    new->numcolunas=est_bn->numcolunas;
    for(i=0;i<est_bn->numlinhas;i++){
        new->segmentos_linhas[i]=est_bn->segmentos_linhas[i];
    }for(i=0;i<est_bn->numcolunas;i++){
        new->segmentos_colunas[i]=est_bn->segmentos_colunas[i];
    }for(l=0;l<est_bn->numlinhas;l++){
         for(c=0;c<est_bn->numcolunas;c++){
            new->tabuleiro[l][c]=est_bn->tabuleiro[l][c];
         }
    }
    return new;
}

/** 
O comando c lê o tabuleiro a partir do standard input.

@param A função vai receber um tabuleiro a partir do standard input.
@param Recebe a estrutura (est_bn), dois arrays um linha (recebe a linha do tabuleiro) e restolinha (guarda o resto da linha).
@param lin e col são as duas variáveis para o tamanho das linhas e colunas respetivamente.

@returns Não retorna nada apenas lê o tabuleiro a partir do teclado.
*/
int cmd_c (ESTRUTURA_BN *est_bn, stck *stack) {
    char linha [MAX_SIZE],restolinha [MAX_SIZE];
    int lin, col;
    union dados d;

    d.dadosest=newDadosEst(est_bn);
    push(stack,'c',d);

    if(fgets (linha, MAX_SIZE, stdin) == NULL) return 0;
    sscanf(linha, "%d %d", &est_bn -> numlinhas, &est_bn -> numcolunas);
    if(fgets (linha, MAX_SIZE, stdin) == NULL) return 0;
    for(lin = 0; lin < est_bn -> numlinhas; lin++){
        sscanf (linha, "%d %[^\n]", &est_bn-> segmentos_linhas[lin], restolinha);
        strcpy (linha,restolinha);
    }
    if(fgets (linha, MAX_SIZE, stdin) == NULL) return 0;
    for(col = 0; col < est_bn -> numcolunas; col++){
        sscanf (linha, "%d %[^\n]", &est_bn-> segmentos_colunas[col], restolinha);
        strcpy (linha,restolinha);
    }
    for (lin= 0; lin < est_bn -> numlinhas; lin++){
        if(fgets(linha,MAX_SIZE, stdin) == NULL) return 0;
        for(col = 0; col < est_bn -> numcolunas; col++){
            est_bn -> tabuleiro[lin][col] = linha[col];
        }
    }
    return 1;
}

/** 
O comando m mostra o tabuleiro.

@param A função vai receber um tabuleiro e vai imprimi-lo no ecrã.
@param lin e col são as duas variáveis para o tamanho das linhas e colunas respetivamente.

@returns Não retorna nada apenas mostra tabuleiro.
*/
int cmd_m (ESTRUTURA_BN *est_bn){
    int lin,col;

    for(lin=0; lin<est_bn-> numlinhas;lin++){
        for(col=0; col<est_bn-> numcolunas;col++){
            printf("%c", est_bn-> tabuleiro[lin][col]);
        }
        printf(" %d", est_bn-> segmentos_linhas[lin]);
        printf("\n");
    }
    for(col=0; col<est_bn->numcolunas; col++){
    printf("%d", est_bn->segmentos_colunas[col]);
    }
    printf("\n");
    return 1;
}

/**
O comando h coloca um estado em todas as grelhas de uma linha.

@param A função coloca o estado de todas as grelhas da linha n o num que ainda não estão determinadas como sendo água.
@param Onde está '.' vai colocar '~' na linha correspondente.

@returns Não retorna nada apenas altera o tabuleiro.
*/
int cmd_h (ESTRUTURA_BN *est_bn, int l, stck *stack){
    union dados d;
    int c,i;
    int lin [MAX_SIZE];
    for (i=0;i<(est_bn->numcolunas);i++){
        lin[i]=est_bn->tabuleiro[l][i];
    }
    d.dadosvh=newDadosVH(l,(est_bn->numcolunas),lin);
    push(stack,'h',d);
    for(c=0;est_bn->tabuleiro [l] [c] != '\0';c++){
    if (est_bn->tabuleiro [l][c] == '.')
        est_bn->tabuleiro [l][c] = '~';
    }
    return 1;
}

/**
Esta função irá criar uma nova estrutur que guarda os dados de uma fila/coluna inteira.

@param como primeiro argumento a função recebe o numero da linha/coluna que quer copiar.
@param recebe também o tamanho da linha/coluna.
@param por último a função recebe a propria linha/coluna que quer copiar.

@returns os dados da fila ou coluna que foram copiados.
*/
DadosVH *newDadosVH (int lc, int t, int a[MAX_SIZE]){
    int i;
    DadosVH *new=(DadosVH*)malloc(sizeof(DadosVH));
    new->x=lc;
    for(i=0;i<t;i++){
        new->ant[i]=a[i];
    }return new;
}

/**
O comando v coloca um estado em todas as grelhas de uma coluna

@param A função coloca o estado de todas as grelhas da coluna n o num que ainda não estão determinadas como sendo água, ou seja, onde está '.' vai colocar '~' na coluna correspondente.
@param c e l são as variáveis correspondentes à linha e coluna correspondentes.

@returns Não retorna nada apenas altera o tabuleiro.
*/
int cmd_v (ESTRUTURA_BN *est_bn, int c, stck *stack){
    union dados d;
    int l,i;
    int co[MAX_SIZE];
    for (i=0;i<(est_bn->numlinhas);i++){
        co[i]=est_bn->tabuleiro[i][c];
    }
    d.dadosvh=newDadosVH(c,(est_bn->numlinhas),co);
    push(stack,'v',d);
   for(l=0;l<(est_bn->numlinhas);l++){
    if (est_bn->tabuleiro[l][c] == '.')
        est_bn->tabuleiro[l][c] = '~';
    }
    return 1;
}

/**
Esta funcao cria os dados relativos a uma unica posição.

@param a funcao recebe os números da linha e coluna alterados.
@param recebe também o carater que estava na posicao antes de esta ser alterada.

@returns os dados relativos à posição.
*/
DadosP *newDadosP (int lin, int col, char chr){
    DadosP * new= (DadosP*) malloc (sizeof(DadosP));
    new->l=lin;
    new->c=col;
    new->ch=chr;
    return new;
}

/**
O comando p coloca uma peça de um barco ou submarino na linha e coluna dados.

@param A função ao receber um carater deve colocar na linha e coluna correspondentes esse mesmo carater.
@param ln e cl são as variáveis que correspondem à linha ln e coluna cl.

@returns Não retorna nada apenas altera o tabuleiro.
*/
int cmd_p (ESTRUTURA_BN *est_bn, char x, int l, int c, stck *stack){
    union dados d;
    char antx;
    antx = est_bn->tabuleiro[l][c];
    d.dadosp=newDadosP (l,c,antx);
    push(stack,'p',d);
    est_bn->tabuleiro[l][c] = x;
    return 1;
 }
