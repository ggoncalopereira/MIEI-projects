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
public class Categoria {
	public Categoria() {
		_OrmAdapter = new CategoriaORMAdapter(this);
		estabelecimento1 = new EstabelecimentoSetCollection<Categoria>(this, _OrmAdapter, ORMConstants.KEY_CATEGORIA_ESTABELECIMENTO1, ORMConstants.KEY_ESTABELECIMENTO_CATEGORIA1, ORMConstants.KEY_MUL_ONE_TO_MANY);
	}
	
	public static Categoria LoadCategoriaByORMID(int id_categoria) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCategoriaByORMID(session,id_categoria);
	}
	
	public static Categoria LoadCategoriaByORMID(PersistentSession session,int id_categoria) {
		return (Categoria) session.Load(typeof(Categoria), (Int32)id_categoria);
	}
	
	public static Categoria[] ListCategoriaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListCategoriaByQuery(session, condition, orderBy);
	}
	
	public static Categoria[] ListCategoriaByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Categoria as Categoria");
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
			Categoria[] result = new Categoria[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static Categoria LoadCategoriaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadCategoriaByQuery(session, condition, orderBy);
	}
	
	public static Categoria LoadCategoriaByQuery(PersistentSession session, string condition, string orderBy) {
		Categoria[] categorias = ListCategoriaByQuery(session, condition, orderBy);
		if (categorias != null && categorias.Length > 0)
			return categorias[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateCategoriaByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateCategoriaByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateCategoriaByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Categoria as Categoria");
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
	
	public static Categoria LoadCategoriaByCriteria(CategoriaCriteria categoriaCriteria) {
		Categoria[] categorias = ListCategoriaByCriteria(categoriaCriteria);
		if(categorias == null || categorias.Length == 0) {
			return null;
		}
		return categorias[0];
	}
	
	public static Categoria[] ListCategoriaByCriteria(CategoriaCriteria categoriaCriteria) {
		return categoriaCriteria.ListCategoria();
	}
	
	public static Categoria CreateCategoria() {
		return new Categoria();
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
			Estabelecimento[] lEstabelecimento1s = estabelecimento1.ToArray();
			foreach(Estabelecimento lEstabelecimento1 in lEstabelecimento1s) {
				lEstabelecimento1.Categoria1 = null;
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
			Estabelecimento[] lEstabelecimento1s = estabelecimento1.ToArray();
			foreach(Estabelecimento lEstabelecimento1 in lEstabelecimento1s) {
				lEstabelecimento1.Categoria1 = null;
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
		if (key == ORMConstants.KEY_CATEGORIA_ESTABELECIMENTO1)
			return (System.Collections.Generic.ISet<T>) __estabelecimento1;
		return null;
	}
	
	private class CategoriaORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly Categoria __Categoria;
		
		internal CategoriaORMAdapter(Categoria value) {
			__Categoria = value;
		}
		
		public override System.Collections.Generic.ISet<T> GetSet<T>(int key) {
			return __Categoria.This_GetSet<T>(key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private int __id_categoria;
	
	private string __descricao;
	
	private System.Collections.Generic.ISet<Estabelecimento> __estabelecimento1 = new System.Collections.Generic.HashSet<Estabelecimento>();
	
	public int Id_categoria {
		set {
			this.__id_categoria = value;			
		}
		get {
			return __id_categoria;			
		}
	}
	
	public int ORMID {
		get {
			return Id_categoria;			
		}
	}
	
	public string Descricao {
		set {
			this.__descricao = value;			
		}
		get {
			return __descricao;			
		}
	}
	
	private System.Collections.Generic.ISet<Estabelecimento> ORM_Estabelecimento1 {
		get  {
			return __estabelecimento1;			
		}
		
		set {
			__estabelecimento1 = value;			
		}
	}
	
	public readonly EstabelecimentoSetCollection<Categoria> estabelecimento1;
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Id_categoria);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("Categoria[ ");
			sb.AppendFormat("Id_categoria={0} ", Id_categoria);
			sb.AppendFormat("Descricao={0} ", Descricao);
			sb.AppendFormat("estabelecimento1.size={0} ", estabelecimento1.Size());
			sb.Append("]");
			return sb.ToString();
		}
	}
	
	public const String PROP_ID_CATEGORIA = "Id_categoria";
	public const String PROP_DESCRICAO = "Descricao";
	public const String PROP_ESTABELECIMENTO1 = "ORM_Estabelecimento1";
}
