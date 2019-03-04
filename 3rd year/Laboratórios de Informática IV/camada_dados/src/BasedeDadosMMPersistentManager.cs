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
using Orm.Cfg;
using System.Runtime.CompilerServices;
using NHibernate;

/// <summary>
/// ORM-Persistable Class
/// </summary>
public class BasedeDadosMMPersistentManager : PersistentManager {
	private static readonly string PROJECT_NAME ="BasedeDadosMM";
	private static PersistentManager _instance = null;
	private static SessionType _sessionType = SessionType.THREAD_BASE;
	private static DotNetConnectionSetting _connectionSetting = null;
	
	private BasedeDadosMMPersistentManager() : base(_connectionSetting, _sessionType) {
		base.FlushMode = FlushMode.Commit;
	}
	
	public override string GetProjectName() {
		return PROJECT_NAME;
	}
	
	[MethodImpl(MethodImplOptions.Synchronized)]
	public static PersistentManager Instance() {
		if(_instance == null) {
			_instance = new BasedeDadosMMPersistentManager();
		}
		
		return _instance;
	}
	
	public void DisposePersistentManger() {
		_instance = null;
		base.DisposePersistentManager();
	}
	
	public static void SetSessionType(SessionType sessionType) {
		if (_instance != null) {
			throw new PersistentException("Cannot set session type after create PersistentManager instance");
		}
		else {
			_sessionType = sessionType;
		}
		
	}
	
	public static void SetConnectionSetting(DotNetConnectionSetting connectionSetting) {
		if (_instance != null) {
			throw new PersistentException("Cannot set session type after create PersistentManager instance");
		}
		else {
			_connectionSetting = connectionSetting;
		}
		
	}
	
	public override global::System.Reflection.Assembly GetAssembly() {
		return global::System.Reflection.Assembly.GetExecutingAssembly();
	}
	
}

