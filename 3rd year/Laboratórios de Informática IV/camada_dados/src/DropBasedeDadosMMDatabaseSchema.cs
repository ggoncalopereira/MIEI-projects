using System;
using Orm;

public class DropBasedeDadosMMDatabaseSchema {
	[STAThread]
	public static void Main(string[] args) {
		System.Console.WriteLine("Are you sure to drop table(s)? (Y/N)");
		if (System.Console.ReadLine().Trim().ToUpper().Equals("Y")) {
			ORMDatabaseInitiator.DropSchema(BasedeDadosMMPersistentManager.Instance());
		}
		
	}
	
}
