/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema.Gestao.Despesas.Data;

import Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores.AConta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Sistema.Gestao.Despesas.SQL.Connector;
import Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores.SMorador;
import Sistema.Gestao.Despesas.LN.Subsistema.Utilizadores.SSenhorio;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Andre
 */
public class MoradorDAO implements Map<String,SMorador> 
{

    @Override
    public int size() 
    {
        int size = -1;
        Connection con = null;
        try 
        {
            con = Connector.connect();
            PreparedStatement ps = con.prepareStatement("select count(Nome) from morador");
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
        Connection con = null;
        boolean result =false;
        String Username=(String)key;
        try { 
            con = Connector.connect();    
            /**
            * Atualizar tabela Morador.
            */
            PreparedStatement ps = con.prepareStatement("SELECT Username FROM Conta where Username=?"); 
            ps.setString(1, Username);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) 
            {
                result=true;
            }        
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }       
        return result;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SMorador get(Object key) {
        Connection con = null;
        String Username =(String) key;
        SMorador morador=null;
        try { 
            con = Connector.connect();    
            /**
            * Atualizar tabela Morador.
            */
            PreparedStatement ps = con.prepareStatement("SELECT Username,Password,Morador FROM Conta where Username=?"); 
            ResultSet rs=ps.executeQuery();
            if(rs.next()) 
            {
            }        
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                morador=null;
            }
        }       
        return morador;
    }

