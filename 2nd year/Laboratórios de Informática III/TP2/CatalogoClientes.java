
/**
 * Classe que guarda catálogo de clientes e uma expressão regular 
 * com o formato sintático de um cliente.
 * 
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.util.*;
import java.util.regex.Pattern;
import java.io.Serializable;
public class CatalogoClientes implements Serializable
{
    private static Pattern p=Pattern.compile("[A-Z]\\d{4}");
    private TreeSet<String> catcli;

    /**
     * Constructor for objects of class CatalogoClientes
     */
    public CatalogoClientes()
    {
        catcli=new TreeSet<>();
    }
    
    public void adiciona(String cliente) {
        if (checkCliente(cliente))
            catcli.add(cliente);
    }
    
    /**
     * Devolve um clone do catálogo.
     */
    public TreeSet<String> getCatalogo(){
        return new TreeSet<String>(catcli);
    }
    
    public int getTotalClientes(){
        return catcli.size();
    }
    
    public boolean existeCliente(String cliente){
        return catcli.contains(cliente);
    }
    
    public static boolean checkCliente(String cliente){
        return CatalogoClientes.p.matcher(cliente).matches();
    }

    public boolean equals(Object o){
        if (o==this) {
            if(o!=null && this.getClass()==o.getClass()){
                CatalogoClientes cc=(CatalogoClientes)o; 
                if(cc.getCatalogo().equals(catcli))
                    return true;
            }
            return false;
        }
        return true;
    }
    
    public int hashCode(){
        return catcli.hashCode();
    }
}
