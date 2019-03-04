/**
 * Representação da classe Actores, com strings correspondentes a e-mail, nome,
 * palavra-passe, morada e data de nascimento para um actor.
 * 
 * @author (Gonçalo Pereira, Matias Araújo, Nuno Vieira) 
 * @version (27/04/2016)
 */
public class Actores
{
    private String email, nome, password, morada, datadenascimento;
    
    public Actores(){
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.datadenascimento = "";
    }
    
    public Actores (String email, String nome, String password, String morada, String
                  datadenascimento){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.datadenascimento = datadenascimento;
    }
    
    public Actores (Actores a){
        setEmail(a.getEmail());
        setNome(a.getNome());
        setPassword(a.getPassword());
        setMorada(a.getMorada());
        setData(a.getData());
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getMorada(){
        return this.morada;
    }
    
    public void setMorada(String morada){
        this.morada = morada;
    }
    
    public String getData(){
        return this.datadenascimento;
    }
    
    public void setData(String datadenascimento){
        this.datadenascimento = datadenascimento;
    }
    
}
