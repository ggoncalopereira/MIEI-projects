
/**
 * Classe que guarda catálogo de produtos e uma expressão regular 
 * com o formato sintático de um produto.
 * 
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.util.*;
import java.util.regex.Pattern;
import java.io.Serializable;
public class CatalogoProdutos implements Serializable
{
    private static Pattern p=Pattern.compile("[A-Z]{2}\\d{4}");
    private TreeSet<String> catprods;

    /**
     * Constructor for objects of class CatalogoProdutos
     */
    public CatalogoProdutos()
    {
        catprods=new TreeSet<>();
    }
    
    public void adiciona(String produto) {
        if(checkProduto(produto))
            catprods.add(produto);
    }
    
    /**
     * Devolve um clone do catálogo.
     */
    public TreeSet<String> getCatalogo(){
        return new TreeSet<String>(catprods);
    }
    
    public int getTotalProdutos(){
        return catprods.size();
    }
    
    public boolean existeProduto(String produto){
        return catprods.contains(produto);
    }

    public static boolean checkProduto(String produto){
        return CatalogoProdutos.p.matcher(produto).matches();
    }
    
    public boolean equals(Object o){
        if (o==this) {
            if(o!=null && this.getClass()==o.getClass()){
                CatalogoProdutos cc=(CatalogoProdutos)o; 
                if(cc.getCatalogo().equals(catprods))
                    return true;
            }
            return false;
        }
        return true;
    }
    
    public int hashCode(){
        return catprods.hashCode();
    }
}