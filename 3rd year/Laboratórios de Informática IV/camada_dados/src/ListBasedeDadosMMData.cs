using System;
using Orm;

public class ListBasedeDadosMMData {
	private readonly int ROW_COUNT = 100;
	public void ListData() {
		System.Console.WriteLine("Listing Estabelecimento...");
		Estabelecimento[] estabelecimentos = Estabelecimento.ListEstabelecimentoByQuery(null, null);
		int length = Math.Min(estabelecimentos.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(estabelecimentos[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Iguaria...");
		Iguaria[] iguarias = Iguaria.ListIguariaByQuery(null, null);
		length = Math.Min(iguarias.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(iguarias[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Cliente...");
		Cliente[] clientes = Cliente.ListClienteByQuery(null, null);
		length = Math.Min(clientes.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(clientes[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Utilizador...");
		Utilizador[] utilizadors = Utilizador.ListUtilizadorByQuery(null, null);
		length = Math.Min(utilizadors.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(utilizadors[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Categoria...");
		Categoria[] categorias = Categoria.ListCategoriaByQuery(null, null);
		length = Math.Min(categorias.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(categorias[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Cliente_critica_Iguaria...");
		Cliente_critica_Iguaria[] cliente_critica_Iguarias = Cliente_critica_Iguaria.ListCliente_critica_IguariaByQuery(null, null);
		length = Math.Min(cliente_critica_Iguarias.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(cliente_critica_Iguarias[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Cliente_seleciona_iguaria...");
		Cliente_seleciona_iguaria[] cliente_seleciona_iguarias = Cliente_seleciona_iguaria.ListCliente_seleciona_iguariaByQuery(null, null);
		length = Math.Min(cliente_seleciona_iguarias.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(cliente_seleciona_iguarias[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing SelecaoIguaria...");
		SelecaoIguaria[] selecaoIguarias = SelecaoIguaria.ListSelecaoIguariaByQuery(null, null);
		length = Math.Min(selecaoIguarias.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(selecaoIguarias[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing HorarioEstabelecimento...");
		HorarioEstabelecimento[] horarioEstabelecimentos = HorarioEstabelecimento.ListHorarioEstabelecimentoByQuery(null, null);
		length = Math.Min(horarioEstabelecimentos.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(horarioEstabelecimentos[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Cliente_seleciona_Estabelecimento...");
		Cliente_seleciona_Estabelecimento[] cliente_seleciona_Estabelecimentos = Cliente_seleciona_Estabelecimento.ListCliente_seleciona_EstabelecimentoByQuery(null, null);
		length = Math.Min(cliente_seleciona_Estabelecimentos.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(cliente_seleciona_Estabelecimentos[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing Cliente_avalia_Estabelecimento...");
		Cliente_avalia_Estabelecimento[] cliente_avalia_Estabelecimentos = Cliente_avalia_Estabelecimento.ListCliente_avalia_EstabelecimentoByQuery(null, null);
		length = Math.Min(cliente_avalia_Estabelecimentos.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(cliente_avalia_Estabelecimentos[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
		System.Console.WriteLine("Listing SelecaoEstabelecimento...");
		SelecaoEstabelecimento[] selecaoEstabelecimentos = SelecaoEstabelecimento.ListSelecaoEstabelecimentoByQuery(null, null);
		length = Math.Min(selecaoEstabelecimentos.Length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.Console.WriteLine(selecaoEstabelecimentos[i]);
		}
		
		System.Console.WriteLine(length + " record(s) retrieved.");
		
	}
	
	public void ListByCriteria() {
		System.Console.WriteLine("Listing Estabelecimento by Criteria...");
		EstabelecimentoCriteria estabelecimentoCriteria = new EstabelecimentoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//estabelecimentoCriteria.id_estabelecimento.Eq();
		estabelecimentoCriteria.SetMaxResults(ROW_COUNT);
		Estabelecimento[] estabelecimentos = estabelecimentoCriteria.ListEstabelecimento();
		int length =estabelecimentos== null ? 0 : Math.Min(estabelecimentos.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(estabelecimentos[i]);
		}
		System.Console.WriteLine(length + " Estabelecimento record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Iguaria by Criteria...");
		IguariaCriteria iguariaCriteria = new IguariaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//iguariaCriteria.id_iguaria.Eq();
		//EstabelecimentoCriteria iguariaCriteria_Estabelecimento = iguariaCriteria.CreateEstabelecimentoCriteria();
		//iguariaCriteria_Estabelecimento.id_estabelecimento.Eq();
		//iguariaCriteria.estabelecimento_id_estabelecimento.Eq();
		iguariaCriteria.SetMaxResults(ROW_COUNT);
		Iguaria[] iguarias = iguariaCriteria.ListIguaria();
		length =iguarias== null ? 0 : Math.Min(iguarias.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(iguarias[i]);
		}
		System.Console.WriteLine(length + " Iguaria record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Cliente by Criteria...");
		ClienteCriteria clienteCriteria = new ClienteCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//clienteCriteria.id_cliente.Eq();
		clienteCriteria.SetMaxResults(ROW_COUNT);
		Cliente[] clientes = clienteCriteria.ListCliente();
		length =clientes== null ? 0 : Math.Min(clientes.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(clientes[i]);
		}
		System.Console.WriteLine(length + " Cliente record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Utilizador by Criteria...");
		UtilizadorCriteria utilizadorCriteria = new UtilizadorCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//utilizadorCriteria.email.Eq();
		utilizadorCriteria.SetMaxResults(ROW_COUNT);
		Utilizador[] utilizadors = utilizadorCriteria.ListUtilizador();
		length =utilizadors== null ? 0 : Math.Min(utilizadors.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(utilizadors[i]);
		}
		System.Console.WriteLine(length + " Utilizador record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Categoria by Criteria...");
		CategoriaCriteria categoriaCriteria = new CategoriaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//categoriaCriteria.id_categoria.Eq();
		categoriaCriteria.SetMaxResults(ROW_COUNT);
		Categoria[] categorias = categoriaCriteria.ListCategoria();
		length =categorias== null ? 0 : Math.Min(categorias.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(categorias[i]);
		}
		System.Console.WriteLine(length + " Categoria record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Cliente_critica_Iguaria by Criteria...");
		Cliente_critica_IguariaCriteria cliente_critica_IguariaCriteria = new Cliente_critica_IguariaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ClienteCriteria cliente_critica_IguariaCriteria_Cliente = cliente_critica_IguariaCriteria.CreateClienteCriteria();
		//cliente_critica_IguariaCriteria_Cliente.id_cliente.Eq();
		//IguariaCriteria cliente_critica_IguariaCriteria_Iguaria = cliente_critica_IguariaCriteria.CreateIguariaCriteria();
		//cliente_critica_IguariaCriteria_Iguaria.estabelecimento_id_estabelecimento.Eq();
		//EstabelecimentoCriteria cliente_critica_IguariaCriteria_Iguaria_Estabelecimento = cliente_critica_IguariaCriteria_Iguaria.CreateEstabelecimentoCriteria();
		//cliente_critica_IguariaCriteria_Iguaria_Estabelecimento.id_estabelecimento.Eq();
		//cliente_critica_IguariaCriteria_Iguaria.id_iguaria.Eq();
		//cliente_critica_IguariaCriteria.cliente_id_cliente.Eq();
		//cliente_critica_IguariaCriteria.iguaria_id_iguaria.Eq();
		//cliente_critica_IguariaCriteria.iguaria_Estabelecimento.Eq();
		cliente_critica_IguariaCriteria.SetMaxResults(ROW_COUNT);
		Cliente_critica_Iguaria[] cliente_critica_Iguarias = cliente_critica_IguariaCriteria.ListCliente_critica_Iguaria();
		length =cliente_critica_Iguarias== null ? 0 : Math.Min(cliente_critica_Iguarias.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(cliente_critica_Iguarias[i]);
		}
		System.Console.WriteLine(length + " Cliente_critica_Iguaria record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Cliente_seleciona_iguaria by Criteria...");
		Cliente_seleciona_iguariaCriteria cliente_seleciona_iguariaCriteria = new Cliente_seleciona_iguariaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//ClienteCriteria cliente_seleciona_iguariaCriteria_Cliente = cliente_seleciona_iguariaCriteria.CreateClienteCriteria();
		//cliente_seleciona_iguariaCriteria_Cliente.id_cliente.Eq();
		//IguariaCriteria cliente_seleciona_iguariaCriteria_Iguaria = cliente_seleciona_iguariaCriteria.CreateIguariaCriteria();
		//cliente_seleciona_iguariaCriteria_Iguaria.estabelecimento_id_estabelecimento.Eq();
		//EstabelecimentoCriteria cliente_seleciona_iguariaCriteria_Iguaria_Estabelecimento = cliente_seleciona_iguariaCriteria_Iguaria.CreateEstabelecimentoCriteria();
		//cliente_seleciona_iguariaCriteria_Iguaria_Estabelecimento.id_estabelecimento.Eq();
		//cliente_seleciona_iguariaCriteria_Iguaria.id_iguaria.Eq();
		//cliente_seleciona_iguariaCriteria.cliente_id_cliente.Eq();
		//cliente_seleciona_iguariaCriteria.iguaria_id_iguaria.Eq();
		//cliente_seleciona_iguariaCriteria.iguaria_Estabelecimento.Eq();
		cliente_seleciona_iguariaCriteria.SetMaxResults(ROW_COUNT);
		Cliente_seleciona_iguaria[] cliente_seleciona_iguarias = cliente_seleciona_iguariaCriteria.ListCliente_seleciona_iguaria();
		length =cliente_seleciona_iguarias== null ? 0 : Math.Min(cliente_seleciona_iguarias.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(cliente_seleciona_iguarias[i]);
		}
		System.Console.WriteLine(length + " Cliente_seleciona_iguaria record(s) retrieved."); 
		
		System.Console.WriteLine("Listing SelecaoIguaria by Criteria...");
		SelecaoIguariaCriteria selecaoIguariaCriteria = new SelecaoIguariaCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//selecaoIguariaCriteria.id_visualizacao.Eq();
		//Cliente_seleciona_iguariaCriteria selecaoIguariaCriteria_Cliente_seleciona_iguaria = selecaoIguariaCriteria.CreateClienteCriteria();
		//selecaoIguariaCriteria_Cliente_seleciona_iguaria.iguaria_Estabelecimento.Eq();
		//selecaoIguariaCriteria_Cliente_seleciona_iguaria.iguaria_id_iguaria.Eq();
		//selecaoIguariaCriteria_Cliente_seleciona_iguaria.cliente_id_cliente.Eq();
		//IguariaCriteria selecaoIguariaCriteria_Cliente_seleciona_iguaria_Iguaria = selecaoIguariaCriteria_Cliente_seleciona_iguaria.CreateIguariaCriteria();
		//selecaoIguariaCriteria_Cliente_seleciona_iguaria_Iguaria.estabelecimento_id_estabelecimento.Eq();
		//EstabelecimentoCriteria selecaoIguariaCriteria_Cliente_seleciona_iguaria_Iguaria_Estabelecimento = selecaoIguariaCriteria_Cliente_seleciona_iguaria_Iguaria.CreateEstabelecimentoCriteria();
		//selecaoIguariaCriteria_Cliente_seleciona_iguaria_Iguaria_Estabelecimento.id_estabelecimento.Eq();
		//selecaoIguariaCriteria_Cliente_seleciona_iguaria_Iguaria.id_iguaria.Eq();
		//ClienteCriteria selecaoIguariaCriteria_Cliente_seleciona_iguaria_Cliente = selecaoIguariaCriteria_Cliente_seleciona_iguaria.CreateClienteCriteria();
		//selecaoIguariaCriteria_Cliente_seleciona_iguaria_Cliente.id_cliente.Eq();
		//selecaoIguariaCriteria.cliente_Cliente.Eq();
		//selecaoIguariaCriteria.cliente_Iguaria.Eq();
		//selecaoIguariaCriteria.cliente_Estabelecimento.Eq();
		selecaoIguariaCriteria.SetMaxResults(ROW_COUNT);
		SelecaoIguaria[] selecaoIguarias = selecaoIguariaCriteria.ListSelecaoIguaria();
		length =selecaoIguarias== null ? 0 : Math.Min(selecaoIguarias.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(selecaoIguarias[i]);
		}
		System.Console.WriteLine(length + " SelecaoIguaria record(s) retrieved."); 
		
		System.Console.WriteLine("Listing HorarioEstabelecimento by Criteria...");
		HorarioEstabelecimentoCriteria horarioEstabelecimentoCriteria = new HorarioEstabelecimentoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//horarioEstabelecimentoCriteria.id_horario.Eq();
		//EstabelecimentoCriteria horarioEstabelecimentoCriteria_Estabelecimento = horarioEstabelecimentoCriteria.CreateEstabelecimentoCriteria();
		//horarioEstabelecimentoCriteria_Estabelecimento.id_estabelecimento.Eq();
		//horarioEstabelecimentoCriteria.estabelecimento_id_estabelecimento.Eq();
		horarioEstabelecimentoCriteria.SetMaxResults(ROW_COUNT);
		HorarioEstabelecimento[] horarioEstabelecimentos = horarioEstabelecimentoCriteria.ListHorarioEstabelecimento();
		length =horarioEstabelecimentos== null ? 0 : Math.Min(horarioEstabelecimentos.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(horarioEstabelecimentos[i]);
		}
		System.Console.WriteLine(length + " HorarioEstabelecimento record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Cliente_seleciona_Estabelecimento by Criteria...");
		Cliente_seleciona_EstabelecimentoCriteria cliente_seleciona_EstabelecimentoCriteria = new Cliente_seleciona_EstabelecimentoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//EstabelecimentoCriteria cliente_seleciona_EstabelecimentoCriteria_Estabelecimento = cliente_seleciona_EstabelecimentoCriteria.CreateEstabelecimentoCriteria();
		//cliente_seleciona_EstabelecimentoCriteria_Estabelecimento.id_estabelecimento.Eq();
		//ClienteCriteria cliente_seleciona_EstabelecimentoCriteria_Cliente = cliente_seleciona_EstabelecimentoCriteria.CreateClienteCriteria();
		//cliente_seleciona_EstabelecimentoCriteria_Cliente.id_cliente.Eq();
		//cliente_seleciona_EstabelecimentoCriteria.estabelecimento_id_estabelecimento.Eq();
		//cliente_seleciona_EstabelecimentoCriteria.cliente_id_cliente.Eq();
		cliente_seleciona_EstabelecimentoCriteria.SetMaxResults(ROW_COUNT);
		Cliente_seleciona_Estabelecimento[] cliente_seleciona_Estabelecimentos = cliente_seleciona_EstabelecimentoCriteria.ListCliente_seleciona_Estabelecimento();
		length =cliente_seleciona_Estabelecimentos== null ? 0 : Math.Min(cliente_seleciona_Estabelecimentos.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(cliente_seleciona_Estabelecimentos[i]);
		}
		System.Console.WriteLine(length + " Cliente_seleciona_Estabelecimento record(s) retrieved."); 
		
		System.Console.WriteLine("Listing Cliente_avalia_Estabelecimento by Criteria...");
		Cliente_avalia_EstabelecimentoCriteria cliente_avalia_EstabelecimentoCriteria = new Cliente_avalia_EstabelecimentoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//EstabelecimentoCriteria cliente_avalia_EstabelecimentoCriteria_Estabelecimento = cliente_avalia_EstabelecimentoCriteria.CreateEstabelecimentoCriteria();
		//cliente_avalia_EstabelecimentoCriteria_Estabelecimento.id_estabelecimento.Eq();
		//ClienteCriteria cliente_avalia_EstabelecimentoCriteria_Cliente = cliente_avalia_EstabelecimentoCriteria.CreateClienteCriteria();
		//cliente_avalia_EstabelecimentoCriteria_Cliente.id_cliente.Eq();
		//cliente_avalia_EstabelecimentoCriteria.estabelecimento_id_estabelecimento.Eq();
		//cliente_avalia_EstabelecimentoCriteria.cliente_id_cliente.Eq();
		cliente_avalia_EstabelecimentoCriteria.SetMaxResults(ROW_COUNT);
		Cliente_avalia_Estabelecimento[] cliente_avalia_Estabelecimentos = cliente_avalia_EstabelecimentoCriteria.ListCliente_avalia_Estabelecimento();
		length =cliente_avalia_Estabelecimentos== null ? 0 : Math.Min(cliente_avalia_Estabelecimentos.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(cliente_avalia_Estabelecimentos[i]);
		}
		System.Console.WriteLine(length + " Cliente_avalia_Estabelecimento record(s) retrieved."); 
		
		System.Console.WriteLine("Listing SelecaoEstabelecimento by Criteria...");
		SelecaoEstabelecimentoCriteria selecaoEstabelecimentoCriteria = new SelecaoEstabelecimentoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//selecaoEstabelecimentoCriteria.id_selecao.Eq();
		//Cliente_seleciona_EstabelecimentoCriteria selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento = selecaoEstabelecimentoCriteria.CreateEstabelecimentoCriteria();
		//selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento.cliente_id_cliente.Eq();
		//selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento.estabelecimento_id_estabelecimento.Eq();
		//ClienteCriteria selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento_Cliente = selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento.CreateClienteCriteria();
		//selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento_Cliente.id_cliente.Eq();
		//EstabelecimentoCriteria selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento_Estabelecimento = selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento.CreateEstabelecimentoCriteria();
		//selecaoEstabelecimentoCriteria_Cliente_seleciona_Estabelecimento_Estabelecimento.id_estabelecimento.Eq();
		//selecaoEstabelecimentoCriteria.estabelecimento_Estabelecimento.Eq();
		//selecaoEstabelecimentoCriteria.estabelecimento_Cliente.Eq();
		selecaoEstabelecimentoCriteria.SetMaxResults(ROW_COUNT);
		SelecaoEstabelecimento[] selecaoEstabelecimentos = selecaoEstabelecimentoCriteria.ListSelecaoEstabelecimento();
		length =selecaoEstabelecimentos== null ? 0 : Math.Min(selecaoEstabelecimentos.Length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.Console.WriteLine(selecaoEstabelecimentos[i]);
		}
		System.Console.WriteLine(length + " SelecaoEstabelecimento record(s) retrieved."); 
		
	}
	
	[STAThread]
	public static void Main(string[] args) {
		ListBasedeDadosMMData listBasedeDadosMMData = new ListBasedeDadosMMData();
		try {
			listBasedeDadosMMData.ListData();
//			listBasedeDadosMMData.ListByCriteria();
		}
		finally {
			BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
		}
		
	}
	
}
