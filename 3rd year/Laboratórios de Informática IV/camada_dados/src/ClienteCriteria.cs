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

public class ClienteCriteria : AbstractORMCriteria {
	private Int32Expression _id_cliente;
	public Int32Expression Id_cliente {
		get {
			return  _id_cliente;
		}
		
	}
	
	private StringExpression _nome_cliente;
	public StringExpression Nome_cliente {
		get {
			return  _nome_cliente;
		}
		
	}
	
	private ByteExpression _ord_rat_igu;
	public ByteExpression Ord_rat_igu {
		get {
			return  _ord_rat_igu;
		}
		
	}
	
	private ByteExpression _ord_rat_est;
	public ByteExpression Ord_rat_est {
		get {
			return  _ord_rat_est;
		}
		
	}
	
	private ByteExpression _ord_dist;
	public ByteExpression Ord_dist {
		get {
			return  _ord_dist;
		}
		
	}
	
	private ByteExpression _ord_pop_igu;
	public ByteExpression Ord_pop_igu {
		get {
			return  _ord_pop_igu;
		}
		
	}
	
	private ByteExpression _ord_pop_est;
	public ByteExpression Ord_pop_est {
		get {
			return  _ord_pop_est;
		}
		
	}
	
	public ClienteCriteria(ICriteria criteria) : base(criteria) {
		_id_cliente =  new Int32Expression("Id_cliente", this);
		_nome_cliente =  new StringExpression("Nome_cliente", this);
		_ord_rat_igu =  new ByteExpression("Ord_rat_igu", this);
		_ord_rat_est =  new ByteExpression("Ord_rat_est", this);
		_ord_dist =  new ByteExpression("Ord_dist", this);
		_ord_pop_igu =  new ByteExpression("Ord_pop_igu", this);
		_ord_pop_est =  new ByteExpression("Ord_pop_est", this);
	}
	
	public ClienteCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(Cliente))) {
	}
	
	public ClienteCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public UtilizadorCriteria CreateUtilizadorCriteria() {
		return new UtilizadorCriteria(CreateCriteria("__utilizador"));
	}
	
	public Cliente_critica_IguariaCriteria CreateCliente_critica_IguariaCriteria() {
		return new Cliente_critica_IguariaCriteria(CreateCriteria("ORM_Cliente_critica_Iguaria"));
	}
	
	public Cliente_seleciona_iguariaCriteria CreateCliente_seleciona_iguariaCriteria() {
		return new Cliente_seleciona_iguariaCriteria(CreateCriteria("ORM_Cliente_seleciona_iguaria"));
	}
	
	public Cliente_seleciona_EstabelecimentoCriteria CreateCliente_seleciona_EstabelecimentoCriteria() {
		return new Cliente_seleciona_EstabelecimentoCriteria(CreateCriteria("ORM_Cliente_seleciona_Estabelecimento"));
	}
	
	public Cliente_avalia_EstabelecimentoCriteria CreateCliente_avalia_EstabelecimentoCriteria() {
		return new Cliente_avalia_EstabelecimentoCriteria(CreateCriteria("ORM_Cliente_avalia_Estabelecimento"));
	}
	
	public Cliente UniqueCliente() {
		return (Cliente)base.UniqueResult();
	}
	
	public Cliente[] ListCliente() {
		IList lList = base.List();
		Cliente[] lValues = new Cliente[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

