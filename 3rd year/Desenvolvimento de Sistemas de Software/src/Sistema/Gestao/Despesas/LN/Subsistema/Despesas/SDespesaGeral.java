package Sistema.Gestao.Despesas.LN.Subsistema.Despesas;

import Sistema.Gestao.Despesas.LN.Subsistema.Pagamentos.SPagamento;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class SDespesaGeral extends ADespesa 
{

    public SDespesaGeral(Timestamp now,
                         boolean b,
                         String Nome,
                         EstadoDespesa estadoDespesa,
                         Map<String, List<SPagamento>> pagamentos) 
    {
        super(now, b, Nome, estadoDespesa, pagamentos);
    }
}