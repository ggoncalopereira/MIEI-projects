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
using System.Collections;
using Orm.Criteria;
using Orm;
using NHibernate;

public class CategoriaCriteria : AbstractORMCriteria {
	private Int32Expression _id_categoria;
	public Int32Expression Id_categoria {
		get {
			return  _id_categoria;
		}
		
	}
	
	private StringExpression _descricao;
	public StringExpression Descricao {
		get {
			return  _descricao;
		}
		
	}
	
	public CategoriaCriteria(ICriteria criteria) : base(criteria) {
		_id_categoria =  new Int32Expression("Id_categoria", this);
		_descricao =  new StringExpression("Descricao", this);
	}
	
	public CategoriaCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(Categoria))) {
	}
	
	public CategoriaCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public EstabelecimentoCriteria CreateEstabelecimento1Criteria() {
		return new EstabelecimentoCriteria(CreateCriteria("ORM_Estabelecimento1"));
	}
	
	public Categoria UniqueCategoria() {
		return (Categoria)base.UniqueResult();
	}
	
	public Categoria[] ListCategoria() {
		IList lList = base.List();
		Categoria[] lValues = new Categoria[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

