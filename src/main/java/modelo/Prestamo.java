package modelo;


public class Prestamo {
	//Atributo
	private int idPrestamo;
	private String fechaPrestamo;
	private String fechaDevolucion;
	private Libro libro;
	private Lector lector;
	
	
	//Constructor
	public Prestamo() {
		
	}
	public Prestamo(String fechaPrestamo, String fechaDevolucion, Libro libro, Lector lector) {
		super();
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.libro = libro;
		this.lector = lector;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public Lector getLector() {
		return lector;
	}
	public void setLector(Lector lector) {
		this.lector = lector;
	}
	
	@Override
	public String toString() {
		return "Prestamo " + idPrestamo + ": fecha de prestamo= " + fechaPrestamo + ", fecha de devolucion= "
				+ fechaDevolucion + ", libro= id " + libro.getIdLibro()+ " título " + libro.getTitulo() + ", lector= id " 
				+ lector.getIdLector() + " nombre " +	lector.getNombre();
	}
	
	
}
