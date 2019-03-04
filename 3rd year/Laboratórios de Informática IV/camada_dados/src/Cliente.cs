/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
using System;
using Orm;
using System.Collections;
using NHibernate;

/// <summary>
/// ORM-Persistable Class
/// </summary>
[Serializable]
public class Cliente {
	public Cliente() {
		_OrmAdapter = new ClienteORMAdapter(this);
		cliente_critica_Iguaria = new Cliente_critica_IguariaSetCollection<Cliente>(this, _OrmAdapter, ORMConstants.KEY_CLIENTE_CLIENTE_CRITICA_IGUARIA, ORMConstants.KEY_CLIENTE_CRITICA_IGUARIA_CLIENTE, ORMConstants.KEY_MUL_ONE_TO_MANY);
		cliente_seleciona_iguaria = new Cliente_seleciona_iguariaSetCollection<Cliente>(this, _OrmAdapter, ORMConstants.KEY_CLIENTE_CLIENTE_SELECIONA_IGUARIA, ORMConstants.KEY_CLIENTE_SELECIONA_IGUARIA_CLIENTE, ORMConstants.KEY_MUL_ONE_TO_MANY);
		cliente_seleciona_Estabelecimento = new Cliente_seleciona_EstabelecimentoSetCollection<Cliente>(this, _OrmAdapter, ORMConstants.KEY_CLIENTE_CLIENTE_SELECIONA_ESTABELECIMENTO, ORMConstants.KEY_CLIENTE_SELECIONA_ESTABELECIMENTO_CLIENTE, ORMConstants.KEY_MUL_ONE_TO_MANY);
		cliente_avalia_Estabelecimento = new Cliente_avalia_EstabelecimentoSetCollection<Cliente>(this, _OrmAdapter, ORMConstants.KEY_CLIENTE_CLIENTE_AVALIA_ESTABELECIMENTO, ORMConstants.KEY_CLIENTE_AVALIA_ESTABELECIMENTO_CLIENTE, ORMConstants.KEY_MUL_ONE_TO_MANY);
	}
	
