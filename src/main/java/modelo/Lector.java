package modelo;

public class Lector {
	//Atributos
	private int idLector;
	private String nombre;
	private String  apellido;
	private int edad;
	private String email;
	
	//Construcor
	public Lector() {
		
	}
	public Lector(String nombre, String apellido, int edad, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.email = email;
	}

	public int getIdLector() {
		return idLector;
	}

	public void setIdLector(int idLector) {
		this.idLector = idLector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	@Override
	public String toString() {
		return "Lector " + idLector + ": nombre= " + nombre + ", apellido= " + apellido + ", edad= "
				+ edad + ", e-mail= " + email;
	}
	
	
	
}

