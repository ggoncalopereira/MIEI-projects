 
 /**
 * Representação da classe Terreno, que contém todos os parâmetros de imóvel, e ainda outros
 * correspondentes ao seu próprio tipo.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */
public class Terreno
{
    public double areaC;
    public String tipo;
    public double diametroCanalizacoes;
    public boolean redeEletrica;
    public double kWhMax;
    public boolean redeEsgotos;
   
    public Terreno(){
        areaC=0.0;
        tipo="";
        diametroCanalizacoes=0.0;
        redeEletrica=false;
        kWhMax=0.0;
        redeEsgotos=false;
    }
    public Terreno(double areaC, String tipo, double dCanal,
                   boolean redeE, double kWhMax, boolean redeEsgotos){
        setAreaC(areaC);
        setTipo(tipo);
        setDiametroCanalizacoes(dCanal);
        setRedeEletrica(redeE);
        setKWhMax(kWhMax);
        setRedeEsgotos(redeEsgotos);
    }
    public Terreno(Terreno t){
        areaC=t.getAreaC();
        tipo=t.getTipo();
        diametroCanalizacoes=t.getDiametroCanalizacoes();
        redeEletrica=t.getRedeEletrica();
        kWhMax=t.getKWhMax();
        redeEsgotos=t.getRedeEsgotos();
    }
    public double getAreaC(){
        return this.areaC;
    }
    public void setAreaC(double a){
        areaC=a;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String t){
        tipo=t;
    }
    public double getDiametroCanalizacoes(){
        return this.diametroCanalizacoes;
    }
    public void setDiametroCanalizacoes(double d){
        diametroCanalizacoes=d;
    }
    public boolean getRedeEletrica (){
        return this.redeEletrica;
    }
    public void setRedeEletrica (boolean r){
        redeEletrica=r;
    }
    public double getKWhMax (){
        return this.kWhMax;
    }
    public void setKWhMax(double k){
        kWhMax=k;
    }
    public boolean getRedeEsgotos(){
        return this.redeEsgotos;
    }
    public void setRedeEsgotos(boolean r){
        redeEsgotos=r;
    }
    public boolean equals(Terreno t){
        boolean r=false;
        if(areaC==t.getAreaC() && tipo==t.getTipo() && diametroCanalizacoes==t.getDiametroCanalizacoes()
           && redeEletrica==t.getRedeEletrica() && kWhMax==t.getKWhMax() && redeEsgotos==t.getRedeEsgotos()) r=true;
        return r;
    }
    public Terreno clone(){
        return new Terreno(this);
    }
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Área para construção: ").append(this.getAreaC()).append(". Tipo: ").append(this.getTipo())
          .append(". Diametro das canalizações: ").append(this.getDiametroCanalizacoes()).append(". Possui rede elétrica: ")
          .append(this.getRedeEletrica()).append(". kWatts máximos /hora: ").append(this.getKWhMax()).append("possui acesso à rede de esgotos")
          .append(this.getRedeEsgotos());
        return sb.toString();  
    }
}