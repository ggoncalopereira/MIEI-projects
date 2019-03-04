
/**
 * Write a description of class ParStringDoublePorString here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;
public class ParClientePorProdutosC 
        implements Comparator<ParClienteProdutosC>
{
    public int compare(ParClienteProdutosC psd1,ParClienteProdutosC psd2){
        if(psd1.getProdutosC()==psd2.getProdutosC())
            return psd1.getCliente().compareTo(psd2.getCliente());
        else
            //Ordem decrescente, psd2 primeiro
            return Integer.compare(psd2.getProdutosC(),psd1.getProdutosC());
    }
}