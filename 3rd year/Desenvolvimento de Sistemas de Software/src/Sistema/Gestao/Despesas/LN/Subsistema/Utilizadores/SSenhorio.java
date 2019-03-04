package Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores;

public class SSenhorio extends AConta {
	public String endereco;
	public String nome;

    public SSenhorio(String Username, 
                     String Password,
                     String Endereco,
                     String Nome)
    {
        super(Username, Password);
        endereco=Endereco;
        nome=Nome;
    }
}