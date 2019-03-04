#ifndef __HEADER_H__
#define __HEADER_H__

#define MAX_SIZE 1024
#define MAX_TAB 100

/**
Estrutura definida para o tabuleiro. 
*/
typedef struct est_bn{
    int numlinhas;
    int numcolunas;
    int segmentos_linhas[MAX_TAB];
    int segmentos_colunas[MAX_TAB];
    char tabuleiro[MAX_TAB][MAX_TAB];
}ESTRUTURA_BN;

typedef struct dadosvh{
    int x;
    int ant[MAX_SIZE];
}DadosVH;

typedef struct dadosp {
    int l, c;
    char ch;
}DadosP;

typedef struct elem{
     char com; /*p,v,h*/
     union dados {
        DadosP *dadosp;
        DadosVH *dadosvh;
        ESTRUTURA_BN *dadosest;
        }Dados;
}ELEM;

typedef struct stack{
    ELEM estado;
    struct stack *prox;
}STACK;

typedef STACK *stck;

void push (stck *, char, union dados);

void pop (stck *);

void interpretador (ESTRUTURA_BN *, stck *);

int interpretar (ESTRUTURA_BN *, char *, stck *);

int cmd_c (ESTRUTURA_BN *, stck*);

int cmd_m (ESTRUTURA_BN *);

int cmd_h (ESTRUTURA_BN *, int, stck*);

int cmd_v (ESTRUTURA_BN *, int, stck *);

DadosP *newDadosP (int , int, char);

int cmd_p (ESTRUTURA_BN *, char, int, int, stck*);

int cmd_l (ESTRUTURA_BN *,  char *, stck *);

int cmd_e (ESTRUTURA_BN *, char *);

int cmd_V (ESTRUTURA_BN *);

int verificaAgua(ESTRUTURA_BN *);

int verificaBarcos(ESTRUTURA_BN *);

int verificaCantosP(ESTRUTURA_BN *);

int verificaSUB(ESTRUTURA_BN *,char , int , int );

int verificaSeg(ESTRUTURA_BN *);

int verificaPecaEx(ESTRUTURA_BN *);

int cmd_E1(ESTRUTURA_BN *, stck*);

int cmd_E2(ESTRUTURA_BN *, stck*);

int E2L (ESTRUTURA_BN *);

void haux (ESTRUTURA_BN *, int);

int E2C (ESTRUTURA_BN *);

void vaux (ESTRUTURA_BN *, int);

int pertence (char);

int cmd_E3(ESTRUTURA_BN *, stck*);

void tiraros (ESTRUTURA_BN *);

int porsegs (ESTRUTURA_BN *);

int porbarcos (ESTRUTURA_BN *);

void poebarcosAuxc (ESTRUTURA_BN *, int, int);

void casoEc (ESTRUTURA_BN *, int, int);

void casoEl (ESTRUTURA_BN *, int, int);

void poebarcosAuxl (ESTRUTURA_BN *, int, int);

int porsubs (ESTRUTURA_BN *);

void poros (ESTRUTURA_BN *);

void porosc (ESTRUTURA_BN *);

int contapc (ESTRUTURA_BN *, int);

int contasegc (ESTRUTURA_BN *, int);

void porosl (ESTRUTURA_BN *);

int contapl (ESTRUTURA_BN *, int);

int contasegl (ESTRUTURA_BN *, int);

int pertence3 (char);

void E1_filtraresto(ESTRUTURA_BN *);

void E1_primlinha(ESTRUTURA_BN *);

int contaaguas(ESTRUTURA_BN *);

void cmd_D(ESTRUTURA_BN *, stck *);

void undo_p (ESTRUTURA_BN *, stck *);

void undo_v (ESTRUTURA_BN *, stck *);

void undo_h (ESTRUTURA_BN *, stck *);

void undo_CLE (ESTRUTURA_BN *, stck *);

void cmd_R(ESTRUTURA_BN *, stck *);

DadosVH *newDadosVH (int, int, int []);

DadosP *newDadosP (int, int, char);

ESTRUTURA_BN *newDadosEst (ESTRUTURA_BN *);

#endif