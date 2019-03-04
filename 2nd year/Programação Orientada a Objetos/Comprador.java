/**
 * Representação da classe Comprador, subclasse de Actores, com as mesmas strings que
 * a classe Actor tem e, adicionalmente, um Set para armazenar quais os imóveis preferidos
 * por um comprador.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */

import java.util.Set;
import java.util.HashSet;

public class Comprador extends Actores
{
    private Set<Imovel> favoritos;
    
    public Comprador(){
        super();
        favoritos = new HashSet<>();
    }
    
    public Comprador(String email, String nome, String password,
                    String morada, String datadenascimento,
                    Set<Imovel> favoritos){
         super(email, nome, password, morada, datadenascimento);
         setFavoritos(favoritos);
    }
    
    public Comprador(Comprador comprador){
        super(comprador.getEmail(), comprador.getNome(), comprador.getPassword(), comprador.getMorada(), comprador.getData());
        favoritos = comprador.getFavoritos();
    }
    
    public void setFavoritos(Set<Imovel> favoritos) {
        this.favoritos = new HashSet<>();
        for(Imovel i : favoritos) {
            this.favoritos.add(i.clone());
        }
    }

    public Set<Imovel> getFavoritos() {
        return favoritos;
    }

    protected Comprador clone(){
        return new Comprador(this);
    }
}

