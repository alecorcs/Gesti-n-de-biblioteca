package Controlador;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import modelo.BaseDatosHibernate;
import modelo.Libro;
import vista.Menus;

public class ControlLibros {
	
	//Se inserta un libro nuevo
	public static void crearEntradaLibro(Session session, Scanner sc) {
				
		System.out.println("Inserta el título del libro");
		String titulo = sc.nextLine();
		System.out.println("Inserta el autor del libro");
		String autor = sc.nextLine();
		System.out.println("Inserta el año de publicación");
		int anoPubli = sc.nextInt();
		sc.nextLine();
		//Pasamos los nuevos valores del libro por parámetro para crear un nuevo objeto libro
		Libro libro = new Libro(titulo, autor, anoPubli);
		try {
			//Se guarda en la BD.
			BaseDatosHibernate.crearLibro(session, libro);
			System.out.println("Libro insertado correctamente");
		//Si salta excepcion lanza mensaje
		}catch(HibernateException e) {
			System.out.println("No se ha podido registrar el libro");
		}
	}

	//Devuelve el listado de los libros registrados.
	public static void crearListadoLibros(Session session) {
		//Crea una lista donde guarda la lista devuelta de la BD
		List<Libro> libros= BaseDatosHibernate.listadoLibros(session);
		
		//Si no hay libros se notifica.
		if(libros.isEmpty()) {
			System.out.println("No hay registros");
		}else {
			//Si hay libros se imprime la lista
			for(Libro lib : libros) {
				System.out.println(lib.toString());
			}
		}		
	}
	
	//Se modifica un libro
	public static void crearEntradaModificarLibro(Session session, Scanner sc) {
		//Se indica el id del libro que se quiere modificar.
		System.out.println("Indica el ID del libro que quieres actualizar");
		int id = sc.nextInt();
		sc.nextLine();
		Libro lib = session.get(Libro.class, id);
		
		//Verifica que se encontró el libro
		if(lib != null) {
			//declaramos el mecanísmo de entrada del bucle switch case.
			int opcion = -1;
			String almohadilla = "#:";  
				
			//Llamamos al menu que mostrará graficamente las opciones.
				Menus.modificarLibro();
				System.out.print(almohadilla); 
				opcion = sc.nextInt();
				sc.nextLine();
				//Eleccion segun el cambio que se quiera hacer	 		
			switch(opcion) {
			case 1://Cambio íulo
			  	System.out.println("¿Cuál es el nuevo título?");
			   	String newTit = sc.nextLine();
			   	lib.setTitulo(newTit);		 
				break;
					
			case 2://Cambio autor
			  	System.out.println("¿Cuál es el nuevo autor?");
			   	String newAut = sc.nextLine();
			   	lib.setAutor(newAut);		    	
				break;
			case 3://Cambio año de publicación
				System.out.println("¿Cuál es el nuevo año de publicación");
			    int newAno = sc.nextInt();
			    sc.nextLine();
			    lib.setAnoPublicacion(newAno);			    	
				break;
			case 4://Cambio estado del préstamo
				System.out.println("¿Cuál es el nuevo estado del préstamo");
			    boolean newPres = sc.nextBoolean();
			    lib.setPrestado(newPres);			    	
				break;			    
			default:
				System.out.println("Opción no válida. Por favor, elige una opción válida.");
			    break;	 
			}
			try {
				//Se guarda la modificación en el BD.
				BaseDatosHibernate.modificarLibroID(session, lib);
				
				System.out.println("Libro modificado exitosamente");
				
			}catch(HibernateException e) {
				System.out.println("Error al modificar libro");
			}	
		} else {
			System.out.println("No se encontró el Libro con el ID especificado");
		}
	}
	
	//Opción para eliminar un libro
	public static void crearEntradaEliminarLibro(Session session, Scanner sc) {
		//Se llama al método anterior para mostrar la lista de libros
		crearListadoLibros(session);
		
		System.out.println("Indica el ID del libro que quieres eliminar");
		int id = sc.nextInt();
		sc.nextLine();
		Libro libro = session.get(Libro.class, id);
		
		try {
			//Se elimina el libro de la BD.
			BaseDatosHibernate.eliminarLibro(session, libro);
			
			System.out.println("Libro borrado correctamente");
			
		}catch(HibernateException e) {
			System.out.println("No se encontró el Libro con el ID especificado");
		}		
	}
	
	//Muestra la lista de libros disponibles para el préstamo
	public static void crearlibrosNoPrestados(Session session) {
		//Recuperramos los libros no prestados de la BD.
		List<Libro> libros = BaseDatosHibernate.librosNoPrestados(session);
		
		if (libros.isEmpty()) {//Si la lista está vacía se lanza mensaje
	        System.out.println("Todos los libros están prestados en este momento.");
	    } else {//Si hayy libros en la lista muestra los libros disponibles.
	        System.out.println("Libros disponibles para el préstamo:");
	        for (Libro lib : libros) {
	            System.out.println(lib.toString());
	        }
	    }
	}
					    	
}
