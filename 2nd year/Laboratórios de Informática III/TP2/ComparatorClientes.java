
/**
 * Write a description of interface ComparatorVendas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;
public class ComparatorClientes implements Comparator<String>
{
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
