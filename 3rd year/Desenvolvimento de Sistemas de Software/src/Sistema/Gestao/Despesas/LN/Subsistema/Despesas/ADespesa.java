package Sistema.Gestao.Despesas.LN.Subsistema.Despesas;

import Sistema.Gestao.Despesas.LN.Subsistema.Pagamentos.SPagamento;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ADespesa 
{
    private Timestamp dataDespesa;
    private boolean ocasional;
    private String descricao;
    private EstadoDespesa estado;
    private Map<String,List<SPagamento>> pagamentos;

    public ADespesa(Timestamp now,
                         boolean b,
                         String Nome,
                         EstadoDespesa estadoDespesa,
                         Map<String,List<SPagamento>> pagamentos) 
    {
        dataDespesa=now;
        ocasional=b;
        descricao=Nome;
        estado=estadoDespesa;
        this.pagamentos=pagamentos;
    }

    public void arquiva(){
        this.estado=EstadoDespesa.DespesaArquivada;
    }
        
        protected void mudaParaAtiva(){
            this.estado=EstadoDespesa.DespesaAtiva;
        }
        
        protected void mudaParaSuspensa(){
            this.estado=EstadoDespesa.DespesaSuspensa;
        }            
        
	public String buscaInformacoes () {
            return descricao;
	}

        public List<SPagamento> buscaPagamentos() 
        {
            List<SPagamento> resultados= new ArrayList<>(10);
            pagamentos.values().stream().forEach(l->l.stream().forEach(resultados::add));
            return resultados;
        }
        
        public List<SPagamento> buscaPagamentos(String moradorDespesa) {
            return pagamentos.get(moradorDespesa);
	}

	public boolean haPagamento ()
        {
            return pagamentos.values()
                             .stream()
                             .anyMatch(
                                (p) -> (p.stream()
                                         .anyMatch(pag->pag.buscaPago())));
	}

	public boolean estaArquivada(){
            return this.estado==EstadoDespesa.DespesaArquivada;
	}

	public boolean estaAtiva(){
		return this.estado==EstadoDespesa.DespesaAtiva;
	}

	public boolean estaSuspensa() {
		return this.estado==EstadoDespesa.DespesaSuspensa;
	}

	public Set<String> buscaMoradores() {
            return pagamentos.keySet();
	}

	public Timestamp buscaData(){
            return this.dataDespesa;
	}
        
        public String buscaDescricao() {
            return this.descricao;
        }
        
        public boolean buscaOcasional(){
            return this.ocasional;
        }
        
        public boolean buscaLocal(){
            return this.ocasional;
        }
        
        public EstadoDespesa buscaEstado() {
            return this.estado;
        }

}