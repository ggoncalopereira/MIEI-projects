package Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores;

public abstract class AConta {
    private String username;
    private String password;
        
    public AConta(String Username,String Password)
    {
        username=Username;
        password=Password;
    }
    
    public void alteraUser(String Username)
    {
        username=Username;
    }
    
    public void alteraPassword(String Password)
    {
        password=Password;
    }
    
    public String buscaUsername() 
    {
        return username;
    }

    public String buscaPassword() {
        return password;
    }
}