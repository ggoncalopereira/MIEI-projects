using System;
using System.Collections.Generic;
using Business;

public class SampleCenterControl
{

    static void Main(string[] args)
    {
        try
        {


            Console.WriteLine("A iniciar ... aguarde!");


            /**************** Estabelecimento *******************/


            /**
             ************************************
             * 
             * Registar Estabelecimento
             * 
             ************************************
             */



            //List<Horario> horarios = new List<Horario>();

            //for (byte i = 1; i < 8; i++)
            //{

            //    horarios.Add(new Horario(i, new TimeSpan(9, 0, 0), new TimeSpan(23, 59, 00)));
            //}

            // AreaEstabelecimento es = new AreaEstabelecimento();

            //es.RegistarEstabelecimento("julio@mail.pt", "abc123", "Acolhedor. Informal.", "Real Taberna", 253063600, horarios, new Endereco("4700-262", "Real",  497, "R. Costa Gomes"), 1);




            /**
            ***********************************
            * 
            * Login Estabelecimento
            * 
            *
            **********************************/

            //es.Login("julio@mail.pt", "abc123");




            /**
            ************************************
            * 
            * Registar Ementa
            * 
            ************************************ 
            */

            //byte[] foto = new byte[] { 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20 };
            //Dictionary<int, Business.Iguaria> menu = new Dictionary<int, Business.Iguaria>
            //{
            //    { 1, new Business.Iguaria("Bifana", foto, 5.5M, 1, es.AreaEstabelecimentoEstabelecimento.IdEstabelecimento, IguariaStatus.ToInsert) },
            //    { 2, new Business.Iguaria("Caldo Verde", foto, 5.5M, 2, es.AreaEstabelecimentoEstabelecimento.IdEstabelecimento, IguariaStatus.ToInsert) },
            //    { 3, new Business.Iguaria("Broa", foto, 5.5M, 3, es.AreaEstabelecimentoEstabelecimento.IdEstabelecimento, IguariaStatus.ToInsert) },
            //    { 4, new Business.Iguaria("Bacalhau à Gomes de Sá", foto, 5.5M, 4, es.AreaEstabelecimentoEstabelecimento.IdEstabelecimento, IguariaStatus.ToInsert) },
            //    { 5, new Business.Iguaria("Cabrito Assado", foto, 5.5M, 5, es.AreaEstabelecimentoEstabelecimento.IdEstabelecimento, IguariaStatus.ToInsert) },
            //    { 6, new Business.Iguaria("Tripas à moda do Porto", foto, 5.5M, 6, es.AreaEstabelecimentoEstabelecimento.IdEstabelecimento, IguariaStatus.ToInsert) },
            //    { 7, new Business.Iguaria("Francesinha Especial", foto, 5.5M, 8, es.AreaEstabelecimentoEstabelecimento.IdEstabelecimento, IguariaStatus.ToInsert) }
            //};
            //es.RegistarEditarEmenta(menu);




            /**
            ************************************
            * 
            * Consultar Ementa
            * 
            ************************************ 
            */

            //es.CarregarEmenta();


            //Dictionary<int, Business.Iguaria> list = es.AreaEstabelecimentoEstabelecimento.IguariasMap;
            //foreach (var item in list)
            //{
            //    Console.WriteLine("Iguaria : {0}", item.Value.Nome);
            //}





            /**************** Cliente *******************/




            AreaCliente cl = new AreaCliente();



            /**
            ************************************
            * 
            * Registar Cliente
            * 
            ************************************ 
            */
            //cl.RegistarCliente("to_silva@mail.com", "mm", "António Silva");


            /**
             * 
             * 
             * Login Cliente
             * 
             * 
             */

            //cl.Login("to_silva@mail.com", "mm");


            /**
             * 
             * 
             * Atualizar Cliente
             * 
             * 
             */

            //cl.AtualizarCliente("m1", "António");

            /**
             * 
             * 
             * Consultar Preferencias
             * 
             * 
             */ 
            //cl.Login("to_silva@mail.com", "m1");
            //Console.WriteLine("Consultar preferências");
            //cl.ConsultarPreferencias();
            //Console.WriteLine(cl.AreaClienteCliente.ToString());



            /**
             * 
             * 
             * Editar Preferencias
             * 
             * 
             */
            //Console.WriteLine("Editar preferências");

            //cl.EditarPreferencias(0, 0, 1, 0, 1);
            //Console.ReadLine();
            //Console.WriteLine(cl.AreaClienteCliente.ToString());
            //Console.ReadLine();
            //Console.WriteLine("Consultar preferências");
            //cl.ConsultarPreferencias();
            //Console.WriteLine(cl.AreaClienteCliente.ToString());



            /**
             * 
             * 
             * Gerar Pedido
             * 
             * 
             */
            double latitude = 41.562901f;
            double longitude = -8.402888f;
            //Console.WriteLine("Necessária a sua localização geográfica para efetuar pedido!");
            //Console.WriteLine("Escreva a latitude!");
            //latitude = Double.Parse(Console.ReadLine());
            //Console.WriteLine("Escreva a longitude!");
            //longitude = Double.Parse(Console.ReadLine());
            //Business.Iguaria[] igu = cl.GerarPedido("Bife", latitude, longitude).ToArray();
            //foreach (var item in igu)
            //{
            //    Console.WriteLine(item.ToString());
            //}



            /**
             * 
             * 
             * Escolher Iguaria 
             * 
             * 
             */
            int idIguaria = 0;
            int idEstabelecimento = 0;
            Console.WriteLine("Digite um ID iguaria");
            idIguaria = Int32.Parse(Console.ReadLine());
            Console.WriteLine("Digite um ID Estabelecimento");
            idEstabelecimento = Int32.Parse(Console.ReadLine());
            Business.Estabelecimento estab = cl.EscolherIguaria(idIguaria, idEstabelecimento);

            Console.WriteLine(estab.ToString());
            Console.ReadLine();


            /**
             * 
             * 
             *  Publicar Critica
             * 
             */
            Console.WriteLine("Publicar critica??(0-N, 1-S)");
            int pub = Int32.Parse(Console.ReadLine());
            decimal ratingEstabelecimento = 0;
            decimal ratingIgu = 0;

            //decimal latitude = 0;
            //decimal longitude = 0;
            string descricao = "";
            switch (pub)
            {
                case 0:
                    break;

                case 1:
                    Console.WriteLine("Escreva a critica!");
                    descricao = Console.ReadLine();
                    Console.WriteLine("Avalie o estabelecimento!");
                    ratingEstabelecimento = Decimal.Parse(Console.ReadLine());
                    Console.WriteLine("Avalie a Iguaria!");
                    ratingIgu = Decimal.Parse(Console.ReadLine());
                    cl.PublicarCritica(estab.IguariasMap[idIguaria].IdIguaria, estab.IdEstabelecimento, descricao, ratingEstabelecimento, ratingIgu);
                    break;
            }




            /**
             * 
             * 
             * Pedir Direçoes
             * 
             * 
             */
            //pub = -1;
            //Business.Direcoes dir = new Direcoes();
            //Console.WriteLine("Deseja pedir direçoes??(0-N, 1-S)");
            //pub = Int32.Parse(Console.ReadLine());

            //switch (pub)
            //{
            //    case 0:
            //        break;

            //    case 1:

            //        Console.WriteLine("Escreva a latitude!");
            //        latitude = Double.Parse(Console.ReadLine());
            //        Console.WriteLine("Escreva a longitude!");
            //        longitude = Double.Parse(Console.ReadLine());
            //        Console.WriteLine("A procurar direcoes .. Aguarde!");
            //        dir = cl.PedirDirecoes(estab, latitude, longitude);
            //        if (dir.ListaDirecoes != null && dir.ListaDirecoes.Count > 0)
            //        {

            //            foreach (var item in dir.ListaDirecoes)
            //            {
            //                Console.WriteLine(item);
            //            }

            //        }

            //        break;
            //}





            /**
             * 
             * 
             * Cosultar Histórico
             * 
             * 
             */
            //Console.WriteLine("Histórico");
            //Console.ReadLine();
            //Business.Iguaria[] ls = cl.ConsultarHistorico().ToArray();

            //foreach (var item in ls)
            //{
            //    Console.WriteLine(item.ToString());
            //    Console.WriteLine();
            //}



            /**
             * 
             * 
             * Consultar Tendências
             * 
             * 
             */
            //Console.WriteLine();
            //Console.WriteLine();
            //Console.WriteLine("Tendências");
            //Console.ReadLine();

            //List<string>[] matrix = cl.ListarTendencias().ToArray();

            //Console.WriteLine("Rating Iguarias\n --------------");
            //foreach (var item in matrix[0])
            //{
            //    Console.WriteLine(item);
            //}
            //Console.WriteLine();
            //Console.WriteLine("Popularidade Iguarias\n --------------");
            //foreach (var item in matrix[1])
            //{
            //    Console.WriteLine(item);
            //}
            //Console.WriteLine();
            //Console.WriteLine("Rating Estabelecimentos\n --------------");
            //foreach (var item in matrix[2])
            //{
            //    Console.WriteLine(item);
            //}
            //Console.WriteLine();
            //Console.WriteLine("Popularidade Estabelecimentos\n --------------");
            //foreach (var item in matrix[3])
            //{
            //    Console.WriteLine(item);
            //}
            //Console.WriteLine();




            //Console.WriteLine(estab.ToString());

            //Console.ReadLine();
        }
        finally
        {
            BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
        }

    }

}

