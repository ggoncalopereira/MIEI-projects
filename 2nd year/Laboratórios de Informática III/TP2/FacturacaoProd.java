
/**
 * FacturacaoProd contém informações de facturação de um produto para as 3 filiais.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.io.Serializable;
import java.util.Arrays;
public class FacturacaoProd implements Serializable
{
    private int vendas[];
    private int quant[];
    private double fact[];
    
    public FacturacaoProd (FacturacaoProd f) {
        int i;
        vendas= new int[3];
        quant= new int[3];
        fact= new double[3];
        for(i=0;i<3;i++) {
            vendas[i]=f.getVendas(i);
            quant[i]=f.getQuant(i);
            fact[i]=f.getFact(i);
        }
    }
    
    public FacturacaoProd (){
        vendas=new int[3];
        quant=new int[3];
        fact=new double[3];
    }
    
/*    public TreeSet<String> getClientes(){
        return clientes;
    }
    
    public int getNClientes (){
        return clientes.size();
    }
*/

    public int getVendasTotal(){
        int i,v;
        for(i=v=0;i<3;i++)
            v+=vendas[i];
        return v;
    }
    
    public int getVendas (int filial){
        return vendas[filial];
    }
    
    public double getFactTotal(){
        int i;
        double v=0.0;
        for(i=0;i<3;i++)
            v+=fact[i];
        return v;
    }
    
    public double getFact (int filial){
        return fact[filial];
    }

    public int getQuantTotal(){
        int i,q;
        for(i=q=0;i<3;i++)
            q+=quant[i];
        return q;
    }    
    public int getQuant(int filial) {
        return quant[filial];
    }
    
    public void adiciona(int filial,int quant,double preco) {
        fact[filial]+= quant*preco;
        this.quant[filial]+= quant;
        vendas[filial]++;
    }
    
    public boolean equals (Object o){
        int i;
        if (o==this) {
            if (o!=null&& o.getClass()==this.getClass()){
                FacturacaoProd f=(FacturacaoProd)o;
                boolean eqs=true;
                for(i=0;i<3 && eqs;i++) {
                    if (vendas[i]!=(f.getVendas(i))||
                        fact[i]!=(f.getFact(i)) ||
                        quant[i]!=(f.getQuant(i)))
                        eqs=false;
                }
                return eqs;
            }
            return false;
        }
        return true;
    }
    
    public FacturacaoProd clone () {
        return new FacturacaoProd(this);
    }
    
    public int hashCode() {
        return Arrays.hashCode(new Object[]{vendas,quant,fact});
    }
    
}
