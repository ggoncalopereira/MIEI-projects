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

public class Cliente_seleciona_iguariaCriteria : AbstractORMCriteria {
	private Int32Expression _cliente_id_cliente;
	public Int32Expression Cliente_id_cliente {
		get {
			return  _cliente_id_cliente;
		}
		
	}
	
	private Int32Expression _iguaria_id_iguaria;
	public Int32Expression Iguaria_id_iguaria {
		get {
			return  _iguaria_id_iguaria;
		}
		
	}
	
	private Int32Expression _iguaria_Estabelecimento;
	public Int32Expression Iguaria_Estabelecimento {
		get {
			return  _iguaria_Estabelecimento;
		}
		
	}
	
	public Cliente_seleciona_iguariaCriteria(ICriteria criteria) : base(criteria) {
		_cliente_id_cliente =  new Int32Expression("Cliente_id_cliente", this);
		_iguaria_id_iguaria =  new Int32Expression("Iguaria_id_iguaria", this);
		_iguaria_Estabelecimento =  new Int32Expression("Iguaria_Estabelecimento", this);
	}
	
	public Cliente_seleciona_iguariaCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(Cliente_seleciona_iguaria))) {
	}
	
	public Cliente_seleciona_iguariaCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public ClienteCriteria CreateClienteCriteria() {
		return new ClienteCriteria(CreateCriteria("ORM_Cliente"));
	}
	
	public IguariaCriteria CreateIguariaCriteria() {
		return new IguariaCriteria(CreateCriteria("ORM_Iguaria"));
	}
	
	public SelecaoIguariaCriteria CreateSelecaoIguariaCriteria() {
		return new SelecaoIguariaCriteria(CreateCriteria("ORM_SelecaoIguaria"));
	}
	
	public Cliente_seleciona_iguaria UniqueCliente_seleciona_iguaria() {
		return (Cliente_seleciona_iguaria)base.UniqueResult();
	}
	
	public Cliente_seleciona_iguaria[] ListCliente_seleciona_iguaria() {
		IList lList = base.List();
		Cliente_seleciona_iguaria[] lValues = new Cliente_seleciona_iguaria[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

