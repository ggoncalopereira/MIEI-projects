#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "header.h"

/**Esta função foi criada com o intuito de resolver tabuleiros,
apesar de a nossa nao estar complexa o suficiente para tal.

@param a função recebe a estrutura, do tipo ESTRUTURA_BN.
@param recebe também a stack com o intuito de ser possivel desfazer as alterações.
*/

void cmd_R(ESTRUTURA_BN *est_bn, stck *stack){
    int e1,e2,e3,max=0;
    stck s;
    union dados d;

    d.dadosest=newDadosEst(est_bn);
    push(stack,'R',d);

    do{
        e1=cmd_E1(est_bn,&s);
        e2=cmd_E2(est_bn,&s);
        e3=cmd_E3(est_bn,&s);
        ++max;
        if (max>=150)
            e1=0;
            e2=0;
            e3=0;
    }while (e1!=0 || e2!=0 || e3!=0);
}