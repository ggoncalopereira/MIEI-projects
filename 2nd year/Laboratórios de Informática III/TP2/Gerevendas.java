
/**
 * Classe que trata de todo o Input/Output, estabelece unicamente menus e métodos de impressão.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.util.*;
import java.io.*;
import java.util.function.*;
public class Gerevendas
{
    private static Hipermercado hp;

    /**
     * Lê catálogos de ficheiros dados pelo utilizador.
     */
    private static void lerCatalogos(BufferedReader bf)
        throws IOException
    {
        if (hp==null)
            hp=new Hipermercado();
        try{
            System.out.println("Digite o caminho para o Catálogo de Clientes");
            hp.readCatalogoCli(Menu.readString(bf));
            System.out.println("Digite o caminho para o Catálogo de Produtos");
            hp.readCatalogoProd(Menu.readString(bf));
            if (!hp.isEmpty())
                hp.resetVendas();
        }
        catch(CatalogoVazioException e){
            System.out.println(e.message());
            hp.reset();        
        }
    }
    
    /**
     * Lê os catálogos predefinidos.
     */
    private static void lerPredefCat(){
        if (hp==null)
            hp=new Hipermercado();
        try{
            hp.readCatalogoCli("./Clientes.txt");
            hp.readCatalogoProd("./Produtos.txt");
            if (!hp.isEmpty())
                hp.resetVendas();
        }
        catch(CatalogoVazioException e){
            System.out.println(e.message());
            hp.reset();
        }
    }
    
    /**
     * Lê um ficheiro de vendas dado pelo utilizador.
     */
    private static void lerVendas(BufferedReader bf)
        throws IOException
    {
        if (!hp.isEmpty())
            hp.resetVendas();
        System.out.println("Digite o caminho para o ficheiro de Vendas");
        hp.readVendasWithBuff(Menu.readString(bf));
    }

    
    /**
     * Lê o ficheiro de 1 milhão de vendas.
     */
    private static void lerVendas1M(){
        if (!hp.isEmpty())
            hp.resetVendas();
        hp.readVendasWithBuff("./Vendas_1M.txt");
    }

    /**
     * Lê o ficheiro de 3 milhão de vendas.
     */
    private static void lerVendas3M(){
        if (!hp.isEmpty())
            hp.resetVendas();
        hp.readVendasWithBuff("./Vendas_3M.txt");
    }
    
    /**
     * Lê o ficheiro de 5 milhão de vendas.
     */    
    private static void lerVendas5M(){
        if (!hp.isEmpty())
            hp.resetVendas();
        hp.readVendasWithBuff("./Vendas_5M.txt");
    }
    
    /**
     * Cria e corre um menu de leitura de catálgos.
     */
    private static void lerCatalogosMenu(BufferedReader bf)
        throws IOException
    {
        StringBuilder sb=new StringBuilder();
        sb.append("0 - Sair\n");
        sb.append("1 - Ler catálogos de ficheiro\n");
        sb.append("2 - Ler predefinições(Clientes.txt,Produtos.txt)\n");
        Menu m= new Menu(sb,bf);
        m.addChoice((InputProcedure)Gerevendas::lerCatalogos);
        m.addChoice((Procedure)Gerevendas::lerPredefCat);
        m.run();
    }

    
    /**
     * Cria e corre um menu de leitura de ficheiros de vendas.
     */
    private static void lerVendasMenu(BufferedReader bf) 
            throws IOException
    {
        StringBuilder sb=new StringBuilder();
        sb.append("0 - Sair\n");
        sb.append("1 - Ler vendas de ficheiro\n");
        sb.append("2 - Ler predefinição (Vendas_1M.txt)\n");
        sb.append("3 - Ler predefinição (Vendas_3M.txt)\n");
        sb.append("4 - Ler predefinição (Vendas_5M.txt)\n");
        Menu m= new Menu(sb,bf);
        m.addChoice((InputProcedure)Gerevendas::lerVendas);
        m.addChoice((Procedure)Gerevendas::lerVendas1M);
        m.addChoice((Procedure)Gerevendas::lerVendas3M);
        m.addChoice((Procedure)Gerevendas::lerVendas5M);
        m.run();
    }
    
    /**
     * Lê imediatamente as predefinições mais simples.
     */
    private static void lerPredef() 
    {
        if(hp==null)
            hp= new Hipermercado();
        else
            hp.reset();
        try {
            hp.readCatalogoCli("./Clientes.txt");
            hp.readCatalogoProd("./Produtos.txt");
            hp.readVendasWithBuff("./Vendas_1M.txt");
        }
        catch(CatalogoVazioException e){
            System.out.println(e.message());
            hp.reset();
        }
    }
    
    /**
     * Imprime informações do último ficheiro lido.
     */
    private static void consultaEstatistica1(){
        try {
            System.out.println(hp.getInfo().toString());
        }
        catch(HipermercadoNaoInicializadoException e){
            System.out.println(e.message());
        }
    }
    
    /**
     * Imprime informações das estruturas do hipermercado atual.
     */
    private static void consultaEstatistica2(){
        try {
            System.out.println(hp.getInfoF().toString());
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }
    }

    /**
     * Imprime o resultado da 1ª consulta.
     */
    private static void consultaInter1(BufferedReader bf){
        try{
            Pager pg= new Pager(hp.query1(),40,6,bf);
            pg.run();
        }
        catch(TamanhoPaginaInvalidoException e){
            System.out.println(e.message());
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }
    }

    /**
     * Imprime o resultado da 2ª consulta.
     */    
    private static void consultaInter2(BufferedReader bf){
        try{
            Collection<Collection<String>> a= new ArrayList<>();
            a.add(hp.query1());
            a.add(hp.query1());
            a.add(hp.query1());
            a.add(hp.query1());
            Pager pg= new Pager(a,40,bf);
            pg.setHeader("    Filial1    Filial2    Filial3    Total\n");
            pg.run();
        }
        catch(TamanhoPaginaInvalidoException e){
            System.out.println(e.message());
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }
    }

    /**
     * Imprime o resultado da 3ª consulta.
     */    
    private static void consultaInter3(BufferedReader bf){
        try{
            Collection<Collection<ParProdutoQVenda>> a= new ArrayList<>();
            a.add(hp.test());
            a.add(hp.test());
            a.add(hp.test());
            a.add(hp.test());
            Pager pg= new Pager(a,40,bf);
            pg.setHeader("    Filial1    Filial2    Filial3    Total\n");
            pg.run();
        }
        catch(TamanhoPaginaInvalidoException e){
            System.out.println(e.message());
        }
    }

    /**
     * Imprime o resultado da 4ª consulta.
     */    
    private static void consultaInter4(){
        try {
            System.out.println(hp.getInfoF().toString());
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }
    }

    /**
     * Imprime o resultado da 5ª consulta.
     */    
    private static void consultaInter5(){
        try {
            System.out.println(hp.getInfoF().toString());
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }
    }

    /**
     * Imprime o resultado da 6ª consulta.
     */    
    private static void consultaInter6(BufferedReader bf)
                throws IOException
    {
        int s=Menu.readPosInt(bf);
        try{
            Collection<Collection<ParProdutoQVenda>> p=new ArrayList<>(1);
            Collection<ParProdutoQVenda> t=hp.query6(s);
            p.add(t);
            Pager pg= new Pager(p,40,bf);
            pg.run();
        }
        catch(TamanhoPaginaInvalidoException e){
            System.out.println(e.message());
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }        
    }

    /**
     * Imprime o resultado da 7ª consulta.
     */    
    private static void consultaInter7(BufferedReader bf)
                throws IOException
    {
        try{
            Pager pg= new Pager(hp.query7(),40,bf);
            pg.setHeader("                   Filial1                          Filial2                          Filial3\n");
            pg.run();
        }
        catch(HipermercadoNaoInicializadoException e) {
                System.out.println(e.message());
        }
        catch(TamanhoPaginaInvalidoException e){
                System.out.println(e.message());
        }
    }
    
    /**
     * Imprime o resultado da 8ª consulta.
     */    
    private static void consultaInter8(BufferedReader bf)
                throws IOException
    {
        int s=Menu.readPosInt(bf);
        try{
            Collection<Collection<ParClienteProdutosC>> p=new ArrayList<>(1);
            p.add(hp.query8(s));            
            Pager pg= new Pager(hp.query8(s),40,1,bf);
            pg.run();
        }
        catch(TamanhoPaginaInvalidoException e){
            System.out.println(e.message());
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }
    }

    /**
     * Imprime o resultado da 9ª consulta.
     */    
    private static void consultaInter9(BufferedReader bf)
            throws IOException
    {
        int i=Menu.readPosInt(bf);
        String s = Menu.readString(bf);
        try {
            Collection<Collection<ParClienteCompra>> p = new ArrayList<>(1);
            p.add(hp.query9(s, i));
            Pager pg = new Pager(p, 40, bf);
            pg.setHeader("                   Clientes que mais compraram o produto " + s + "\n");
            pg.run();
        }
        catch(HipermercadoNaoInicializadoException e) {
            System.out.println(e.message());
        }
        catch(ProdutoNaoExisteException e){
            System.out.println(e.message());
        }
        catch(TamanhoPaginaInvalidoException e){
            System.out.println(e.message());
        }
    }

    /**
     * Cria e corre um menu das 2 consultas estatísticas.
     */
    private static void consultasEstat(BufferedReader bf) 
            throws IOException
    {
        StringBuilder sb= new StringBuilder();
        sb.append("0 - Sair\n");
        sb.append("1 - Consultar informações do último ficheiro lido\n");
        if (!hp.isEmpty())
            sb.append("2 - Consultar informações de hipermercado\n");
        Menu m= new Menu(sb,bf);
        m.addChoice((Procedure)Gerevendas::consultaEstatistica1);
        if (!hp.isEmpty())
            m.addChoice((Procedure)Gerevendas::consultaEstatistica2);
        m.run();
    }

    /**
     * Cria e corre um menu das consultas interativas.
     */    
    private static void consultasInter(BufferedReader bf) 
            throws IOException
    {
        if (!hp.isEmpty()) {
            StringBuilder sb= new StringBuilder();
            sb.append("0 - Sair\n");
            sb.append("1 - Lista ordenada de produtos nunca comprados\n");
            sb.append("2 - Número total global de vendas realizadas num mês\n");
            sb.append("3 - Determinar compras efetuadas por um cliente\n");
            sb.append("4 - Determinar compras de um produto\n");
            sb.append("5 - Lista de produtos mais comprados por um cliente\n");
            sb.append("6 - Determinar o conjunto dos X produtos mais vendidos");       
            sb.append(" em todo o ano\n");       
            sb.append("7 - Determinar a lista dos três maiores compradores");        
            sb.append(" em cada filial\n");        
            sb.append("8 - Determinar os X clientes que compraram mais produtos");        
            sb.append(" diferentes\n");       
            sb.append("9 - Determinar o conjunto dos X clientes que mais");        
            sb.append(" compraram um produto dado\n");        
            Menu m= new Menu(sb,bf);        
            m.addChoice((InputProcedure)Gerevendas::consultaInter1);        
            m.addChoice((InputProcedure)Gerevendas::consultaInter2);        
            m.addChoice((InputProcedure)Gerevendas::consultaInter3);       
            m.addChoice((Procedure)Gerevendas::consultaInter4);        
            m.addChoice((Procedure)Gerevendas::consultaInter5);        
            m.addChoice((InputProcedure)Gerevendas::consultaInter6);        
            m.addChoice((InputProcedure)Gerevendas::consultaInter7);       
            m.addChoice((InputProcedure)Gerevendas::consultaInter8);        
            m.addChoice((InputProcedure)Gerevendas::consultaInter9);        
            m.run();
        }
    }    
    
    /**
     * Cria e corre o menu de leitura de ficheiros que chama os menus individuais
     * de leitura de cada ficheiro.
     */
    private static void lerFicheirosMenu(BufferedReader bf) 
            throws IOException
    {
        StringBuilder sb= new StringBuilder();
        sb.append("0 - Sair\n");
        sb.append("1 - Ler catálogos\n");
        sb.append("2 - Ler vendas\n");
        sb.append("3 - Ler predefinições(Clientes.txt,Produtos.txt,Vendas_1M.txt)\n");
        Menu m= new Menu(sb,bf);
        m.addChoice((InputProcedure)Gerevendas::lerCatalogosMenu);
        m.addChoice((InputProcedure)Gerevendas::lerVendasMenu);
        m.addChoice((Procedure)Gerevendas::lerPredef);
        m.run();
    }
    
    /**
     * Lê o ficheiro predefinido de hipermercado.
     * 
     * Se falhar a leitura preserva o hipermercado atual.
     */
    private static void atualizaEstadoDef(){
        Hipermercado h=Hipermercado.leFicheiro("hipermercado.dat");
        if(h!=null)
            hp=h;
    }

    /**
     * Lê de um ficheiro dado pelo utilizador um hipermercado.
     * 
     * Se falhar a leitura preserva o hipermercado atual.
     */    
    private static void atualizaEstadoFich(BufferedReader bf) 
            throws IOException
    {
        Hipermercado h=Hipermercado.leFicheiro(Menu.readString(bf));
        if(h!=null)
            hp=h;
    }

    /**
     * Cria e corre um menu de leitura de hipermercados em ficheiro.
     */
    private static void atualizaEstadoMenu(BufferedReader bf) 
            throws IOException
    {
        StringBuilder sb= new StringBuilder();
        sb.append("0 - Sair\n");
        sb.append("1 - Ler de ficheiro definição(hipermercado.dat)\n");
        sb.append("2 - Ler de outro ficheiro\n");
        Menu m= new Menu(sb,bf);
        m.addChoice((Procedure)Gerevendas::atualizaEstadoDef);
        m.addChoice((InputProcedure)Gerevendas::atualizaEstadoFich);
        m.run();
    }

    
    /**
     * Cria e corre um menu de consultas que chama
     * os menus de consultas específicas.
     * 
     */    
    private static void consultasMenu(BufferedReader bf) 
            throws IOException
    {
        if (hp==null || !hp.haFicheiroAnterior())
                System.out.println("Leia um ficheiro de Vendas");
        else {
            StringBuilder sb= new StringBuilder();
            sb.append("0 - Sair\n");
            sb.append("1 - Consultas Estatisticas\n");
            if(!hp.isEmpty())
                sb.append("2 - Consultas Interativas\n");
            Menu m=new Menu(sb,bf);
            m.addChoice((InputProcedure)Gerevendas::consultasEstat);
            if(!hp.isEmpty())
                m.addChoice((InputProcedure)Gerevendas::consultasInter);
            m.run();
        }
    }

    /**
     * Grava o hipermercado atual para um ficheiro dado pelo utilizador.
     */
    private static void gravaEstadoFich(BufferedReader bf)
        throws IOException
    {
        System.out.println("Digite o caminho para o ficheiro de Vendas");
        Hipermercado.gravaFicheiro(Menu.readString(bf),hp);
    }

    /**
     * Grava o hipermercado atual para o ficheiro predefinido "hipermercado.dat".
     */    
    private static void gravaEstadoDef(){
        Hipermercado.gravaFicheiro("hipermercado.dat",hp);
    }

    /**
     * Cria e corre um menu de gravação de hipermercados em ficheiro.
     */        
    private static void gravaEstadoMenu(BufferedReader bf)
        throws IOException
    {
        if (hp==null || !hp.isEmpty())
            System.out.println("Leia um ficheiro de Vendas");
        else {
            StringBuilder sb= new StringBuilder();
            sb.append("0 - Sair\n");
            sb.append("1 - Gravar para ficheiro\n");
            sb.append("2 - Gravar para predefinição(hipermercado.dat)\n");
            Menu m=new Menu(sb,bf);
            m.addChoice((InputProcedure)Gerevendas::gravaEstadoFich);
            m.addChoice((Procedure)Gerevendas::gravaEstadoDef);
            m.run();
        }    
    }
    
    /**
     * Cria e corre o menu principal.
     */
    public static void main(String[] args)
    {
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        sb.append("0 - Sair\n");
        sb.append("1 - Ler ficheiros\n");
        sb.append("2 - Abrir hipermercado existente\n");
        sb.append("3 - Gerir o hipermercado\n");
        sb.append("4 - Gravar o hipermercado");
        hp=null;
        Menu m= new Menu(sb,bf);
        m.addChoice((InputProcedure)Gerevendas::lerFicheirosMenu);
        m.addChoice((InputProcedure)Gerevendas::atualizaEstadoMenu);
        m.addChoice((InputProcedure)Gerevendas::consultasMenu);
        m.addChoice((InputProcedure)Gerevendas::gravaEstadoMenu);
        try {
            m.run();
        }catch (IOException e) {System.out.println(e.getMessage());}
    }
}
