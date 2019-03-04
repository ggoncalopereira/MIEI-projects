
/**
 * Contém um duplo mapeamento, Clientes -> Produtos -> informações de vendas por mês.
 * 
 * MESES SÃO INDEXADOS DE 0-11 MAS RECEBIDOS DE 1-12.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.io.Serializable;
import java.util.*;
public class Filial implements Serializable
{
    private Map<String,Map<String,ArrayList<FilialFact>>> clientes;
    
    public Filial (){
        clientes=new TreeMap<String,Map<String,ArrayList<FilialFact>>>();
    }
    
    /**
     * Devolve o número de clientes compradores.
     */
    public int getTotalClientes() {
        return clientes.size();
    }
    
    /**
     * Devolve um conjunto de todos os clientes compradores(Ordem Natural).
     */
    public TreeSet<String> getClientes() {
        return new TreeSet<String>(clientes.keySet());
    }
    
    /**
     * Devolve um conjunto de todos os clientes compradores num mes(Ordem Natural).
     */
    public TreeSet<String> getClientes(int mes) {
        TreeSet<String> t=new TreeSet<>();
        Iterator<Map.Entry<String,Map<String,ArrayList<FilialFact>>>> it
                =clientes.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry<String,Map<String,ArrayList<FilialFact>>> me = it.next();
            Iterator<ArrayList<FilialFact>> it2 = me.getValue().values().iterator();
           
            while(it2.hasNext()){
                ArrayList<FilialFact> al = it2.next();
                Iterator<FilialFact> it3 = al.iterator();
                FilialFact f=null;
                while(it3.hasNext() && (f=it3.next()).getMes()!=mes-1);
                if(f.getMes() == mes-1) 
                    t.add(me.getKey());
            }
        }
        return t;
    }
    
    /**
     * Devolve um conjunto de todos os clientes compradores do produto.
     */
    public TreeSet<String> getClientes(String produto) {
        TreeSet<String> t=new TreeSet<>();
        Iterator<Map.Entry<String,Map<String,ArrayList<FilialFact>>>> it
                =clientes.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry<String,Map<String,ArrayList<FilialFact>>> me = it.next();
            
            if (me.getValue().containsKey(produto))
                t.add(me.getKey());
        }
        return t;
    }

    /**
     * Devolve o número de clientes compradores do produto.
     */
    public int getTotalClientes(String produto) {
        int count=0;
        Iterator<Map<String,ArrayList<FilialFact>>> it
                =clientes.values().iterator();
                
        while (it.hasNext()) {
            if (it.next().containsKey(produto))
                count++;
        }
        return count;
    }
    
    /**
     * Devolve o número de clientes compradores num dado mes.
     */
    public int getTotalClientes(int mes) {
        int count=0;
        Iterator<Map<String,ArrayList<FilialFact>>> it
                =clientes.values().iterator();
                
        while (it.hasNext()) {
            Map<String,ArrayList<FilialFact>> me = it.next();
            Iterator<ArrayList<FilialFact>> it2 = me.values().iterator();
           
            while(it2.hasNext()){
                ArrayList<FilialFact> al = it2.next();
                Iterator<FilialFact> it3 = al.iterator();
                FilialFact f=null;
                while(it3.hasNext() && (f=it3.next()).getMes()!=mes-1);
                if(f.getMes() == mes-1)
                    count++;
            }
        }
        return count;
    }
    
    /**
     * Devolve um Set de clientes que compraram um produto em específico, para a Query 6.
     */
    
    public Set<String> getClientesCompraProduto(String produto){
        Set<String> clientesDiferentes = new TreeSet<String>();
        Iterator<Map.Entry<String,Map<String,ArrayList<FilialFact>>>> it
                =clientes.entrySet().iterator();
        
        while(it.hasNext()){
            Map.Entry<String,Map<String,ArrayList<FilialFact>>> entry = it.next();
            if (entry.getValue().containsKey(produto)){
                clientesDiferentes.add(entry.getKey());
            }
        }
        return clientesDiferentes;
    }
    
    /**
     * Devolve um Set com todos clientes, ordenados pela quantidade de dinheiro gasta, para a Query 7.
     */
    
    public Set<ParClienteFaturacao> getClientesMaisCompradores(){
        Set<ParClienteFaturacao> clientesMaisComp = new TreeSet<ParClienteFaturacao>(new ParClienteFaturacaoPorFaturacao());
        Iterator<Map.Entry<String,Map<String,ArrayList<FilialFact>>>> it
                =clientes.entrySet().iterator();
                
        while(it.hasNext()){
            Map.Entry<String,Map<String,ArrayList<FilialFact>>> entry = it.next();
            double factTotal = 0;
            for(ArrayList<FilialFact> f : entry.getValue().values()){
                for(FilialFact fil : f) factTotal += fil.getFact();
            }
            clientesMaisComp.add(new ParClienteFaturacao(entry.getKey(), factTotal));
        }
        
        return clientesMaisComp; 
    }
    
    /**
     * Devolve um Set com a quantidade de produtos diferentes comprados por cliente, para a Query 8.
     */
    public Set<String> getProdutosCompradosCliente(String cliente){
        Set<String> produtosComprados = new TreeSet<String>(); 
        if(clientes.containsKey(cliente))
            produtosComprados.addAll(clientes.get(cliente).keySet());
                
        return produtosComprados;
    }
    
    /**
     * Devolve um Set com a os clientes que mais compraram um produto, dado o código do produto, para a Query 9.
     */
    
    public Map<String,ParClienteCompra> getClientesMaisCompraram(String produto){
        Map<String, ParClienteCompra> maisCompras = new TreeMap<String, ParClienteCompra>();
        Iterator<Map.Entry<String,Map<String,ArrayList<FilialFact>>>> it
                =clientes.entrySet().iterator();
                
        while(it.hasNext()){
            Map.Entry<String,Map<String,ArrayList<FilialFact>>> entry = it.next();
            if(entry.getValue().containsKey(produto)){
                Iterator<ArrayList<FilialFact>> it2 = entry.getValue().values().iterator();
                int quant = 0;
                double gast = 0;
                while(it2.hasNext()){
                    ArrayList<FilialFact> al = it2.next();
                    Iterator<FilialFact> it3 = al.iterator();
                    while(it3.hasNext()){
                        FilialFact f = it3.next();
                        gast += f.getFact();
                        quant += f.getQuant();
                    }
                }
                maisCompras.put(entry.getKey(), new ParClienteCompra(entry.getKey(), quant, gast));
            }
        }
        return maisCompras;
    }
    
    /**
     * Adiciona uma venda à filial. ÚNICO QUE RECEBE MES 0-11.
     */
    public void adiciona(int mes,int quant, double preco,
                         String cliente,String produto) {
        if (!clientes.containsKey(cliente))
            clientes.put(cliente,new TreeMap<String,ArrayList<FilialFact>>());
        Map<String,ArrayList<FilialFact>> f=clientes.get(cliente);
        if (!f.containsKey(produto)) {
            f.put(produto,new ArrayList<FilialFact>(1));
            f.get(produto).add(new FilialFact(quant,preco,mes));
        }
        else {
            ArrayList<FilialFact> f2=f.get(produto);
            Iterator<FilialFact> i = f2.iterator(); 
            FilialFact n = null;
            while(i.hasNext() && (n=i.next()).getMes() != mes);
            if (!(n.getMes() == mes)) {
                n = new FilialFact(quant, preco, mes);
                f2.add(n);
            }
            else{
                n.adiciona(quant,preco);
            }
        }
    }
    
        
    public int hashCode() {
        return clientes.hashCode();
    }
    
}
