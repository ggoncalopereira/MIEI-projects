/**
 * Facturação guarda o total global de vendas para todas as filiais,
 * o totalFacturado em cada filial, em cada mês e um mapeamento de produtos
 * para 12 meses de facturações desse produto.
 * 
 * MESES E FILIAIS SÃO INDEXADOS DE 0-11 0-2 NA LEITURA
 * MAS RECEBIDOS DO UTILIZADOR DE 1-12 1-3.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */

import java.io.Serializable;
import java.util.*;
public class Facturacao implements Serializable
{
    private int totalVendas[];
    private double totalFacturado[][];
    private Map<String,FacturacaoProd[]> factmap;

    
    public Facturacao (){
        int i;
        totalVendas=new int[12];
        totalFacturado=new double[3][12];
        factmap=new TreeMap<String,FacturacaoProd[]>();
    }
    
    /**
     * Devolve se o mapeamento está ou não vazio.
     */
    public boolean isEmpty(){
        return factmap.isEmpty();
    }
    
    /**
     * Devolve se um produto existe no mapeamento.
     */
    public boolean noMapeamento(String produto) {
        return factmap.containsKey(produto);
    }
    
    /**
     * Devolve a quantidade de produtos vendidos.
     */
    public int getTotalProdutos(){
        return factmap.size();
    }
    
/*    public int getTotalClientes() {
        int i;
        TreeSet<String> t= new TreeSet<>();
        for(i=0;i<12;i++) {
            for(FacturacaoProd f:factmaps.get(i).values())
                t.addAll(f.getClientes());
        }
        return t.size();
    }
    
    public int getTotalClientes(int mes) {
        TreeSet<String> t= new TreeSet<>();
        for(FacturacaoProd f:factmaps.get(mes-1).values())
            t.addAll(f.getClientes());
        return t.size();
    }
*/

    /**
     * Devolve todas as vendas registadas.
     */
    public int getTotalVendas() {
        int i,r;
        for(i=r=0;i<12;i++)
            r+=totalVendas[i];
        return r;
    }
    
    /**
     * Devolve todas as vendas registadas para cada mês.
     */
    public int getTotalVendas(int mes) {
        return totalVendas[mes-1];
    }
    
    /**
     * Devolve o total facturado globalmente.
     */
    public double getTotalFacturado() {
        int i,j;
        double fact=0.0;
        for(i=0;i<3;i++) {
            for(j=0;j<12;j++) {
                fact+=totalFacturado[i][j];
            }
        }
        return fact;
    }
    
    /**
     * Devolve o total facturado globalmente num mes.
     */
    public double getTotalFacturado(int mes) {
        int i,j;
        double fact=0.0;
        for(i=0;i<3;i++) {
            fact+=totalFacturado[i][mes-1];
        }
        return fact;
    }
        
    /**
     * Devolve o total facturado numa filial durante o ano.
     */
    public double getTotalFacturadoF(int filial) {
        int i,j;
        double fact=0.0;
        for(j=0;j<12;j++) {
            fact+=totalFacturado[filial-1][j];
        }
        return fact;
    }
    
    /**
     * Devolve o total facturado numa filial num mes.
     */
    public double getTotalFacturado(int mes,int filial) {
        return totalFacturado[filial-1][mes-1];
    }
    
    /**
     * Devolve a facturação total dum produto.
     */
    public double getFacturacaoProduto(String produto) {
        int i;double fact=0.0;
        FacturacaoProd f[]= factmap.get(produto);
        for(i=0;i<12;i++) {
            if (f[i]!=null)
                fact+=f[i].getFactTotal();
        }
        return fact;
    }

    
    /**
     * Devolve a facturação do produto num determinado mês para a filial dada.
     */
    public double getFacturacaoProduto(int mes,int filial,String produto) {
        int i;double fact=0.0;
        FacturacaoProd f= factmap.get(produto)[mes-1];
        if (f!=null) {
            fact+=f.getFact(filial-1);
        }
        return fact;
    }
    
    /**
     * Devolve a facturação do produto num determinado mês nas 3 filiais.
     */
    public double getFacturacaoProduto(int mes,String produto) {
        int i;double fact=0.0;
        FacturacaoProd f= factmap.get(produto)[mes-1];
        if (f!=null) {
            for(i=0;i<3;i++)
                fact+=f.getFact(i);
        }
        return fact;
    }
    
    /**
     * Devolve a quantidade total vendida dum produto.
     */
    public int getQuantProduto(String produto) {
        int i;int quant=0;
        if (factmap.containsKey(produto)) {
            FacturacaoProd f[]= factmap.get(produto);
            for(i=0;i<12;i++) {
                if (f[i]!=null)
                    quant+=f[i].getQuantTotal();
            }
        }
        return quant;
    }
    
    public Set<ParProdutoQVenda> produtosMaisVendidos(){
        Set<ParProdutoQVenda> prodMaisVend = new TreeSet<>(new ParProdutoQVendaPorQuant()); //produto, quantidade vendida
        Iterator<String> it = factmap.keySet().iterator();
        
        while(it.hasNext()){
            String produto = it.next();
            prodMaisVend.add(new ParProdutoQVenda(produto, getQuantProduto(produto)));
        }
        
        return prodMaisVend;        
    }
    
    /**
     * Adiciona uma venda à facturação. ÚNICO QUE RECEBE MES 0-11, FILIAL 0-2.
     */
    public void adiciona(int mes,int filial,int quant,double preco, String produto)
    {
        FacturacaoProd f[];
        totalVendas[mes]++;
        totalFacturado[filial][mes]+=quant*preco;
        if (!factmap.containsKey(produto)) {
            factmap.put(produto,new FacturacaoProd[12]);
        }
        f=factmap.get(produto);
        if (f[mes]==null)
            f[mes]= new FacturacaoProd();
        f[mes].adiciona(filial,quant,preco);
    }
    
        
    public int hashCode() {
        return Arrays.hashCode(new Object[]
                            {totalVendas,totalFacturado,factmap});
    }
    
}
