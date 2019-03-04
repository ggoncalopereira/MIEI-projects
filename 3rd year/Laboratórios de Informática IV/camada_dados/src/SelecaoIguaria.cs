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
public class SelecaoIguaria {
	public SelecaoIguaria() {
		_OrmAdapter = new SelecaoIguariaORMAdapter(this);
	}
	
	public static SelecaoIguaria LoadSelecaoIguariaByORMID(int id_visualizacao, int cliente_Cliente, int cliente_Iguaria, int cliente_Estabelecimento) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadSelecaoIguariaByORMID(session,id_visualizacao, cliente_Cliente, cliente_Iguaria, cliente_Estabelecimento);
	}
	
	public static SelecaoIguaria LoadSelecaoIguariaByORMID(PersistentSession session,int id_visualizacao, int cliente_Cliente, int cliente_Iguaria, int cliente_Estabelecimento) {
		SelecaoIguaria selecaoiguaria = new SelecaoIguaria();
		selecaoiguaria.Id_visualizacao = id_visualizacao;
		selecaoiguaria.Cliente_Cliente = cliente_Cliente;
		selecaoiguaria.Cliente_Iguaria = cliente_Iguaria;
		selecaoiguaria.Cliente_Estabelecimento = cliente_Estabelecimento;
		
		return (SelecaoIguaria) session.Load(typeof(SelecaoIguaria), selecaoiguaria);
	}
	
	public static SelecaoIguaria[] ListSelecaoIguariaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListSelecaoIguariaByQuery(session, condition, orderBy);
	}
	
	public static SelecaoIguaria[] ListSelecaoIguariaByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From SelecaoIguaria as SelecaoIguaria");
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
			SelecaoIguaria[] result = new SelecaoIguaria[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static SelecaoIguaria LoadSelecaoIguariaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadSelecaoIguariaByQuery(session, condition, orderBy);
	}
	
	public static SelecaoIguaria LoadSelecaoIguariaByQuery(PersistentSession session, string condition, string orderBy) {
		SelecaoIguaria[] selecaoIguarias = ListSelecaoIguariaByQuery(session, condition, orderBy);
		if (selecaoIguarias != null && selecaoIguarias.Length > 0)
			return selecaoIguarias[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateSelecaoIguariaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateSelecaoIguariaByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateSelecaoIguariaByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From SelecaoIguaria as SelecaoIguaria");
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
	
	public static SelecaoIguaria LoadSelecaoIguariaByCriteria(SelecaoIguariaCriteria selecaoIguariaCriteria) {
		SelecaoIguaria[] selecaoIguarias = ListSelecaoIguariaByCriteria(selecaoIguariaCriteria);
		if(selecaoIguarias == null || selecaoIguarias.Length == 0) {
			return null;
		}
		return selecaoIguarias[0];
	}
	
	public static SelecaoIguaria[] ListSelecaoIguariaByCriteria(SelecaoIguariaCriteria selecaoIguariaCriteria) {
		return selecaoIguariaCriteria.ListSelecaoIguaria();
	}
	
	public override bool Equals(object obj) {
		if (obj == this)
			return true;
		if (!(obj is SelecaoIguaria))
			return false;
		SelecaoIguaria selecaoiguaria = obj as SelecaoIguaria;
		if (Id_visualizacao != selecaoiguaria.Id_visualizacao)
			return false;
		if (Cliente_Cliente != selecaoiguaria.Cliente_Cliente)
			return false;
		if (Cliente_Iguaria != selecaoiguaria.Cliente_Iguaria)
			return false;
		if (Cliente_Estabelecimento != selecaoiguaria.Cliente_Estabelecimento)
			return false;
		return true;
	}
	
	public override int GetHashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) Id_visualizacao;
		hashcode = hashcode + (int) Cliente_Cliente;
		hashcode = hashcode + (int) Cliente_Iguaria;
		hashcode = hashcode + (int) Cliente_Estabelecimento;
		return hashcode;
	}
	
	public static SelecaoIguaria CreateSelecaoIguaria() {
		return new SelecaoIguaria();
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
				Cliente.selecaoIguaria.Remove(this);
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
				Cliente.selecaoIguaria.Remove(this);
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
		if (key == ORMConstants.KEY_SELECAOIGUARIA_CLIENTE) {
			this.__cliente = (Cliente_seleciona_iguaria) owner;
		}
	}
	
	private class SelecaoIguariaORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly SelecaoIguaria __SelecaoIguaria;
		
		internal SelecaoIguariaORMAdapter(SelecaoIguaria value) {
			__SelecaoIguaria = value;
		}
		
		public override void SetOwner(object owner, int key) {
			__SelecaoIguaria.This_SetOwner(owner, key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private int __id_visualizacao;
	
	private Cliente_seleciona_iguaria __cliente;
	
	private DateTime __data_hora_visualizacao;
	
	public int Id_visualizacao {
		set {
			this.__id_visualizacao = value;			
		}
		get {
			return __id_visualizacao;			
		}
	}
	
	public DateTime Data_hora_visualizacao {
		set {
			this.__data_hora_visualizacao = value;			
		}
		get {
			return __data_hora_visualizacao;			
		}
	}
	
	public Cliente_seleciona_iguaria Cliente {
		set {
			if(__cliente!= null) {
				__cliente.selecaoIguaria.Remove(this);
			}
			
			if(value != null) {
				value.selecaoIguaria.Add(this);
			}
			if (value != null) {
				__cliente_Cliente = value.Cliente.Id_cliente;
				__cliente_Iguaria = value.Iguaria.Id_iguaria;
				__cliente_Estabelecimento = value.Iguaria.Estabelecimento.Id_estabelecimento;
			}
			else {
				__cliente_Cliente = 0;
				__cliente_Iguaria = 0;
				__cliente_Estabelecimento = 0;
			}
		}
		get {
			return __cliente;			
		}
	}
	
	private Cliente_seleciona_iguaria ORM_Cliente {
		set {
			this.__cliente = value;			
		}
		
		get {
			return __cliente;			
		}
	}
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Id_visualizacao) + " "+ Convert.ToString(Cliente_Cliente) + " "+ Convert.ToString(Cliente_Iguaria) + " "+ Convert.ToString(Cliente_Estabelecimento);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("SelecaoIguaria[ ");
			sb.AppendFormat("Id_visualizacao={0} ", Id_visualizacao);
			if (Cliente != null)
				sb.AppendFormat("Cliente.Persist_ID={0} ", Cliente.ToString(true) + "");
			else
				sb.Append("Cliente=null ");
			sb.AppendFormat("Data_hora_visualizacao={0} ", Data_hora_visualizacao);
			sb.AppendFormat("Cliente_Cliente={0} ", Cliente_Cliente);
			sb.AppendFormat("Cliente_Iguaria={0} ", Cliente_Iguaria);
			sb.AppendFormat("Cliente_Estabelecimento={0} ", Cliente_Estabelecimento);
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
	
	
	private int __cliente_Cliente;
	
	public int Cliente_Cliente {
		set {
			this.__cliente_Cliente = value;			
		}
		get {
			return __cliente_Cliente;			
		}
	}
	
	private int __cliente_Iguaria;
	
	public int Cliente_Iguaria {
		set {
			this.__cliente_Iguaria = value;			
		}
		get {
			return __cliente_Iguaria;			
		}
	}
	
	private int __cliente_Estabelecimento;
	
	public int Cliente_Estabelecimento {
		set {
			this.__cliente_Estabelecimento = value;			
		}
		get {
			return __cliente_Estabelecimento;			
		}
	}
	
	public const String PROP_ID_VISUALIZACAO = "Id_visualizacao";
	public const String PROP_CLIENTE = "__cliente";
	public const String PROP_DATA_HORA_VISUALIZACAO = "Data_hora_visualizacao";
	public const String PROP_CLIENTE__CLIENTE = "Cliente_Cliente";
	public const String PROP_CLIENTE__IGUARIA = "Cliente_Iguaria";
	public const String PROP_CLIENTE__ESTABELECIMENTO = "Cliente_Estabelecimento";
}
