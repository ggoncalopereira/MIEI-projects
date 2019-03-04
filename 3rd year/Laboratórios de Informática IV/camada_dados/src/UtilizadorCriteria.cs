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

public class UtilizadorCriteria : AbstractORMCriteria {
	private StringExpression _email;
	public StringExpression Email {
		get {
			return  _email;
		}
		
	}
	
	private StringExpression _password;
	public StringExpression Password {
		get {
			return  _password;
		}
		
	}
	
	private ByteExpression _tipo;
	public ByteExpression Tipo {
		get {
			return  _tipo;
		}
		
	}
	
	public UtilizadorCriteria(ICriteria criteria) : base(criteria) {
		_email =  new StringExpression("Email", this);
		_password =  new StringExpression("Password", this);
		_tipo =  new ByteExpression("Tipo", this);
	}
	
	public UtilizadorCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(Utilizador))) {
	}
	
	public UtilizadorCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public EstabelecimentoCriteria CreateEstabelecimentoCriteria() {
		return new EstabelecimentoCriteria(CreateCriteria("__estabelecimento"));
	}
	
	public ClienteCriteria CreateClienteCriteria() {
		return new ClienteCriteria(CreateCriteria("__cliente"));
	}
	
	public Utilizador UniqueUtilizador() {
		return (Utilizador)base.UniqueResult();
	}
	
	public Utilizador[] ListUtilizador() {
		IList lList = base.List();
		Utilizador[] lValues = new Utilizador[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

