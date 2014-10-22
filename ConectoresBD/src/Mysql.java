import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.CallableStatement;

public class Mysql {
	private static Scanner sc = new Scanner(System.in);
	private static String consulta = null;
	java.sql.Connection conexión;

	public static void main(String[] args) {
		Mysql ms = new Mysql();
		int select;
		String con1 = new String(
				"SELECT * FROM empleados WHERE localidad IN ('CATARROJA') AND dept_no = 20 ;");
		String con2 = new String(
				"SELECT * from empleados where salario < 850 AND localidad not LIKE 'SILLA';");
		String con3 = new String(
				"SELECT AVG(salario), localidad FROM empleados GROUP BY 2;");
		String con4 = new String(
				"SELECT empleados.emp_no, empleados.nombre, departamentos.dnombre, departamentos.loc FROM empleados, departamentos "
						+ "WHERE departamentos.loc IN ('MADRID','BARCELONA') "
						+ "AND empleados.dept_no = departamentos.dept_no;");
		String con5 = new String(
				"SELECT empleados.emp_no, empleados.nombre, departamentos.dnombre, departamentos.loc FROM empleados, departamentos WHERE departamentos.loc IN ('SEVILLA')"
						+ "AND empleados.localidad NOT LIKE 'CATARROJA'"
						+ "AND empleados.dept_no = departamentos.dept_no;");
		String con6 = new String(
				"SELECT dept_no, count(nombre), AVG(salario) FROM empleados GROUP BY 1;");
		String sqlIn = new String("INSERT INTO empleados VALUES (?,?,?,?,?,?)");
		String sqlDel = new String("DELETE FROM empleados WHERE emp_no = ?");
		String sqlModA = new String(
				"UPDATE empleados SET salario = (salario+?) WHERE localidad = ? ");
		String sqlModB = new String(
				"UPDATE empleados SET salario = (salario-?) WHERE dept_no = (SELECT dept_no FROM departamentos WHERE loc = ?) ");
		do {
			System.out.println("Selecciona una consulta\n");
			System.out
					.println("1.- Ejercicio 1 : Listado empleados departamento 2.\n");
			System.out
					.println("2.- Ejercicio 2 : Listado de empleados que cobran menos de 850 y no son de SILLA.\n");
			System.out
					.println("3.- Ejercicio 3 : Media de salario por localidad.\n");
			System.out
					.println("4.- Ejercicio 4 : Empleados cuyo departamento está en Madrid o Barcelona.\n");
			System.out.println("5.- Ejercicio 5.\n");
			System.out.println("6.- Ejercicio 6.\n");
			System.out.println("7.- Ejercicio 7. Insertar empleados.\n");
			System.out.println("8.- Ejercicio 8. Eliminar empleado.\n");
			System.out
					.println("9.- Ejercicio 9. Sumar 15 al salario de empleados de ALBAL.\n");
			System.out
					.println("10.- Ejercicio 10. Restar 1 al salario de los que tengan el departamento en Sevilla.\n");
			System.out.println("11.- Ejercicio 11. PROCEDURE 1.\n");
			System.out.println("12.- Ejercicio 12. PROCEDURE 2.\n");
			System.out.println("13.- Ejercicio 13. PROCEDURE 3.\n");
			System.out.println("14.- Ejercicio 14. PROCEDURE 4.\n");
			System.out.println("15.- Ejercicio 15. PROCEDURE 5.\n");
			select = Integer.parseInt(sc.next());
		} while (select <= 0 || select > 15);

		switch (select) {
		case 1:
			sc.close();
			ms.consultaTipoA(con1);
		case 2:
			sc.close();
			ms.consultaTipoA(con2);
			break;
		case 3:
			sc.close();
			ms.consultaTipoB(con3);
			break;
		case 4:
			sc.close();
			ms.consultaTipoA(con4);
			break;
		case 5:
			sc.close();
			ms.consultaTipoA(con5);
			break;
		case 6:
			sc.close();
			ms.consultaTipoC(con6);
			break;
		case 7:
			consulta = sqlIn;
			ms.introduceEmpleado();
			break;
		case 8:
			consulta = sqlDel;
			ms.eliminaEmpleado();
			break;
		case 9:
			consulta = sqlModA;
			ms.masSalario();
			break;
		case 10:
			consulta = sqlModB;
			ms.menosSalario();
			break;
		case 11:
			ms.llamaProcedure1();
			break;
		case 12:
			ms.llamaProcedure2();
			break;
		case 13:
			ms.llamaProcedure3();
			break;
		case 14:
			ms.llamaProcedure4();
			break;
		case 15:
			ms.llamaProcedure5();
			break;
		}
	}

