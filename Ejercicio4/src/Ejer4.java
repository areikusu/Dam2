import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Aleix
 *
 */
public class Ejer4 {

	private Connection conexion;
	Scanner sc = new Scanner(System.in);
	String query1;
	String query2;
	String query3;

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
		Ejer4 e = new Ejer4();
		int op;
		do {
			System.out.println("¿Que base de datos quieres llenar?\n");
			System.out.println("1.- MySQL");
			System.out.println("2.- SQLite");
			op = Integer.parseInt(e.sc.next());
		} while (op > 2 || op < 0);

		switch (op) {
		case 1:
			e.loadDriverMySQL();
			e.cargadatos1();
			e.cargadatos2();
			e.cargadatos3();
			try {
				e.conexion.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.menu2();
			break;
		case 2:
			e.loadDriverSQLite();
			e.cargadatos1();
			e.cargadatos2();
			e.cargadatos3();
			try {
				e.conexion.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.menu2();
			break;
		}

	}

	public void cargadatos1() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"./productos.csv"));
			String line;
			Statement stmt;
			stmt = conexion.createStatement();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] value = line.split(",");
				for (String v : value) {
					System.out.println(v);
				}
				stmt.executeUpdate("INSERT INTO productos VALUES(" + value[0]
						+ ",'" + value[1] + "'," + value[2] + "," + value[3]
						+ "," + value[4] + ");");

			}
			br.close();
			System.out.println("Inserción correcta.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargadatos2() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"./clientes.csv"));
			String line;
			Statement stmt;
			stmt = conexion.createStatement();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] value = line.split(",");
				for (String v : value) {
					System.out.println(v);
				}
				stmt.executeUpdate("INSERT INTO clientes VALUES(" + value[0]
						+ "," + value[1] + "," + value[2] + "," + value[3]
						+ "," + value[4] + "," + value[5] + ");");
			}
			br.close();
			System.out.println("Inserción correcta.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargadatos3() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"./ventas.csv"));
			String line;
			Statement stmt;
			stmt = conexion.createStatement();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] value = line.split(",");
				for (String v : value) {
					System.out.println(v);
				}
				stmt.executeUpdate("INSERT INTO ventas VALUES(" + value[0]
						+ "," + value[1] + "," + value[2] + "," + value[3]
						+ "," + value[4] + ");");
			}
			br.close();
			System.out.println("Inserción correcta.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void menu2() {
		int op;
		do {
			System.out.println("¿Que base de datos quieres visualizar?\n");
			System.out.println("1.- MySQL");
			System.out.println("2.- SQLite");
			System.out.println("3.- Salir");
			op = Integer.parseInt(sc.next());
		} while (op > 3 || op < 0);
		switch (op) {
		case 1:
			loadDriverMySQL();
			menu3();
			break;
		case 2:
			loadDriverSQLite();
			menu3();
			break;
		case 3:
			try {
				conexion.close();
				sc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public void menu3() {
		int op;
		do {
			System.out.println("¿Que tabla quieres visualizar?\n");
			System.out.println("1.- Productos");
			System.out.println("2.- Clientes");
			System.out.println("3.- Ventas");
			System.out.println("4.- Volver");
			op = Integer.parseInt(sc.next());
		} while (op > 4 || op < 0);
		switch (op) {
		case 1:
			mostrarProductos();
			try {
				conexion.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			menu2();
			break;
		case 2:
			mostrarClientes();
			try {
				conexion.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			menu2();
			break;
		case 3:
			mostrarVentas();
			try {
				conexion.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			menu2();
			break;
		case 4:
			menu2();
			break;
		}

	}

	public void mostrarProductos() {
		try {
			Statement sentencia1 = conexion.createStatement();
			ResultSet res2 = sentencia1
					.executeQuery("SELECT count(id) from productos;");
			if(res2.next()){
				System.out.println("Hay " + res2.getInt(1)
						+ " lineas en esta tabla.\n");
			}
			
			Statement sentencia = conexion.createStatement();
			ResultSet result = sentencia
					.executeQuery("SELECT * FROM productos");
			while (result.next()) {
				System.out.println(result.getInt(1) + "||"
						+ result.getString(2) + " " + result.getInt(3) + "||"
						+ result.getInt(4) + "||" + result.getFloat(5));
			}
			result.close();
			res2.close();
			sentencia.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarVentas() {
		try {
			Statement sentencia1 = conexion.createStatement();
			ResultSet res2 = sentencia1
					.executeQuery("SELECT count(idV) from ventas;");
			if(res2.next()){
				System.out.println("Hay " + res2.getInt(1)
						+ " lineas en esta tabla.\n");
			}
			Statement sentencia = conexion.createStatement();
			ResultSet result = sentencia
					.executeQuery("SELECT * FROM ventas");
			while (result.next()) {
				System.out.println(result.getInt(1) + "||"
						+ result.getString(2) + " " + result.getString(3)
						+ "||" + result.getInt(4) + "||" + result.getInt(5));
			}
			result.close();
			res2.close();
			sentencia.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarClientes() {
		try {
			Statement sentencia1 = conexion.createStatement();
			ResultSet res2 = sentencia1
					.executeQuery("SELECT count(idC) from clientes;");
			if(res2.next()){
				System.out.println("Hay " + res2.getInt(1)
						+ " lineas en esta tabla.\n");
			}
			Statement sentencia = conexion.createStatement();
			ResultSet result = sentencia
					.executeQuery("SELECT * FROM clientes");
			while (result.next()) {
				System.out.println(result.getString(1) + "||"
						+ result.getString(2) + " " + result.getString(3)
						+ "||" + result.getString(4) + "||" + result.getInt(5)
						+ "||" + result.getInt(6));
			}
			result.close();
			res2.close();
			sentencia.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadDriverMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/unidad2", "root", "1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadDriverSQLite() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:Unidad2.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
