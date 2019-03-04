
/**
 * Write a description of class LerFichsTexto_Teste here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class LerFichsTexto_Teste
{
    private static int nlinhas;
    
    /**
     * Constructor for objects of class LerFichsTexto_Teste
     */
    public LerFichsTexto_Teste()
    {
    }
    
    public static ArrayList<String> readLinesWithBuff(String fich) {
        ArrayList<String> linhas = new ArrayList<>();
        BufferedReader inStream = null; 
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ) {
                linhas.add(linha);
                LerFichsTexto_Teste.nlinhas++;
            }
        }
        catch(IOException e) { 
            System.out.println(e.getMessage()); return null; 
        };
        return linhas;  
    }

    public static ArrayList<String> readLinesArrayWithScanner(String ficheiro) {
         ArrayList<String> linhas = new ArrayList<>();
         Scanner scanFile = null;
         try {
             scanFile = new Scanner(new FileReader(ficheiro));
             scanFile.useDelimiter("\n\r");
             while(scanFile.hasNext()) {
                 linhas.add(scanFile.nextLine());
                 LerFichsTexto_Teste.nlinhas++;
             }
         }
         catch(IOException e) { 
             System.out.println(e.getMessage());
             return null; 
         }
         finally { 
             if(scanFile != null)
                scanFile.close(); 
         }
         return linhas;
    }
    
    /* Absolutely terrible, so is Input
    public static Venda parseLinhaVenda(String linha) throws VendaInválidaException { 
        Scanner in = new Scanner(linha);
        String p,c,pro;
        double pr;
        boolean promo;
        int quant,mes,filial,size;
        try {
            p=in.next();
            pr=in.nextDouble();
            quant=in.nextInt();
            pro=in.next();
            if (pro.equals("P"))
                promo=true;
            else if (pro.equals("N"))
                promo=false;
            else
                throw new VendaInválidaException();
            c=in.next();
            mes=in.nextInt();
            filial=in.nextInt();
        }
        catch (InputMismatchException e) {
                throw new VendaInválidaException();
        }
        return new Venda(quant,mes,filial,pr,promo,c,p);
    }
    */   
   
    /*Kinda long and sucky but super fast, only slightly faster than split, probly worse if tons of exceptions
    public static Venda parseLinhaVenda(String linha) throws VendaInválidaException { 
        String p,c,pro;
        double pr;
        boolean promo;
        int quant,mes,filial,left,right;
        left=0;
        try {
            right=linha.indexOf(" ",left);
            if (right!=-1)
                p=linha.substring(0,right);
            else
                throw new VendaInválidaException();
            left=linha.indexOf(" ",right+1);
            if(left!=-1)
                pr=Double.valueOf(linha.substring(right+1,left));
            else
                throw new VendaInválidaException();
            right=linha.indexOf(" ",left+1);
            if(right!=-1)
                quant=Integer.parseInt(linha.substring(left+1,right));
            else
                throw new VendaInválidaException();
            left=linha.indexOf(" ",right+1);
            if(left!=-1)
                pro=linha.substring(right+1,left);
            else
                throw new VendaInválidaException();
            if (pro.equals("P"))
                promo=true;
            else if (pro.equals("N"))
                promo=false;
            else
                throw new VendaInválidaException();
            right=linha.indexOf(" ",left+1);
            if (right!=-1)
                c=linha.substring(left+1,right);
            else
                throw new VendaInválidaException();
            left=linha.indexOf(" ",right+1);
            if (left!=-1)
                mes=Integer.parseInt(linha.substring(right+1,left));
            else
                throw new VendaInválidaException();
            filial=Integer.parseInt(linha.substring(left+1));
        }
        catch (NumberFormatException e) {
                throw new VendaInválidaException();
        }
        return new Venda(quant,mes,filial,pr,promo,c,p);
    } 
    */
    
    public static Venda parseLinhaVenda(String linha) throws VendaInvalidaException { 
        String p,c,pro;
        double pr;
        boolean promo;
        int quant,mes,filial,size;
        String sp[]=linha.split(" ");
        if (sp.length!=7)
            throw new VendaInvalidaException();
        try {
            p=sp[0];
            pr=Double.valueOf(sp[1]);
            quant=Integer.parseInt(sp[2]);
            pro=sp[3];
            if (pro.equals("P"))
                promo=true;
            else if (pro.equals("N"))
                promo=false;
            else
                throw new VendaInvalidaException();
            c=sp[4];
            mes=Integer.parseInt(sp[5]);
            filial=Integer.parseInt(sp[6]);
        }
        catch (NumberFormatException e) {
                throw new VendaInvalidaException();
        }
        return new Venda(quant,mes,filial,pr,promo,c,p);
    } 

    
    public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas) {
        ArrayList<Venda> v= new ArrayList<Venda>();
        Iterator<String> it= linhas.iterator();
        while(it.hasNext()) {
            try {
                v.add(parseLinhaVenda(it.next()));
            }catch(VendaInvalidaException e){}
        }
        return v;
    }
 
    public static HashSet<Venda> parseAllLinhasToSet(ArrayList<String> linhas) {
        HashSet<Venda> v= new HashSet<Venda>();
        Iterator<String> it= linhas.iterator();
        while(it.hasNext()) {
            try {
                v.add(parseLinhaVenda(it.next()));
            }catch(VendaInvalidaException e){}
        }
        return v;
    }

    
        public static void main(String[] args) {
        int l;
        Crono tmp= new Crono();
        StringBuilder sb= new StringBuilder();
        ArrayList<String> linhas;
        HashSet<Venda> vendas;
        //String s= new String("../Vendas_3M.txt");
        String s= new String("../Vendas_5M.txt");
        l=0;
        LerFichsTexto_Teste.nlinhas=0;
        tmp.start();
        linhas=readLinesArrayWithScanner(s);
        tmp.stop();
        l=LerFichsTexto_Teste.nlinhas;
        sb.append(s);
        sb.append(" lido usando Scanner e FileReader !\nLidas e guardadas ");
        sb.append(l);
        sb.append(" linhas.\nTempo: ");
        sb.append(tmp.print());
        sb.append("\n--------------------------------------------------------------------------------\n");
        System.out.println(sb.toString());
        LerFichsTexto_Teste.nlinhas=0;
        tmp.start();
        linhas=readLinesWithBuff(s);
        tmp.stop();
        l=LerFichsTexto_Teste.nlinhas;
        sb.append(s);
        sb.append(" lido usando BufferedReader !\nLidas e guardadas ");
        sb.append(l);
        sb.append(" linhas.\nTempo: ");
        sb.append(tmp.print());
        tmp.start();
        vendas=parseAllLinhasToSet(linhas);
        tmp.stop();
        sb.append("\nTempo de Parsing: ");
        sb.append(tmp.print());
        sb.append("\nTotal de vendas: ");
        sb.append(vendas.size());
        sb.append("\nTotal de vendas com preço 0: ");
        sb.append(vendas.stream().mapToDouble(Venda::getPreco).filter(a->a==0.0).count());
        System.out.println(sb.toString());
    }
}
