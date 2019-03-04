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

public class HorarioEstabelecimentoCriteria : AbstractORMCriteria {
	private Int32Expression _id_horario;
	public Int32Expression Id_horario {
		get {
			return  _id_horario;
		}
		
	}
	
	private ByteExpression _dia;
	public ByteExpression Dia {
		get {
			return  _dia;
		}
		
	}
	
	private DateTimeExpression _hora_abertura;
	public DateTimeExpression Hora_abertura {
		get {
			return  _hora_abertura;
		}
		
	}
	
	private DateTimeExpression _hora_fecho;
	public DateTimeExpression Hora_fecho {
		get {
			return  _hora_fecho;
		}
		
	}
	
	private Int32Expression _estabelecimento_id_estabelecimento;
	public Int32Expression Estabelecimento_id_estabelecimento {
		get {
			return  _estabelecimento_id_estabelecimento;
		}
		
	}
	
	public HorarioEstabelecimentoCriteria(ICriteria criteria) : base(criteria) {
		_id_horario =  new Int32Expression("Id_horario", this);
		_dia =  new ByteExpression("Dia", this);
		_hora_abertura =  new DateTimeExpression("Hora_abertura", this);
		_hora_fecho =  new DateTimeExpression("Hora_fecho", this);
		_estabelecimento_id_estabelecimento =  new Int32Expression("Estabelecimento_id_estabelecimento", this);
	}
	
	public HorarioEstabelecimentoCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(HorarioEstabelecimento))) {
	}
	
	public HorarioEstabelecimentoCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public EstabelecimentoCriteria CreateEstabelecimentoCriteria() {
		return new EstabelecimentoCriteria(CreateCriteria("ORM_Estabelecimento"));
	}
	
	public HorarioEstabelecimento UniqueHorarioEstabelecimento() {
		return (HorarioEstabelecimento)base.UniqueResult();
	}
	
	public HorarioEstabelecimento[] ListHorarioEstabelecimento() {
		IList lList = base.List();
		HorarioEstabelecimento[] lValues = new HorarioEstabelecimento[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