    @Override
    public SMorador put(String key, SMorador value) {
        Connection con = null;
        try { 
            con = Connector.connect();    
            /**
            * Atualizar tabela Morador.
            */
            PreparedStatement ps = con.prepareStatement("INSERT INTO Morador (Nome,Data,Removido)\n" 
                                                        +"VALUES (?,?,?)\n");
            ps.setString(1, value.buscaNome());
            ps.setDate(2, value.buscaDataDeResidencia());
            ps.setBoolean(3,false);
            ps.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO Conta (Username,Password,Morador,Senhorio)\n" 
                                                         + "VALUES (?,?,?,?)");
            ps2.setString(1, value.buscaUsername());
            ps2.setString(2,value.buscaPassword());
            ps2.setString(3, value.buscaNome());
            ps2.setNull(4, Types.VARCHAR);
            ps2.executeUpdate();        
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                value=null;
            }
        }       
        return value;
    }
    
    

    @Override
    public SMorador remove(Object o) {
         String nome=(String)o;
         Connection con = null;
        try { 
            con = Connector.connect();    
            /**
            * Atualizar tabela Morador.
            */
            PreparedStatement ps = con.prepareStatement("Update Morador (Nome,Data,Removido)\n"
                                                       +"SET Removido=?\n"
                                                       +"Where Nome=?\n");
            ps.setBoolean(1,true);
            ps.setString(2, nome);
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
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends SMorador> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        int i=0;
        Set<String> res = null;
        String nome = null;
            Connection con = null;
            try 
            {
                con = Connector.connect();
                PreparedStatement ps = con.prepareStatement("select Nome from morador");
                ResultSet rs = ps.executeQuery();
                while(rs.next()) 
                {
                    nome = rs.getString(1);
                    res.add(nome);
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
        return res;
    }

    @Override
    public Collection<SMorador> values() {
        Connection con = null;
        Collection<SMorador> moradores=null;
        try { 
            con = Connector.connect();    
            /**
            * Atualizar tabela Morador.
            */
            PreparedStatement ps = con.prepareStatement("SELECT Username,Password,Morador,Data,Removido FROM Conta join Morador on Morador=Nome"); 
            ResultSet rs=ps.executeQuery();
            moradores= new ArrayList<>(10);
            while(rs.next()) 
            {
                if(!rs.getBoolean(5)) 
                {
                    moradores.add(new SMorador(rs.getString(1),rs.getString(2),rs.getDate(4),rs.getString(3)));
                }
            }        
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                moradores=null;
            }
        }       
        return moradores;
    }
    
    @Override
    public Set<Entry<String, SMorador>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SSenhorio buscaSenhorio() 
    {
        Connection con=null;
        SSenhorio res=null;
        String nome=null;
        String endereco=null;
        try 
        {
            con = Connector.connect();
            PreparedStatement ps = con.prepareStatement("select Nome,Endereco from Senhorio");
            ResultSet rs= ps.executeQuery();
            if(rs.next()) 
            {
                endereco=rs.getString(2); 
                nome=rs.getString(1);
                PreparedStatement ps2 = con.prepareStatement("select Username,Password from conta where Senhorio=?");
                ps2.setString(1, nome);
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()) 
                {
                    res= new SSenhorio(rs2.getString(1),rs2.getString(2),endereco,nome);
                }
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
        return res;
    }

    public void atualizaSenhorio(SSenhorio Senhorio) {
        Connection con = null;
        try { 
            con = Connector.connect();    
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO Senhorio (Nome,Endereco)" 
                                                        +"VALUES (?,?) ON DUPLICATE KEY UPDATE Endereco=?");
            ps.setString(1, Senhorio.nome);
            ps.setString(2, Senhorio.endereco);
            ps.setString(3, Senhorio.endereco);
            ps.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement("Select Username from Conta where Username=?");
            ps2.setString(1,Senhorio.buscaUsername());
            ResultSet rs=ps2.executeQuery();
            if(!rs.next()) 
            {
                PreparedStatement ps3 = con.prepareStatement("Select Username from Conta where Senhorio=?"); 
                ps3.setString(1, Senhorio.nome);
                ps3.executeQuery();
                ResultSet rs2=ps3.executeQuery();
                if(rs2.next()) 
                {
                    PreparedStatement ps5 = con.prepareStatement("DELETE FROM Conta where Username=?"); 
                    ps5.setString(1, rs2.getString(1));
                    ps5.executeUpdate();
                }                
            }
            PreparedStatement ps4 = con.prepareStatement("INSERT INTO Conta (Username,Password,Morador,Senhorio)" 
                                                         + "VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE Password=?");
            ps4.setString(1, Senhorio.buscaUsername());
            ps4.setString(2,Senhorio.buscaPassword());
            ps4.setNull(3, Types.VARCHAR);
            ps4.setString(4, Senhorio.nome);
            ps4.setString(5,Senhorio.buscaPassword());
            ps4.executeUpdate();        
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }       
    }

    public AConta Login(String Utilizador, String Password) {
        int i=0;
        AConta res = null;
        Connection con = null;
        String Morador=null;
        String Senhorio=null;
        try 
        {
            con = Connector.connect();
            PreparedStatement ps = con.prepareStatement("select Morador,Senhorio from conta where Username=? and Password=?");
            ps.setString(1, Utilizador);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) 
            {
                    Morador = rs.getString(1);
                    Senhorio = rs.getString(2);
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        try 
        {
            if (Morador!=null || Senhorio !=null)
            {
                if (Morador!=null)
                {
                    PreparedStatement ps = con.prepareStatement("select Data,Removido from Morador where Nome=?");
                    ps.setString(1,Morador);
                    ResultSet rs= ps.executeQuery();
                    if(rs.next()) 
                    {
                        if(!rs.getBoolean(2)) 
                        {
                            Date t=rs.getDate(1);
                            res=new SMorador(Utilizador, Password, t, Morador);
                        }
                    }
                }
                else 
                {
                    PreparedStatement ps = con.prepareStatement("select Endereco from Senhorio where Nome=?");
                    ps.setString(1,Senhorio);
                    ResultSet rs= ps.executeQuery();
                    if(rs.next()) 
                    {
                        String endereco=rs.getString(1); 
                        res=new SSenhorio(Utilizador, Password, endereco, Senhorio);
                    }
                }
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
        return res;
    }
}