	public void consultaTipoA(String c) {
		try {
			loadDriver();
			java.sql.Statement sentencia = conexión.createStatement();
			ResultSet result = sentencia.executeQuery(c);
			while (result.next()) {
				System.out
						.println(result.getInt(1) + " " + result.getString(2)
								+ " " + result.getString(3) + " "
								+ result.getString(4));
			}
			result.close();
			sentencia.close();
			conexión.close();
		} catch (Exception cn) {
			cn.printStackTrace();
		}
	}

	public void consultaTipoB(String c) {
		try {
			loadDriver();
			java.sql.Statement sentencia = conexión.createStatement();
			ResultSet result = sentencia.executeQuery(c);
			while (result.next()) {
				System.out
						.println(result.getInt(1) + " " + result.getString(2));
			}
			result.close();
			sentencia.close();
			conexión.close();
		} catch (Exception cn) {
			cn.printStackTrace();
		}
	}

	public void consultaTipoC(String c) {
		try {

			loadDriver();
			java.sql.Statement sentencia = conexión.createStatement();
			ResultSet result = sentencia.executeQuery(c);
			while (result.next()) {
				System.out.println(result.getInt(1) + " " + result.getInt(2)
						+ " " + result.getInt(3));
			}
			result.close();
			sentencia.close();
			conexión.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @author Aleix Casanova
	 */
	public void introduceEmpleado() {
		try {
			loadDriver();
			PreparedStatement ins = conexión.prepareStatement(consulta);
			System.out.println("Introduce el Numero de empleado");
			int n = sc.nextInt();
			ins.setInt(1, n);
			System.out.println("Introduzca el nombre");
			String nom = sc.next();
			ins.setString(2, nom);
			System.out.println("Introduzca la fecha de alta");
			String fecha = sc.next();
			ins.setString(3, fecha);
			System.out.println("Introduzca Localidad");
			String loc = sc.next();
			ins.setString(4, loc);
			System.out.println("Introduce el Salario");
			int s = sc.nextInt();
			ins.setInt(5, s);
			System.out.println("Introduce el Numero de Departamento.");
			int d = sc.nextInt();
			ins.setInt(6, d);
			ins.executeUpdate();
			sc.close();
			mostrarEmpleados();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void eliminaEmpleado() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexión = DriverManager.getConnection(
					"jdbc:mysql://localhost/empresa", "root", "1234");
			loadDriver();
			PreparedStatement ins = conexión.prepareStatement(consulta);
			System.out.println("Introduce el numero de empleado a eliminar.");
			int num = Integer.parseInt(sc.next());
			ins.setInt(1, num);
			ins.executeUpdate();
			sc.close();
			mostrarEmpleados();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void masSalario() {
		try {
			loadDriver();
			PreparedStatement ins = conexión.prepareStatement(consulta);
			System.out.println("Introduce cuanto aumentar el salario.");
			int num = Integer.parseInt(sc.next());
			ins.setInt(1, num);
			System.out.println("Introduce la localidad del departamento");
			String loc = sc.next();
			ins.setString(2, loc);
			ins.executeUpdate();
			sc.close();
			mostrarEmpleados();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void menosSalario() {
		try {
			loadDriver();
			PreparedStatement ins = conexión.prepareStatement(consulta);
			System.out.println("Introduce cuanto disminuir el salario.");
			int num = Integer.parseInt(sc.next());
			ins.setInt(1, num);
			System.out.println("Introduce la localidad del empleado. ");
			mostrarDepartamentos();
			String loc = sc.next();
			ins.setString(2, loc);
			ins.executeUpdate();
			sc.close();
			mostrarEmpleados();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarEmpleados() {
		try {
			loadDriver();
			Statement sentencia = conexión.createStatement();
			ResultSet result = sentencia
					.executeQuery("SELECT * FROM empleados");
			while (result.next()) {
				System.out
						.println(result.getInt(1) + " " + result.getString(2)
								+ " " + result.getString(3) + " "
								+ result.getString(4) + " "
								+ result.getString(5) + " "
								+ result.getString(6));
			}
			result.close();
			sentencia.close();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void mostrarDepartamentos() {
		try {
			loadDriver();
			Statement sentencia = conexión.createStatement();
			ResultSet result = sentencia
					.executeQuery("SELECT * FROM departamentos");
			while (result.next()) {
				System.out.println(result.getInt(1) + " " + result.getString(2)
						+ " " + result.getString(3));
			}
			result.close();
			sentencia.close();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llamaProcedure1() {
		String call = "{ call procedure1 (?)}";

		try {
			loadDriver();
			CallableStatement call1 = conexión.prepareCall(call);
			System.out
					.println("Introduce el nombre del departamento para mostrar sus empleados.");
			call1.setString(1, sc.next());
			ResultSet result = call1.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) + "  "
						+ result.getString(2));
			}
			result.close();
			call1.close();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llamaProcedure2() {
		String call = "{ call procedure2 (?)}";

		try {
			loadDriver();
			CallableStatement call1 = conexión.prepareCall(call);
			System.out
					.println("Introduce el año para ver los empleados dados de alta ese año.");
			call1.setInt(1, sc.nextInt());
			ResultSet result = call1.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) + "  "
						+ result.getString(2));
			}
			result.close();
			call1.close();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llamaProcedure3() {
		String call = "{ call procedure3 (?,?)}";
		try {
			loadDriver();
			CallableStatement call1 = conexión.prepareCall(call);
			System.out.println("Introduce Localidad del empleado.");
			call1.setString(1, sc.next());
			System.out.println("Introduce nombre del departamento.");
			call1.setString(2, sc.next());
			ResultSet result = call1.executeQuery();
			System.out.println("Resultados....\n");
			while (result.next()) {
				System.out.println(result.getString(1) + "  "
						+ result.getString(2));
			}
			result.close();
			call1.close();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llamaProcedure4() {
		String call = "{ call procedure4 (?,?)}";
		try {
			loadDriver();
			CallableStatement call1 = conexión.prepareCall(call);
			System.out.println("Introduce Localidad del empleado.");
			call1.setString(1, sc.next());
			System.out.println("Introduce salario a partir del cual se mostrara.");
			call1.setInt(2, sc.nextInt());
			ResultSet result = call1.executeQuery();
			System.out.println("Resultados....\n");
			while (result.next()) {
				System.out
				.println(result.getInt(1) + " " + result.getString(2)
						+ " " + result.getString(3) + " "
						+ result.getString(4) + " "
						+ result.getString(5) + " "
						+ result.getString(6));
			}
			result.close();
			call1.close();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void llamaProcedure5() {
		String call = "{ call procedure5 (?,?,?) }";
		mostrarEmpleados();
		try {
			loadDriver();
			CallableStatement call1 = conexión.prepareCall(call);
			System.out.println("Introduce el porcentaje de la subida.");
			call1.setInt(1, sc.nextInt());
			System.out.println("Introduce Localidad del empleado.");
			call1.setString(2, sc.next());
			System.out.println("Introduce nombre departamento.");
			call1.setString(3, sc.next());
			ResultSet result = call1.executeQuery();
			result.close();
			call1.close();
			conexión.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			mostrarEmpleados();
		}
	}

	public void loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexión = DriverManager.getConnection(
					"jdbc:mysql://localhost/empresa", "root", "1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
