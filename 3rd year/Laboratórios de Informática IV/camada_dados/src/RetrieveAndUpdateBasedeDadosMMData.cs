using System;
using Orm;

public class RetrieveAndUpdateBasedeDadosMMData {
	private void RetrieveAndUpdateData() {
		PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
		try {
			Estabelecimento estabelecimento = Estabelecimento.LoadEstabelecimentoByQuery(null, null);
			// Update the properties of the persistent object
			estabelecimento.Save();
			Iguaria iguaria = Iguaria.LoadIguariaByQuery(null, null);
			// Update the properties of the persistent object
			iguaria.Save();
			Cliente cliente = Cliente.LoadClienteByQuery(null, null);
			// Update the properties of the persistent object
			cliente.Save();
			Utilizador utilizador = Utilizador.LoadUtilizadorByQuery(null, null);
			// Update the properties of the persistent object
			utilizador.Save();
			Categoria categoria = Categoria.LoadCategoriaByQuery(null, null);
			// Update the properties of the persistent object
			categoria.Save();
			Cliente_critica_Iguaria cliente_critica_Iguaria = Cliente_critica_Iguaria.LoadCliente_critica_IguariaByQuery(null, null);
			// Update the properties of the persistent object
			cliente_critica_Iguaria.Save();
			Cliente_seleciona_iguaria cliente_seleciona_iguaria = Cliente_seleciona_iguaria.LoadCliente_seleciona_iguariaByQuery(null, null);
			// Update the properties of the persistent object
			cliente_seleciona_iguaria.Save();
			SelecaoIguaria selecaoIguaria = SelecaoIguaria.LoadSelecaoIguariaByQuery(null, null);
			// Update the properties of the persistent object
			selecaoIguaria.Save();
			HorarioEstabelecimento horarioEstabelecimento = HorarioEstabelecimento.LoadHorarioEstabelecimentoByQuery(null, null);
			// Update the properties of the persistent object
			horarioEstabelecimento.Save();
			Cliente_seleciona_Estabelecimento cliente_seleciona_Estabelecimento = Cliente_seleciona_Estabelecimento.LoadCliente_seleciona_EstabelecimentoByQuery(null, null);
			// Update the properties of the persistent object
			cliente_seleciona_Estabelecimento.Save();
			Cliente_avalia_Estabelecimento cliente_avalia_Estabelecimento = Cliente_avalia_Estabelecimento.LoadCliente_avalia_EstabelecimentoByQuery(null, null);
			// Update the properties of the persistent object
			cliente_avalia_Estabelecimento.Save();
			SelecaoEstabelecimento selecaoEstabelecimento = SelecaoEstabelecimento.LoadSelecaoEstabelecimentoByQuery(null, null);
			// Update the properties of the persistent object
			selecaoEstabelecimento.Save();
			t.Commit();
		}
		catch(Exception e) {
			t.RollBack();
			Console.WriteLine(e);
		}
		
	}
	
