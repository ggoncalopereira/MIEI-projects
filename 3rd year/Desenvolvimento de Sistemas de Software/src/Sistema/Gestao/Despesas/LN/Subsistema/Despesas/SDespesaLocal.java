package Sistema.Gestao.Despesas.LN.Subsistema.Despesas;

import Sistema.Gestao.Despesas.LN.Subsistema.Pagamentos.SPagamento;
import Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores.SMorador;
import java.util.List;
import java.sql.Timestamp;
import java.util.Map;

public class SDespesaLocal extends ADespesa {
    private SDespesaLocal DespesaOriginal;
    private SDespesaLocal[] DespesasAlteradas;
    private Map<String,Boolean> votosValidar;
    private Map<String,Boolean> votosRemover;

    public SDespesaLocal(Timestamp now,
                         boolean b,
                         String Nome,
                         EstadoDespesa estadoDespesa,
                         Map<String,List<SPagamento>> pagamentos) 
    {
        super(now,b,Nome,estadoDespesa,pagamentos);
        DespesaOriginal=null;
        DespesasAlteradas=null;
        pagamentos.keySet()
                  .forEach(k->{votosValidar.put(k, false);votosRemover.put(k, false);});
    }

	public void ativa(){
            super.mudaParaAtiva();
	}
        
	public void suspende(){
            super.mudaParaSuspensa();
	}        

	public boolean paraRemover() {
            return votosRemover.values().stream().allMatch(a->a==true);
	}

        public boolean porValidar() {
            return votosValidar.values().stream().anyMatch(a->a==false);
	}
        
        /*
	public void remove() {

	}
        */
	public void valida(SMorador morador) {
            votosValidar.replace(morador.buscaNome(), true);
	}
        
	public void votoRemocao(SMorador morador) {
            votosRemover.replace(morador.buscaNome(),true);
	}
        
    public Map<String,Boolean> buscaVotosValidar() 
    {
        return votosValidar;
    }

    public Map<String,Boolean> buscaVotosRemover() {
        return votosRemover;
    }


}