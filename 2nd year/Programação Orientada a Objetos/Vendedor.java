/**
 * Representação da classe Vendedor, subclasse de Actores, com as mesmas strings que
 * a classe Actor tem e, adicionalmente, dois Sets para armazenar os imóveis disponíveis
 * para venda e os imóveis já vendidos anteriormente.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */

import java.util.Set;
import java.util.HashSet;

public class Vendedor extends Actores
{
    private Set<Imovel> paraVenda;
    private Set<Imovel> vendidos;
    
    public Vendedor(){
        super();
        paraVenda = new HashSet<>();
        vendidos = new HashSet<>();
    }
    
    public Vendedor(String email, String nome, String password,
                    String morada, String datadenascimento,
                    Set<Imovel> paraVenda, Set<Imovel> vendidos){
         super(email, nome, password, morada, datadenascimento);
         setParaVenda(paraVenda);
         setVendidos(vendidos); 
    }
    
    public Vendedor(Vendedor vendedor){
        super(vendedor.getEmail(), vendedor.getNome(), vendedor.getPassword(), vendedor.getMorada(), vendedor.getData());
        paraVenda = vendedor.getParaVenda();
        vendidos = vendedor.getVendidos();
    }
    
    public void setVendidos(Set<Imovel> vendidos) {
        this.vendidos = new HashSet<>();
        for(Imovel i : vendidos) {
            this.vendidos.add(i.clone());
        }
    }
    
    public void setParaVenda(Set<Imovel> paraVenda) {
        this.paraVenda = new HashSet<>();
        for(Imovel i : paraVenda) {
            this.paraVenda.add(i.clone());
        }
    }

    public Set<Imovel> getParaVenda() {
        return paraVenda;
    }
    
    public Set<Imovel> getVendidos() {
        return paraVenda;
    }
    
    protected Vendedor clone(){
        return new Vendedor(this);
    }
}
