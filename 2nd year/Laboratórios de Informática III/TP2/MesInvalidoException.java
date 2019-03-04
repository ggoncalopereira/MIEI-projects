
/**
 * Só existem Meses de 1-12.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class MesInvalidoException extends Exception
{
    public String message(){
        return "Mês : 1-12";
    }
}
