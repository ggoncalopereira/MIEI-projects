 /**
 * Representação da classe Moradia, que contém todos os parâmetros de imóvel, e ainda outros
 * correspondestes ao seu próprio tipo.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */
public class Moradia extends Imovel
{
    private String tipo;
    private double areaI;
    private double areaC;
    private double areaT;
    private int numWC;
    private int numPorta;
    private int precoMin;
   
    public Moradia(){
        setTipo("Inválido");
        setAreaI(0.0);
        setAreaC(0.0);
        setAreaT(0.0);
        setNumQuartosWC(0);
        setNumPorta(0);
        setRua("N/A");
        setPrecoMin(0.0);
        setPrecoPed(0.0);
    }
    
    public Moradia(String tipo, double areaI, double areaC, double areaT, int numWC,
                   int numPorta, String rua, double precoMin, double precoPed){
        setTipo(tipo);
        setAreaI(areaI);
        setAreaC(areaC);
        setAreaT(areaT);
        setNumQuartosWC(numWC);
        setNumPorta(numPorta);
        setRua(rua);
        setPrecoMin(precoMin);
        setPrecoPed(precoPed);
    }
    
    public Moradia(Moradia m){
        setTipo(m.getTipo());
        setAreaI(m.getAreaI());
        setAreaC(m.getAreaC());
        setAreaT(m.getAreaT());
        setNumQuartosWC(m.getNumQuartosWC());
        setNumPorta(m.getNumPorta());
        setRua(m.getRua());
        setPrecoMin(m.getPrecoMin());
        setPrecoPed(m.getPrecoPed());
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String t){
        if (t == "Isolada"  ||
            t == "Geminada" ||
            t == "Bando"    ||
            t == "Gaveto") tipo=t;
        else tipo="Inválido";
    }
    
    public double getAreaI(){
        return this.areaI;
    }
    
    public void setAreaI(double a){
       areaI=a;
    }
    
    public double getAreaC(){
        return this.areaC;
    }
    
    public void setAreaC(double a){
        areaC=a;
    }
    
    public double getAreaT(){
        return this.areaT;
    }
    
    public void setAreaT(double a){
        areaT=a;
    }
    
    public int getNumQuartosWC(){
        return this.numWC;
    }
    
    public void setNumQuartosWC(int n){
        numWC=n;
    }
    
    public int getNumPorta(){
        return this.numPorta;
    }
    
    public void setNumPorta(int n){
        numPorta=n;
    }
    
    public boolean equals(Moradia m){
        boolean r=false;
        if(tipo     == m.getTipo()         &&
           areaI    == m.getAreaI()        &&
           areaC    == m.getAreaC()        &&
           areaT    == m.getAreaT()        &&
           numWC    == m.getNumQuartosWC() &&
           numPorta == m.getNumPorta()) r=true;
        return r;
    }
    
    public Moradia clone(){
        return new Moradia(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(this.getTipo())
                           .append("\nArea de Implantação: ").append(this.getAreaI())
                           .append("\nArea Total Coberta: ").append(this.getAreaC())
                           .append("\nArea do terreno envolvente: ").append(this.getAreaT())
                           .append("\nNumero de quartos e WC: ").append(this.getNumQuartosWC())
                           .append("\nNumero de porta: ").append(this.getNumPorta())
                           .append("\nRua: ").append(this.getRua())
                           .append("\nPreço Pedido: ").append(this.getPrecoPed())
                           .append("\n");
        return sb.toString();
    }
}