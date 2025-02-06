package Controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import modelo.BaseDatosHibernate;
import modelo.Lector;
import modelo.Libro;
import modelo.Prestamo;

public class ControlPrestamo {
	//Se crea nuevo préstamo
	public static void crearNuevoPrestamo(Session session, Scanner sc) {
		//Llamamos a la BD para que nos devuelva la lista de Lectores
		List<Lector> lectorRegis = BaseDatosHibernate.listadoLectores(session); 
		//Indicamos el lector que quiere coger el libro
		System.out.println("Indica el ID del lector que está realizando el préstamo:");
	    int lectorId = sc.nextInt();
	    sc.nextLine();
	    Lector lector = session.get(Lector.class, lectorId);
	    
		//Si el lector esta en la lista y por tanto en la BD puede reservarlo, sino tendrá que inscribirse.
		if (lectorRegis.contains(lector)) {       
	        
				//Crear nuevo préstamo. Indicamos el id del libro
				System.out.println("Indica el ID del libro que quieres prestar");
				int id = sc.nextInt();
				sc.nextLine();
				Libro lib = session.get(Libro.class, id);
				
				//Si el libro está prestado lanza mensaje
				if(lib.isPrestado()== true) {
					System.out.println("Lo siento el libro ya esta prestado");
				}else {//Si no lo está se realiza el prestamo
					/*
					 * Calendar (Java Platform SE 8 ). (2024, January 8). https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
					 * Fecha actual
					 */
					//Nos da la fecha en la que se realiza la llamada a Calendar.getInstance()
					Calendar current = Calendar.getInstance();
					//Le damos formatto para que se guarde SimpleDateFormat (Java Platform SE 8 ). (2024, April 4). https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					//Guardamos la fecha en una variable.
					String fechaActual  = sdf.format(current.getTime());
					
					//Pasamos por paramero los atribuos dee préstamo
					Prestamo prestamo = new Prestamo(fechaActual, null, lib, lector);
					//Indicamos el libro como prestado.
					lib.setPrestado(true);
					
					//Guardamos el nuevo prestamo y la modificación del libro en la BD
					BaseDatosHibernate.nuevoPrestamo(session, prestamo, lib);
					
					System.out.println("Nuevo prestamo registrado");
					
					//Devuelve el nuevo présamo por consola.
					System.out.println(prestamo.toString());
				}
		} else System.out.println("Primero debe registrar el lector");	
	}
	
	//Deevolver un préstamo
	public static void crearDevolverPrestamo(Session session, Scanner sc) {
		
		System.out.println("Indica el ID del lector que quiere devolver un préstamo:");
	    int lectorId = sc.nextInt();
	    sc.nextLine();
	    Lector lector = session.get(Lector.class, lectorId);

	    //Si el lector existe en la BD iniciamos la operación de devolución
	    if (lector != null) {
	    	//Recogemos en una lista los préstamos que no están devueltos por el lector.
	    	List<Prestamo> prestamoActivo =  BaseDatosHibernate.librosPrestadosLector(session, lector);  	
	        
	    	//Si tiene préstamos activos iniciamos la devolución, sino se india que no tiene prestamos activos.
	        if (prestamoActivo != null) {
	        	
	        	//Devolvemos los prestamos activos para identificarlos y elegir que libro quiere devolver
	        	System.out.println("Lista de préstamos activos del lector:");
	        	for(Prestamo prestamo : prestamoActivo) {
	            	System.out.println(prestamo.toString());
	        	}
	        	//Indicamos el id del libro
	        	System.out.println("Indica el ID del libro que quieres devolver");
				int id = sc.nextInt();
				sc.nextLine();
				Libro lib = session.get(Libro.class, id);   

				Prestamo seleccionado = null;
				
	            //Si el libro está en los prestamos activos se guarda en Prestamos seleccionado
				for(Prestamo prestamo : prestamoActivo) {
					if(prestamo.getLibro().getIdLibro() == id) {
	            		seleccionado = prestamo;
	            		break;
	            	}
	        	}  
				if(seleccionado != null) {
					// Marcar el libro como disponible
	                lib = seleccionado.getLibro();
	                lib.setPrestado(false);
	                
			        // Actualizar fecha de devolución
			        Calendar current = Calendar.getInstance();
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			        String fechaDevolucion = sdf.format(current.getTime());
			        seleccionado.setFechaDevolucion(fechaDevolucion);
			        
			        //Guardamos en la BD la situaión del prestamo y del libro, que pasa a estar disponible
			        BaseDatosHibernate.devolverPrestamo(session, seleccionado, lib);
			        
			        System.out.println("Préstamo devuelto correctamente.");
		            
			        //Listamos la nueva situacion del préstamo y el libro para verificar.
		            System.out.println(seleccionado.toString());
		            
		            System.out.println(lib.toString());
		            
				}else {
					System.out.println("El lector no tiene el libro especificado prestado.");
				}
	            
	        } else {
	            System.out.println("El lector no tiene préstamos activos.");
	        }
	    } else {
	        System.out.println("No se encontró un lector con el ID especificado.");
	    }
	}
}
