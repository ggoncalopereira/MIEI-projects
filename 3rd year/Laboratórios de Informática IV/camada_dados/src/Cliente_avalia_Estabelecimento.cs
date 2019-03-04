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
public class Cliente_avalia_Estabelecimento {
	public Cliente_avalia_Estabelecimento() {
		_OrmAdapter = new Cliente_avalia_EstabelecimentoORMAdapter(this);
	}
	
	public static Cliente_avalia_Estabelecimento LoadCliente_avalia_EstabelecimentoByORMID(int estabelecimento_id_estabelecimento, int cliente_id_cliente) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCliente_avalia_EstabelecimentoByORMID(session,estabelecimento_id_estabelecimento, cliente_id_cliente);
	}
	
	public static Cliente_avalia_Estabelecimento LoadCliente_avalia_EstabelecimentoByORMID(PersistentSession session,int estabelecimento_id_estabelecimento, int cliente_id_cliente) {
		Cliente_avalia_Estabelecimento cliente_avalia_estabelecimento = new Cliente_avalia_Estabelecimento();
		cliente_avalia_estabelecimento.Estabelecimento_id_estabelecimento = estabelecimento_id_estabelecimento;
		cliente_avalia_estabelecimento.Cliente_id_cliente = cliente_id_cliente;
		
		return (Cliente_avalia_Estabelecimento) session.Load(typeof(Cliente_avalia_Estabelecimento), cliente_avalia_estabelecimento);
	}
	
	public static Cliente_avalia_Estabelecimento[] ListCliente_avalia_EstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListCliente_avalia_EstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static Cliente_avalia_Estabelecimento[] ListCliente_avalia_EstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente_avalia_Estabelecimento as Cliente_avalia_Estabelecimento");
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
			Cliente_avalia_Estabelecimento[] result = new Cliente_avalia_Estabelecimento[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static Cliente_avalia_Estabelecimento LoadCliente_avalia_EstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCliente_avalia_EstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static Cliente_avalia_Estabelecimento LoadCliente_avalia_EstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		Cliente_avalia_Estabelecimento[] cliente_avalia_Estabelecimentos = ListCliente_avalia_EstabelecimentoByQuery(session, condition, orderBy);
		if (cliente_avalia_Estabelecimentos != null && cliente_avalia_Estabelecimentos.Length > 0)
			return cliente_avalia_Estabelecimentos[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateCliente_avalia_EstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateCliente_avalia_EstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateCliente_avalia_EstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Cliente_avalia_Estabelecimento as Cliente_avalia_Estabelecimento");
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
	
	public static Cliente_avalia_Estabelecimento LoadCliente_avalia_EstabelecimentoByCriteria(Cliente_avalia_EstabelecimentoCriteria cliente_avalia_EstabelecimentoCriteria) {
		Cliente_avalia_Estabelecimento[] cliente_avalia_Estabelecimentos = ListCliente_avalia_EstabelecimentoByCriteria(cliente_avalia_EstabelecimentoCriteria);
		if(cliente_avalia_Estabelecimentos == null || cliente_avalia_Estabelecimentos.Length == 0) {
			return null;
		}
		return cliente_avalia_Estabelecimentos[0];
	}
	
	public static Cliente_avalia_Estabelecimento[] ListCliente_avalia_EstabelecimentoByCriteria(Cliente_avalia_EstabelecimentoCriteria cliente_avalia_EstabelecimentoCriteria) {
		return cliente_avalia_EstabelecimentoCriteria.ListCliente_avalia_Estabelecimento();
	}
	
	public override bool Equals(object obj) {
		if (obj == this)
			return true;
		if (!(obj is Cliente_avalia_Estabelecimento))
			return false;
		Cliente_avalia_Estabelecimento cliente_avalia_estabelecimento = obj as Cliente_avalia_Estabelecimento;
		if (Estabelecimento_id_estabelecimento != cliente_avalia_estabelecimento.Estabelecimento_id_estabelecimento)
			return false;
		if (Cliente_id_cliente != cliente_avalia_estabelecimento.Cliente_id_cliente)
			return false;
		return true;
	}
	
	public override int GetHashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) Estabelecimento_id_estabelecimento;
		hashcode = hashcode + (int) Cliente_id_cliente;
		return hashcode;
	}
	
	public static Cliente_avalia_Estabelecimento CreateCliente_avalia_Estabelecimento() {
		return new Cliente_avalia_Estabelecimento();
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
				Estabelecimento.cliente_avalia_Estabelecimento.Remove(this);
			}
			if(Cliente != null) {
				Cliente.cliente_avalia_Estabelecimento.Remove(this);
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
				Estabelecimento.cliente_avalia_Estabelecimento.Remove(this);
			}
			if(Cliente != null) {
				Cliente.cliente_avalia_Estabelecimento.Remove(this);
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
		if (key == ORMConstants.KEY_CLIENTE_AVALIA_ESTABELECIMENTO_CLIENTE) {
			this.__cliente = (Cliente) owner;
		}
		
		else if (key == ORMConstants.KEY_CLIENTE_AVALIA_ESTABELECIMENTO_ESTABELECIMENTO) {
			this.__estabelecimento = (Estabelecimento) owner;
		}
	}
	
	private class Cliente_avalia_EstabelecimentoORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly Cliente_avalia_Estabelecimento __Cliente_avalia_Estabelecimento;
		
		internal Cliente_avalia_EstabelecimentoORMAdapter(Cliente_avalia_Estabelecimento value) {
			__Cliente_avalia_Estabelecimento = value;
		}
		
		public override void SetOwner(object owner, int key) {
			__Cliente_avalia_Estabelecimento.This_SetOwner(owner, key);
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
	
	private Decimal __rating_est = new Decimal(0);
	
	private DateTime __data_avaliacao;
	
	public Decimal Rating_est {
		set {
			this.__rating_est = value;			
		}
		get {
			return __rating_est;			
		}
	}
	
	public DateTime Data_avaliacao {
		set {
			this.__data_avaliacao = value;			
		}
		get {
			return __data_avaliacao;			
		}
	}
	
	public Cliente Cliente {
		set {
			if(__cliente!= null) {
				__cliente.cliente_avalia_Estabelecimento.Remove(this);
			}
			
			if(value != null) {
				value.cliente_avalia_Estabelecimento.Add(this);
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
	
	public Estabelecimento Estabelecimento {
		set {
			if(__estabelecimento!= null) {
				__estabelecimento.cliente_avalia_Estabelecimento.Remove(this);
			}
			
			if(value != null) {
				value.cliente_avalia_Estabelecimento.Add(this);
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
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Estabelecimento_id_estabelecimento) + " "+ Convert.ToString(Cliente_id_cliente);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("Cliente_avalia_Estabelecimento[ ");
			if (Estabelecimento != null)
				sb.AppendFormat("Estabelecimento.Persist_ID={0} ", Estabelecimento.ToString(true) + "");
			else
				sb.Append("Estabelecimento=null ");
			if (Cliente != null)
				sb.AppendFormat("Cliente.Persist_ID={0} ", Cliente.ToString(true) + "");
			else
				sb.Append("Cliente=null ");
			sb.AppendFormat("Rating_est={0} ", Rating_est);
			sb.AppendFormat("Data_avaliacao={0} ", Data_avaliacao);
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
	public const String PROP_RATING_EST = "Rating_est";
	public const String PROP_DATA_AVALIACAO = "Data_avaliacao";
	public const String PROP_ESTABELECIMENTO_ID_ESTABELECIMENTO = "Estabelecimento_id_estabelecimento";
	public const String PROP_CLIENTE_ID_CLIENTE = "Cliente_id_cliente";
}
