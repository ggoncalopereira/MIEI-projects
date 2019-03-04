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
public class Cliente_critica_Iguaria {
	public Cliente_critica_Iguaria() {
		_OrmAdapter = new Cliente_critica_IguariaORMAdapter(this);
	}
	
	public static Cliente_critica_Iguaria LoadCliente_critica_IguariaByORMID(int cliente_id_cliente, int iguaria_id_iguaria, int iguaria_Estabelecimento) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCliente_critica_IguariaByORMID(session,cliente_id_cliente, iguaria_id_iguaria, iguaria_Estabelecimento);
	}
	
	public static Cliente_critica_Iguaria LoadCliente_critica_IguariaByORMID(PersistentSession session,int cliente_id_cliente, int iguaria_id_iguaria, int iguaria_Estabelecimento) {
		Cliente_critica_Iguaria cliente_critica_iguaria = new Cliente_critica_Iguaria();
		cliente_critica_iguaria.Cliente_id_cliente = cliente_id_cliente;
		cliente_critica_iguaria.Iguaria_id_iguaria = iguaria_id_iguaria;
		cliente_critica_iguaria.Iguaria_Estabelecimento = iguaria_Estabelecimento;
		
		return (Cliente_critica_Iguaria) session.Load(typeof(Cliente_critica_Iguaria), cliente_critica_iguaria);
	}
	
	public static Cliente_critica_Iguaria[] ListCliente_critica_IguariaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListCliente_critica_IguariaByQuery(session, condition, orderBy);
	}
	
	public static Cliente_critica_Iguaria[] ListCliente_critica_IguariaByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente_critica_Iguaria as Cliente_critica_Iguaria");
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
			Cliente_critica_Iguaria[] result = new Cliente_critica_Iguaria[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static Cliente_critica_Iguaria LoadCliente_critica_IguariaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCliente_critica_IguariaByQuery(session, condition, orderBy);
	}
	
	public static Cliente_critica_Iguaria LoadCliente_critica_IguariaByQuery(PersistentSession session, string condition, string orderBy) {
		Cliente_critica_Iguaria[] cliente_critica_Iguarias = ListCliente_critica_IguariaByQuery(session, condition, orderBy);
		if (cliente_critica_Iguarias != null && cliente_critica_Iguarias.Length > 0)
			return cliente_critica_Iguarias[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateCliente_critica_IguariaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateCliente_critica_IguariaByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateCliente_critica_IguariaByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente_critica_Iguaria as Cliente_critica_Iguaria");
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
	
	public static Cliente_critica_Iguaria LoadCliente_critica_IguariaByCriteria(Cliente_critica_IguariaCriteria cliente_critica_IguariaCriteria) {
		Cliente_critica_Iguaria[] cliente_critica_Iguarias = ListCliente_critica_IguariaByCriteria(cliente_critica_IguariaCriteria);
		if(cliente_critica_Iguarias == null || cliente_critica_Iguarias.Length == 0) {
			return null;
		}
		return cliente_critica_Iguarias[0];
	}
	
	public static Cliente_critica_Iguaria[] ListCliente_critica_IguariaByCriteria(Cliente_critica_IguariaCriteria cliente_critica_IguariaCriteria) {
		return cliente_critica_IguariaCriteria.ListCliente_critica_Iguaria();
	}
	
	public override bool Equals(object obj) {
		if (obj == this)
			return true;
		if (!(obj is Cliente_critica_Iguaria))
			return false;
		Cliente_critica_Iguaria cliente_critica_iguaria = obj as Cliente_critica_Iguaria;
		if (Cliente_id_cliente != cliente_critica_iguaria.Cliente_id_cliente)
			return false;
		if (Iguaria_id_iguaria != cliente_critica_iguaria.Iguaria_id_iguaria)
			return false;
		if (Iguaria_Estabelecimento != cliente_critica_iguaria.Iguaria_Estabelecimento)
			return false;
		return true;
	}
	
	public override int GetHashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) Cliente_id_cliente;
		hashcode = hashcode + (int) Iguaria_id_iguaria;
		hashcode = hashcode + (int) Iguaria_Estabelecimento;
		return hashcode;
	}
	
	public static Cliente_critica_Iguaria CreateCliente_critica_Iguaria() {
		return new Cliente_critica_Iguaria();
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
			if(Cliente != null) {
				Cliente.cliente_critica_Iguaria.Remove(this);
			}
			if(Iguaria != null) {
				Iguaria.cliente_critica_Iguaria.Remove(this);
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
			if(Cliente != null) {
				Cliente.cliente_critica_Iguaria.Remove(this);
			}
			if(Iguaria != null) {
				Iguaria.cliente_critica_Iguaria.Remove(this);
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
	
	private void This_SetOwner(object owner, int key) {
		if (key == ORMConstants.KEY_CLIENTE_CRITICA_IGUARIA_CLIENTE) {
			this.__cliente = (Cliente) owner;
		}
		
		else if (key == ORMConstants.KEY_CLIENTE_CRITICA_IGUARIA_IGUARIA) {
			this.__iguaria = (Iguaria) owner;
		}
	}
	
	private class Cliente_critica_IguariaORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly Cliente_critica_Iguaria __Cliente_critica_Iguaria;
		
		internal Cliente_critica_IguariaORMAdapter(Cliente_critica_Iguaria value) {
			__Cliente_critica_Iguaria = value;
		}
		
		public override void SetOwner(object owner, int key) {
			__Cliente_critica_Iguaria.This_SetOwner(owner, key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private Cliente __cliente;
	
	private int __clienteId;
	
	public int ClienteId {
		set {
			this.__clienteId = value;			
		}
		get {
			return __clienteId;			
		}
	}
	
	private Iguaria __iguaria;
	
	private string __desc_critica;
	
	private DateTime __data_critica;
	
	private Decimal __rating_igu = new Decimal(0);
	
	public string Desc_critica {
		set {
			this.__desc_critica = value;			
		}
		get {
			return __desc_critica;			
		}
	}
	
	public DateTime Data_critica {
		set {
			this.__data_critica = value;			
		}
		get {
			return __data_critica;			
		}
	}
	
	public Decimal Rating_igu {
		set {
			this.__rating_igu = value;			
		}
		get {
			return __rating_igu;			
		}
	}
	
	public Cliente Cliente {
		set {
			if(__cliente!= null) {
				__cliente.cliente_critica_Iguaria.Remove(this);
			}
			
			if(value != null) {
				value.cliente_critica_Iguaria.Add(this);
			}
			if (value != null) {
				__cliente_id_cliente = value.Id_cliente;
			}
			else {
				__cliente_id_cliente = 0;
			}
		}
		get {
			return __cliente;			
		}
	}
	
	private Cliente ORM_Cliente {
		set {
			this.__cliente = value;			
		}
		
		get {
			return __cliente;			
		}
	}
	
	public Iguaria Iguaria {
		set {
			if(__iguaria!= null) {
				__iguaria.cliente_critica_Iguaria.Remove(this);
			}
			
			if(value != null) {
				value.cliente_critica_Iguaria.Add(this);
			}
			if (value != null) {
				__iguaria_id_iguaria = value.Id_iguaria;
				__iguaria_Estabelecimento = value.Estabelecimento.Id_estabelecimento;
			}
			else {
				__iguaria_id_iguaria = 0;
				__iguaria_Estabelecimento = 0;
			}
		}
		get {
			return __iguaria;			
		}
	}
	
	private Iguaria ORM_Iguaria {
		set {
			this.__iguaria = value;			
		}
		
		get {
			return __iguaria;			
		}
	}
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Cliente_id_cliente) + " "+ Convert.ToString(Iguaria_id_iguaria) + " "+ Convert.ToString(Iguaria_Estabelecimento);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("Cliente_critica_Iguaria[ ");
			if (Cliente != null)
				sb.AppendFormat("Cliente.Persist_ID={0} ", Cliente.ToString(true) + "");
			else
				sb.Append("Cliente=null ");
			if (Iguaria != null)
				sb.AppendFormat("Iguaria.Persist_ID={0} ", Iguaria.ToString(true) + "");
			else
				sb.Append("Iguaria=null ");
			sb.AppendFormat("Desc_critica={0} ", Desc_critica);
			sb.AppendFormat("Data_critica={0} ", Data_critica);
			sb.AppendFormat("Rating_igu={0} ", Rating_igu);
			sb.AppendFormat("Cliente_id_cliente={0} ", Cliente_id_cliente);
			sb.AppendFormat("Iguaria_id_iguaria={0} ", Iguaria_id_iguaria);
			sb.AppendFormat("Iguaria_Estabelecimento={0} ", Iguaria_Estabelecimento);
			sb.Append("]");
			return sb.ToString();
		}
	}
	
	private bool _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public bool isSaved() {
		return _saved;
	}
	
	
	private int __cliente_id_cliente;
	
	public int Cliente_id_cliente {
		set {
			this.__cliente_id_cliente = value;			
		}
		get {
			return __cliente_id_cliente;			
		}
	}
	
	private int __iguaria_id_iguaria;
	
	public int Iguaria_id_iguaria {
		set {
			this.__iguaria_id_iguaria = value;			
		}
		get {
			return __iguaria_id_iguaria;			
		}
	}
	
	private int __iguaria_Estabelecimento;
	
	public int Iguaria_Estabelecimento {
		set {
			this.__iguaria_Estabelecimento = value;			
		}
		get {
			return __iguaria_Estabelecimento;			
		}
	}
	
	public const String PROP_CLIENTE = "__cliente";
	public const String PROP_IGUARIA = "__iguaria";
	public const String PROP_DESC_CRITICA = "Desc_critica";
	public const String PROP_DATA_CRITICA = "Data_critica";
	public const String PROP_RATING_IGU = "Rating_igu";
	public const String PROP_CLIENTE_ID_CLIENTE = "Cliente_id_cliente";
	public const String PROP_IGUARIA_ID_IGUARIA = "Iguaria_id_iguaria";
	public const String PROP_IGUARIA__ESTABELECIMENTO = "Iguaria_Estabelecimento";
}
