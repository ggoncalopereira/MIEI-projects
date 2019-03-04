package Sistema.Gestao.Despesas.UI;

import java.util.*;
import java.io.*;

/**A class Menu guarda o texto do menu, um BufferedReader de onde ler input e
 * uma lista de métodos associados a cada escolha no menu.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class Menu
{
    // instance variables - replace the example below with your own
    private ArrayList<MenuFunctions> calls;
    private StringBuilder sb;
    private BufferedReader bf;
    private int choices;

    /**
     * Método que lê uma string não vazia.
     */
    public static String readString(BufferedReader readIM)
        throws IOException
    {
        String temp="";
        temp=readIM.readLine();
        while (temp==null) {
            System.out.println("Digite algo");
            temp=readIM.readLine();
        }
        return temp;
    }
    
    public static int[] readCSVInts(BufferedReader bf) throws IOException
    {
        
        String temp="";
        temp=bf.readLine();
        boolean quit=false;
        String Temps[];
        int res[]=null;
        while(!quit) 
        {
            while (temp==null) 
            {
                System.out.println("Digite algo");
                temp=bf.readLine();
            }
            if(!temp.equals("@quit"))
            {
                Temps=temp.trim().split(",");
                res= new int[Temps.length];
                try 
                {
                    for(int i=0;i<Temps.length;i++)
                    {
                        res[i]=Integer.parseInt(Temps[i]);
                    }
                    quit=true;
                }
                catch (NumberFormatException e) 
                {
                    System.out.println("Digite números separados por vírgulas");
                }
            }
            else
            {
                quit=true;
            }
        }
        return res;
    }
    
    
    /**
     * Método que lê um inteiro positivo.
     */
    public static int readPosInt(BufferedReader bf)
        throws IOException
    {
        int x=0,quit=1;
        while (quit>0) {
            try {
                x=Integer.parseInt(bf.readLine());
                if(x>=0)
                    quit--;
                else {
                    System.out.println("Número Inválido");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Número Inválido");
            }
        }
        return x;
    }
    
    public static Float readPosFloat(BufferedReader bf)
            throws IOException
    {
        int quit=1;
        float x=0;
        while (quit>0) {
            try {
                x=Float.parseFloat(bf.readLine());
                if(x>=0)
                    quit--;
                else {
                    System.out.println("Número Inválido");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Número Inválido");
            }
        }
        return x;
    }

    
    /**
     * Construtor para objetos da classe Menu
     */
    public Menu(StringBuilder sb1,BufferedReader bf1)
    {
        sb=sb1;
        calls= new ArrayList<>();
        bf=bf1;
        choices=0;
    }

    /**
     * Adiciona um método às escolhas possíveis do menu
     * 
     * @param  r   é uma função do menu
     */
    public void addChoice(MenuFunctions m)
    {
        calls.add(m);
        choices++;
    }
    
    /**
     * Corre o menu, fica a ler inteiros para escolher algo no menu, 0 sai do menu.
     * 
     * Uma escolha implica invocar o método correspondente.
     */
    public void run() 
        throws IOException
    {
        int x=-1,quit=1;
        MenuFunctions m;
        Procedure p;
        InputProcedure i;
        while(quit>0) {
            System.out.println(sb.toString());
            try {
                x=Menu.readPosInt(bf);
            }
            catch (IOException e) {
                quit--;
            }
            if (x>0 && x<=choices) {
                m=calls.get(x-1);
                if (m instanceof Procedure) {
                    p=(Procedure) m;
                    p.invoke();
                }
                else { 
                    i=(InputProcedure) m;
                    i.accept(bf);
                }
            }
            else if (x==0) {
                quit--;
            }
            else {
                System.out.println("Escolha Inválida");
            }
        }
    }
    
}
