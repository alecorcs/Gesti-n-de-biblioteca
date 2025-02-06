package Controlador;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import modelo.BaseDatosHibernate;
import modelo.Lector;
import modelo.Prestamo;
import vista.Menus;

public class ControlLector {
	
	//Se introduce nuevo Lector en BD
	public static void crearEntradaLector(Session session, Scanner sc) {			
		
		System.out.println("Inserta el nombre del lector");
		String nombre = sc.nextLine();
		System.out.println("Inserta el apellido del lector");
		String apellido = sc.nextLine();
		System.out.println("Inserta la edad del lector");
		int edad = sc.nextInt();
		sc.nextLine();
		System.out.println("Inserta el e-mail del lector");
		String email = sc.nextLine();
		//Creamos nuevo objeto Lector pasando atributos por parámetro
		Lector lector = new Lector(nombre, apellido, edad, email);
		try {
			//Se guarda en la BD.
			BaseDatosHibernate.crearLector(session, lector);
			System.out.println("Lector insertado correctamente");
			
		}catch(HibernateException e) {
			System.out.println("No se ha podido crear el lector");
		}				
	}
	
	//Muestra listado de lectores
	public static void crearListadoLectores(Session session) {
		List<Lector> lectores = BaseDatosHibernate.listadoLectores(session);
		//Si la listta esta vacía se notifica.
		if(lectores.isEmpty()) {
			System.out.println("No hay registros");
		}else {//Si no se muestra el listado
			for(Lector lec : lectores) {
				System.out.println(lec.toString());
			}
		}	
	}
	
	//Se modifica un lector
	public static void crearEntradaModificarLector(Session session, Scanner sc) {
		//Indentificamos el lector por id
		System.out.println("Indica el ID del lector que quieres actualizar");
		int id = sc.nextInt();
		sc.nextLine();
		Lector lec = session.get(Lector.class, id);
	
		
		//Verifica que se  encontró el  lector
		if(lec != null) {
			//declaramos el mecanísmo de entrada del bucle switch case.
			int opcion = -1;
			String almohadilla = "#:";  
			
			Menus.modificarLector();
			System.out.print(almohadilla); 
			opcion = sc.nextInt();
			sc.nextLine();
			//Eleccion segun el cambio que se quiera hacer	 		
			switch(opcion) {
			case 1://Cambio nombre
			   	System.out.println("¿Cuál es el nuevo nombre?");
			   	String newNom = sc.nextLine();
			   	lec.setNombre(newNom);		    	
				break;
				
			case 2://Cambio apellido
			   	System.out.println("¿Cuál es el nuevo apellido?");
			   	String newApe = sc.nextLine();
			   	lec.setApellido(newApe);		    		
				break;
			case 3://Cambio e-mail
			   	System.out.println("¿Cuál es el nuevo e-mail");
			   	String newmail = sc.nextLine();
			   	lec.setEmail(newmail);	   	
				break;
				
			case 0:
			   	System.out.println("Saliendo de modificación");
			    break;
			    
			    //Si se entra otro valor por tecla da una advertencia.			    
			 default:
			    System.out.println("Opción no válida. Por favor, elige una opción válida.");
			    break;
			}
			
			try {
				//Se guarda el lector modificado en la BD
				BaseDatosHibernate.modificarLectorID(session, lec);
				
				System.out.println("Lector modificado exitosamente");
				
			}catch(HibernateException e) {
				System.out.println("Error al modificar lector");
			}	
			
		} else {
			System.out.println("No se encontró el Lector con el ID especificado");
		}	
	}
	
	//Eliminamos lector
	public static void crearEntradaEliminarLector(Session session, Scanner sc) {
		//Llamamos al método anterior que muestra la lista de lectores
		crearListadoLectores(session);
		
		//Indicamos el lector a eliminar
		System.out.println("Indica el ID del lector que quieres eliminar");
		int id = sc.nextInt();
		sc.nextLine();
		Lector lector = session.get(Lector.class, id);
		
		try {
			//Lo eliminamos de la BD.
			BaseDatosHibernate.eliminarLector(session, lector);
			
			System.out.println("Lector borrado correctamente");
			
		}catch(HibernateException e) {
			System.out.println("No se encontró el Lector con el ID especificado");
		}			
	}
	
	//Mostramos los libros que tiene prestados un lector
	public static void crearLibrosPrestadosLector(Session session, Scanner sc) {
		
		//Indicamos id del lector a consultar
		System.out.println("Indica el ID del lector.");
	    int lectorId = sc.nextInt();
	    sc.nextLine();
	    Lector lector = session.get(Lector.class, lectorId);
	    //Mostramos lista de préstamos activos del lector
	    List<Prestamo> prestamos = BaseDatosHibernate.librosPrestadosLector(session, lector);
	    
	    if(prestamos.isEmpty()) {//Si no tiene se noifica
        	System.out.println("El lector no tiene libros prestados");
        }else {//Si tiene se muestran los libros prestados
        	System.out.println("Los libros que tiene " + lector.getNombre() + " son:");
        	for(Prestamo prestamo : prestamos) {
            	System.out.println(prestamo.getLibro());
            }	
        }      
	}
	
	//Indicamos el Historial de préstamos de un lector, tanto devueltos como no devueltos.
	public static void crearPrestamosLector(Session session, Scanner sc) {
		//Indicamos id del lector
		System.out.println("Indica el ID del lector.");
	    int lectorId = sc.nextInt();
	    sc.nextLine();
	    Lector lector = session.get(Lector.class, lectorId);
	    
	    //Se crea lista con el historial de prestamos
	    List<Prestamo> prestamos = BaseDatosHibernate.prestamosLector(session,lector);
	    
	    //Si no tiene préstamos aun se notifica
	    if (prestamos.isEmpty()) {
	        System.out.println("El lector no tiene historial de préstamos.");
	    } else {//Si tiene se muestra la lista
	        System.out.println("Historial de préstamos del lector:");
	        for (Prestamo prestamo : prestamos) {
	            System.out.println(prestamo.toString());
	        }
	    }
	}
}
