package primero;

// Generated 04-nov-2014 18:37:09 by Hibernate Tools 3.4.0.CR1

/**
 * Clientes generated by hbm2java
 */
public class Clientes implements java.io.Serializable {

	private String idC;
	private String nombre;
	private String direccion;
	private String poblacion;
	private Integer tlf;
	private Integer dni;

	public Clientes() {
	}

	public Clientes(String idC) {
		this.idC = idC;
	}

	public Clientes(String idC, String nombre, String direccion,
			String poblacion, Integer tlf, Integer dni) {
		this.idC = idC;
		this.nombre = nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.tlf = tlf;
		this.dni = dni;
	}

	public String getIdC() {
		return this.idC;
	}

	public void setIdC(String idC) {
		this.idC = idC;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public Integer getTlf() {
		return this.tlf;
	}

	public void setTlf(Integer tlf) {
		this.tlf = tlf;
	}

	public Integer getDni() {
		return this.dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

}