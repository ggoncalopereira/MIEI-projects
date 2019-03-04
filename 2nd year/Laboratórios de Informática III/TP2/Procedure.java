
/**
 * Interface funcional que apenas invoca um método sem argumentos ou exceções.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.io.*;
@FunctionalInterface
public interface Procedure extends MenuFunctions
{
   void invoke();
}
