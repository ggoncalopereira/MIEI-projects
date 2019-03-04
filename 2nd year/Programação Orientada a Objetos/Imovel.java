/**
 * Representação da classe Imóvel, que tem três strings correspondentes à rua do imóvel,
 * ao preço mínimo que o vendedor aceita baixar e ao preço pedido aquando da compra do imóvel.
 * O preço mínimo será escondido, isto é, os compradores não terão acesso a tal informação.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */

public class Imovel
{
    private String rua;
    private double precoMin;
    private double precoPed;
   
    public Imovel(){
        this.rua="N/A";
        this.precoMin=0.0;
        this.precoPed=0.0;
    }
    
    public Imovel(String rua, double precoMin, double precoPed){
        setRua(rua);
        setPrecoMin(precoMin);
        setPrecoPed(precoPed);
    }
    
    public Imovel(Imovel i){
        rua=i.getRua();
        precoMin=i.getPrecoMin();
        precoPed=i.getPrecoPed();
    }
    
    public String getRua(){
        return(this.rua);
    }
    
    public void setRua(String rua){
        this.rua=rua;
    }
    
    public double getPrecoMin(){
        return this.precoMin;
    }
    
    public void setPrecoMin(double precoMin){
        this.precoMin=precoMin;
    }
    
    public double getPrecoPed(){
        return this.precoPed;
    }
    
    public void setPrecoPed(double precoPed){
        this.precoPed=precoPed;
    }
    
    public boolean equals(Imovel i){
        boolean r=false;
        if(this.rua==i.getRua() && this.precoMin==i.getPrecoMin() && this.precoPed==i.getPrecoPed()) r=true;
        return r;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rua: ").append(rua)
        .append("\nPreço Pedido: ").append(precoPed)
        .append("\n");
        return sb.toString();
    }
    
    public Imovel clone(){
        return (new Imovel(this));
    }
    
}