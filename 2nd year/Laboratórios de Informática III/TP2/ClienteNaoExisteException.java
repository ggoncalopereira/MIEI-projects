
/**
 * Cliente tem de existir no catálogo de clientes.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
public class ClienteNaoExisteException extends Exception
{
    public String message() {
        return "Cliente especificado não existe no catálogo de clientes.";
    }
}
