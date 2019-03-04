
/**
 * InfoFicheiro guarda todas as informações necessárias sobre o último ficheiro lido.
 * 
 * @author André Sousa Diogo, António Pedro Silva, Luís Gonçalo Epifânio Pereira
 * @version 9/6/2016
 */
import java.io.Serializable;
public class InfoFicheiro implements Serializable
{
    // instance variables - replace the example below with your own
    private String nomeFicheiro;
    private int vendasErradas;
    private int totalProdutos;
    private int totalProdutosC;
    private int totalProdutosNC;
    private int totalClientes;
    private int totalClientesC;
    private int totalClientesNC;
    private int comprasZero;
    private double factTotal;
    

    /**
     * Constructor for objects of class InfoFicheiro
     */
    public InfoFicheiro(String ficheiro)
    {
        nomeFicheiro=ficheiro;
        vendasErradas=0;
        totalProdutos=0;
        totalProdutosC=0;
        totalProdutosNC=0;
        totalClientes=0;
        totalClientesC=0;
        totalClientesNC=0;
        comprasZero=0;
        factTotal=0.0;
    }

    public void atualiza(){
        vendasErradas++;
    }
    
    public void atualiza(int totalProdutos,int totalProdutosC,int totalProdutosNC,
                         int totalClientes,int totalClientesC,int totalClientesNC,
                         int comprasZero,double factTotal) {
        this.totalProdutos=totalProdutos;
        this.totalProdutosC=totalProdutosC;
        this.totalProdutosNC=totalProdutosNC;
        this.totalClientes=totalClientes;
        this.totalClientesC=totalClientesC;
        this.totalClientesNC=totalClientesNC;
        this.comprasZero=comprasZero;
        this.factTotal=factTotal;    
    }
    
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("Último Ficheiro Lido: ").append(nomeFicheiro);
        sb.append("\nNúmero total de registos de venda errados: ").append(vendasErradas);
        sb.append("\nTotal Produtos no ficheiro: ").append(totalProdutos);
        sb.append("\nTotal Produtos comprados no ficheiro: ").append(totalProdutosC);
        sb.append("\nTotal Produtos não comprados do catálogo: ");
        sb.append(totalProdutosNC);
        sb.append("\nTotal Clientes no ficheiro: ").append(totalClientes);        
        sb.append("\nTotal Clientes compradores no ficheiro: ");
        sb.append(totalClientesC);
        sb.append("\nTotal Clientes não compradores do catálogo: ");
        sb.append(totalClientesNC);
        sb.append("\nTotal de compras de valor zero no ficheiro: ");
        sb.append(comprasZero);
        sb.append("\nTotal de facturação no ficheiro: ").append(factTotal);        
        return sb.toString();
    }
}
