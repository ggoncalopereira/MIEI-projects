
/**
 * Write a description of class ParStringDouble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Arrays;
public class ParClienteProdutosC
{
    private String s;
    private int p;

    /**
     * Constructor for objects of class ParProdutoVenda
     */

    public String getCliente(){
        return s;
    }
    
    public int getProdutosC() {
        return p;
    }
        
    public ParClienteProdutosC(String s,int p) {
        this.s=s;
        this.p=p;
    }

    
    public ParClienteProdutosC(ParClienteProdutosC pcp) {
        this(pcp.getCliente(),pcp.getProdutosC());
    }
    
    public ParClienteProdutosC clone() {
        return new ParClienteProdutosC(this);
    }    
    
    public boolean equals(Object o) {
        if (o==this) {
            if (o!=null&& o.getClass()==this.getClass()){
                ParClienteProdutosC f=(ParClienteProdutosC)o;
                if (f.getCliente()==getCliente() &&
                    f.getProdutosC()==getProdutosC())
                    return true;
            }
            return false;
        }
        return true;
    }
    
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Cliente: ").append(getCliente())
                              .append("\nProdutos Diferentes: ")
                              .append(getProdutosC());
        return sb.toString();
    }
    
    public int hashCode(){
        return Arrays.hashCode(new Object[]{getCliente(),getProdutosC()});
    }
}
