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

public class Cliente_seleciona_EstabelecimentoCriteria : AbstractORMCriteria {
	private Int32Expression _estabelecimento_id_estabelecimento;
	public Int32Expression Estabelecimento_id_estabelecimento {
		get {
			return  _estabelecimento_id_estabelecimento;
		}
		
	}
	
	private Int32Expression _cliente_id_cliente;
	public Int32Expression Cliente_id_cliente {
		get {
			return  _cliente_id_cliente;
		}
		
	}
	
	public Cliente_seleciona_EstabelecimentoCriteria(ICriteria criteria) : base(criteria) {
		_estabelecimento_id_estabelecimento =  new Int32Expression("Estabelecimento_id_estabelecimento", this);
		_cliente_id_cliente =  new Int32Expression("Cliente_id_cliente", this);
	}
	
	public Cliente_seleciona_EstabelecimentoCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(Cliente_seleciona_Estabelecimento))) {
	}
	
	public Cliente_seleciona_EstabelecimentoCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public EstabelecimentoCriteria CreateEstabelecimentoCriteria() {
		return new EstabelecimentoCriteria(CreateCriteria("ORM_Estabelecimento"));
	}
	
	public ClienteCriteria CreateClienteCriteria() {
		return new ClienteCriteria(CreateCriteria("ORM_Cliente"));
	}
	
	public SelecaoEstabelecimentoCriteria CreateSelecaoEstabelecimentoCriteria() {
		return new SelecaoEstabelecimentoCriteria(CreateCriteria("ORM_SelecaoEstabelecimento"));
	}
	
	public Cliente_seleciona_Estabelecimento UniqueCliente_seleciona_Estabelecimento() {
		return (Cliente_seleciona_Estabelecimento)base.UniqueResult();
	}
	
	public Cliente_seleciona_Estabelecimento[] ListCliente_seleciona_Estabelecimento() {
		IList lList = base.List();
		Cliente_seleciona_Estabelecimento[] lValues = new Cliente_seleciona_Estabelecimento[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

