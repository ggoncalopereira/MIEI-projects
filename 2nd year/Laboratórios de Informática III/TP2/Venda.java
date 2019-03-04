
/**
 * Classe com todas as informações parsed duma linha de vendas.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.io.Serializable;
public class Venda implements Serializable
{
    // instance variables - replace the example below with your own
    private int quant,mes,filial;
    private double preco;
    private boolean promocao;
    private String cliente,produto;

    /**
     * Constructor for objects of class Venda
     */
    public Venda()
    {
        this(0,0,0,0.0,false,"","");
    }

    public Venda(int quant,int mes,int filial,double preco,boolean promocao,String cliente,String produto)
    {
        this.quant=quant;
        this.mes=mes;
        this.filial=filial;
        this.preco=preco;
        this.promocao=promocao;
        this.cliente=cliente;
        this.produto=produto;
    }
    
    public Venda(Venda v){
        this(v.getQuant(),v.getMes(),v.getFilial(),v.getPreco(),
             v.getPromocao(),v.getCliente(),v.getProduto());
    }
    
    
    public int getQuant() {
        return this.quant;
    }
    
    public int getMes(){
        return this.mes;
    }
    
    public int getFilial(){
        return this.filial;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    public boolean getPromocao(){
        return this.promocao;
    }
    
    public double getFact() {
        return quant*preco;
    }

    public String getCliente(){
        return this.cliente;
    }
    
    public String getProduto(){
        return this.produto;
    }
    
    public boolean equals(Venda v) {
        if (v!=null)
            if(this.cliente.equals(v.getCliente()) &&
               this.produto.equals(v.getProduto()) &&
               this.preco == v.getPreco() &&
               this.promocao == v.getPromocao() &&
               this.mes == v.getMes() && this.quant == v.getQuant() &&
               this.filial == v.getFilial())
               return true;
        return false;
    }
    
    public Venda clone(){
        return new Venda(this);
    }
    
}
