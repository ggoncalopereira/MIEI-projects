package Sistema.Gestao.Despesas.LN;

import Sistema.Gestao.Despesas.LN.Subsistema.Pagamentos.SPagamento;
import Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores.AConta;
import Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores.SSenhorio;
import Sistema.Gestao.Despesas.Data.DespesaDAO;
import Sistema.Gestao.Despesas.Data.DividasDAO;
import Sistema.Gestao.Despesas.LN.Subsistema.Despesas.ADespesa;
import Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores.SMorador;
import Sistema.Gestao.Despesas.Data.MoradorDAO;
import java.util.List;
import java.util.Set;

public class Facade {
	private final MoradorDAO moradores;
	private final DespesaDAO despesas;
	private final DividasDAO dividasDAO;
	private AConta moradorAtual;

        public Facade() 
        {
            moradores= new MoradorDAO();
            despesas= new DespesaDAO();
            dividasDAO = new DividasDAO();
            moradorAtual=null;
        }
	/**
	 * 
	 * @param Morador
	 */
	public void adicionaMorador(SMorador morador) {
            moradores.put(morador.buscaNome(),morador);
	}

	/**
	 * 
	 * @param Despesa
	 */
	public void adicionaDespesa(ADespesa despesa) {
            despesas.insereDespesa(despesa);
	}

	/**
	 * 
	 * @param Despesa
	 * @param DespesaAlterada
	 */
	public void alteraDespesaGeral(ADespesa despesa, ADespesa despesaAlterada) {
            Set<String> nomes=moradores.keySet();
            /*for (String s : nomes) {
                despesas.put(s,despesaAlterada);
            }*/
	}

	public void alteraDespesaLocal(ADespesa despesa, ADespesa despesaAlterada) {
            Set<String> nomes=despesa.buscaMoradores();
            /*for (String s : nomes) {
                despesas.put(s,despesaAlterada);
            }*/
	}        
        
	/**
	 * 
	 * @param Despesa
	 */
	public void arquivaDespesa(ADespesa despesa) {
            Set<String> nomes=moradores.keySet();
            despesa.arquiva();
            /*for (String s : nomes) {
                despesas.put(s, despesa);
            }*/
	}

	/**
	 * 
	 * @param Divida
	 */
	public void arquivaDivida(SPagamento divida) {
            divida.atualizaPagamento();
	}

	/**
	 * 
	 * @param Morador
	 */
	public void atualizaMorador(SMorador Morador) {
            
	}

    /**
     * 
     * @param Senhorio
    */
    public void atualizaSenhorio(SSenhorio Senhorio) 
    {
        moradores.atualizaSenhorio(Senhorio);
    }

	public ADespesa buscaDespesa() {
		// TODO - implement Facade.buscaDespesa
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Nome
	 */
	public ADespesa buscaDespesaOcasional(String Nome) {
            
            throw new UnsupportedOperationException();
	}

	public List<ADespesa> buscaListaDespesas() {
		// TODO - implement Facade.buscaListaDespesas
		throw new UnsupportedOperationException();
	}

	public List<ADespesa> buscaListaDespesasAtivas() {
		// TODO - implement Facade.buscaListaDespesasAtivas
		throw new UnsupportedOperationException();
	}

	public List<ADespesa> buscaListaDespesasGerais() {
		// TODO - implement Facade.buscaListaDespesasGerais
		throw new UnsupportedOperationException();
	}

	public List<ADespesa> buscaListaDespesasGeraisAtivas() {
		// TODO - implement Facade.buscaListaDespesasGeraisAtivas
		throw new UnsupportedOperationException();
	}

    public List<ADespesa> buscaListaDespesasSuspensas() 
    {
        throw new UnsupportedOperationException();
    }

	public List<SPagamento> buscaListaDividas() {
		// TODO - implement Facade.buscaListaDividas
		throw new UnsupportedOperationException();
	}

    public List<SMorador> buscaListaMoradores() {
        return (List<SMorador>) moradores.values();
    }

	public List<SPagamento> buscaListaPagamentos() {
		// TODO - implement Facade.buscaListaPagamentos
		throw new UnsupportedOperationException();
	}

    public SSenhorio buscaSenhorio() 
    {
        return moradores.buscaSenhorio();
    }

	/**
	 * 
	 * @param Utilizador
	 */
	public boolean existeUtilizador(String Utilizador) {
            return moradores.containsKey(Utilizador);
        }

	public List<SPagamento> listaPagamentosDespesasGerais() {
		// TODO - implement Facade.listaPagamentosDespesasGerais
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param MoradorPagamento
	 * @param Pagamento
	 */
	public void registaDivida(String MoradorPagamento, SPagamento Pagamento) {
		// TODO - implement Facade.registaD�vida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Despesa
	 */
	public void removeDespesa(ADespesa Despesa) {
		// TODO - implement Facade.removeDespesa
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Morador
	 */
	public void removeMorador(SMorador Morador) {
		// TODO - implement Facade.removeMorador
		throw new UnsupportedOperationException();
	}

    /**
     * 
     * @param Utilizador
     * @param Password
     */
    public boolean validaLogin(String Utilizador, String Password) {
        boolean result=false;
        AConta conta=moradores.Login(Utilizador, Password);
        if (conta!=null)
        {
            moradorAtual=conta;
            result=true;
        }
        else
        {
            moradorAtual=null;
        }
        return result;
    }

    public AConta buscaMoradorAtual() 
    {
        return moradorAtual;
    }

    public void alteraDespesa(ADespesa despesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<SPagamento> buscaListaPagamentosGerais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}