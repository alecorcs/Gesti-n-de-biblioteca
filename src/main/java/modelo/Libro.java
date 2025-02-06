package modelo;

public class Libro {
	//Atributos
	private int idLibro;
	private String titulo;
	private String  autor;
	private int anoPublicacion;
	private boolean prestado;
	
	//Contructor
	public Libro() {
		
	}
	
	public Libro(String titulo, String autor, int anoPublicacion) {
		super();		
		this.titulo = titulo;
		this.autor = autor;
		this.anoPublicacion = anoPublicacion;		
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnoPublicacion() {
		return anoPublicacion;
	}

	public void setAnoPublicacion(int anoPublicacion) {
		this.anoPublicacion = anoPublicacion;
	}

	
	public boolean isPrestado() {
		return prestado;
		
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	@Override
	public String toString() {
		return "Libro " + idLibro + ": titulo= " + titulo + ", autor= " + autor + ", año de publicación= "
				+ anoPublicacion + ", prestado= " + (prestado ? "Está prestado" : "Está disponible");
	}
	
	
}
