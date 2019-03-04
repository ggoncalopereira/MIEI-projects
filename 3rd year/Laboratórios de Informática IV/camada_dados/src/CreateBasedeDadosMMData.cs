using System;
using Orm;

public class CreateBasedeDadosMMData {
	public void CreateData() {
		PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
		try {
			Estabelecimento estabelecimento = Estabelecimento.CreateEstabelecimento();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : cliente_avalia_Estabelecimento, cliente_seleciona_Estabelecimento, horarioEstabelecimento, iguaria, cod_postal, localidade, numero, rua, latitude, longitude, visual_estabelecimento, telefone, rating_medio_estabelecimento, desc_ambiente, nome_estabelecimento, utilizador, categoria1
			estabelecimento.Save();
			Iguaria iguaria = Iguaria.CreateIguaria();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : cliente_seleciona_iguaria, cliente_critica_Iguaria, preco, fotografia, rating_medio_iguaria, visual_iguaria, nome_iguaria
			iguaria.Save();
			Cliente cliente = Cliente.CreateCliente();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : cliente_avalia_Estabelecimento, cliente_seleciona_Estabelecimento, cliente_seleciona_iguaria, cliente_critica_Iguaria, ord_pop_est, ord_pop_igu, ord_dist, ord_rat_est, ord_rat_igu, nome_cliente
			cliente.Save();
			Utilizador utilizador = Utilizador.CreateUtilizador();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : estabelecimento, tipo, password
			utilizador.Save();
			Categoria categoria = Categoria.CreateCategoria();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : estabelecimento1, descricao
			categoria.Save();
			Cliente_critica_Iguaria cliente_critica_Iguaria = Cliente_critica_Iguaria.CreateCliente_critica_Iguaria();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : rating_igu, data_critica, desc_critica
			cliente_critica_Iguaria.Save();
			Cliente_seleciona_iguaria cliente_seleciona_iguaria = Cliente_seleciona_iguaria.CreateCliente_seleciona_iguaria();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : selecaoIguaria
			cliente_seleciona_iguaria.Save();
			SelecaoIguaria selecaoIguaria = SelecaoIguaria.CreateSelecaoIguaria();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : data_hora_visualizacao
			selecaoIguaria.Save();
			HorarioEstabelecimento horarioEstabelecimento = HorarioEstabelecimento.CreateHorarioEstabelecimento();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : hora_fecho, hora_abertura, dia
			horarioEstabelecimento.Save();
			Cliente_seleciona_Estabelecimento cliente_seleciona_Estabelecimento = Cliente_seleciona_Estabelecimento.CreateCliente_seleciona_Estabelecimento();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : selecaoEstabelecimento
			cliente_seleciona_Estabelecimento.Save();
			Cliente_avalia_Estabelecimento cliente_avalia_Estabelecimento = Cliente_avalia_Estabelecimento.CreateCliente_avalia_Estabelecimento();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : data_avaliacao, rating_est
			cliente_avalia_Estabelecimento.Save();
			SelecaoEstabelecimento selecaoEstabelecimento = SelecaoEstabelecimento.CreateSelecaoEstabelecimento();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : data_hora_selecao
			selecaoEstabelecimento.Save();
			t.Commit();
		}
		catch(Exception e) {
			t.RollBack();
			Console.WriteLine(e);
		}
		
	}
	
	[STAThread]
	public static void Main(string[] args) {
		CreateBasedeDadosMMData createBasedeDadosMMData = new CreateBasedeDadosMMData();
		try {
			createBasedeDadosMMData.CreateData();
		}
		finally {
			BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
		}
		
	}
	
}
