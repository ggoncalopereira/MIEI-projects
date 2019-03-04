
/**
 * Comparador de ParClienteCompra, por ordem decrescente de quantidade e, em caso de igualdade,
 * por ordem alfabética.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.util.Comparator;
public class ParClienteCompraPorCompra
        implements Comparator<ParClienteCompra>
{
    public int compare(ParClienteCompra psd1,ParClienteCompra psd2){
        if(psd1.getQuant()==psd2.getQuant())
            return psd1.getCliente().compareTo(psd2.getCliente());
        else
            //Ordem decrescente, psd2 primeiro
            return Integer.compare(psd2.getQuant(),psd1.getQuant());
    }
}