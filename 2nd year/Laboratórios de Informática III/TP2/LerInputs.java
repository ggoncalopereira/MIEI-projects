
/**
 * Write a description of class LerInputs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LerInputs
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class LerInputs
     */
    public LerInputs()
    {
        // initialise instance variables
        x = 0;
    }

    public static int main(){
        String s1 = "KR1583 77.72 128 P L4891 2 1";
        String s2 = "QQ1041 536.53 194 P X4054 12 3";
        String p1,p2,c1,c2;
        double d1,d2;
        Input i1 = new Input();
        Input i2 = new Input();
        p1=i1.lerString(s1);
        p2=i2.lerString(s2);
        System.out.println(p1+"\n"+p2);
        return 0;
    }
}
