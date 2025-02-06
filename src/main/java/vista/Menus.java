package vista;

public class Menus {
	/**** Mostrar las distintas opciones validas del Menu de Opciones ****/
	public static void mostrarMenu(){
		System.out.println("--------------------------------");
		System.out.println("           BIBLIOTECA           ");
		System.out.println("--------------------------------");
		System.out.println("  1 Insertar Libro");
	    System.out.println("  2 Insertar Lector");
	    System.out.println("  3 Listado de Libros");
	    System.out.println("  4 Listado de Lecotores");
	    System.out.println("  5 Actualizar Libro por ID");
	    System.out.println("  6 Actualizar Lector por ID");
	    System.out.println("  7 Nuevo Préstamo");
	    System.out.println("  8 Devolver Préstamo");
	    System.out.println("  9 Eliminar Libro");
	    System.out.println("  10 Eliminar Lector");
	    System.out.println("  11 Consultar Libros Prestados a un Lector");
	    System.out.println("  12 Consultar Libros Disponibles");
	    System.out.println("  13 Historial Préstamos Lector");
	    System.out.println("  0 Salir");

	}
	
	public static void modificarLibro() {
		System.out.println("--------------------------------");
		System.out.println("  1 Modificar Titulo");
	    System.out.println("  2 Modificar Autor");
	    System.out.println("  3 Modificar Año de publicación");
	    System.out.println("  4 Modificar estado de préstamo");
	    System.out.println("--------------------------------");
	}
	
	public static void modificarLector() {
		System.out.println("--------------------------------");
		System.out.println("  1 Modificar Nombre");
	    System.out.println("  2 Modificar Apellido");
	    System.out.println("  3 Modificar E-mail");
	    System.out.println("--------------------------------");
	}
}
