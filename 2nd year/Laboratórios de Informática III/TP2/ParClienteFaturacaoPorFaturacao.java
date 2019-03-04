/**
 * Comparador de ParClienteCompra, por ordem decrescente de facturação e, em caso de igualdade,
 * por ordem alfabética.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */

import java.util.Comparator;
public class ParClienteFaturacaoPorFaturacao
        implements Comparator<ParClienteFaturacao>
{
    public int compare(ParClienteFaturacao psd1,ParClienteFaturacao psd2){
        if(psd1.getFact()==psd2.getFact())
            return psd1.getCliente().compareTo(psd2.getCliente());
        else
            //Ordem decrescente, psd2 primeiro
            return Double.compare(psd2.getFact(),psd1.getFact());
    }
}