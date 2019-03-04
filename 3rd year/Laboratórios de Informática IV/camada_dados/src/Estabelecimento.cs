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
public class Estabelecimento {
	public Estabelecimento() {
		_OrmAdapter = new EstabelecimentoORMAdapter(this);
		iguaria = new IguariaSetCollection<Estabelecimento>(this, _OrmAdapter, ORMConstants.KEY_ESTABELECIMENTO_IGUARIA, ORMConstants.KEY_IGUARIA_ESTABELECIMENTO, ORMConstants.KEY_MUL_ONE_TO_MANY);
		horarioEstabelecimento = new HorarioEstabelecimentoSetCollection<Estabelecimento>(this, _OrmAdapter, ORMConstants.KEY_ESTABELECIMENTO_HORARIOESTABELECIMENTO, ORMConstants.KEY_HORARIOESTABELECIMENTO_ESTABELECIMENTO, ORMConstants.KEY_MUL_ONE_TO_MANY);
		cliente_seleciona_Estabelecimento = new Cliente_seleciona_EstabelecimentoSetCollection<Estabelecimento>(this, _OrmAdapter, ORMConstants.KEY_ESTABELECIMENTO_CLIENTE_SELECIONA_ESTABELECIMENTO, ORMConstants.KEY_CLIENTE_SELECIONA_ESTABELECIMENTO_ESTABELECIMENTO, ORMConstants.KEY_MUL_ONE_TO_MANY);
		cliente_avalia_Estabelecimento = new Cliente_avalia_EstabelecimentoSetCollection<Estabelecimento>(this, _OrmAdapter, ORMConstants.KEY_ESTABELECIMENTO_CLIENTE_AVALIA_ESTABELECIMENTO, ORMConstants.KEY_CLIENTE_AVALIA_ESTABELECIMENTO_ESTABELECIMENTO, ORMConstants.KEY_MUL_ONE_TO_MANY);
	}
	
