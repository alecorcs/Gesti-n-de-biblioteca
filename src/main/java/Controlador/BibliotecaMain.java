package Controlador;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import vista.Menus;

public class BibliotecaMain {

	public static void main(String[] args) {
		//Creamos el objeto de la clase Scanner, permite escribir por teclado.
		Scanner sc = new Scanner(System.in);
				
		System.out.println("INICIO DEL PROGRAMA...");
		//Indicamos que queremos usar Hibernate, definimos las interfaces
		
		Configuration cfg = new Configuration().configure();
		
		//Unica instancia de sessionFacory y session en nuestra sesión
		SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
		Session session = sessionFactory.openSession();
		
		System.out.println("CONFIGURACIÓN REALIZADA");
				
		//declaramos el mecanísmo de entrada del bucle switch case.
		int opcion = -1;
		
		//Adorno que indica donde escribir
		String almohadilla = "#:";  
			
		//Bucle para Mostrar menú hasta que se desee salir.
		do {			
			Menus.mostrarMenu();
			System.out.print(almohadilla); 
			
		/*Leemos la opción que quiere el usuario y lo usamos 
		* de mecanismo de entrada en el bucle.
		*/
			opcion = sc.nextInt();
			sc.nextLine();
		//Bucle que llama a los métodos dependiendo lo que se quiera realizar	 		
		    switch(opcion) {
		    case 1:
		    	ControlLibros.crearEntradaLibro(session, sc);
		    	break;
		    case 2:
		    	ControlLector.crearEntradaLector(session, sc);
		    	break;
		    case 3:
		    	ControlLibros.crearListadoLibros(session);
		    	break;
		    case 4:
		    	ControlLector.crearListadoLectores(session);
		    	break;
		    case 5:
		    	ControlLibros.crearEntradaModificarLibro(session, sc);
		    	break;
		    	
		    case 6:
		    	ControlLector.crearEntradaModificarLector(session, sc);
		    	break;
		    case 7:
		    	ControlPrestamo.crearNuevoPrestamo(session, sc);
		    	break;
		    case 8:
		    	ControlPrestamo.crearDevolverPrestamo(session,sc);
		    	break;
		    case 9:
		    	ControlLibros.crearEntradaEliminarLibro(session, sc);
		    	break;
		    case 10:
		    	ControlLector.crearEntradaEliminarLector(session, sc);
		    	break;
		    case 11:
		    	ControlLector.crearLibrosPrestadosLector(session, sc);
		    	break;
		    case 12:
		    	ControlLibros.crearlibrosNoPrestados(session);
		    	break;
		    case 13:
		    	ControlLector.crearPrestamosLector(session, sc);
		    	break;
		    case 0:
		    	System.out.println("Fin del programa.");
		    	System.out.println("Gracias por usar nuestra biblioteca");
		        break;
		        //Si se entra otro valor por teclada da una advertencia.
		    default:
		        System.out.println("Opción no válida. Por favor, elige una opción válida.");
		        break;
		        } 	 
		    //Cuando se escribe 0 se finaliza el bucle y el programa.
		}while (opcion != 0);
		
		//Cerramos la clase Scanner, sessionFacory y session al finalizar el programa.
		sc.close();
		session.close();
		sessionFactory.close();
	
	}	
}
