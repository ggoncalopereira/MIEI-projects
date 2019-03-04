
/**
 * Classe que contém um cliente e respeciva facturação.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.util.Arrays;
public class ParClienteFaturacao
{
    private String s;
    private double f;

    /**
     * Constructor for objects of class ParClienteVenda
     */

    public String getCliente(){
        return s;
    }
    
    public double getFact(){
        return f;
    }
   
    public ParClienteFaturacao(String s,double f) {
        this.s=s;
        this.f=f;
    }

    public ParClienteFaturacao(String s) {
        this.s=s;
        this.f=0;
    }

    public ParClienteFaturacao(ParClienteFaturacao ppv) {
        this(ppv.getCliente(),ppv.getFact());
    }
    
    public ParClienteFaturacao clone() {
        return new ParClienteFaturacao(this);
    }    
    
    public boolean equals(Object o) {
        if (o==this) {
            if (o!=null&& o.getClass()==this.getClass()){
                ParClienteFaturacao f=(ParClienteFaturacao)o;
                if (f.getCliente()==getCliente() &&
                    f.getFact()==getFact())
                    return true;
            }
            return false;
        }
        return true;
    }
    
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Cliente: ").append(getCliente()).append("\nFaturação: ").append(getFact());
        return sb.toString();
    }
    
    public int hashCode(){
        return Arrays.hashCode(new Object[]{getCliente(),getFact()});
    }
}
