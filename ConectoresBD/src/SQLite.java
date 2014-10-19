import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLite {
	private static String consulta = null;
	private static Scanner sc = new Scanner(System.in);
	Connection conexión;
	public static void main(String[] args) {
		SQLite sq = new SQLite();
		int select;
		String con1 = new String("SELECT * from empleados WHERE dept_no = 20 AND localidad = 'CATARROJA' ");
		String con2 = new String("SELECT * from empleados where salario < 850 AND localidad not LIKE 'SILLA';");
		String con3 = new String("SELECT AVG(salario), localidad FROM empleados GROUP BY 2;");
		String con4 = new String("SELECT empleados.emp_no, empleados.nombre, departamentos.dnombre, departamentos.loc FROM empleados, departamentos "
				+ "WHERE departamentos.loc IN ('MADRID','BARCELONA') "
				+ "AND empleados.dept_no = departamentos.dept_no;");
		String con5 = new String("SELECT empleados.emp_no, empleados.nombre, departamentos.dnombre, departamentos.loc FROM empleados, departamentos WHERE departamentos.loc IN ('SEVILLA')"
				+ "AND empleados.localidad NOT LIKE 'CATARROJA'"
				+ "AND empleados.dept_no = departamentos.dept_no;");
		String con6 = new String("SELECT dept_no, count(nombre), AVG(salario) FROM empleados GROUP BY 1;");
		String sqlIn = new String("INSERT INTO empleados VALUES (?,?,?,?,?,?)");
		String sqlDel = new String("DELETE FROM empleados WHERE emp_no = ?");
		String sqlModA = new String("UPDATE empleados SET salario = (salario+?) WHERE localidad = ? ");
		String sqlModB = new String("UPDATE empleados SET salario = (salario-?) WHERE dept_no = (SELECT dept_no FROM departamentos WHERE loc = ?) ");
		do{
			System.out.println("Selecciona una consulta\n");
			System.out.println("1.- Ejercicio 1 : Listado empleados departamento 2.\n");
			System.out.println("2.- Ejercicio 2 : Listado de empleados que cobran menos de 850 y no son de SILLA.\n");
			System.out.println("3.- Ejercicio 3 : Media de salario por localidad.\n");
			System.out.println("4.- Ejercicio 4 : Empleados cuyo departamento está en Madrid o Barcelona.\n");
			System.out.println("5.- Ejercicio 5.\n");
			System.out.println("6.- Ejercicio 6.\n");
			System.out.println("7.- Ejercicio 7. Insertar empleados.\n");
			System.out.println("8.- Ejercicio 8. Eliminar empleado.\n");
			System.out.println("9.- Ejercicio 9. Sumar 15 al salario de empleados de ALBAL.\n");
			System.out.println("10.- Ejercicio 10. Restar 1 al salario de los que tengan el departamento en Sevilla.\n");
			select = Integer.parseInt(sc.next());
		}
		while (select <=0 || select>10);

		switch(select){
		case 1 : 
			consulta = con1;
			sc.close();
			sq.consultaTipoA();
		case 2 : 
			consulta = con2;
			sc.close();
			sq.consultaTipoA();
			break;
		case 3 : 
			consulta = con3;
			sc.close();
			sq.consultaTipoB();
			break;
		case 4 : 
			consulta = con4;
			sc.close();
			sq.consultaTipoA();
			break;
		case 5 : 
			consulta = con5;
			sc.close();
			sq.consultaTipoA();
			break;
		case 6 : 
			consulta = con6;
			sc.close();
			sq.consultaTipoC();
			break;
		case 7 : 
			consulta = sqlIn;
			sq.introduceEmpleado();
			break;
		case 8 : 
			consulta = sqlDel;
			sq.eliminaEmpleado();
			break;
		case 9 : 
			consulta = sqlModA;
			sq.masSalario();
			break;
		case 10 : 
			consulta = sqlModB;
			sq.menosSalario();
			break;
		}

	}
	public void consultaTipoA(){
		try {

			Class.forName("org.sqlite.JDBC");
			Connection conexión = DriverManager.getConnection 
					("jdbc:sqlite:ejemplo.db");
			Statement sentencia = conexión. createStatement();
			ResultSet result = sentencia.executeQuery(consulta);
			while (result.next())
			{System.out.println(result.getInt(1) + " " + result.getString(2) + " "+result.getString(3)+" "+result.getString(4));}
			result.close();
			sentencia.close();
			conexión.close();
			mostrarEmpleados();
		}
		catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consultaTipoB(){
		try {

			Class.forName("org.sqlite.JDBC");
			Connection conexión = DriverManager.getConnection 
					("jdbc:sqlite:ejemplo.db", "ejemplo", "ejemplo");
			Statement sentencia = conexión. createStatement();
			ResultSet result = sentencia.executeQuery(consulta);
			while (result.next())
			{System.out.println(result.getInt(1) + " " + result.getString(2));}
			result.close();
			sentencia.close();
			conexión.close();
			mostrarEmpleados();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void consultaTipoC(){
		try {
			loadDriver();
			Statement sentencia = conexión. createStatement();
			ResultSet result = sentencia.executeQuery(consulta);
			while (result.next())
			{System.out.println(result.getInt(1) + " " + result.getInt(2)+ " " + result.getInt(3));}
			result.close();
			sentencia.close();
			conexión.close();
			mostrarEmpleados();
		}
		catch (Exception cn) {
			cn.printStackTrace();
		}
	}
	/*
	 * @author Aleix Casanova
	 */
	public void introduceEmpleado(){
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
			String fecha =  sc.next();
			ins.setString(3,fecha);
			System.out.println("Introduzca Localidad");
			String loc =  sc.next();
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

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void eliminaEmpleado(){
		try{
			loadDriver();
			PreparedStatement ins = conexión.prepareStatement(consulta);
			System.out.println("Introduce el numero de empleado a eliminar.");
			int num = Integer.parseInt(sc.next());
			ins.setInt(1, num);
			ins.executeUpdate();
			sc.close();
			mostrarEmpleados();
			conexión.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void masSalario(){
		try{
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
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void menosSalario(){
		try{
			loadDriver();
			PreparedStatement ins = conexión.prepareStatement(consulta);
			System.out.println("Introduce cuanto disminuir el salario.");
			int num = Integer.parseInt(sc.next());
			ins.setInt(1, num);
			System.out.println("Introduce la localidad del departamento. ");
			mostrarDepartamentos();
			String loc = sc.next();
			ins.setString(2, loc);
			ins.executeUpdate();
			sc.close();
			mostrarEmpleados();
			conexión.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void mostrarEmpleados(){
		try{
			loadDriver();
			Statement sentencia = conexión. createStatement();
			ResultSet result = sentencia.executeQuery("SELECT * FROM empleados");
			while (result.next())
			{System.out.println(result.getInt(1) + " " + result.getString(2) + " "+result.getString(3)+ " "+result.getString(4)+ " "+result.getString(5)+ " "+result.getString(6));}
			result.close();
			sentencia.close();
			conexión.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void mostrarDepartamentos(){
		try{
			loadDriver();
			Statement sentencia = conexión. createStatement();
			ResultSet result = sentencia.executeQuery("SELECT * FROM departamentos");
			while (result.next())
			{System.out.println(result.getInt(1) + " " + result.getString(2) + " "+result.getString(3));}
			result.close();
			sentencia.close();
			conexión.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void loadDriver(){
		try{
			Class.forName("org.sqlite.JDBC");
			conexión = DriverManager.getConnection("jdbc:sqlite:ejemplo.db");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

