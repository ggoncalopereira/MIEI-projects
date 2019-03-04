
/**
 * Write a description of class HiperTeste here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class HiperTeste
{
    private static int nlinhas;
    
    /**
     * Constructor for objects of class HiperTeste
     */
    public HiperTeste()
    {
    }
    
    public static ArrayList<Venda> readLinesWithBuff(String fich) {
        ArrayList<Venda> vendas = new ArrayList<>();
        BufferedReader inStream = null; 
        String linha = null;
        Venda v = null;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ) {
                v=parseLinhaVenda(linha);
                if (v!=null)
                    vendas.add(v);
                HiperTeste.nlinhas++;
            }
        }
        catch(IOException e) { 
            System.out.println(e.getMessage()); return null; 
        };
        return vendas;  
    }

    
    public static Venda parseLinhaVenda(String linha) { 
        String pro;
        double pr;
        boolean promo;
        int quant,mes,filial,size;
        String sp[]=linha.split(" ");
        if (sp.length!=7)
            return null;
        try {
            pr=Double.parseDouble(sp[1].trim());
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        try {
            quant=Integer.parseInt(sp[2].trim());
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        pro=sp[3].trim();
        if (pro.equals("P"))
            promo=true;
        else if (pro.equals("N"))
            promo=false;
        else
            return null;
        try {
            mes=Integer.parseInt(sp[5].trim());
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        try {
            filial=Integer.parseInt(sp[6]);
        }
        catch (NumberFormatException | NullPointerException e) {
            return null;
        }
        return new Venda(quant,mes,filial,pr,promo,sp[4].trim(),sp[0].trim());
    } 
    
    public static ArrayList<Venda> parseAllLinhas(ArrayList<String> linhas) {
        ArrayList<Venda> v= new ArrayList<Venda>();
        linhas.stream()
              .map(HiperTeste::parseLinhaVenda)
              .forEach(a->{ if (a!=null) v.add(a);}); 
        return v;
    }
    
    /*public static ArrayList<Venda> parseAllLinhasExternal(ArrayList<String> linhas) {
        ArrayList<Venda> v = new ArrayList<Venda>();
        Iterator<String> it = linhas.iterator();
        String venda;
        
        while (it.hasNext()){
            venda = it.next();
            if(parseLinhaVenda(venda) != null) v.add(parseLinhaVenda(venda));
        }
        
        return v;
    }
    
    --------------is this needed, external-iterator wise? acho que não é preciso, porque parsing é parsing, mas w/e
    
    */
    
   
    public static long totalComprasFilialF(ArrayList<Venda> vendas,int filial){
        return vendas.stream()
                     .map(Venda::getFilial)
                     .filter(a->a==filial)
                     .count();
    }
    
    public static long totalComprasFilialFExternal(ArrayList<Venda> vendas,int filial){
        Iterator<Venda> it = vendas.iterator();
        long count = 0;
        
        while(it.hasNext()){
            if (it.next().getFilial() == filial) count++;
        }
        
        return count;
    }

    public static long totalComprasFilialFor(ArrayList<Venda> vendas,int filial){
        ArrayList<Venda> inter = new ArrayList<Venda>();
        
        vendas.forEach(v->{if (v.getFilial()==filial) inter.add(v);});
        
        return inter.size();
    }

    
    public static long vendasPrecoZeroF(ArrayList<Venda> vendas) {
        return vendas.stream()
                     .mapToDouble(Venda::getPreco)
                     .filter(a->a==0.0)
                     .count();
    }
    
    public static long vendasPrecoZeroFExternal(ArrayList<Venda> vendas) {
        Iterator<Venda> it = vendas.iterator();
        long count = 0;
        
        while(it.hasNext()){
            if (it.next().getPreco() == 0.0) count++;
        }
        
        return count;    
    }
    
    public static long vendasPrecoZeroFor(ArrayList<Venda> vendas) {
        ArrayList<Venda> inter= new ArrayList<>();
        vendas.forEach(v->{if (v.getPreco()==0.0) inter.add(v);});
        return inter.size();
    }

    public static long vendasPrecoZeroPlain(ArrayList<Venda> vendas) {
        long count=0;
        for(Venda v:vendas) {
            if (v.getPreco()==0.0) 
                count++;
        }
        return count;
    }
    
    public static long vendasIguaisF(ArrayList<Venda> vendas) {
        return vendas.size()-vendas.stream()
                                   .distinct()
                                   .count();
    }
    
    
    public static long vendasIguaisFExternal(ArrayList<Venda> vendas) {
        Set<Venda> a = new HashSet<Venda>(vendas.size());
        Iterator<Venda> it = vendas.iterator();
        Venda v;
        
        while (it.hasNext()){
            v = it.next();
            a.add(v);
        }
        return vendas.size()-a.size();
    }

    public static long vendasIguaisFor(ArrayList<Venda> vendas) {
        Set<Venda> a = new HashSet<Venda>(vendas.size());
        
        vendas.forEach(v->a.add(v));
        
        return vendas.size()-a.size();
    }
    
    public static long totalProdutosLetraF(ArrayList<Venda> vendas,char letra){
        return vendas.stream()
                     .map(Venda::getProduto)
                     .filter(p->p.charAt(0)==letra)
                     .distinct()
                     .count();
    }
    
    public static long totalProdutosLetraFExternal(ArrayList<Venda> vendas,char letra){
        HashSet<String> hs= new HashSet<>(10000);
        Iterator<Venda> it = vendas.iterator();
        Venda v;
        
        while(it.hasNext()){
            v=it.next();
            if(v.getProduto().charAt(0) == letra) hs.add(v.getProduto());
        }
        
        return hs.size();
    }
    
    public static long totalProdutosLetraFor(ArrayList<Venda> vendas,char letra){
        HashSet<String> hs= new HashSet<>(10000);
        
        vendas.forEach(v->{if (v.getProduto().charAt(0) == letra) hs.add(v.getProduto());});
        
        return hs.size();
    }
                    
    public static HashSet<String> ConjuntoClientesFilial1 (ArrayList<Venda> vendas,int filial){
        HashSet<String> hs=new HashSet<>();
        vendas.stream()
              .filter(a->a.getFilial()==filial)
              .map(Venda::getCliente).forEach(a->hs.add(a));
        return hs;
    }
    
    public static HashSet<String> ConjuntoClientesFilial1External (ArrayList<Venda> vendas,int filial){
        HashSet<String> hs=new HashSet<>();
        Iterator<Venda> it = vendas.iterator();
        Venda v;
        
        while(it.hasNext()){
            v = it.next();
            if(v.getFilial() == filial) hs.add(v.getCliente());
        }
        
        return hs;
    }
    
    
    public static TreeSet<String> ConjuntoClientesFilial2 (ArrayList<Venda> vendas,int filial){
        TreeSet<String> hs=new TreeSet<>(new ComparatorClientes());
        vendas.stream()
              .filter(a->a.getFilial()==filial)
              .map(Venda::getCliente)
              .forEach(a->hs.add(a));
        return hs;
    }
    
    public static TreeSet<String> ConjuntoClientesFilial2External (ArrayList<Venda> vendas,int filial){
        TreeSet<String> hs=new TreeSet<>(new ComparatorClientes());
        Iterator<Venda> it = vendas.iterator();
        Venda v;
        
        while(it.hasNext()){
            v = it.next();
            if(v.getFilial() == filial) hs.add(v.getCliente());
        }
        
        return hs;
    }
   
    public static TreeSet<String> ConjuntoClientesFilial3 (ArrayList<Venda> vendas,int filial){
        Comparator<String> ordemDecStrings= (s1,s2)-> s1.compareTo(s2);
        TreeSet<String> hs=new TreeSet<>(ordemDecStrings);
        vendas.stream()
              .filter(a->a.getFilial()==filial)
              .map(Venda::getCliente)
              .forEach(a->hs.add(a));
        return hs;
    }
    
    public static TreeSet<String> ConjuntoClientesFilial3External (ArrayList<Venda> vendas,int filial){
        Comparator<String> ordemDecStrings= (s1,s2)-> s1.compareTo(s2);
        TreeSet<String> hs=new TreeSet<>(ordemDecStrings);
        Iterator<Venda> it = vendas.iterator();
        Venda v;
        
        while(it.hasNext()){
            v = it.next();
            if(v.getFilial() == filial) hs.add(v.getCliente());
        }
        
        return hs;
    }
    
    public static void main(String[] args) {
        int i;
        char letra;
        Crono tmp= new Crono();
        StringBuilder sb= new StringBuilder();
        ArrayList<Venda> vendas;
        //String s= new String("../Vendas_3M.txt");
        String s= new String("../Vendas_5M.txt");
        HiperTeste.nlinhas=0;
        tmp.start();
        vendas=readLinesWithBuff(s);
        sb.append(s);
        sb.append(" lido usando BufferedReader !\nLidas ");
        sb.append(HiperTeste.nlinhas);
        sb.append(" linhas.\nTempo: ");
        sb.append(tmp.print());
        sb.append("\nTotal de vendas: ");
        sb.append(vendas.size());
        sb.append("\nTotal de vendas com preço 0: ");
        tmp.start();
        sb.append(HiperTeste.vendasPrecoZeroFExternal(vendas));
        sb.append("\n Tempo em iterador: ");
        sb.append(tmp.print());
        sb.append("\n");
        tmp.start();
        sb.append(HiperTeste.vendasPrecoZeroF(vendas));
        sb.append("\n Tempo em Stream: ");
        sb.append(tmp.print());
        sb.append("\n");
        tmp.start();
        sb.append(HiperTeste.vendasPrecoZeroFor(vendas));
        sb.append("\n Tempo em ForEach: ");
        sb.append(tmp.print());
        sb.append("\n");
        tmp.start();
        sb.append(HiperTeste.vendasPrecoZeroPlain(vendas));
        sb.append("\n Tempo em for-each: ");
        sb.append(tmp.print());
        sb.append("\n");
        for(i=1;i<4;i++) {
            sb.append("Total de vendas na filial ").append(i);
            tmp.start();
            sb.append(": ").append(HiperTeste.totalComprasFilialFExternal(vendas,i));
            sb.append("\n Tempo em iterador: ");
            sb.append(tmp.print());
            sb.append("\n");
            tmp.start();
            sb.append(": ").append(HiperTeste.totalComprasFilialF(vendas,i));
            sb.append("\n Tempo em Stream: ");
            sb.append(tmp.print());
            sb.append("\n");        
            tmp.start();
            sb.append(HiperTeste.totalComprasFilialFor(vendas,i));
            sb.append("\n Tempo em ForEach: ");
            sb.append(tmp.print());
            sb.append("\n");
        }
        sb.append("Total de vendas duplicadas: ");
        tmp.start();
        sb.append(HiperTeste.vendasIguaisFExternal(vendas));
        sb.append("\n Tempo em iterador: ");
        sb.append(tmp.print());
        sb.append("\n");
        tmp.start();
        sb.append(HiperTeste.vendasIguaisF(vendas));
        sb.append("\n Tempo em Stream: ");
        sb.append(tmp.print());
        sb.append("\n");
        tmp.start();
        sb.append(HiperTeste.vendasIguaisFor(vendas));
        sb.append("\n Tempo em ForEach: ");
        sb.append(tmp.print());
        sb.append("\n");

        for(letra='A';letra<='Z';letra++) {
            sb.append("Total de produtos com ").append(letra);
            sb.append(": ");
            tmp.start();
            sb.append(HiperTeste.totalProdutosLetraFExternal(vendas,letra));
            sb.append("\n Tempo em Iterador: ");
            sb.append(tmp.print());
            sb.append("\n");
            tmp.start();
            sb.append(HiperTeste.totalProdutosLetraF(vendas,letra));
            sb.append("\n Tempo em Stream: ");
            sb.append(tmp.print());
            sb.append("\n");
            tmp.start();
            sb.append(HiperTeste.totalProdutosLetraFor(vendas,letra));
            sb.append("\n Tempo em ForEach: ");
            sb.append(tmp.print());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