	public static Cliente LoadClienteByORMID(int id_cliente) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadClienteByORMID(session,id_cliente);
	}
	
	public static Cliente LoadClienteByORMID(PersistentSession session,int id_cliente) {
		return (Cliente) session.Load(typeof(Cliente), (Int32)id_cliente);
	}
	
	public static Cliente[] ListClienteByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListClienteByQuery(session, condition, orderBy);
	}
	
	public static Cliente[] ListClienteByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente as Cliente");
		if (condition != null) {
			sb.Append(" Where ");
			sb.Append(condition);
		}
		if (orderBy != null) {
			sb.Append(" Order By ");
			sb.Append(orderBy);
		}
		IQuery query = session.CreateQuery(sb.ToString());
		try {
			IList list = query.List();
			Cliente[] result = new Cliente[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static Cliente LoadClienteByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadClienteByQuery(session, condition, orderBy);
	}
	
	public static Cliente LoadClienteByQuery(PersistentSession session, string condition, string orderBy) {
		Cliente[] clientes = ListClienteByQuery(session, condition, orderBy);
		if (clientes != null && clientes.Length > 0)
			return clientes[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateClienteByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateClienteByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateClienteByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente as Cliente");
		if (condition != null) {
			sb.Append(" Where ");
			sb.Append(condition);
		}
		if (orderBy != null) {
			sb.Append(" Order By ");
			sb.Append(orderBy);
		}
		IQuery query = session.CreateQuery(sb.ToString());
		try {
			return query.Enumerable();
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static Cliente LoadClienteByCriteria(ClienteCriteria clienteCriteria) {
		Cliente[] clientes = ListClienteByCriteria(clienteCriteria);
		if(clientes == null || clientes.Length == 0) {
			return null;
		}
		return clientes[0];
	}
	
	public static Cliente[] ListClienteByCriteria(ClienteCriteria clienteCriteria) {
		return clienteCriteria.ListCliente();
	}
	
	public static Cliente CreateCliente() {
		return new Cliente();
	}
	
	public bool Save() {
		try {
			BasedeDadosMMPersistentManager.Instance().SaveObject(this);
			return true;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public bool Delete() {
		try {
			BasedeDadosMMPersistentManager.Instance().DeleteObject(this);
			return true;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public bool Refresh() {
		try {
			BasedeDadosMMPersistentManager.Instance().GetSession().Refresh(this);
			return true;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public bool DeleteAndDissociate() {
		try {
			if(Utilizador != null) {
				Utilizador.Cliente = null;
			}
			Cliente_critica_Iguaria[] lCliente_critica_Iguarias = cliente_critica_Iguaria.ToArray();
			foreach(Cliente_critica_Iguaria lCliente_critica_Iguaria in lCliente_critica_Iguarias) {
				lCliente_critica_Iguaria.Cliente = null;
			}
			Cliente_seleciona_iguaria[] lCliente_seleciona_iguarias = cliente_seleciona_iguaria.ToArray();
			foreach(Cliente_seleciona_iguaria lCliente_seleciona_iguaria in lCliente_seleciona_iguarias) {
				lCliente_seleciona_iguaria.Cliente = null;
			}
			Cliente_seleciona_Estabelecimento[] lCliente_seleciona_Estabelecimentos = cliente_seleciona_Estabelecimento.ToArray();
			foreach(Cliente_seleciona_Estabelecimento lCliente_seleciona_Estabelecimento in lCliente_seleciona_Estabelecimentos) {
				lCliente_seleciona_Estabelecimento.Cliente = null;
			}
			Cliente_avalia_Estabelecimento[] lCliente_avalia_Estabelecimentos = cliente_avalia_Estabelecimento.ToArray();
			foreach(Cliente_avalia_Estabelecimento lCliente_avalia_Estabelecimento in lCliente_avalia_Estabelecimentos) {
				lCliente_avalia_Estabelecimento.Cliente = null;
			}
			return Delete();
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public bool DeleteAndDissociate(global::Orm.PersistentSession session) {
		try {
			if(Utilizador != null) {
				Utilizador.Cliente = null;
			}
			Cliente_critica_Iguaria[] lCliente_critica_Iguarias = cliente_critica_Iguaria.ToArray();
			foreach(Cliente_critica_Iguaria lCliente_critica_Iguaria in lCliente_critica_Iguarias) {
				lCliente_critica_Iguaria.Cliente = null;
			}
			Cliente_seleciona_iguaria[] lCliente_seleciona_iguarias = cliente_seleciona_iguaria.ToArray();
			foreach(Cliente_seleciona_iguaria lCliente_seleciona_iguaria in lCliente_seleciona_iguarias) {
				lCliente_seleciona_iguaria.Cliente = null;
			}
			Cliente_seleciona_Estabelecimento[] lCliente_seleciona_Estabelecimentos = cliente_seleciona_Estabelecimento.ToArray();
			foreach(Cliente_seleciona_Estabelecimento lCliente_seleciona_Estabelecimento in lCliente_seleciona_Estabelecimentos) {
				lCliente_seleciona_Estabelecimento.Cliente = null;
			}
			Cliente_avalia_Estabelecimento[] lCliente_avalia_Estabelecimentos = cliente_avalia_Estabelecimento.ToArray();
			foreach(Cliente_avalia_Estabelecimento lCliente_avalia_Estabelecimento in lCliente_avalia_Estabelecimentos) {
				lCliente_avalia_Estabelecimento.Cliente = null;
			}
			try {
				session.Delete(this);
				return true;
			}
			catch (Exception) {
				return false;
			}
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	private System.Collections.Generic.ISet<T> This_GetSet<T>(int key) {
		if (key == ORMConstants.KEY_CLIENTE_CLIENTE_CRITICA_IGUARIA)
			return (System.Collections.Generic.ISet<T>) __cliente_critica_Iguaria;
		else if (key == ORMConstants.KEY_CLIENTE_CLIENTE_SELECIONA_IGUARIA)
			return (System.Collections.Generic.ISet<T>) __cliente_seleciona_iguaria;
		else if (key == ORMConstants.KEY_CLIENTE_CLIENTE_SELECIONA_ESTABELECIMENTO)
			return (System.Collections.Generic.ISet<T>) __cliente_seleciona_Estabelecimento;
		else if (key == ORMConstants.KEY_CLIENTE_CLIENTE_AVALIA_ESTABELECIMENTO)
			return (System.Collections.Generic.ISet<T>) __cliente_avalia_Estabelecimento;
		return null;
	}
	
	private class ClienteORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly Cliente __Cliente;
		
		internal ClienteORMAdapter(Cliente value) {
			__Cliente = value;
		}
		
		public override System.Collections.Generic.ISet<T> GetSet<T>(int key) {
			return __Cliente.This_GetSet<T>(key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private int __id_cliente;
	
	private Utilizador __utilizador;
	
	private string __nome_cliente;
	
	private byte __ord_rat_igu = 0;
	
	private byte __ord_rat_est = 0;
	
	private byte __ord_dist = 0;
	
	private byte __ord_pop_igu = 0;
	
	private byte __ord_pop_est = 0;
	
	private System.Collections.Generic.ISet<Cliente_critica_Iguaria> __cliente_critica_Iguaria = new System.Collections.Generic.HashSet<Cliente_critica_Iguaria>();
	
	private System.Collections.Generic.ISet<Cliente_seleciona_iguaria> __cliente_seleciona_iguaria = new System.Collections.Generic.HashSet<Cliente_seleciona_iguaria>();
	
	private System.Collections.Generic.ISet<Cliente_seleciona_Estabelecimento> __cliente_seleciona_Estabelecimento = new System.Collections.Generic.HashSet<Cliente_seleciona_Estabelecimento>();
	
	private System.Collections.Generic.ISet<Cliente_avalia_Estabelecimento> __cliente_avalia_Estabelecimento = new System.Collections.Generic.HashSet<Cliente_avalia_Estabelecimento>();
	
	public int Id_cliente {
		set {
			this.__id_cliente = value;			
		}
		get {
			return __id_cliente;			
		}
	}
	
	public int ORMID {
		get {
			return Id_cliente;			
		}
	}
	
	public string Nome_cliente {
		set {
			this.__nome_cliente = value;			
		}
		get {
			return __nome_cliente;			
		}
	}
	
	public byte Ord_rat_igu {
		set {
			this.__ord_rat_igu = value;			
		}
		get {
			return __ord_rat_igu;			
		}
	}
	
	public byte Ord_rat_est {
		set {
			this.__ord_rat_est = value;			
		}
		get {
			return __ord_rat_est;			
		}
	}
	
	public byte Ord_dist {
		set {
			this.__ord_dist = value;			
		}
		get {
			return __ord_dist;			
		}
	}
	
	public byte Ord_pop_igu {
		set {
			this.__ord_pop_igu = value;			
		}
		get {
			return __ord_pop_igu;			
		}
	}
	
	public byte Ord_pop_est {
		set {
			this.__ord_pop_est = value;			
		}
		get {
			return __ord_pop_est;			
		}
	}
	
	public Utilizador Utilizador {
		set {
			if (this.__utilizador != value) {
				Utilizador l__utilizador = this.__utilizador;
				this.__utilizador = value;
				if (value != null) {
					__utilizador.Cliente = this;
				}
				if (l__utilizador != null && l__utilizador.Cliente == this) {
					l__utilizador.Cliente = null;
				}
			}
		}
		get {
			return __utilizador;			
		}
	}
	
	private System.Collections.Generic.ISet<Cliente_critica_Iguaria> ORM_Cliente_critica_Iguaria {
		get  {
			return __cliente_critica_Iguaria;			
		}
		
		set {
			__cliente_critica_Iguaria = value;			
		}
	}
	
	public readonly Cliente_critica_IguariaSetCollection<Cliente> cliente_critica_Iguaria;
	
	private System.Collections.Generic.ISet<Cliente_seleciona_iguaria> ORM_Cliente_seleciona_iguaria {
		get  {
			return __cliente_seleciona_iguaria;			
		}
		
		set {
			__cliente_seleciona_iguaria = value;			
		}
	}
	
	public readonly Cliente_seleciona_iguariaSetCollection<Cliente> cliente_seleciona_iguaria;
	
	private System.Collections.Generic.ISet<Cliente_seleciona_Estabelecimento> ORM_Cliente_seleciona_Estabelecimento {
		get  {
			return __cliente_seleciona_Estabelecimento;			
		}
		
		set {
			__cliente_seleciona_Estabelecimento = value;			
		}
	}
	
	public readonly Cliente_seleciona_EstabelecimentoSetCollection<Cliente> cliente_seleciona_Estabelecimento;
	
	private System.Collections.Generic.ISet<Cliente_avalia_Estabelecimento> ORM_Cliente_avalia_Estabelecimento {
		get  {
			return __cliente_avalia_Estabelecimento;			
		}
		
		set {
			__cliente_avalia_Estabelecimento = value;			
		}
	}
	
	public readonly Cliente_avalia_EstabelecimentoSetCollection<Cliente> cliente_avalia_Estabelecimento;
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Id_cliente);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("Cliente[ ");
			sb.AppendFormat("Id_cliente={0} ", Id_cliente);
			if (Utilizador != null)
				sb.AppendFormat("Utilizador.Persist_ID={0} ", Utilizador.ToString(true) + "");
			else
				sb.Append("Utilizador=null ");
			sb.AppendFormat("Nome_cliente={0} ", Nome_cliente);
			sb.AppendFormat("Ord_rat_igu={0} ", Ord_rat_igu);
			sb.AppendFormat("Ord_rat_est={0} ", Ord_rat_est);
			sb.AppendFormat("Ord_dist={0} ", Ord_dist);
			sb.AppendFormat("Ord_pop_igu={0} ", Ord_pop_igu);
			sb.AppendFormat("Ord_pop_est={0} ", Ord_pop_est);
			sb.AppendFormat("cliente_critica_Iguaria.size={0} ", cliente_critica_Iguaria.Size());
			sb.AppendFormat("cliente_seleciona_iguaria.size={0} ", cliente_seleciona_iguaria.Size());
			sb.AppendFormat("cliente_seleciona_Estabelecimento.size={0} ", cliente_seleciona_Estabelecimento.Size());
			sb.AppendFormat("cliente_avalia_Estabelecimento.size={0} ", cliente_avalia_Estabelecimento.Size());
			sb.Append("]");
			return sb.ToString();
		}
	}
	
	public const String PROP_ID_CLIENTE = "Id_cliente";
	public const String PROP_UTILIZADOR = "__utilizador";
	public const String PROP_NOME_CLIENTE = "Nome_cliente";
	public const String PROP_ORD_RAT_IGU = "Ord_rat_igu";
	public const String PROP_ORD_RAT_EST = "Ord_rat_est";
	public const String PROP_ORD_DIST = "Ord_dist";
	public const String PROP_ORD_POP_IGU = "Ord_pop_igu";
	public const String PROP_ORD_POP_EST = "Ord_pop_est";
	public const String PROP_CLIENTE_CRITICA__IGUARIA = "ORM_Cliente_critica_Iguaria";
	public const String PROP_CLIENTE_SELECIONA_IGUARIA = "ORM_Cliente_seleciona_iguaria";
	public const String PROP_CLIENTE_SELECIONA__ESTABELECIMENTO = "ORM_Cliente_seleciona_Estabelecimento";
	public const String PROP_CLIENTE_AVALIA__ESTABELECIMENTO = "ORM_Cliente_avalia_Estabelecimento";
}
