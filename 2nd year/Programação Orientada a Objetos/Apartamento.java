 /**
 * Representação da classe Apartamento, que contém todos os parâmetros de imóvel, e ainda outros
 * correspondentes ao seu próprio tipo.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */
public class Apartamento extends Imovel
{
    public String tipo;
    public double areaT;
    public int numWC;
    public int numPorta;
    boolean garagem;
    private double precoMin;
    
    public Apartamento(){
        setTipo("Inválido");
        setAreaT(0.0);
        setNumQuartosWC(0);
        setNumPorta(0);
        setGaragem(false);
        setRua("N/A");
        setPrecoMin(0.0);
        setPrecoPed(0.0);
    }
    
    public Apartamento(String tipo, double areaT, int numWC, int numPorta,
                       boolean garagem, String rua, double precoMin, double precoPed){
        setTipo(tipo);
        setAreaT(areaT);
        setNumQuartosWC(numWC);
        setNumPorta(numPorta);
        setGaragem(garagem);
        setRua(rua);
        setPrecoMin(precoMin);
        setPrecoPed(precoPed);
    }
    
    public Apartamento(Apartamento a){
        setTipo(a.getTipo());
        setAreaT(a.getAreaT());
        setNumQuartosWC(a.getNumQuartosWC());
        setNumPorta(a.getNumPorta());
        setGaragem(a.getGaragem());
        setRua(a.getRua());
        setPrecoMin(a.getPrecoMin());
        setPrecoPed(a.getPrecoPed());
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String t){
        if (t == "Simplex" || t == "Duplex" || t == "Triplex") tipo=t;
        else tipo="Inválido";
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
    
    public boolean getGaragem(){
        return this.garagem;
    }
    
    public void setGaragem(boolean g){
        garagem=g;
    }
    
    public boolean equals(Apartamento a){
        boolean r=false;
        if(tipo     == a.getTipo()         &&
           areaT    == a.getAreaT()        &&
           numWC    == a.getNumQuartosWC() &&
           numPorta == a.getNumPorta()     &&
           garagem  == a.getGaragem()) r=true;
        return r;
    }
    
    public Apartamento clone(){
        return new Apartamento(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(this.getTipo())
            .append("\nArea Total: ").append(this.getAreaT())
            .append("\nNumero de quartos e WC: ").append(this.getNumQuartosWC())
            .append("\nNumero de porta: ").append(this.getNumPorta());
        if (garagem==true)sb.append("\nTem garagem");
        else sb.append("\nNão tem garagem");
            sb.append("\nRua: ").append(this.getRua())
            .append("\nPreço Pedido: ").append(this.getPrecoPed()).append("\n");
        return sb.toString();
    }
}