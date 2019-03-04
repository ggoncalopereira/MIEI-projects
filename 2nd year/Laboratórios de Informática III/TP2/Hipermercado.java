
/**
 * Classe contém informações do último ficheiro de venda lido, um catálogo de produtos, um de clientes,
 * uma facturação, e um array de 3 filiais.
 * 
 * Trata de toda a lógica de resposta a pedidos de informação.
 * 
 * Tratar todas as exceções aqui.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.TextStyle;
public class Hipermercado implements Serializable
{
    // instance variables - replace the example below with your own
    private CatalogoClientes catcli;
    private CatalogoProdutos catprods;
    private Facturacao fact;
    private InfoFicheiro ultvendas;
    private Filial filiais[];

    /**
     * Lê de um ficheiro fich um hipermercado gravado em binário.
     */
    public static Hipermercado leFicheiro(String fich) {    
        ObjectInputStream ois=null;
        Hipermercado hp;
        try {
            ois= new ObjectInputStream(new FileInputStream
                                            (fich));
            hp=(Hipermercado)ois.readObject();
            ois.close();
        }
        catch(IOException e) { 
            System.out.println("Gravação Inexistente: " + e.getMessage());
            hp=null;
        }
        catch (ClassNotFoundException f) {
            System.out.println("Gravação Corrompida");
            hp=null;
        }
        return hp;
    }
    
    /**
     * Grava um hipermercado para ficheiro fich em binário.
     */
    public static void gravaFicheiro(String fich,Hipermercado hp)
    {
        ObjectOutputStream oos=null;
        try{
            oos= new ObjectOutputStream
                                    (new FileOutputStream
                                        (fich));
            oos.writeObject(hp);
            oos.flush();
            oos.close();
        }
        catch(IOException e) {
            System.out.println("Falha a escrever ficheiro: " + e.getMessage());
        }
    }
    
    
    /**
     * Construtor da classe Hipermercado.
     */
    public Hipermercado()
    {
        int i;
        catcli= new CatalogoClientes();
        catprods= new CatalogoProdutos();
        fact= new Facturacao();
        filiais= new Filial[3];
        ultvendas=null;
        for(i=0;i<3;i++)
            filiais[i]=new Filial();
    }
    
    /**
     * Devolve um boolean que informa se o hipermercado tem
     * informações de facturação ou não.
     */
    public boolean isEmpty(){
        return fact.isEmpty();
    }
    
    public boolean haFicheiroAnterior(){
        return ultvendas!=null;
    }
    
    /**
     * Devolve as informações do último ficheiro lido.
     */
    public String getInfo()
        throws HipermercadoNaoInicializadoException
    {
        if (ultvendas==null)
            throw new HipermercadoNaoInicializadoException();
        return ultvendas.toString();
    }

    /**
     * Devolve as informações deste hipermercado
     */
    public String getInfoF()
        throws HipermercadoNaoInicializadoException
    {
        if (isEmpty())
            throw new HipermercadoNaoInicializadoException();
        int i,j;
        String s;
        ArrayList<String> strs= new ArrayList<>(13);
        strs.add("");
        Locale l=new Locale("pt","PT");
        TextStyle f=TextStyle.FULL;
        for(i=1;i<13;i++)
            strs.add(Month.of(i).getDisplayName(f,l));
        StringBuilder sb=new StringBuilder();        
        sb.append("Vendas:\n"); 
        for(i=1;i<13;i++) {
            s=strs.get(i);
            sb.append(s);
            for(j=s.length();j<9;j++,sb.append(' '));
            sb.append(": ").append(fact.getTotalVendas(i)).append('\n');
        }
        sb.append("\nFacturação:\n");
        sb.append("          Filial 1     Filial 2     Filial 3     Total\n");
        for(i=1;i<13;i++) {
            s=strs.get(i);
            sb.append(s);
            for(j=s.length();j<9;j++,sb.append(' '));
            sb.append(": ")
              .append(String.format("%.4E    %.4E    %.4E   %.4E\n",
                                    fact.getTotalFacturado(i,1),
                                    fact.getTotalFacturado(i,2),
                                    fact.getTotalFacturado(i,3),
                                    fact.getTotalFacturado(i)));
        }
        sb.append("\nClientes distintos:\n");
        TreeSet<String> t;
        for(i=1;i<13;i++) {        
            s=strs.get(i);
            sb.append(s);
            for(j=s.length();j<9;j++,sb.append(' '));
            t=filiais[0].getClientes(i);                
            t.addAll(filiais[1].getClientes(i));             
            t.addAll(filiais[2].getClientes(i));            
            sb.append(": ").append(t.size()).append('\n');           
        }
        return sb.toString();
    }
    
    /**
     * Reinicia o hipermercado completo, caso seja necessário reler todos os ficheiros.
     */
    public void reset(){
        int i;
        catcli= new CatalogoClientes();
        catprods= new CatalogoProdutos();
        fact= new Facturacao();
        filiais= new Filial[3];
        for(i=0;i<3;i++)
            filiais[i]=new Filial();
    }
    
    /**
     * Reinicia as informações relativas às vendas, caso os catálogos mudem.
     */
    public void resetVendas() {
        int i;
        fact= new Facturacao();
        filiais= new Filial[3];
        for(i=0;i<3;i++)
            filiais[i]=new Filial();
    }
    
    /**
     * Lê um catálogo de Clientes, faz verificação sintática dos clientes.
     */
    public void readCatalogoCli(String fich)
        throws CatalogoVazioException    
    {
        BufferedReader inStream = null; 
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null )
                catcli.adiciona(linha.trim());
        }
        catch(IOException e) { 
            System.out.println(e.getMessage()); 
        }
        if (catcli.getTotalClientes()==0)
            throw new CatalogoVazioException();
    }

    /**
     * Lê um catálogo de Produtos, faz verificação sintática dos produtos.
     */
    public void readCatalogoProd(String fich)
        throws CatalogoVazioException
    {
        BufferedReader inStream = null; 
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null )
                catprods.adiciona(linha.trim());
        }
        catch(IOException e) { 
            System.out.println(e.getMessage()); 
        }
        if (catprods.getTotalProdutos()==0)
            throw new CatalogoVazioException();
    }

    /**
     * Lê o ficheiro de vendas, linha a linha, faz o parse e adiciona a venda às filiais
     * e facturação.
     * 
     * Coleciona informações sobre o ficheiro para colocar no InfoFicheiro.
     */
    public void readVendasWithBuff(String fich) {
        BufferedReader inStream = null; 
        String linha = null;
        Venda v = null;
        Crono tmp=new Crono();
        int count=0;
        double t1,t2;
        t1=t2=0.0;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            ultvendas= new InfoFicheiro(fich);
            while( (linha = inStream.readLine()) != null ) {
                tmp.start();
                v=parseLinhaVenda(linha);
                t1+=tmp.stop();
                if (v!=null) {
                    if (v.getFact()==0.0)
                        count++;
                    tmp.start();
                    int mes,filial,quant;
                    mes=v.getMes();
                    filial=v.getFilial();
                    quant=v.getQuant();
                    double preco=v.getPreco();
                    String cliente,produto;
                    produto=v.getProduto();
                    fact.adiciona(mes,filial,quant,preco,produto);
                    cliente=v.getCliente();
                    filiais[filial].adiciona(mes,quant,preco,cliente,produto);
                    t2+=tmp.stop();
                }
                else
                    ultvendas.atualiza();
            }
            int x,y,a,b;
            double f;
            TreeSet<String> t=new TreeSet<>();
            tmp.start();
            x=fact.getTotalProdutos();
            y=catprods.getTotalProdutos()-x;
            f=fact.getTotalFacturado();
            for(a=0;a<3;a++){
                t.addAll(filiais[a].getClientes());
            }
            a=t.size();
            b=catcli.getTotalClientes()-a;
            System.out.println("fact: " + tmp.print() + "\nparse: " + t1 + "\nadds: " + t2);
            ultvendas.atualiza(x,x,y,a,a,b,count,f);
        }
        catch(IOException e) { 
            System.out.println(e.getMessage());
            this.resetVendas();
            ultvendas=null;
        }  
    }

    /**
     * Efetua o parsing duma linha de vendas.
     */
    private Venda parseLinhaVenda(String linha) { 
        String pro;
        double pr;
        boolean promo;
        int quant,mes,filial,size;
        String sp[]=linha.split(" ");
        if (sp.length!=7)
            return null;
        if (!catprods.existeProduto(sp[0]))
            return null;
        try {
            pr=Double.parseDouble(sp[1].trim());
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        try {
            quant=Integer.parseInt(sp[2].trim());
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        pro=sp[3].trim();
        if (pro.equals("P"))
            promo=true;
        else if (pro.equals("N"))
            promo=false;
        else
            return null;
        if (!catcli.existeCliente(sp[4]))
            return null;
        try {
            mes=Integer.parseInt(sp[5].trim())-1;
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        try {
            filial=Integer.parseInt(sp[6])-1;
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        return new Venda(quant,mes,filial,pr,promo,sp[4].trim(),sp[0].trim());
    }
    
    /**
     * Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o
     * seu respectivo total.
     */
    
    public Collection<String> query1()
        throws HipermercadoNaoInicializadoException
    {
        if (isEmpty())
            throw new HipermercadoNaoInicializadoException();
        TreeSet<String> t=catprods.getCatalogo();
        TreeSet<String> n= new TreeSet<>();
        for(String produto:t)
            if (!fact.noMapeamento(produto))
                n.add(produto);
        return n;
    }

    public Collection<ParProdutoQVenda> test(){
        int fake=0;
        TreeSet<String> t=catprods.getCatalogo();
        TreeSet<ParProdutoQVenda> n= new TreeSet<>(new ParProdutoQVendaPorQuant());
        for(String produto:t)
            if (!fact.noMapeamento(produto)) {
                n.add(new ParProdutoQVenda(produto,fake));
                fake+=5;
            }
        return n;
    }
    
    /**
     * Determina o conjunto dos X produtos mais vendidos em todo o ano (em número de
     * unidades vendidas), indicando o número total de distintos clientes que o
     * compraram (X é um inteiro dado pelo utilizador).
     */
    
    public Collection<ParProdutoQVenda> query6(int produtos)
        throws HipermercadoNaoInicializadoException
    {
        if (isEmpty())
            throw new HipermercadoNaoInicializadoException();
        Set<String> clientesCompraramProduto;
        Set<ParProdutoQVenda> prodMaisVend = fact.produtosMaisVendidos();
        Iterator<ParProdutoQVenda> it = prodMaisVend.iterator();
        Set<ParProdutoQVenda> firstP= new TreeSet<>(new ParProdutoQVendaPorQuant());
        String produto;
        int i;
        
        while(it.hasNext() && (produtos !=0)){
            ParProdutoQVenda item = it.next();
            produto=item.getProduto();
            clientesCompraramProduto=filiais[0].getClientes(produto);
            clientesCompraramProduto.addAll(filiais[1].getClientes(produto));
            clientesCompraramProduto.addAll(filiais[2].getClientes(produto));
            item.setClientes(clientesCompraramProduto.size());
            firstP.add(item);
            produtos--;
        }
                
        return firstP;
    }
    
    /**
     * Determina, para cada filial, a lista dos três maiores compradores em termos de
     * dinheiro facturado.
     */
    
    public Collection<Set<ParClienteFaturacao>> query7()
        throws HipermercadoNaoInicializadoException
    {
        if (isEmpty())
            throw new HipermercadoNaoInicializadoException();
        Set<ParClienteFaturacao> maisFaturadoFilial = new TreeSet<ParClienteFaturacao>(new ParClienteFaturacaoPorFaturacao());

        List<Set<ParClienteFaturacao>> maisFaturadoTotal = new ArrayList<Set<ParClienteFaturacao>>(3);
        int count, i=3;
        for(count = 0; count < 3; count++){
            maisFaturadoFilial = filiais[count].getClientesMaisCompradores();
            Iterator<ParClienteFaturacao> it = maisFaturadoFilial.iterator();
            Set<ParClienteFaturacao> tresMais = new TreeSet<ParClienteFaturacao>(new ParClienteFaturacaoPorFaturacao());
            for(i = 3; i>0 && it.hasNext(); i--) tresMais.add(it.next());
            maisFaturadoTotal.add(tresMais);
        }
        return maisFaturadoTotal;
    }
    
    /**
     * Determina os códigos dos X clientes (sendo X dado pelo utilizador) que compraram
     * mais produtos diferentes (não interessa a quantidade nem o valor), indicando
     * quantos, sendo o critério de ordenação a ordem decrescente do número de
     * produtos.
     */
    
    public Collection<ParClienteProdutosC> query8(int clientes)
        throws HipermercadoNaoInicializadoException
    {
        if (isEmpty())
            throw new HipermercadoNaoInicializadoException();
        Set<String> tdsClientes = new TreeSet<String>();
        Set<String> produtos = new TreeSet<String>();
        
        Set<ParClienteProdutosC> todos= new TreeSet<ParClienteProdutosC>
                                                (new ParClientePorProdutosC());
                            
        Set<ParClienteProdutosC> clientesProdDiferentes
                   =  new TreeSet<ParClienteProdutosC> (new ParClientePorProdutosC());
        
        String s;
        int i;
        for(i=0; i<=2; i++)
            tdsClientes.addAll(filiais[i].getClientes());
        Iterator<String> it = tdsClientes.iterator();
        while(it.hasNext()){
            s=it.next();
            for(i=0;i<=2;i++)
                produtos.addAll(filiais[i].getProdutosCompradosCliente(s));
            todos.add(new ParClienteProdutosC(s, produtos.size()));
        }
        Iterator<ParClienteProdutosC> it2 = todos.iterator();
        while(it2.hasNext() && clientes>0){
            clientesProdDiferentes.add(it2.next());
            clientes--;
        }
        return clientesProdDiferentes;
    }
    
    /**
     * Dado o código de um produto que deve existir, determina o conjunto dos X clientes
     * que mais o compraram e, para cada um, qual o valor gasto.
     */
    
    public Collection<ParClienteCompra> query9(String produto, int clientes)
        throws HipermercadoNaoInicializadoException, ProdutoNaoExisteException
    {
        if (isEmpty())
            throw new HipermercadoNaoInicializadoException();
        if (!catprods.existeProduto(produto))
            throw new ProdutoNaoExisteException();
        
        Set<ParClienteCompra> geral = new TreeSet<ParClienteCompra>(new ParClienteCompraPorCompra());
        Set<ParClienteCompra> geralCortado = new TreeSet<ParClienteCompra>(new ParClienteCompraPorCompra());
        Map<String,ParClienteCompra> prodComp = new TreeMap<String, ParClienteCompra>();
        Map<String,ParClienteCompra> prodCompGeral = new TreeMap<String, ParClienteCompra>();
        List<Map<String, ParClienteCompra>> auxFilial = new ArrayList<Map<String, ParClienteCompra>>(1);
        int i;
        for(i=0; i<3; i++) 
               auxFilial.add(filiais[i].getClientesMaisCompraram(produto));
        
        Iterator<Map<String, ParClienteCompra>> it = auxFilial.iterator(); //itera pelo map
        while(it.hasNext()){
            Map<String, ParClienteCompra> entry = it.next(); //map de cada filial
            Iterator<Map.Entry<String, ParClienteCompra>> pares = entry.entrySet().iterator();
            while(pares.hasNext()){
                Map.Entry<String, ParClienteCompra> actual = pares.next();
                if(prodCompGeral.containsKey(actual.getKey())){
                    prodCompGeral.get(actual.getValue().getCliente()).addGasto(actual.getValue().getGasto());
                    prodCompGeral.get(actual.getValue().getCliente()).addQuant(actual.getValue().getQuant());
                }
                else prodCompGeral.put(actual.getValue().getCliente(),
                                   new ParClienteCompra(actual.getValue().getCliente(), actual.getValue().getQuant(), actual.getValue().getGasto()));
            }
        }
            
        Iterator<ParClienteCompra> it2 = prodCompGeral.values().iterator();
        while(it2.hasNext()) geral.add(it2.next());
        
        Iterator<ParClienteCompra> geralIterator = geral.iterator();
        while(geralIterator.hasNext() && (clientes > 0)){
            geralCortado.add(geralIterator.next());
            clientes--;
        }
        
        return geralCortado;
    }
    /*
    public static Hipermercado debug() {
        Crono tmp = new Crono();
        Hipermercado h= new Hipermercado();
        tmp.start();
        h.readCatalogoCli("./Clientes.txt");
        System.out.println(tmp.print());
        tmp.start();
        h.readCatalogoProd("./Produtos.txt");
        System.out.println(tmp.print());
        tmp.start();
        h.readVendasWithBuff("./Vendas_1M.txt") <-- metes o ficheiro na pasta do BlueJ;
        System.out.println(tmp.print());
        return h;
    }
    */
    
    /*
    public Filial getFilialD(int filial){
        return filiais[filial];
    }
    */
}