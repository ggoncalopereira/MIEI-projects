package Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores;


import java.sql.Date;
import java.sql.Timestamp;

public class SMorador extends AConta
{
    private Date dataDeResidencia;
    private String nome;

    public SMorador(String Username,
                    String Password,
                    Date DataDeResidencia,
                    String Nome)
    {
        super(Username, Password);
        dataDeResidencia=DataDeResidencia;
        nome=Nome;
    }

    public String buscaNome() 
    {
    	return this.nome;
    }
    
    public Date buscaDataDeResidencia (){
        return this.dataDeResidencia;
    }


}