package Sistema.Gestao.Despesas.UI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * Pager é uma classe que faz paginação e formatação de coleções automaticamente.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class Pager
{
    private ArrayList<String> pages;
    private BufferedReader bf;
    private static String menuGen=
                "q - Sair\nn - Próxima página\nb - Página anterior";
    private static Pattern p=Pattern.compile("\n");
    private String menu;
    private String header;
    private int index;

    /**
     * Construtor que constroi páginas por tabelas para coleções
     * sem newlines nem cabeçalhos.  
     */
    public Pager(Collection<?> t,int lines, int columns,BufferedReader buf)
        throws TamanhoPaginaInvalidoException
    {
        int i=lines*columns,j=0;
        index=0;
        if (lines<=0 || columns<=0 || t==null || t.size()==0)
            throw new TamanhoPaginaInvalidoException();
        bf=buf;
        pages= new ArrayList<>(t.size()/(lines*columns));
        ArrayList<StringBuilder> l = new ArrayList<>(t.size()/lines);        
        Iterator<?> it= t.iterator();
        StringBuilder sb=new StringBuilder();
        String s;
        header=null;
        while(it.hasNext()) {
            if (i==0) {
                for(StringBuilder sbd:l)
                    sb.append(sbd.toString()+"\n");
                pages.add(sb.toString());
                i=lines*columns;
                l=new ArrayList<>(t.size()/lines);
                sb=new StringBuilder();
            }
            else if (j==lines) {
                j=0;
            }
            else {
                s=it.next().toString();
                if(l.size()<=j+1)
                    l.add(new StringBuilder());    
                l.get(j).append("   "+ s);
                i--;
                j++;
            }
        }
        if (j>0 || i>0) {
            for(StringBuilder sbd:l)
                sb.append(sbd.toString()+"\n");
            pages.add(sb.toString());                
        }
        sb=new StringBuilder();
        sb.append(pages.size()).append(" páginas\n");
        sb.append("1-").append(pages.size());
        sb.append(" - Navegar para essa página\n");
        sb.append(Pager.menuGen);
        menu=sb.toString();
    }
    
    /**
     * Construtor que constroi páginas por tabelas para coleções de coleções
     * que requerem cabeçalhos. 
     * 
     * Funde as coleções exteriores string a string
     * pelos newlines.
     */
    public Pager(Collection<? extends Collection<?>> t,int lines,BufferedReader buf)
        throws TamanhoPaginaInvalidoException
    {
        int i=0,j=0,k=0,l,m=0,n=0;
        boolean quit=true;
        index=0;
        if (lines<=0 || t==null || t.isEmpty())
            throw new TamanhoPaginaInvalidoException();
        /* Há alguma colleção dentro vazia? 
         * Aproveita e calcula o máximo de tamanho entre elas*/
        for(Collection<?> a:t) {
            if (a==null || a.isEmpty())
                throw new TamanhoPaginaInvalidoException();
            if(a.size()>j)
                j=a.size();
        }
        bf=buf;
        pages= new ArrayList<>(j/lines);
        /*Constroi iteradores sobre todas as coleções interiores*/
        ArrayList<Iterator<?>> its= new ArrayList<>(t.size());
        for(Collection<?> a:t)
            its.add(a.iterator());
        StringBuilder sb=new StringBuilder();
        String s;
        header=null;
        ArrayList<String[]> strs=null;
        while(quit) {
            /* Já preencheu uma página?*/
            if(k==lines){
                k=0;
                pages.add(sb.toString());
                sb=new StringBuilder();                
            }
            /* Já chegou ao fim? 
             * fim=máximo de tamanho entre as coleções exteriores*/
            else if (i==j) {
                if (k!=0) {
                    pages.add(sb.toString());
                    sb=new StringBuilder();
                }
                quit=false;
            }
            else {
                strs= new ArrayList<>(t.size());        
                for(Iterator<?> a:its) {
                    if (a.hasNext()) {
                        s=a.next().toString();
                        if (s.contains("\n")) {
                            strs.add(p.split(s,0));
                        }
                        else {
                            sb.append("    ");
                            sb.append(a.next().toString());
                        }
                    }
                }
                /*Se havia newlines é preciso fundir cada elemento linha a linha*/
                if (strs!=null && !strs.isEmpty()) {
                    l=0;
                    /*Descobrir entre os toStrings dos elementos qual tinha mais linhas
                     *Aproveitar e descobrir qual a maior string 
                     *para formatar por colunas */
                    for(String[] a:strs) {
                        if (a.length>m)
                            m=a.length;
                        for(String b:a)
                            if(b.length()>n)
                                n=b.length();
                    }
                    /* Para cada indice do array fundir as string desse indice*/
                    while (l<m) {
                        for(String[] a:strs) {
                            if (l<a.length) {
                                for(int index=n-a[l].length()+4;index>0;index--)
                                    sb.append(' ');
                                sb.append(a[l]);
                            }
                        }
                        sb.append("\n");
                        l++;
                    }
                    sb.append("\n");
                }
                else
                    sb.append("\n");
                i++;
                k++;
            }
        }
        sb=new StringBuilder();
        sb.append(pages.size()).append(" páginas\n");
        sb.append("1-").append(pages.size());
        sb.append(" - Navegar para essa página\n");
        sb.append(Pager.menuGen);
        menu=sb.toString();
    }
    
    /**
     * Aplica a string dada como cabeçalho de todas as páginas.
     */
    public void setHeader(String s) {
        header=s;
    }

    /**
     * Consulta a próxima página até chegar ao fim das páginas.
     */
    private String nextPage() {
        if (index==pages.size())
            return null;
        else {
            index++;
            return pages.get(index-1);
        }
    }

    /**
     * Consulta a página anterior até chegar ao fim das páginas.
     */
    private String previousPage() {
        if (index<=1)
            return null;
        else {
            index--;
            return pages.get(index-1);
        }
    }
    
    /**
     * Consulta a página dada.
     */
    private String getPage(int page) {
        index=page;
        return pages.get(index-1);
    }
    
    /**
     * Corre um menu de consulta das páginas.
     */
    public void run() {
        boolean quit=true;
        int page;
        String s;
        System.out.println(menu);
        try {
            while(quit) {
                s=bf.readLine();
                if (s.equals("n")) {
                    s=nextPage();
                    if (s!=null)
                        if (header==null)
                            System.out.print(s);
                        else
                            System.out.print(header+s);
                }
                else if (s.equals("b")) {
                    s=previousPage();
                    if (s!=null)
                        if (header==null)
                            System.out.print(s);
                        else
                            System.out.print(header+s);
                }
                else if (s.equals("q"))
                    quit=false;
                else 
                    try {
                        if ((page=Integer.parseInt(s))>=1 && page<=pages.size())
                            if (header==null)
                                System.out.print(getPage(page));
                            else
                                System.out.print(header+getPage(page));
                    }
                    catch(NumberFormatException | NullPointerException e){}
                if (quit)
                    System.out.println(menu);
            }
        }
        catch(IOException e){System.out.println(e.getMessage());}
    }
}
