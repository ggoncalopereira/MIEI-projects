
/**
 * Hipermercado tem de ser inicializado antes de se poder consultar coisas.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class HipermercadoNaoInicializadoException extends Exception
{
    public String message() {
        return "Hipermercado não foi inicializado";
    }
}
