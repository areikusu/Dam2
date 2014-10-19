import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Derby {
	public static void main(String[] args) {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection conexi�n = DriverManager.getConnection 
					("jdbc:derby:Derby/ejemplo");
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
