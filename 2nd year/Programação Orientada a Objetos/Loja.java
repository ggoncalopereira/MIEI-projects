 /**
 * Representação da classe Loja, que contém todos os parâmetros de imóvel, e ainda outros
 * correspondentes ao seu próprio tipo.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */

public class Loja extends Imovel
{
    public double areaT;
    public boolean wc;
    public String tipoNegocio;
    public int nPorta;
    public boolean habitavel;
    public String tipo;
    public int nQuartos;
    public int nWc;
    public int andar;
    public boolean garagem;
   
    public Loja(){
        areaT=0.0;
        wc=false;
        tipoNegocio="";
        nPorta=0;
        habitavel=false;
        tipo="";
        nQuartos=0;
        nWc=0;
        andar=0;
        garagem=false;
    }
    public Loja(double areaT, boolean wc, String tipoNegocio, int nPorta, boolean habitavel,
                String tipo, int nQuartos, int nWc, int andar, boolean garagem){
        setAreaT(areaT);
        setWc(wc);
        setTipoNegocio(tipoNegocio);
        setNPorta(nPorta);
        setHabitavel(habitavel);
        setTipo(tipo);
        setNQuartos(nQuartos);
        setNWc(nWc);
        setAndar(andar);
        setGaragem(garagem);
    }
    public Loja(Loja a){
        areaT=a.getAreaT();
        wc=a.getWc();
        tipoNegocio=a.getTipoNegocio();
        nPorta=a.getNPorta();
        habitavel=a.getHabitavel();
        tipo=a.getTipo();
        nQuartos=a.getNQuartos();
        andar=a.getAndar();
        garagem=a.getGaragem();
    }
    public double getAreaT(){
        return this.areaT;
    }
    public void setAreaT(double a){
        areaT=a;
    }
    public boolean getWc(){
        return this.wc;
    }
    public void setWc(boolean wc){
        this.wc=wc;
    }
    public String getTipoNegocio(){
        return this.tipoNegocio;
    }
    public void setTipoNegocio(String t){
        tipoNegocio=t;
    }
    public int getNPorta(){
        return this.nPorta;
    }
    public void setNPorta(int n){
        nPorta=n;
    }
    public boolean getHabitavel(){
        return this.habitavel;
    }
    public void setHabitavel(boolean h){
        habitavel=h;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String t){
        tipo=t;
    }
    public int getNQuartos(){
        return this.nQuartos;
    }
    public void setNQuartos(int n){
        nQuartos=n;
    }
    public int getNWc(){
        return this.nWc;
    }
    public void setNWc(int n){
        nWc=n;
    }
    public int getAndar(){
        return this.andar;
    }
    public void setAndar(int a){
        andar=a;
    }
    public boolean getGaragem(){
        return this.garagem;
    }
    public void setGaragem(boolean g){
        garagem=g;
    }
    public boolean equals(Loja a){
        boolean r=false;
        if(areaT==a.getAreaT() && wc==a.getWc() && tipoNegocio==a.getTipoNegocio()
           && nPorta==a.getNPorta() && habitavel==a.getHabitavel() && tipo==a.getTipo()
           && nQuartos==a.getNQuartos() && nWc==a.getNWc() && andar==a.getAndar()
           && garagem==a.getGaragem()) r=true;
        return r;
    }
    public Loja clone(){
        return new Loja(this);
    }
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Área total: ").append(this.getAreaT()).append(". Possui wc: ").append(this.getWc())
          .append(". Tipo de Negócio: ").append(this.getTipoNegocio()).append(". Número da Porta: ").append(this.getNPorta())
          .append(". É habitável: ").append(this.getHabitavel());
        if(this.getHabitavel()==true){
          sb.append(". Tipo: ").append(this.getTipo()).append(". Número de Quartos: ").append(this.getNQuartos())
          .append(". Número de wc: ").append(this.getNWc()).append(". Andar: ").append(this.getAndar())
          .append(". Possui garagem: ").append(this.getGaragem());
        }
         
        return sb.toString();
    }
}