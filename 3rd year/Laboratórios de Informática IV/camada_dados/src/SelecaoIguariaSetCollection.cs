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
/// <summary>
/// ORM-Persistable Class
/// </summary>
public class SelecaoIguariaSetCollection<O> : Orm.Util.ORMSet<O, SelecaoIguaria> {
	public SelecaoIguariaSetCollection(O owner, Orm.Util.ORMAdapter adapter, int ownerKey, int targetKey, int collType) : base(owner, adapter, ownerKey, targetKey, true, collType) {
	}
	
	public SelecaoIguariaSetCollection(O owner, Orm.Util.ORMAdapter adapter, int ownerKey, int collType) : base(owner, adapter, ownerKey, -1, false, collType) {
	}
	
	/// <summary>
	/// Return IEnumerator over the persistent objects
	/// </summary>
	new public System.Collections.IEnumerator GetEnumerator() {
		return base.GetEnumerator();
	}
	
	/// <summary>
	/// Add the specified persistent object to ORMSet.
	/// </summary>
	public void Add(SelecaoIguaria value) {
		base.Add(value, value._OrmAdapter);
	}
	
	/// <summary>
	/// Remove the specified persistent object
	/// </summary>
	public void Remove(SelecaoIguaria value) {
		base.Remove(value, value._OrmAdapter);
	}
	
	/// <summary>
	/// Return the boolean of the specified persistent object is existed or not.
	/// </summary>
	new public bool Contains(SelecaoIguaria value) {
		return base.Contains(value);
	}
	
	/// <summary>
	/// Return an array containing all of the persistent objects in ORMSet.
	/// </summary>
	public SelecaoIguaria[] ToArray() {
		SelecaoIguaria[] a = new SelecaoIguaria[Size()];
		base.ToArray(a);
		return a;
	}
	
}