	public static Estabelecimento LoadEstabelecimentoByORMID(int id_estabelecimento) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadEstabelecimentoByORMID(session,id_estabelecimento);
	}
	
	public static Estabelecimento LoadEstabelecimentoByORMID(PersistentSession session,int id_estabelecimento) {
		return (Estabelecimento) session.Load(typeof(Estabelecimento), (Int32)id_estabelecimento);
	}
	
	public static Estabelecimento[] ListEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static Estabelecimento[] ListEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Estabelecimento as Estabelecimento");
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
			Estabelecimento[] result = new Estabelecimento[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static Estabelecimento LoadEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static Estabelecimento LoadEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		Estabelecimento[] estabelecimentos = ListEstabelecimentoByQuery(session, condition, orderBy);
		if (estabelecimentos != null && estabelecimentos.Length > 0)
			return estabelecimentos[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From Estabelecimento as Estabelecimento");
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
	
	public static Estabelecimento LoadEstabelecimentoByCriteria(EstabelecimentoCriteria estabelecimentoCriteria) {
		Estabelecimento[] estabelecimentos = ListEstabelecimentoByCriteria(estabelecimentoCriteria);
		if(estabelecimentos == null || estabelecimentos.Length == 0) {
			return null;
		}
		return estabelecimentos[0];
	}
	
	public static Estabelecimento[] ListEstabelecimentoByCriteria(EstabelecimentoCriteria estabelecimentoCriteria) {
		return estabelecimentoCriteria.ListEstabelecimento();
	}
	
	public static Estabelecimento CreateEstabelecimento() {
		return new Estabelecimento();
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
			if(Categoria1 != null) {
				Categoria1.estabelecimento1.Remove(this);
			}
			if(Utilizador != null) {
				Utilizador.Estabelecimento = null;
			}
			Iguaria[] lIguarias = iguaria.ToArray();
			foreach(Iguaria lIguaria in lIguarias) {
				lIguaria.Estabelecimento = null;
			}
			HorarioEstabelecimento[] lHorarioEstabelecimentos = horarioEstabelecimento.ToArray();
			foreach(HorarioEstabelecimento lHorarioEstabelecimento in lHorarioEstabelecimentos) {
				lHorarioEstabelecimento.Estabelecimento = null;
			}
			Cliente_seleciona_Estabelecimento[] lCliente_seleciona_Estabelecimentos = cliente_seleciona_Estabelecimento.ToArray();
			foreach(Cliente_seleciona_Estabelecimento lCliente_seleciona_Estabelecimento in lCliente_seleciona_Estabelecimentos) {
				lCliente_seleciona_Estabelecimento.Estabelecimento = null;
			}
			Cliente_avalia_Estabelecimento[] lCliente_avalia_Estabelecimentos = cliente_avalia_Estabelecimento.ToArray();
			foreach(Cliente_avalia_Estabelecimento lCliente_avalia_Estabelecimento in lCliente_avalia_Estabelecimentos) {
				lCliente_avalia_Estabelecimento.Estabelecimento = null;
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
			if(Categoria1 != null) {
				Categoria1.estabelecimento1.Remove(this);
			}
			if(Utilizador != null) {
				Utilizador.Estabelecimento = null;
			}
			Iguaria[] lIguarias = iguaria.ToArray();
			foreach(Iguaria lIguaria in lIguarias) {
				lIguaria.Estabelecimento = null;
			}
			HorarioEstabelecimento[] lHorarioEstabelecimentos = horarioEstabelecimento.ToArray();
			foreach(HorarioEstabelecimento lHorarioEstabelecimento in lHorarioEstabelecimentos) {
				lHorarioEstabelecimento.Estabelecimento = null;
			}
			Cliente_seleciona_Estabelecimento[] lCliente_seleciona_Estabelecimentos = cliente_seleciona_Estabelecimento.ToArray();
			foreach(Cliente_seleciona_Estabelecimento lCliente_seleciona_Estabelecimento in lCliente_seleciona_Estabelecimentos) {
				lCliente_seleciona_Estabelecimento.Estabelecimento = null;
			}
			Cliente_avalia_Estabelecimento[] lCliente_avalia_Estabelecimentos = cliente_avalia_Estabelecimento.ToArray();
			foreach(Cliente_avalia_Estabelecimento lCliente_avalia_Estabelecimento in lCliente_avalia_Estabelecimentos) {
				lCliente_avalia_Estabelecimento.Estabelecimento = null;
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
		if (key == ORMConstants.KEY_ESTABELECIMENTO_IGUARIA)
			return (System.Collections.Generic.ISet<T>) __iguaria;
		else if (key == ORMConstants.KEY_ESTABELECIMENTO_HORARIOESTABELECIMENTO)
			return (System.Collections.Generic.ISet<T>) __horarioEstabelecimento;
		else if (key == ORMConstants.KEY_ESTABELECIMENTO_CLIENTE_SELECIONA_ESTABELECIMENTO)
			return (System.Collections.Generic.ISet<T>) __cliente_seleciona_Estabelecimento;
		else if (key == ORMConstants.KEY_ESTABELECIMENTO_CLIENTE_AVALIA_ESTABELECIMENTO)
			return (System.Collections.Generic.ISet<T>) __cliente_avalia_Estabelecimento;
		return null;
	}
	
	private void This_SetOwner(object owner, int key) {
		if (key == ORMConstants.KEY_ESTABELECIMENTO_UTILIZADOR) {
			this.__utilizador = (Utilizador) owner;
		}
		
		else if (key == ORMConstants.KEY_ESTABELECIMENTO_CATEGORIA1) {
			this.__categoria1 = (Categoria) owner;
		}
	}
	
	private class EstabelecimentoORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly Estabelecimento __Estabelecimento;
		
		internal EstabelecimentoORMAdapter(Estabelecimento value) {
			__Estabelecimento = value;
		}
		
		public override System.Collections.Generic.ISet<T> GetSet<T>(int key) {
			return __Estabelecimento.This_GetSet<T>(key);
		}
		
		public override void SetOwner(object owner, int key) {
			__Estabelecimento.This_SetOwner(owner, key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private int __id_estabelecimento;
	
	private Categoria __categoria1;
	
	private Utilizador __utilizador;
	
	private string __nome_estabelecimento;
	
	private string __desc_ambiente;
	
	private Decimal __rating_medio_estabelecimento = new Decimal(0);
	
	private int __telefone;
	
	private int __visual_estabelecimento = 0;
	
	private Decimal __longitude = new Decimal(0);
	
	private Decimal __latitude = new Decimal(0);
	
	private string __rua;
	
	private int __numero;
	
	private string __localidade;
	
	private string __cod_postal;
	
	private System.Collections.Generic.ISet<Iguaria> __iguaria = new System.Collections.Generic.HashSet<Iguaria>();
	
	private System.Collections.Generic.ISet<HorarioEstabelecimento> __horarioEstabelecimento = new System.Collections.Generic.HashSet<HorarioEstabelecimento>();
	
	private System.Collections.Generic.ISet<Cliente_seleciona_Estabelecimento> __cliente_seleciona_Estabelecimento = new System.Collections.Generic.HashSet<Cliente_seleciona_Estabelecimento>();
	
	private System.Collections.Generic.ISet<Cliente_avalia_Estabelecimento> __cliente_avalia_Estabelecimento = new System.Collections.Generic.HashSet<Cliente_avalia_Estabelecimento>();
	
	public int Id_estabelecimento {
		set {
			this.__id_estabelecimento = value;			
		}
		get {
			return __id_estabelecimento;			
		}
	}
	
	public int ORMID {
		get {
			return Id_estabelecimento;			
		}
	}
	
	public string Nome_estabelecimento {
		set {
			this.__nome_estabelecimento = value;			
		}
		get {
			return __nome_estabelecimento;			
		}
	}
	
	public string Desc_ambiente {
		set {
			this.__desc_ambiente = value;			
		}
		get {
			return __desc_ambiente;			
		}
	}
	
	public Decimal Rating_medio_estabelecimento {
		set {
			this.__rating_medio_estabelecimento = value;			
		}
		get {
			return __rating_medio_estabelecimento;			
		}
	}
	
	public int Telefone {
		set {
			this.__telefone = value;			
		}
		get {
			return __telefone;			
		}
	}
	
	public int Visual_estabelecimento {
		set {
			this.__visual_estabelecimento = value;			
		}
		get {
			return __visual_estabelecimento;			
		}
	}
	
	public Decimal Longitude {
		set {
			this.__longitude = value;			
		}
		get {
			return __longitude;			
		}
	}
	
	public Decimal Latitude {
		set {
			this.__latitude = value;			
		}
		get {
			return __latitude;			
		}
	}
	
	public string Rua {
		set {
			this.__rua = value;			
		}
		get {
			return __rua;			
		}
	}
	
	public int Numero {
		set {
			this.__numero = value;			
		}
		get {
			return __numero;			
		}
	}
	
	public string Localidade {
		set {
			this.__localidade = value;			
		}
		get {
			return __localidade;			
		}
	}
	
	public string Cod_postal {
		set {
			this.__cod_postal = value;			
		}
		get {
			return __cod_postal;			
		}
	}
	
	public Utilizador Utilizador {
		set {
			if (this.__utilizador != value) {
				Utilizador l__utilizador = this.__utilizador;
				this.__utilizador = value;
				if (value != null) {
					__utilizador.Estabelecimento = this;
				}
				if (l__utilizador != null && l__utilizador.Estabelecimento == this) {
					l__utilizador.Estabelecimento = null;
				}
			}
		}
		get {
			return __utilizador;			
		}
	}
	
	public Categoria Categoria1 {
		set {
			if(__categoria1!= null) {
				__categoria1.estabelecimento1.Remove(this);
			}
			
			if(value != null) {
				value.estabelecimento1.Add(this);
			}
		}
		get {
			return __categoria1;			
		}
	}
	
	private Categoria ORM_Categoria1 {
		set {
			this.__categoria1 = value;			
		}
		
		get {
			return __categoria1;			
		}
	}
	
	private System.Collections.Generic.ISet<Iguaria> ORM_Iguaria {
		get  {
			return __iguaria;			
		}
		
		set {
			__iguaria = value;			
		}
	}
	
	public readonly IguariaSetCollection<Estabelecimento> iguaria;
	
	private System.Collections.Generic.ISet<HorarioEstabelecimento> ORM_HorarioEstabelecimento {
		get  {
			return __horarioEstabelecimento;			
		}
		
		set {
			__horarioEstabelecimento = value;			
		}
	}
	
	public readonly HorarioEstabelecimentoSetCollection<Estabelecimento> horarioEstabelecimento;
	
	private System.Collections.Generic.ISet<Cliente_seleciona_Estabelecimento> ORM_Cliente_seleciona_Estabelecimento {
		get  {
			return __cliente_seleciona_Estabelecimento;			
		}
		
		set {
			__cliente_seleciona_Estabelecimento = value;			
		}
	}
	
	public readonly Cliente_seleciona_EstabelecimentoSetCollection<Estabelecimento> cliente_seleciona_Estabelecimento;
	
	private System.Collections.Generic.ISet<Cliente_avalia_Estabelecimento> ORM_Cliente_avalia_Estabelecimento {
		get  {
			return __cliente_avalia_Estabelecimento;			
		}
		
		set {
			__cliente_avalia_Estabelecimento = value;			
		}
	}
	
	public readonly Cliente_avalia_EstabelecimentoSetCollection<Estabelecimento> cliente_avalia_Estabelecimento;
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Id_estabelecimento);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("Estabelecimento[ ");
			sb.AppendFormat("Id_estabelecimento={0} ", Id_estabelecimento);
			if (Categoria1 != null)
				sb.AppendFormat("Categoria1.Persist_ID={0} ", Categoria1.ToString(true) + "");
			else
				sb.Append("Categoria1=null ");
			if (Utilizador != null)
				sb.AppendFormat("Utilizador.Persist_ID={0} ", Utilizador.ToString(true) + "");
			else
				sb.Append("Utilizador=null ");
			sb.AppendFormat("Nome_estabelecimento={0} ", Nome_estabelecimento);
			sb.AppendFormat("Desc_ambiente={0} ", Desc_ambiente);
			sb.AppendFormat("Rating_medio_estabelecimento={0} ", Rating_medio_estabelecimento);
			sb.AppendFormat("Telefone={0} ", Telefone);
			sb.AppendFormat("Visual_estabelecimento={0} ", Visual_estabelecimento);
			sb.AppendFormat("Longitude={0} ", Longitude);
			sb.AppendFormat("Latitude={0} ", Latitude);
			sb.AppendFormat("Rua={0} ", Rua);
			sb.AppendFormat("Numero={0} ", Numero);
			sb.AppendFormat("Localidade={0} ", Localidade);
			sb.AppendFormat("Cod_postal={0} ", Cod_postal);
			sb.AppendFormat("iguaria.size={0} ", iguaria.Size());
			sb.AppendFormat("horarioEstabelecimento.size={0} ", horarioEstabelecimento.Size());
			sb.AppendFormat("cliente_seleciona_Estabelecimento.size={0} ", cliente_seleciona_Estabelecimento.Size());
			sb.AppendFormat("cliente_avalia_Estabelecimento.size={0} ", cliente_avalia_Estabelecimento.Size());
			sb.Append("]");
			return sb.ToString();
		}
	}
	
	public const String PROP_ID_ESTABELECIMENTO = "Id_estabelecimento";
	public const String PROP_CATEGORIA1 = "__categoria1";
	public const String PROP_UTILIZADOR = "__utilizador";
	public const String PROP_NOME_ESTABELECIMENTO = "Nome_estabelecimento";
	public const String PROP_DESC_AMBIENTE = "Desc_ambiente";
	public const String PROP_RATING_MEDIO_ESTABELECIMENTO = "Rating_medio_estabelecimento";
	public const String PROP_TELEFONE = "Telefone";
	public const String PROP_VISUAL_ESTABELECIMENTO = "Visual_estabelecimento";
	public const String PROP_LONGITUDE = "Longitude";
	public const String PROP_LATITUDE = "Latitude";
	public const String PROP_RUA = "Rua";
	public const String PROP_NUMERO = "Numero";
	public const String PROP_LOCALIDADE = "Localidade";
	public const String PROP_COD_POSTAL = "Cod_postal";
	public const String PROP_IGUARIA = "ORM_Iguaria";
	public const String PROP_HORARIO_ESTABELECIMENTO = "ORM_HorarioEstabelecimento";
	public const String PROP_CLIENTE_SELECIONA__ESTABELECIMENTO = "ORM_Cliente_seleciona_Estabelecimento";
	public const String PROP_CLIENTE_AVALIA__ESTABELECIMENTO = "ORM_Cliente_avalia_Estabelecimento";
}
