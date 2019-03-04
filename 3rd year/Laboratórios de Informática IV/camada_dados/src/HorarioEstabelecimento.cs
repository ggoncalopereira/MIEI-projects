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
public class HorarioEstabelecimento {
	public HorarioEstabelecimento() {
		_OrmAdapter = new HorarioEstabelecimentoORMAdapter(this);
	}
	
	public static HorarioEstabelecimento LoadHorarioEstabelecimentoByORMID(int id_horario, int estabelecimento_id_estabelecimento) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadHorarioEstabelecimentoByORMID(session,id_horario, estabelecimento_id_estabelecimento);
	}
	
	public static HorarioEstabelecimento LoadHorarioEstabelecimentoByORMID(PersistentSession session,int id_horario, int estabelecimento_id_estabelecimento) {
		HorarioEstabelecimento horarioestabelecimento = new HorarioEstabelecimento();
		horarioestabelecimento.Id_horario = id_horario;
		horarioestabelecimento.Estabelecimento_id_estabelecimento = estabelecimento_id_estabelecimento;
		
		return (HorarioEstabelecimento) session.Load(typeof(HorarioEstabelecimento), horarioestabelecimento);
	}
	
	public static HorarioEstabelecimento[] ListHorarioEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return ListHorarioEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static HorarioEstabelecimento[] ListHorarioEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From HorarioEstabelecimento as HorarioEstabelecimento");
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
			HorarioEstabelecimento[] result = new HorarioEstabelecimento[list.Count];
			list.CopyTo(result, 0);
			return result;
		}
		catch (Exception e) {
			global::System.Console.Error.WriteLine(e.Message);
			global::System.Console.Error.WriteLine(e.StackTrace);
			throw new PersistentException(e);
		}
	}
	
	public static HorarioEstabelecimento LoadHorarioEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return LoadHorarioEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static HorarioEstabelecimento LoadHorarioEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		HorarioEstabelecimento[] horarioEstabelecimentos = ListHorarioEstabelecimentoByQuery(session, condition, orderBy);
		if (horarioEstabelecimentos != null && horarioEstabelecimentos.Length > 0)
			return horarioEstabelecimentos[0];
		else
			return null;
	}
	
	public static global::System.Collections.IEnumerable IterateHorarioEstabelecimentoByQuery(string condition, string orderBy) {
		PersistentSession session = BasedeDadosMMPersistentManager.Instance().GetSession();
		return IterateHorarioEstabelecimentoByQuery(session, condition, orderBy);
	}
	
	public static global::System.Collections.IEnumerable IterateHorarioEstabelecimentoByQuery(PersistentSession session, string condition, string orderBy) {
		global::System.Text.StringBuilder sb = new global::System.Text.StringBuilder("From HorarioEstabelecimento as HorarioEstabelecimento");
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
	
	public static HorarioEstabelecimento LoadHorarioEstabelecimentoByCriteria(HorarioEstabelecimentoCriteria horarioEstabelecimentoCriteria) {
		HorarioEstabelecimento[] horarioEstabelecimentos = ListHorarioEstabelecimentoByCriteria(horarioEstabelecimentoCriteria);
		if(horarioEstabelecimentos == null || horarioEstabelecimentos.Length == 0) {
			return null;
		}
		return horarioEstabelecimentos[0];
	}
	
	public static HorarioEstabelecimento[] ListHorarioEstabelecimentoByCriteria(HorarioEstabelecimentoCriteria horarioEstabelecimentoCriteria) {
		return horarioEstabelecimentoCriteria.ListHorarioEstabelecimento();
	}
	
	public override bool Equals(object obj) {
		if (obj == this)
			return true;
		if (!(obj is HorarioEstabelecimento))
			return false;
		HorarioEstabelecimento horarioestabelecimento = obj as HorarioEstabelecimento;
		if (Id_horario != horarioestabelecimento.Id_horario)
			return false;
		if (Estabelecimento_id_estabelecimento != horarioestabelecimento.Estabelecimento_id_estabelecimento)
			return false;
		return true;
	}
	
	public override int GetHashCode() {
		int hashcode = 0;
		hashcode = hashcode + (int) Id_horario;
		hashcode = hashcode + (int) Estabelecimento_id_estabelecimento;
		return hashcode;
	}
	
	public static HorarioEstabelecimento CreateHorarioEstabelecimento() {
		return new HorarioEstabelecimento();
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
			if(Estabelecimento != null) {
				Estabelecimento.horarioEstabelecimento.Remove(this);
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
			if(Estabelecimento != null) {
				Estabelecimento.horarioEstabelecimento.Remove(this);
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
	
	private void This_SetOwner(object owner, int key) {
		if (key == ORMConstants.KEY_HORARIOESTABELECIMENTO_ESTABELECIMENTO) {
			this.__estabelecimento = (Estabelecimento) owner;
		}
	}
	
	private class HorarioEstabelecimentoORMAdapter : Orm.Util.AbstractORMAdapter {
		private readonly HorarioEstabelecimento __HorarioEstabelecimento;
		
		internal HorarioEstabelecimentoORMAdapter(HorarioEstabelecimento value) {
			__HorarioEstabelecimento = value;
		}
		
		public override void SetOwner(object owner, int key) {
			__HorarioEstabelecimento.This_SetOwner(owner, key);
		}
		
	}
	
	internal Orm.Util.ORMAdapter _OrmAdapter;
	
	private int __id_horario;
	
	private Estabelecimento __estabelecimento;
	
	private int __estabelecimentoId;
	
	public int EstabelecimentoId {
		set {
			this.__estabelecimentoId = value;			
		}
		get {
			return __estabelecimentoId;			
		}
	}
	
	private byte __dia;
	
	private DateTime __hora_abertura;
	
	private DateTime __hora_fecho;
	
	public int Id_horario {
		set {
			this.__id_horario = value;			
		}
		get {
			return __id_horario;			
		}
	}
	
	public byte Dia {
		set {
			this.__dia = value;			
		}
		get {
			return __dia;			
		}
	}
	
	public DateTime Hora_abertura {
		set {
			this.__hora_abertura = value;			
		}
		get {
			return __hora_abertura;			
		}
	}
	
	public DateTime Hora_fecho {
		set {
			this.__hora_fecho = value;			
		}
		get {
			return __hora_fecho;			
		}
	}
	
	public Estabelecimento Estabelecimento {
		set {
			if(__estabelecimento!= null) {
				__estabelecimento.horarioEstabelecimento.Remove(this);
			}
			
			if(value != null) {
				value.horarioEstabelecimento.Add(this);
			}
			if (value != null) {
				__estabelecimento_id_estabelecimento = value.Id_estabelecimento;
			}
			else {
				__estabelecimento_id_estabelecimento = 0;
			}
		}
		get {
			return __estabelecimento;			
		}
	}
	
	private Estabelecimento ORM_Estabelecimento {
		set {
			this.__estabelecimento = value;			
		}
		
		get {
			return __estabelecimento;			
		}
	}
	
	public override string ToString() {
		return ToString(false);
	}
	
	public virtual string ToString(bool idOnly) {
		if (idOnly) {
			return Convert.ToString(Id_horario) + " "+ Convert.ToString(Estabelecimento_id_estabelecimento);
		}
		else {
			System.Text.StringBuilder sb = new System.Text.StringBuilder();
			sb.Append("HorarioEstabelecimento[ ");
			sb.AppendFormat("Id_horario={0} ", Id_horario);
			if (Estabelecimento != null)
				sb.AppendFormat("Estabelecimento.Persist_ID={0} ", Estabelecimento.ToString(true) + "");
			else
				sb.Append("Estabelecimento=null ");
			sb.AppendFormat("Dia={0} ", Dia);
			sb.AppendFormat("Hora_abertura={0} ", Hora_abertura);
			sb.AppendFormat("Hora_fecho={0} ", Hora_fecho);
			sb.AppendFormat("Estabelecimento_id_estabelecimento={0} ", Estabelecimento_id_estabelecimento);
			sb.Append("]");
			return sb.ToString();
		}
	}
	
	private bool _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public bool isSaved() {
		return _saved;
	}
	
	
	private int __estabelecimento_id_estabelecimento;
	
	public int Estabelecimento_id_estabelecimento {
		set {
			this.__estabelecimento_id_estabelecimento = value;			
		}
		get {
			return __estabelecimento_id_estabelecimento;			
		}
	}
	
	public const String PROP_ID_HORARIO = "Id_horario";
	public const String PROP_ESTABELECIMENTO = "__estabelecimento";
	public const String PROP_DIA = "Dia";
	public const String PROP_HORA_ABERTURA = "Hora_abertura";
	public const String PROP_HORA_FECHO = "Hora_fecho";
	public const String PROP_ESTABELECIMENTO_ID_ESTABELECIMENTO = "Estabelecimento_id_estabelecimento";
}
