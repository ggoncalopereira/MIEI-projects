
/**
 * Write a description of class ParStringDouble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Arrays;
public class ParProdutoQVenda
{
    private String s;
    private int q;
    private int c;

    /**
     * Constructor for objects of class ParProdutoVenda
     */

    public String getProduto(){
        return s;
    }
    
    public int getQuant(){
        return q;
    }
    
    public int getClientes() {
        return c;
    }
        
    public ParProdutoQVenda(String s,int q) {
        this.s=s;
        this.q=q;
        this.c=0;
    }

    public ParProdutoQVenda(String s,int q,int c) {
        this.s=s;
        this.q=q;
        this.c=c;
    }
    
    public void setClientes(int c){
        this.c=c;
    }

    public ParProdutoQVenda(ParProdutoQVenda ppv) {
        this(ppv.getProduto(),ppv.getQuant(),ppv.getClientes());
    }
    
    public ParProdutoQVenda clone() {
        return new ParProdutoQVenda(this);
    }    
    
    public boolean equals(Object o) {
        if (o==this) {
            if (o!=null&& o.getClass()==this.getClass()){
                ParProdutoQVenda f=(ParProdutoQVenda)o;
                if (f.getProduto()==getProduto() &&
                    f.getQuant()==getQuant() &&
                    f.getClientes()==getClientes())
                    return true;
            }
            return false;
        }
        return true;
    }
    
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Produto: ").append(getProduto()).append("\nVendas: ").append(getQuant())
                                        .append("\nClientes: ").append(getClientes());
        return sb.toString();
    }
    
    public int hashCode(){
        return Arrays.hashCode(new Object[]{getProduto(),getQuant(),getClientes()});
    }
}
