
/**
 * Quando um catálogo lido é vazio.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class CatalogoVazioException extends Exception
{
    public String message() {
        return "Catálogo vazio, ficheiro não continha linhas válidas";
    }
}
