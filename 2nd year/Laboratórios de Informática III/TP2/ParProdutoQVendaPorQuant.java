
/**
 * Write a description of class ParStringDoublePorString here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;
public class ParProdutoQVendaPorQuant 
        implements Comparator<ParProdutoQVenda>
{
    public int compare(ParProdutoQVenda psd1,ParProdutoQVenda psd2){
        if(psd1.getQuant()==psd2.getQuant())
            return psd1.getProduto().compareTo(psd2.getProduto());
        else
            //Ordem decrescente, psd2 primeiro
            return Integer.compare(psd2.getQuant(),psd1.getQuant());
    }
}