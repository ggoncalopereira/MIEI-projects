
/**
 * Tamanho de página tem de ser positivo e superior a 0.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class TamanhoPaginaInvalidoException extends Exception
{
    public String message() {
        return "Não há resultados para apresentar";
    }
}
