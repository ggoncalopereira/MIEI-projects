
/**
 * Classe que contém informações de venda e a que mês corresponde.
 * 
 * GUARDA O MES 0-11.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.io.Serializable;
import java.util.Arrays;
public class FilialFact implements Serializable
{
    private int vendas;
    private int quant;          
    private double fact;
    private int mes;
    
    public FilialFact (FilialFact f) {
        vendas=f.getVendas();            
        quant=f.getQuant();            
        fact=f.getFact();
        mes=f.getMes();
    }
    
    public FilialFact (int quant, double preco, int mes) {
        vendas = 1;          
        this.quant = quant;
        fact = quant*preco;
        this.mes = mes;
    }

    public int getVendas(){
        return vendas;
    }
    
    public double getFact(){
        return fact;
    }
    
    public int getQuant() {
        return quant;
    }
    
    public int getMes(){
        return mes;
    }

    public void adiciona(int quant,double preco) {
        fact+= quant*preco;
        this.quant+= quant;
        vendas++;
    }
    
    public boolean equals (Object o){
        int i;
        if (o==this) {
            if (o!=null&& o.getClass()==this.getClass()){
                FilialFact f=(FilialFact)o;
                if (vendas==f.getVendas() &&
                    fact==f.getFact() &&
                    quant==f.getQuant() &&
                    mes==f.getMes())
                    return true;
            }
            return false;
        }
        return true;
    }
    
    public FilialFact clone () {
        return new FilialFact(this);
    }
    
    public int hashCode() {
        return Arrays.hashCode(new Object[]{vendas,quant,fact,mes});
    }
    
}
