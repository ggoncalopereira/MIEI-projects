package Sistema.Gestao.Despesas.Data;

import Sistema.Gestao.Despesas.LN.Subsistema.Despesas.ADespesa;
import Sistema.Gestao.Despesas.LN.Subsistema.Despesas.SDespesaLocal;
import Sistema.Gestao.Despesas.LN.Subsistema.Pagamentos.SPagamento;
import Sistema.Gestao.Despesas.SQL.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

public class DespesaDAO implements Map<String,Collection<ADespesa>> {

    @Override
    public int size() 
    {
        int size = -1;
        Connection con = null;
        try 
        {
            con = Connector.connect();
            PreparedStatement ps = con.prepareStatement("select count(Data) from Despesa");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) 
            {
                size = rs.getInt(1);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                con.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<ADespesa> get(Object key) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Collection<ADespesa> put(String s,Collection<ADespesa> d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }
    
    public boolean insereDespesa(ADespesa value) {
        Set<String> nomes = value.buscaMoradores();
        boolean falhou = false;
        Connection con = null;
        try { 
            con = Connector.connect();    
            /**
            * Atualizar tabela Despesa.
            */
            PreparedStatement ps = con.prepareStatement("INSERT INTO Despesa (Data,bOcasional,EstadoDespesa,bLocal,Descricao)\n" 
                                                        +"VALUES (?,?,?,?,?)\n");
            ps.setTimestamp(1, value.buscaData());
            ps.setBoolean(2, value.buscaOcasional());
            ps.setString(3, value.buscaEstado().toString());
            ps.setBoolean(4, value.buscaLocal());
            ps.setString(5, value.buscaDescricao().toString());
            ps.executeUpdate();       
            
            if (value instanceof SDespesaLocal) {          
                    /**
                    * Atualizar tabela MoradorDespesa.
                    */
                    for(String nome : nomes) 
                    {
                        PreparedStatement ps2 
                                = con.prepareStatement("INSERT INTO MoradorDespesa "+
                                                       "(Nome,Despesa,votoRemover,votoValidar)\n" 
                                                       +"VALUES (?,?,?,?)\n");
                        ps2.setString(1, nome);
                        ps2.setTimestamp(2, value.buscaData());
                        ps2.setBoolean(3, false);
                        ps2.setBoolean(4, false);
                        ps2.executeUpdate();
                    }
            } 
            /**
            * Atualizar tabela Pagamento.
            */
            for(SPagamento pagamento : value.buscaPagamentos()) 
            {
                PreparedStatement ps3 = 
                        con.prepareStatement("INSERT INTO Pagamento "+
                                             "(Data,Valor,DataPagamento,Nome)\n" 
                                             +"VALUES (?,?,?,?)\n");
                ps3.setTimestamp(1, pagamento.buscaData());
                ps3.setFloat(2, pagamento.buscaValor());
                ps3.setNull(3, Types.TIMESTAMP);
                ps3.setNull(4, Types.VARCHAR);
                ps3.executeUpdate();    
            }
            
            /**
            * Atualizar tabela MoradorDespesaPagamento.
            */
            for(String nome : nomes) 
            {
                for(SPagamento pagamento : value.buscaPagamentos(nome)) 
                {
                    PreparedStatement ps4 
                        = con.prepareStatement("INSERT INTO MoradorDespesaPagamento" +
                                               "(Nome,Despesa,Pagamento)\n" 
                                               +"VALUES (?,?,?)\n");
                    ps4.setString(1, nome);
                    ps4.setTimestamp(2, value.buscaData());
                    ps4.setTimestamp(3, pagamento.buscaData());
                    ps4.executeUpdate();
                }                 
            }
        } catch (Exception e) {
           e.printStackTrace();
           falhou=true;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return !falhou;
    }

        public ADespesa alteraDespesa(String nome, ADespesa value) {
        Connection con = null;
        try { 
            con = Connector.connect();    
            /**
            * Atualizar tabela Despesa.
            */
            PreparedStatement ps = con.prepareStatement("Update Despesa (Data,bOcasional,EstadoDespesa,bLocal,Descricao)\n"
                                                       +"SET bOcasional=?,EstadoDespesa=?,bLocal=?,Descricao=?\n"
                                                       +"Where Data=?\n");
            ps.setBoolean(1, value.buscaOcasional());
            ps.setString(2, value.buscaEstado().toString());
            ps.setBoolean(3, value.buscaLocal());
            ps.setString(4, value.buscaDescricao());
            ps.setString(5, (value.buscaData()).toString());
            ps.executeUpdate();       
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return value;
    }
    
    @Override
    public Collection<ADespesa> remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Collection<ADespesa>> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<String, Collection<ADespesa>>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public void putAll(Map<? extends String, ? extends Collection<ADespesa>> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
