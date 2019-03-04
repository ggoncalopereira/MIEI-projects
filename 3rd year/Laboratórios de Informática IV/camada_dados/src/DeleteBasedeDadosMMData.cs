using System;
using Orm;

public class DeleteBasedeDadosMMData {
	private void DeleteData() {
		PersistentTransaction t = BasedeDadosMMPersistentManager.Instance().GetSession().BeginTransaction();
		try {
			Estabelecimento estabelecimento = Estabelecimento.LoadEstabelecimentoByQuery(null, null);
			estabelecimento.Delete();
			Iguaria iguaria = Iguaria.LoadIguariaByQuery(null, null);
			iguaria.Delete();
			Cliente cliente = Cliente.LoadClienteByQuery(null, null);
			cliente.Delete();
			Utilizador utilizador = Utilizador.LoadUtilizadorByQuery(null, null);
			utilizador.Delete();
			Categoria categoria = Categoria.LoadCategoriaByQuery(null, null);
			categoria.Delete();
			Cliente_critica_Iguaria cliente_critica_Iguaria = Cliente_critica_Iguaria.LoadCliente_critica_IguariaByQuery(null, null);
			cliente_critica_Iguaria.Delete();
			Cliente_seleciona_iguaria cliente_seleciona_iguaria = Cliente_seleciona_iguaria.LoadCliente_seleciona_iguariaByQuery(null, null);
			cliente_seleciona_iguaria.Delete();
			SelecaoIguaria selecaoIguaria = SelecaoIguaria.LoadSelecaoIguariaByQuery(null, null);
			selecaoIguaria.Delete();
			HorarioEstabelecimento horarioEstabelecimento = HorarioEstabelecimento.LoadHorarioEstabelecimentoByQuery(null, null);
			horarioEstabelecimento.Delete();
			Cliente_seleciona_Estabelecimento cliente_seleciona_Estabelecimento = Cliente_seleciona_Estabelecimento.LoadCliente_seleciona_EstabelecimentoByQuery(null, null);
			cliente_seleciona_Estabelecimento.Delete();
			Cliente_avalia_Estabelecimento cliente_avalia_Estabelecimento = Cliente_avalia_Estabelecimento.LoadCliente_avalia_EstabelecimentoByQuery(null, null);
			cliente_avalia_Estabelecimento.Delete();
			SelecaoEstabelecimento selecaoEstabelecimento = SelecaoEstabelecimento.LoadSelecaoEstabelecimentoByQuery(null, null);
			selecaoEstabelecimento.Delete();
			t.Commit();
		}
		catch(Exception e) {
			t.RollBack();
			Console.WriteLine(e);
		}
		
	}
	
	[STAThread]
	public static void Main(string[] args) {
		DeleteBasedeDadosMMData deleteBasedeDadosMMData = new DeleteBasedeDadosMMData();
		try {
			deleteBasedeDadosMMData.DeleteData();
		}
		finally {
			BasedeDadosMMPersistentManager.Instance().DisposePersistentManager();
		}
		
	}
	
}
