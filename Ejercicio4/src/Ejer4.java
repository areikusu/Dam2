import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 */

/**
 * @author Aleix
 *
 */
public class Ejer4 {

	@SuppressWarnings("unused")
	private Connection conexion;
	
	/**
	 * 
	 */
	public Ejer4() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void loadDriverMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/empresa", "root", "1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void loadDriverSQLite() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/empresa", "root", "1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
