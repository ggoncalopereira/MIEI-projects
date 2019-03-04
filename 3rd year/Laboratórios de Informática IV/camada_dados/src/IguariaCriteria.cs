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

public class IguariaCriteria : AbstractORMCriteria {
	private Int32Expression _id_iguaria;
	public Int32Expression Id_iguaria {
		get {
			return  _id_iguaria;
		}
		
	}
	
	private StringExpression _nome_iguaria;
	public StringExpression Nome_iguaria {
		get {
			return  _nome_iguaria;
		}
		
	}
	
	private Int32Expression _visual_iguaria;
	public Int32Expression Visual_iguaria {
		get {
			return  _visual_iguaria;
		}
		
	}
	
	private DecimalExpression _rating_medio_iguaria;
	public DecimalExpression Rating_medio_iguaria {
		get {
			return  _rating_medio_iguaria;
		}
		
	}
	
	private ByteArrayExpression _fotografia;
	public ByteArrayExpression Fotografia {
		get {
			return  _fotografia;
		}
		
	}
	
	private DecimalExpression _preco;
	public DecimalExpression Preco {
		get {
			return  _preco;
		}
		
	}
	
	private Int32Expression _estabelecimento_id_estabelecimento;
	public Int32Expression Estabelecimento_id_estabelecimento {
		get {
			return  _estabelecimento_id_estabelecimento;
		}
		
	}
	
	public IguariaCriteria(ICriteria criteria) : base(criteria) {
		_id_iguaria =  new Int32Expression("Id_iguaria", this);
		_nome_iguaria =  new StringExpression("Nome_iguaria", this);
		_visual_iguaria =  new Int32Expression("Visual_iguaria", this);
		_rating_medio_iguaria =  new DecimalExpression("Rating_medio_iguaria", this);
		_fotografia =  new ByteArrayExpression("Fotografia", this);
		_preco =  new DecimalExpression("Preco", this);
		_estabelecimento_id_estabelecimento =  new Int32Expression("Estabelecimento_id_estabelecimento", this);
	}
	
	public IguariaCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(Iguaria))) {
	}
	
	public IguariaCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public EstabelecimentoCriteria CreateEstabelecimentoCriteria() {
		return new EstabelecimentoCriteria(CreateCriteria("ORM_Estabelecimento"));
	}
	
	public Cliente_critica_IguariaCriteria CreateCliente_critica_IguariaCriteria() {
		return new Cliente_critica_IguariaCriteria(CreateCriteria("ORM_Cliente_critica_Iguaria"));
	}
	
	public Cliente_seleciona_iguariaCriteria CreateCliente_seleciona_iguariaCriteria() {
		return new Cliente_seleciona_iguariaCriteria(CreateCriteria("ORM_Cliente_seleciona_iguaria"));
	}
	
	public Iguaria UniqueIguaria() {
		return (Iguaria)base.UniqueResult();
	}
	
	public Iguaria[] ListIguaria() {
		IList lList = base.List();
		Iguaria[] lValues = new Iguaria[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

