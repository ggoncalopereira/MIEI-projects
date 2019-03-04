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

public class EstabelecimentoCriteria : AbstractORMCriteria {
	private Int32Expression _id_estabelecimento;
	public Int32Expression Id_estabelecimento {
		get {
			return  _id_estabelecimento;
		}
		
	}
	
	private StringExpression _nome_estabelecimento;
	public StringExpression Nome_estabelecimento {
		get {
			return  _nome_estabelecimento;
		}
		
	}
	
	private StringExpression _desc_ambiente;
	public StringExpression Desc_ambiente {
		get {
			return  _desc_ambiente;
		}
		
	}
	
	private DecimalExpression _rating_medio_estabelecimento;
	public DecimalExpression Rating_medio_estabelecimento {
		get {
			return  _rating_medio_estabelecimento;
		}
		
	}
	
	private Int32Expression _telefone;
	public Int32Expression Telefone {
		get {
			return  _telefone;
		}
		
	}
	
	private Int32Expression _visual_estabelecimento;
	public Int32Expression Visual_estabelecimento {
		get {
			return  _visual_estabelecimento;
		}
		
	}
	
	private DecimalExpression _longitude;
	public DecimalExpression Longitude {
		get {
			return  _longitude;
		}
		
	}
	
	private DecimalExpression _latitude;
	public DecimalExpression Latitude {
		get {
			return  _latitude;
		}
		
	}
	
	private StringExpression _rua;
	public StringExpression Rua {
		get {
			return  _rua;
		}
		
	}
	
	private Int32Expression _numero;
	public Int32Expression Numero {
		get {
			return  _numero;
		}
		
	}
	
	private StringExpression _localidade;
	public StringExpression Localidade {
		get {
			return  _localidade;
		}
		
	}
	
	private StringExpression _cod_postal;
	public StringExpression Cod_postal {
		get {
			return  _cod_postal;
		}
		
	}
	
	public EstabelecimentoCriteria(ICriteria criteria) : base(criteria) {
		_id_estabelecimento =  new Int32Expression("Id_estabelecimento", this);
		_nome_estabelecimento =  new StringExpression("Nome_estabelecimento", this);
		_desc_ambiente =  new StringExpression("Desc_ambiente", this);
		_rating_medio_estabelecimento =  new DecimalExpression("Rating_medio_estabelecimento", this);
		_telefone =  new Int32Expression("Telefone", this);
		_visual_estabelecimento =  new Int32Expression("Visual_estabelecimento", this);
		_longitude =  new DecimalExpression("Longitude", this);
		_latitude =  new DecimalExpression("Latitude", this);
		_rua =  new StringExpression("Rua", this);
		_numero =  new Int32Expression("Numero", this);
		_localidade =  new StringExpression("Localidade", this);
		_cod_postal =  new StringExpression("Cod_postal", this);
	}
	
	public EstabelecimentoCriteria(PersistentSession session) : this(session.CreateCriteria(typeof(Estabelecimento))) {
	}
	
	public EstabelecimentoCriteria() : this(BasedeDadosMMPersistentManager.Instance().GetSession()) {
	}
	
	public CategoriaCriteria CreateCategoria1Criteria() {
		return new CategoriaCriteria(CreateCriteria("__categoria1"));
	}
	
	public UtilizadorCriteria CreateUtilizadorCriteria() {
		return new UtilizadorCriteria(CreateCriteria("__utilizador"));
	}
	
	public IguariaCriteria CreateIguariaCriteria() {
		return new IguariaCriteria(CreateCriteria("ORM_Iguaria"));
	}
	
	public HorarioEstabelecimentoCriteria CreateHorarioEstabelecimentoCriteria() {
		return new HorarioEstabelecimentoCriteria(CreateCriteria("ORM_HorarioEstabelecimento"));
	}
	
	public Cliente_seleciona_EstabelecimentoCriteria CreateCliente_seleciona_EstabelecimentoCriteria() {
		return new Cliente_seleciona_EstabelecimentoCriteria(CreateCriteria("ORM_Cliente_seleciona_Estabelecimento"));
	}
	
	public Cliente_avalia_EstabelecimentoCriteria CreateCliente_avalia_EstabelecimentoCriteria() {
		return new Cliente_avalia_EstabelecimentoCriteria(CreateCriteria("ORM_Cliente_avalia_Estabelecimento"));
	}
	
	public Estabelecimento UniqueEstabelecimento() {
		return (Estabelecimento)base.UniqueResult();
	}
	
	public Estabelecimento[] ListEstabelecimento() {
		IList lList = base.List();
		Estabelecimento[] lValues = new Estabelecimento[lList.Count];
		lList.CopyTo(lValues, 0);
		return lValues;
	}
	
}

