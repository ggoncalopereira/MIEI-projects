package OracleBD;

import java.sql.*;

public class OracleBD {
	
	public static Connection databaseLoader{
		Connection con = null;

		try{
			String driver = "oracle.jdbc.driver.OracleDriver"; //driver default para OracleDB
			String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //assumindo que o 'hostname' é localhost, o 'port' é 1521 e o 'service name' é orcl
			String username = "sys as sysdba"; //assumindo que o id é este
			String password = "oracle"; //assumindo que a pw é esta

			Class.forName(driver);

			con = DriverManager.getConnection(url, username, password);
		}

		catch(Exception e){
			System.out.println(e); //exceção na execução do forName()
		}

		return con;
	}

	public static void main(String[] args){
		Connection con = databaseLoader();

		Statement st = con.createStatement();

		String sqlQuery = "SELECT idade FROM aebd_jogador"; //vale a pena validar queries?

		ResultSet rs = st.executeQuery(sqlQuery);

		while(rs.next()){
			int idade = rs.getInt("idade");
			System.out.println(idade);
		}
		rs.close();
		st.close();
		con.close()
	}
}