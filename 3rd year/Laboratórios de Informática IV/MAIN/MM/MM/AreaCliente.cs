using System;
using System.Collections.Generic;
using Business;
using Orm;
using NHibernate;
using NHibernate.Criterion;

namespace MM
{
    public class AreaCliente
    {
        private Business.Cliente _cliente;
        private List<Business.Iguaria> _historico;
        private Dictionary<int, string> _tendencias;
        private Business.GPSVal _localizacao_cliente;

        private Direcoes _listaDirecoes;

        public string user { get { return _cliente.Nome; } }

        public Direcoes ListaDirecoes
        {
            get
            {
                return _listaDirecoes;
            }
            set
            {
                _listaDirecoes = value;
            }
        }

        public bool Login(string email, string password)
        {
            bool IsLoginValidated = false;


            UtilizadorCriteria utilizadorCriteria = new UtilizadorCriteria();
            utilizadorCriteria.Email.Eq(email);
            utilizadorCriteria.Password.Eq(password);

            Utilizador ut = utilizadorCriteria.UniqueUtilizador();

            if (ut != null)
            {
                Cliente cl = ut.Cliente;
                if (cl != null)
                {
                    _cliente.Nome = cl.Nome_cliente;
                    _cliente.Email = ut.Email;
                    _cliente.Password = ut.Password;
                    IsLoginValidated = true;
                }
            }

            return IsLoginValidated;
        }

        public void PublicarCritica(int idCliente, int idIguaria, int idEstabelecimento, string descricao,  decimal rating)
        {

            DateTime data = DateTime.Now;

            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {
                ClienteCriteria clienteCriteria = new ClienteCriteria();

                Cliente_critica_Iguaria cliente_critica_Iguaria = Cliente_critica_Iguaria.CreateCliente_critica_Iguaria();
                clienteCriteria.Id_cliente.Eq(idCliente);
                Cliente cli = clienteCriteria.UniqueCliente();
                IguariaCriteria iguariaCriteria = new IguariaCriteria();
                iguariaCriteria.Id_iguaria.Eq(idIguaria);
                Iguaria ig = iguariaCriteria.UniqueIguaria();
                EstabelecimentoCriteria estabelecimentoCiteria = new EstabelecimentoCriteria();
                estabelecimentoCiteria.Id_estabelecimento.Eq(idEstabelecimento);
                Estabelecimento est = estabelecimentoCiteria.UniqueEstabelecimento();

                cliente_critica_Iguaria.Rating_igu = rating;
                cliente_critica_Iguaria.Desc_critica = descricao;
                cliente_critica_Iguaria.Data_critica = data;
                cliente_critica_Iguaria.Cliente = cli;
                cliente_critica_Iguaria.Iguaria = ig;
                cliente_critica_Iguaria.Iguaria_Estabelecimento = idEstabelecimento;

                
                cliente_critica_Iguaria.Save();


                

                t.Commit();

            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }

        }

        public bool RegistarCliente(string email, string password, byte tipo, string nome)
        {
            bool IsRegistered = false;

            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {
                Utilizador utilizador = Utilizador.CreateUtilizador();
                utilizador.Email = email;
                utilizador.Password = password;
                utilizador.Tipo = tipo;
                utilizador.Save();

                Cliente cliente = Cliente.CreateCliente();
                cliente.Nome_cliente = nome;
                cliente.Utilizador = utilizador;

                
                cliente.Save();

                t.Commit();
                Preferencias preferencias = new Preferencias();
                _cliente = new Business.Cliente(email, password, tipo, nome, preferencias);
                _cliente.IdCliente = cliente.ORMID;
                IsRegistered = true;

               

            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
                IsRegistered = false;
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }
            return IsRegistered;
        }

        public void AtualizarCliente(string password, string nome)
        {
            ;

            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {

                ClienteCriteria clienteCriteria = new ClienteCriteria();
                
                clienteCriteria.Id_cliente.Eq(_cliente.IdCliente);
                Cliente cliente = clienteCriteria.UniqueCliente();

                cliente.Nome_cliente = nome;

                UtilizadorCriteria utilizadorCriteria = new UtilizadorCriteria();
                
                utilizadorCriteria.Email.Eq(_cliente.Email);

                Utilizador utilizador = utilizadorCriteria.UniqueUtilizador();
                utilizador.Password = password;


                
                utilizador.Save();
                cliente.Save();

                _cliente.Nome = nome;
                _cliente.Password = password;

                t.Commit();


            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }
        }

        public List<Iguaria> ConsultarHistorico(int idIguaria, int idEstabelecimento)
        {
            int id = _cliente.IdCliente;

            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {

               


                SelecaoIguariaCriteria selecaoIguariaCriteria = new SelecaoIguariaCriteria();
                selecaoIguariaCriteria.Cliente.Eq();

                

                

            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }

           



            return new List<Iguaria>();
        }

