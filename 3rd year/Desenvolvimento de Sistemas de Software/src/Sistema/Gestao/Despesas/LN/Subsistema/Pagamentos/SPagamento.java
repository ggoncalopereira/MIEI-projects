package Sistema.Gestao.Despesas.LN.Subsistema.Pagamentos;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class SPagamento 
{
	private Timestamp Data;
	private float valor;
	private String nome;
	private Timestamp DataPagamento;

    public SPagamento(Timestamp Data, float Valor) 
    {
        this.Data=Data;
        valor=Valor;
        nome=null;
        DataPagamento=null;
    }

    public boolean atualizaPagamento() 
    {
        this.DataPagamento=new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        return true;
    }
        
        public boolean buscaPago(){
            return DataPagamento!=null;
        }
        
        public float buscaValor(){
            return this.valor;
        }
        
        public Timestamp buscaData(){
            return this.Data;
        }
        
        public Timestamp buscaDataPagamento(){
            return this.DataPagamento;
        }        
        
        public String buscaNome (){
            return this.nome;
        }
    
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append(Data.toString()).append(": Valor a pagar := ")
                                  .append(valor);
        if (DataPagamento!=null) 
        {
            sb.append(". Paga por:").append(nome).append(" em ").append(DataPagamento.toString());
        }
        return sb.toString();
    }
}