	public void RetrieveByCriteria() {
		System.Console.WriteLine("Retrieving Estabelecimento by EstabelecimentoCriteria");
		EstabelecimentoCriteria estabelecimentoCriteria = new EstabelecimentoCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//estabelecimentoCriteria.id_estabelecimento.Eq();
		System.Console.WriteLine( estabelecimentoCriteria.UniqueEstabelecimento());
		
		System.Console.WriteLine("Retrieving Iguaria by IguariaCriteria");
		IguariaCriteria iguariaCriteria = new IguariaCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//iguariaCriteria.id_iguaria.Eq();
		//iguariaCriteria.estabelecimento.Eq();
		//iguariaCriteria.estabelecimento_id_estabelecimento.Eq();
		System.Console.WriteLine( iguariaCriteria.UniqueIguaria());
		
		System.Console.WriteLine("Retrieving Cliente by ClienteCriteria");
		ClienteCriteria clienteCriteria = new ClienteCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//clienteCriteria.id_cliente.Eq();
		System.Console.WriteLine( clienteCriteria.UniqueCliente());
		
		System.Console.WriteLine("Retrieving Utilizador by UtilizadorCriteria");
		UtilizadorCriteria utilizadorCriteria = new UtilizadorCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//utilizadorCriteria.email.Eq();
		System.Console.WriteLine( utilizadorCriteria.UniqueUtilizador());
		
		System.Console.WriteLine("Retrieving Categoria by CategoriaCriteria");
		CategoriaCriteria categoriaCriteria = new CategoriaCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//categoriaCriteria.id_categoria.Eq();
		System.Console.WriteLine( categoriaCriteria.UniqueCategoria());
		
		System.Console.WriteLine("Retrieving Cliente_critica_Iguaria by Cliente_critica_IguariaCriteria");
		Cliente_critica_IguariaCriteria cliente_critica_IguariaCriteria = new Cliente_critica_IguariaCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//cliente_critica_IguariaCriteria.cliente.Eq();
		//cliente_critica_IguariaCriteria.iguaria.Eq();
		//cliente_critica_IguariaCriteria.cliente_id_cliente.Eq();
		//cliente_critica_IguariaCriteria.iguaria_id_iguaria.Eq();
		//cliente_critica_IguariaCriteria.iguaria_Estabelecimento.Eq();
		System.Console.WriteLine( cliente_critica_IguariaCriteria.UniqueCliente_critica_Iguaria());
		
		System.Console.WriteLine("Retrieving Cliente_seleciona_iguaria by Cliente_seleciona_iguariaCriteria");
		Cliente_seleciona_iguariaCriteria cliente_seleciona_iguariaCriteria = new Cliente_seleciona_iguariaCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//cliente_seleciona_iguariaCriteria.cliente.Eq();
		//cliente_seleciona_iguariaCriteria.iguaria.Eq();
		//cliente_seleciona_iguariaCriteria.cliente_id_cliente.Eq();
		//cliente_seleciona_iguariaCriteria.iguaria_id_iguaria.Eq();
		//cliente_seleciona_iguariaCriteria.iguaria_Estabelecimento.Eq();
		System.Console.WriteLine( cliente_seleciona_iguariaCriteria.UniqueCliente_seleciona_iguaria());
		
		System.Console.WriteLine("Retrieving SelecaoIguaria by SelecaoIguariaCriteria");
		SelecaoIguariaCriteria selecaoIguariaCriteria = new SelecaoIguariaCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//selecaoIguariaCriteria.id_visualizacao.Eq();
		//selecaoIguariaCriteria.cliente.Eq();
		//selecaoIguariaCriteria.cliente_Cliente.Eq();
		//selecaoIguariaCriteria.cliente_Iguaria.Eq();
		//selecaoIguariaCriteria.cliente_Estabelecimento.Eq();
		System.Console.WriteLine( selecaoIguariaCriteria.UniqueSelecaoIguaria());
		
		System.Console.WriteLine("Retrieving HorarioEstabelecimento by HorarioEstabelecimentoCriteria");
		HorarioEstabelecimentoCriteria horarioEstabelecimentoCriteria = new HorarioEstabelecimentoCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//horarioEstabelecimentoCriteria.id_horario.Eq();
		//horarioEstabelecimentoCriteria.estabelecimento.Eq();
		//horarioEstabelecimentoCriteria.estabelecimento_id_estabelecimento.Eq();
		System.Console.WriteLine( horarioEstabelecimentoCriteria.UniqueHorarioEstabelecimento());
		
		System.Console.WriteLine("Retrieving Cliente_seleciona_Estabelecimento by Cliente_seleciona_EstabelecimentoCriteria");
		Cliente_seleciona_EstabelecimentoCriteria cliente_seleciona_EstabelecimentoCriteria = new Cliente_seleciona_EstabelecimentoCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//cliente_seleciona_EstabelecimentoCriteria.estabelecimento.Eq();
		//cliente_seleciona_EstabelecimentoCriteria.cliente.Eq();
		//cliente_seleciona_EstabelecimentoCriteria.estabelecimento_id_estabelecimento.Eq();
		//cliente_seleciona_EstabelecimentoCriteria.cliente_id_cliente.Eq();
		System.Console.WriteLine( cliente_seleciona_EstabelecimentoCriteria.UniqueCliente_seleciona_Estabelecimento());
		
		System.Console.WriteLine("Retrieving Cliente_avalia_Estabelecimento by Cliente_avalia_EstabelecimentoCriteria");
		Cliente_avalia_EstabelecimentoCriteria cliente_avalia_EstabelecimentoCriteria = new Cliente_avalia_EstabelecimentoCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//cliente_avalia_EstabelecimentoCriteria.estabelecimento.Eq();
		//cliente_avalia_EstabelecimentoCriteria.cliente.Eq();
		//cliente_avalia_EstabelecimentoCriteria.estabelecimento_id_estabelecimento.Eq();
		//cliente_avalia_EstabelecimentoCriteria.cliente_id_cliente.Eq();
		System.Console.WriteLine( cliente_avalia_EstabelecimentoCriteria.UniqueCliente_avalia_Estabelecimento());
		
		System.Console.WriteLine("Retrieving SelecaoEstabelecimento by SelecaoEstabelecimentoCriteria");
		SelecaoEstabelecimentoCriteria selecaoEstabelecimentoCriteria = new SelecaoEstabelecimentoCriteria ();
		// Please uncomment the follow line and fill in parameter(s)
		//selecaoEstabelecimentoCriteria.id_selecao.Eq();
		//selecaoEstabelecimentoCriteria.estabelecimento.Eq();
		//selecaoEstabelecimentoCriteria.estabelecimento_Estabelecimento.Eq();
		//selecaoEstabelecimentoCriteria.estabelecimento_Cliente.Eq();
		System.Console.WriteLine( selecaoEstabelecimentoCriteria.UniqueSelecaoEstabelecimento());
		
	}
	
	
	[STAThread]
	public static void Main(string[] args) {
		RetrieveAndUpdateBasedeDadosMMData retrieveAndUpdateBasedeDadosMMData = new RetrieveAndUpdateBasedeDadosMMData();
		try {
			retrieveAndUpdateBasedeDadosMMData.RetrieveAndUpdateData();
//			retrieveAndUpdateBasedeDadosMMData.RetrieveByCriteria();
		}
		finally {
			BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
		}
		
	}
	
}
