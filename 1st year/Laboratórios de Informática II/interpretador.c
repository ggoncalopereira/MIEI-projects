#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "header.h"

/** 
O comando interpretar está encarregue de compreender o comando inserido

@param Conforme o comando inserido o interpretar tem um switch case para os diferentes comandos.

@returns O resultado da função escrita.
*/
int interpretar (ESTRUTURA_BN *est_bn, char *linha, stck *stack){
    char arg1[MAX_SIZE]; 
    char arg2[MAX_SIZE]; 
    char comando[MAX_SIZE];
    int nargs, a=0, b;
    
    nargs = sscanf ( linha , "%s %s %s" , comando , arg1, arg2);

    switch (comando[0]){
        case 'D':
          if (nargs == 1)
             cmd_D (est_bn, stack);
         break;
        case 'c': 
         if (nargs == 1)
            cmd_c (est_bn, stack);
         break;
        case 'm':
         if (nargs == 1)
            cmd_m (est_bn);
         break;
        case 'q':
            return 0;
         break;
        case 'h':
         if (nargs == 2)
            a= (atoi (arg1))-1;
            cmd_h (est_bn, a, stack);
         break;
        case 'v':
         if (nargs == 2)
            a= (atoi (arg1))-1;
            cmd_v (est_bn, a, stack); 
         break;
         case 'p':
         if (nargs == 3){
            a= (atoi (arg1))-1;
            b= (atoi (arg2))-1; 
            cmd_p (est_bn, comando[1], a, b, stack);
        }
         break;
         case 'l':
          if (nargs == 2)
             cmd_l (est_bn, arg1, stack);
         break;
         case 'e':
          if (nargs == 2)
             cmd_e (est_bn, arg1);
         break;
         case 'V':
          if (nargs == 1)
             cmd_V (est_bn); 
         break;
         case 'R':
          if (nargs==1)
             cmd_R (est_bn, stack);
         case 'E':
          if (nargs == 1){
            if (comando[1]==49)
                cmd_E1 (est_bn, stack);
            else if (comando[1]==50)
                cmd_E2 (est_bn, stack);
                else if (comando[1]==51)
                    cmd_E3 (est_bn, stack);
          }
         break; 
        default:
         return -1; 
    }
    return -1;
}

/** 
O interpretador encarrega-se de criar um ciclo até que o comando seja igual NULL
*/
void interpretador (ESTRUTURA_BN *est_bn, stck *stack) {
    int resultado = 0;
    char buffer [MAX_SIZE];
    int ciclo = 1;

    while (ciclo && fgets(buffer, MAX_SIZE, stdin) != NULL){
        resultado = interpretar (est_bn, buffer, stack);
        if (resultado == 0) 
        ciclo = 0;
    }
}

int main () {
    stck stack;
    ESTRUTURA_BN est_bn;
    interpretador (&est_bn,&stack);
    return 0;
  }