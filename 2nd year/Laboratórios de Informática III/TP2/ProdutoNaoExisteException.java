
/**
 * Produto tem de existir no catálogo de produtos.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class ProdutoNaoExisteException extends Exception
{
    public String message() {
        return "Produto especificado não existe no catálogo de produtos.";
    }
}
