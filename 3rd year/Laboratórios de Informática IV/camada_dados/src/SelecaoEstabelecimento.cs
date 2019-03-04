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
public class SelecaoEstabelecimento {
	public SelecaoEstabelecimento() {
		_OrmAdapter = new SelecaoEstabelecimentoORMAdapter(this);
	}
	
	public static SelecaoEstabelecimento LoadSelecaoEstabelecimentoByORMID(int id_selecao, int estabelecimento_Estabelecimento, int estabelecimento_Cliente) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadSelecaoEstabelecimentoByORMID(session,id_selecao, estabelecimento_Estabelecimento, estabelecimento_Cliente);
	}
	
	public static SelecaoEstabelecimento LoadSelecaoEstabelecimentoByORMID(PersistentSession session,int id_selecao, int estabelecimento_Estabelecimento, int estabelecimento_Cliente) {
		SelecaoEstabelecimento selecaoestabelecimento = new SelecaoEstabelecimento();
		selecaoestabelecimento.Id_selecao = id_selecao;
		selecaoestabelecimento.Estabelecimento_Estabelecimento = estabelecimento_Estabelecimento;
		selecaoestabelecimento.Estabelecimento_Cliente = estabelecimento_Cliente;
		
		return (SelecaoEstabelecimento) session.Load(typeof(SelecaoEstabelecimento), selecaoestabelecimento);
	}
	
	public static SelecaoEstabelecimento[] ListSelecaoEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListSelecaoEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static SelecaoEstabelecimento[] ListSelecaoEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From SelecaoEstabelecimento as SelecaoEstabelecimento");
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
			SelecaoEstabelecimento[] result = new SelecaoEstabelecimento[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static SelecaoEstabelecimento LoadSelecaoEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadSelecaoEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static SelecaoEstabelecimento LoadSelecaoEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		SelecaoEstabelecimento[] selecaoEstabelecimentos = ListSelecaoEstabelecimentoByQuery(session, condition, orderBy);
		if (selecaoEstabelecimentos != null && selecaoEstabelecimentos.Length > 0)
			return selecaoEstabelecimentos[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateSelecaoEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateSelecaoEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateSelecaoEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From SelecaoEstabelecimento as SelecaoEstabelecimento");
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
	
	public static SelecaoEstabelecimento LoadSelecaoEstabelecimentoByCriteria(SelecaoEstabelecimentoCriteria selecaoEstabelecimentoCriteria) {
		SelecaoEstabelecimento[] selecaoEstabelecimentos = ListSelecaoEstabelecimentoByCriteria(selecaoEstabelecimentoCriteria);
		if(selecaoEstabelecimentos == null || selecaoEstabelecimentos.Length == 0) {
			return null;
		}
		return selecaoEstabelecimentos[0];
	}
	
	public static SelecaoEstabelecimento[] ListSelecaoEstabelecimentoByCriteria(SelecaoEstabelecimentoCriteria selecaoEstabelecimentoCriteria) {
		return selecaoEstabelecimentoCriteria.ListSelecaoEstabelecimento();
	}
	
	public override bool Equals(object obj) {
		if (obj == this)
			return true;
		if (!(obj is SelecaoEstabelecimento))
			return false;
		SelecaoEstabelecimento selecaoestabelecimento = obj as SelecaoEstabelecimento;
		if (Id_selecao != selecaoestabelecimento.Id_selecao)
			return false;
		if (Estabelecimento_Estabelecimento != selecaoestabelecimento.Estabelecimento_Estabelecimento)
			return false;
		if (Estabelecimento_Cliente != selecaoestabelecimento.Estabelecimento_Cliente)
			return false;
		return true;
	}
	
	public override int GetHashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) Id_selecao;
		hashcode = hashcode + (int) Estabelecimento_Estabelecimento;
		hashcode = hashcode + (int) Estabelecimento_Cliente;
		return hashcode;
	}
	
	public static SelecaoEstabelecimento CreateSelecaoEstabelecimento() {
		return new SelecaoEstabelecimento();
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
				Estabelecimento.selecaoEstabelecimento.Remove(this);
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
				Estabelecimento.selecaoEstabelecimento.Remove(this);
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
		if (key == ORMConstants.KEY_SELECAOESTABELECIMENTO_ESTABELECIMENTO) {
			this.__estabelecimento = (Cliente_seleciona_Estabelecimento) owner;
		}
	}
	
	private class SelecaoEstabelecimentoORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly SelecaoEstabelecimento __SelecaoEstabelecimento;
		
		internal SelecaoEstabelecimentoORMAdapter(SelecaoEstabelecimento value) {
			__SelecaoEstabelecimento = value;
		}
		
		public override void SetOwner(object owner, int key) {
			__SelecaoEstabelecimento.This_SetOwner(owner, key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private int __id_selecao;
	
	private Cliente_seleciona_Estabelecimento __estabelecimento;
	
	private DateTime __data_hora_selecao;
	
	public int Id_selecao {
		set {
			this.__id_selecao = value;			
		}
		get {
			return __id_selecao;			
		}
	}
	
	public DateTime Data_hora_selecao {
		set {
			this.__data_hora_selecao = value;			
		}
		get {
			return __data_hora_selecao;			
		}
	}
	
	public Cliente_seleciona_Estabelecimento Estabelecimento {
		set {
			if(__estabelecimento!= null) {
				__estabelecimento.selecaoEstabelecimento.Remove(this);
			}
			
			if(value != null) {
				value.selecaoEstabelecimento.Add(this);
			}
			if (value != null) {
				__estabelecimento_Estabelecimento = value.Estabelecimento.Id_estabelecimento;
				__estabelecimento_Cliente = value.Cliente.Id_cliente;
			}
			else {
				__estabelecimento_Estabelecimento = 0;
				__estabelecimento_Cliente = 0;
			}
		}
		get {
			return __estabelecimento;			
		}
	}
	
	private Cliente_seleciona_Estabelecimento ORM_Estabelecimento {
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
			return Convert.ToString(Id_selecao) + " "+ Convert.ToString(Estabelecimento_Estabelecimento) + " "+ Convert.ToString(Estabelecimento_Cliente);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("SelecaoEstabelecimento[ ");
			sb.AppendFormat("Id_selecao={0} ", Id_selecao);
			if (Estabelecimento != null)
				sb.AppendFormat("Estabelecimento.Persist_ID={0} ", Estabelecimento.ToString(true) + "");
			else
				sb.Append("Estabelecimento=null ");
			sb.AppendFormat("Data_hora_selecao={0} ", Data_hora_selecao);
			sb.AppendFormat("Estabelecimento_Estabelecimento={0} ", Estabelecimento_Estabelecimento);
			sb.AppendFormat("Estabelecimento_Cliente={0} ", Estabelecimento_Cliente);
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
	
	
	private int __estabelecimento_Estabelecimento;
	
	public int Estabelecimento_Estabelecimento {
		set {
			this.__estabelecimento_Estabelecimento = value;			
		}
		get {
			return __estabelecimento_Estabelecimento;			
		}
	}
	
	private int __estabelecimento_Cliente;
	
	public int Estabelecimento_Cliente {
		set {
			this.__estabelecimento_Cliente = value;			
		}
		get {
			return __estabelecimento_Cliente;			
		}
	}
	
	public const String PROP_ID_SELECAO = "Id_selecao";
	public const String PROP_ESTABELECIMENTO = "__estabelecimento";
	public const String PROP_DATA_HORA_SELECAO = "Data_hora_selecao";
	public const String PROP_ESTABELECIMENTO__ESTABELECIMENTO = "Estabelecimento_Estabelecimento";
	public const String PROP_ESTABELECIMENTO__CLIENTE = "Estabelecimento_Cliente";
}
