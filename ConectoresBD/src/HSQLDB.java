import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLDB {
	public static void main(String[] args) {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection conexi�n = DriverManager.getConnection 
					("jdbc:hsqldb:file:C:\\hsqldb-2.2.6\\hsqldb\\data\\ejemplo\\ejemplo","sa","");
			Statement sentencia = conexi�n. createStatement();
			ResultSet result = sentencia.executeQuery("SELECT * FROM departamentos");
			while (result.next())
			{System.out.println(result.getInt(1) + " " + result.getString(2) + " "+result.getString(3));}
			result.close();
			sentencia.close();
			conexi�n.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}
