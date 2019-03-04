
/**
 * 
 * Classe que contém um triplo de Cliente, quantidade comprada e valor gasto em compras.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.util.Arrays;
public class ParClienteCompra
{
    private String s;
    private int q;
    private double g;

    /**
     * Constructor for objects of class ParClienteVenda
     */

    public String getCliente(){
        return s;
    }

    public int getQuant(){
        return q;
    }
    
    public void addQuant(int quant){
        this.q += quant;
    }
    
    public double getGasto(){
        return g;
    }
    
    public void addGasto(double gasto){
        this.g += gasto;
    }
    
    public ParClienteCompra(String s,int q, double g) {
        this.s=s;
        this.q=q;
        this.g=g;
    }

    public ParClienteCompra(String s) {
        this.s=s;
        this.q=0;
        this.g=0;
    }

    public ParClienteCompra(ParClienteCompra ppv) {
        this(ppv.getCliente(),ppv.getQuant(),ppv.getGasto());
    }

    public ParClienteCompra clone() {
        return new ParClienteCompra(this);
    }    

    public boolean equals(Object o) {
        if (o==this) {
            if (o!=null&& o.getClass()==this.getClass()){
                ParClienteCompra f=(ParClienteCompra)o;
                if (f.getCliente()==getCliente() &&
                f.getQuant()==getQuant() &&
                f.getGasto()==getGasto())
                    return true;
            }
            return false;
        }
        return true;
    }

    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Cliente: ").append(getCliente()).append("\nQuantidade comprada: ").append(getQuant())
                                                   .append("\nValor gasto: ").append(getGasto());
        return sb.toString();
    }

    public int hashCode(){
        return Arrays.hashCode(new Object[]{getCliente(),getQuant(),getGasto()});
    }
}