        public Estabelecimento EscolherIguaria(int idIguaria, int idEstabelecimento)
        {

            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {

                ClienteCriteria clienteCriteria = new ClienteCriteria();

                clienteCriteria.Id_cliente.Eq(_cliente.IdCliente);

                Cliente cli = clienteCriteria.UniqueCliente();

                EstabelecimentoCriteria estabelecimentoCriteria = new EstabelecimentoCriteria();
                estabelecimentoCriteria.Id_estabelecimento.Eq(idEstabelecimento);

                Estabelecimento est = estabelecimentoCriteria.UniqueEstabelecimento();

                IguariaCriteria iguariaCriteria = new IguariaCriteria();
                iguariaCriteria.Id_iguaria.Eq(idIguaria);
                Iguaria ig = iguariaCriteria.UniqueIguaria();


                
               


                SelecaoIguariaCriteria selecaoIguariaCriteria = new SelecaoIguariaCriteria();
                
                
                //cliente_seleciona_EstabelecimentoCriteria.estabelecimento.Eq();
                //cliente_seleciona_EstabelecimentoCriteria.cliente.Eq();

                SelecaoEstabelecimentoCriteria selecaoEstabelecimentoCriteria = new SelecaoEstabelecimentoCriteria();
               
                selecaoEstabelecimentoCriteria.Estabelecimento.Eq(idEstabelecimento);


                IguariaCriteria iguariaCriteria = new IguariaCriteria();
                iguariaCriteria.Id_iguaria.Eq(idIguaria);
                iguariaCriteria.Estabelecimento.Eq(idEstabelecimento);


            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }

            // NOTE : contrutor vazio não está implementado: há uma exceção se se usar
            return new Estabelecimento();
        }

        public List<Business.Iguaria> GerarPedido(string pedido)
        {


            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {

                ClienteCriteria clienteCriteria = new ClienteCriteria();
     
                clienteCriteria.Id_cliente.Eq(_cliente.IdCliente);

                //EstabelecimentoCriteria estabelecimentoCriteria = new EstabelecimentoCriteria();
                //estabelecimentoCriteria.id_estabelecimento.Eq();

                IguariaCriteria iguariaCriteria = new IguariaCriteria();
                iguariaCriteria.Nome_iguaria.Like("%" + pedido + "%");
                iguariaCriteria.ListIguaria();

                Iguaria[] list = iguariaCriteria.ListIguaria();

                List<Business.Iguaria> tmp = new List<Business.Iguaria>();

                for (int i = list.Length - 1; i >= 0; i--)
                {

                    ClienteCriteria cc = new ClienteCriteria();

                    Cliente_critica_Iguaria cliente_critica_Iguaria = Cliente_critica_Iguaria.CreateCliente_critica_Iguaria();
                    clienteCriteria.Id_cliente.Eq(_cliente.IdCliente);
                    Cliente cli = cc.UniqueCliente();
                    IguariaCriteria ic = new IguariaCriteria();
                    iguariaCriteria.Id_iguaria.Eq(list[i].Id_iguaria);
                    Iguaria ig = iguariaCriteria.UniqueIguaria();

                    tmp.Add(new Business.Iguaria(list[i].Nome_iguaria, list[i].Visual_iguaria, list[i].Rating_medio_iguaria, list[i].Fotografia, list[i].Preco, list[i].Id_iguaria, list[i].EstabelecimentoId);

                    if (i == list.Length - 5)
                        break;
                }



                // TODO: ordenar de acordo com preferencias de cliente
                //Distancia 

            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }

            
            return new List<Iguaria>();
        }

        public void PublicarFacebook()
        {
            throw new System.NotImplementedException();
        }

        public void PublicarTwiiter()
        {
            throw new System.NotImplementedException();
        }

        public void ConsultarPreferencias()
        {
           

            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {

                ClienteCriteria clienteCriteria = new ClienteCriteria();
                
                clienteCriteria.Id_cliente.Eq(_cliente.IdCliente);

                // TODO: obter lista de preferencias com base no id do cliente


                // _cliente.ListaPreferencias = resultado;

            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }

            
        }

        public void EditarPreferencias(byte _ordem_rating_iguaria, byte _ordem_rating_estabelecimento, byte _ordem_distancia, byte _ordem_popularidade_iguaria, byte _ordem_popularidade_estabelecimento)
        {
            Preferencias pref = new Preferencias(_ordem_rating_iguaria, _ordem_rating_estabelecimento, _ordem_distancia, _ordem_popularidade_iguaria, _ordem_popularidade_estabelecimento);

            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {
                ClienteCriteria clienteCriteria = new ClienteCriteria();
                
                clienteCriteria.Id_cliente.Eq(_cliente.IdCliente);

                // TODO: obter lista de preferencias com base no id do cliente
            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }

           
        }

        public void ReceberDirecoes(int id_estabelecimento)
        {
            int latitude = _localizacao_cliente.Latitude;
            int longitude = _localizacao_cliente.Longitude;

            

            // TODO: comunicar com servico REST Bing

        }

        public void ApagarConta()
        {
            PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
            try
            {


               
            }
            catch (Exception e)
            {
                t.RollBack();
                Console.WriteLine(e);
                Console.ReadLine();
            }
            finally
            {
                BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
            }
        }
    }
}