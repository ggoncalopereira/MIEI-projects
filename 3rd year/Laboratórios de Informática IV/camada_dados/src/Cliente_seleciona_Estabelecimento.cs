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
public class Cliente_seleciona_Estabelecimento {
	public Cliente_seleciona_Estabelecimento() {
		_OrmAdapter = new Cliente_seleciona_EstabelecimentoORMAdapter(this);
		selecaoEstabelecimento = new SelecaoEstabelecimentoSetCollection<Cliente_seleciona_Estabelecimento>(this, _OrmAdapter, ORMConstants.KEY_CLIENTE_SELECIONA_ESTABELECIMENTO_SELECAOESTABELECIMENTO, ORMConstants.KEY_SELECAOESTABELECIMENTO_ESTABELECIMENTO, ORMConstants.KEY_MUL_ONE_TO_MANY);
	}
	
	public static Cliente_seleciona_Estabelecimento LoadCliente_seleciona_EstabelecimentoByORMID(int estabelecimento_id_estabelecimento, int cliente_id_cliente) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCliente_seleciona_EstabelecimentoByORMID(session,estabelecimento_id_estabelecimento, cliente_id_cliente);
	}
	
	public static Cliente_seleciona_Estabelecimento LoadCliente_seleciona_EstabelecimentoByORMID(PersistentSession session,int estabelecimento_id_estabelecimento, int cliente_id_cliente) {
		Cliente_seleciona_Estabelecimento cliente_seleciona_estabelecimento = new Cliente_seleciona_Estabelecimento();
		cliente_seleciona_estabelecimento.Estabelecimento_id_estabelecimento = estabelecimento_id_estabelecimento;
		cliente_seleciona_estabelecimento.Cliente_id_cliente = cliente_id_cliente;
		
		return (Cliente_seleciona_Estabelecimento) session.Load(typeof(Cliente_seleciona_Estabelecimento), cliente_seleciona_estabelecimento);
	}
	
	public static Cliente_seleciona_Estabelecimento[] ListCliente_seleciona_EstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListCliente_seleciona_EstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static Cliente_seleciona_Estabelecimento[] ListCliente_seleciona_EstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente_seleciona_Estabelecimento as Cliente_seleciona_Estabelecimento");
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
			Cliente_seleciona_Estabelecimento[] result = new Cliente_seleciona_Estabelecimento[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static Cliente_seleciona_Estabelecimento LoadCliente_seleciona_EstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCliente_seleciona_EstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static Cliente_seleciona_Estabelecimento LoadCliente_seleciona_EstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		Cliente_seleciona_Estabelecimento[] cliente_seleciona_Estabelecimentos = ListCliente_seleciona_EstabelecimentoByQuery(session, condition, orderBy);
		if (cliente_seleciona_Estabelecimentos != null && cliente_seleciona_Estabelecimentos.Length > 0)
			return cliente_seleciona_Estabelecimentos[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateCliente_seleciona_EstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateCliente_seleciona_EstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateCliente_seleciona_EstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente_seleciona_Estabelecimento as Cliente_seleciona_Estabelecimento");
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
	
	public static Cliente_seleciona_Estabelecimento LoadCliente_seleciona_EstabelecimentoByCriteria(Cliente_seleciona_EstabelecimentoCriteria cliente_seleciona_EstabelecimentoCriteria) {
		Cliente_seleciona_Estabelecimento[] cliente_seleciona_Estabelecimentos = ListCliente_seleciona_EstabelecimentoByCriteria(cliente_seleciona_EstabelecimentoCriteria);
		if(cliente_seleciona_Estabelecimentos == null || cliente_seleciona_Estabelecimentos.Length == 0) {
			return null;
		}
		return cliente_seleciona_Estabelecimentos[0];
	}
	
	public static Cliente_seleciona_Estabelecimento[] ListCliente_seleciona_EstabelecimentoByCriteria(Cliente_seleciona_EstabelecimentoCriteria cliente_seleciona_EstabelecimentoCriteria) {
		return cliente_seleciona_EstabelecimentoCriteria.ListCliente_seleciona_Estabelecimento();
	}
	
	public override bool Equals(object obj) {
		if (obj == this)
			return true;
		if (!(obj is Cliente_seleciona_Estabelecimento))
			return false;
		Cliente_seleciona_Estabelecimento cliente_seleciona_estabelecimento = obj as Cliente_seleciona_Estabelecimento;
		if (Estabelecimento_id_estabelecimento != cliente_seleciona_estabelecimento.Estabelecimento_id_estabelecimento)
			return false;
		if (Cliente_id_cliente != cliente_seleciona_estabelecimento.Cliente_id_cliente)
			return false;
		return true;
	}
	
	public override int GetHashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) Estabelecimento_id_estabelecimento;
		hashcode = hashcode + (int) Cliente_id_cliente;
		return hashcode;
	}
	
	public static Cliente_seleciona_Estabelecimento CreateCliente_seleciona_Estabelecimento() {
		return new Cliente_seleciona_Estabelecimento();
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
			if(Estabelecimento != null) {
				Estabelecimento.cliente_seleciona_Estabelecimento.Remove(this);
			}
			if(Cliente != null) {
				Cliente.cliente_seleciona_Estabelecimento.Remove(this);
			}
			SelecaoEstabelecimento[] lSelecaoEstabelecimentos = selecaoEstabelecimento.ToArray();
			foreach(SelecaoEstabelecimento lSelecaoEstabelecimento in lSelecaoEstabelecimentos) {
				lSelecaoEstabelecimento.Estabelecimento = null;
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
			if(Estabelecimento != null) {
				Estabelecimento.cliente_seleciona_Estabelecimento.Remove(this);
			}
			if(Cliente != null) {
				Cliente.cliente_seleciona_Estabelecimento.Remove(this);
			}
			SelecaoEstabelecimento[] lSelecaoEstabelecimentos = selecaoEstabelecimento.ToArray();
			foreach(SelecaoEstabelecimento lSelecaoEstabelecimento in lSelecaoEstabelecimentos) {
				lSelecaoEstabelecimento.Estabelecimento = null;
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
		if (key == ORMConstants.KEY_CLIENTE_SELECIONA_ESTABELECIMENTO_SELECAOESTABELECIMENTO)
			return (System.Collections.Generic.ISet<T>) __selecaoEstabelecimento;
		return null;
	}
	
	private void This_SetOwner(object owner, int key) {
		if (key == ORMConstants.KEY_CLIENTE_SELECIONA_ESTABELECIMENTO_ESTABELECIMENTO) {
			this.__estabelecimento = (Estabelecimento) owner;
		}
		
		else if (key == ORMConstants.KEY_CLIENTE_SELECIONA_ESTABELECIMENTO_CLIENTE) {
			this.__cliente = (Cliente) owner;
		}
	}
	
	private class Cliente_seleciona_EstabelecimentoORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly Cliente_seleciona_Estabelecimento __Cliente_seleciona_Estabelecimento;
		
		internal Cliente_seleciona_EstabelecimentoORMAdapter(Cliente_seleciona_Estabelecimento value) {
			__Cliente_seleciona_Estabelecimento = value;
		}
		
		public override System.Collections.Generic.ISet<T> GetSet<T>(int key) {
			return __Cliente_seleciona_Estabelecimento.This_GetSet<T>(key);
		}
		
		public override void SetOwner(object owner, int key) {
			__Cliente_seleciona_Estabelecimento.This_SetOwner(owner, key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private Estabelecimento __estabelecimento;
	
	private int __estabelecimentoId;
	
	public int EstabelecimentoId {
		set {
			this.__estabelecimentoId = value;			
		}
		get {
			return __estabelecimentoId;			
		}
	}
	
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
	
	private System.Collections.Generic.ISet<SelecaoEstabelecimento> __selecaoEstabelecimento = new System.Collections.Generic.HashSet<SelecaoEstabelecimento>();
	
	public Estabelecimento Estabelecimento {
		set {
			if(__estabelecimento!= null) {
				__estabelecimento.cliente_seleciona_Estabelecimento.Remove(this);
			}
			
			if(value != null) {
				value.cliente_seleciona_Estabelecimento.Add(this);
			}
			if (value != null) {
				__estabelecimento_id_estabelecimento = value.Id_estabelecimento;
			}
			else {
				__estabelecimento_id_estabelecimento = 0;
			}
		}
		get {
			return __estabelecimento;			
		}
	}
	
	private Estabelecimento ORM_Estabelecimento {
		set {
			this.__estabelecimento = value;			
		}
		
		get {
			return __estabelecimento;			
		}
	}
	
	public Cliente Cliente {
		set {
			if(__cliente!= null) {
				__cliente.cliente_seleciona_Estabelecimento.Remove(this);
			}
			
			if(value != null) {
				value.cliente_seleciona_Estabelecimento.Add(this);
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
	
	private System.Collections.Generic.ISet<SelecaoEstabelecimento> ORM_SelecaoEstabelecimento {
		get  {
			return __selecaoEstabelecimento;			
		}
		
		set {
			__selecaoEstabelecimento = value;			
		}
	}
	
	public readonly SelecaoEstabelecimentoSetCollection<Cliente_seleciona_Estabelecimento> selecaoEstabelecimento;
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Estabelecimento_id_estabelecimento) + " "+ Convert.ToString(Cliente_id_cliente);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("Cliente_seleciona_Estabelecimento[ ");
			if (Estabelecimento != null)
				sb.AppendFormat("Estabelecimento.Persist_ID={0} ", Estabelecimento.ToString(true) + "");
			else
				sb.Append("Estabelecimento=null ");
			if (Cliente != null)
				sb.AppendFormat("Cliente.Persist_ID={0} ", Cliente.ToString(true) + "");
			else
				sb.Append("Cliente=null ");
			sb.AppendFormat("selecaoEstabelecimento.size={0} ", selecaoEstabelecimento.Size());
			sb.AppendFormat("Estabelecimento_id_estabelecimento={0} ", Estabelecimento_id_estabelecimento);
			sb.AppendFormat("Cliente_id_cliente={0} ", Cliente_id_cliente);
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
	
	
	private int __estabelecimento_id_estabelecimento;
	
	public int Estabelecimento_id_estabelecimento {
		set {
			this.__estabelecimento_id_estabelecimento = value;			
		}
		get {
			return __estabelecimento_id_estabelecimento;			
		}
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
	
	public const String PROP_ESTABELECIMENTO = "__estabelecimento";
	public const String PROP_CLIENTE = "__cliente";
	public const String PROP_SELECAO_ESTABELECIMENTO = "ORM_SelecaoEstabelecimento";
	public const String PROP_ESTABELECIMENTO_ID_ESTABELECIMENTO = "Estabelecimento_id_estabelecimento";
	public const String PROP_CLIENTE_ID_CLIENTE = "Cliente_id_cliente";
}
