using System;
using Orm;

public class CreateBasedeDadosMMDatabaseSchema {
	[STAThread]
	public static void Main(string[] args) {
		ORMDatabaseInitiator.CreateSchema(BasedeDadosMMPersistentManager.Instance());
	}
	
}
