package modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDatosHibernate {
	
	/*
	 * Métodos CRUD
	 */
	
	//Crear Lector
	public static void crearLector(Session  session, Lector lector) {
		Transaction tx = null;
		try {
			//Crear y guardar lector
			tx =session.beginTransaction();
			//Guarda los datos
			session.save(lector);
			//Confirmamos la acción de guardado en la base de datos
			tx.commit();				
		}catch(HibernateException e) {
			if (tx!= null) tx.rollback();
			e.printStackTrace();
		}
		
	}
	
	//Crear Libro
	public static void crearLibro(Session session, Libro libro) {
		//Crear y guardar libro
		Transaction tx = null;
		try {
			//Crear y guardar libro
			tx = session.beginTransaction();
			//Guarda los datos
			session.save(libro);
			//Confirmamos la acción de guardado en la base de datos
			tx.commit();				
		}catch(HibernateException e) {//si no se hace la transaccion rollback
			if (tx!= null) tx.rollback();
			e.printStackTrace();
		}
		
		
	}
	
	//Leer Libros
	public static List<Libro> listadoLibros(Session session) {
		//Consultar y mostrar todos los libros
		List<Libro> libros= session.createQuery("FROM Libro", Libro.class).getResultList();
		
		return libros;
		
	}
	
	//Leer Lectores
	public static List<Lector> listadoLectores(Session session) {
		
		//Consultar y mostrar todos los lectores
		List<Lector> lectores= session.createQuery("FROM Lector", Lector.class).getResultList();
		
		return lectores;		
	}

	//Actualizar Libro
	public static void modificarLibroID(Session session, Libro libro) {
		//Iniciar una transacción
		Transaction tx = session.beginTransaction();
			
		try {
			//Operacion de modificación
			session.update(libro);
			
			//Confirmación
			tx.commit();				
		}catch(Exception e) {
			//En caso de error hace RollBack
			tx.rollback();
			System.err.println("Error al modificar libro: " +  e.getMessage());
		}
	}
	
	//Acutualizar Lector
	public static void modificarLectorID(Session session, Lector lector) {			
		//Iniciar una transacción
		Transaction tx = session.beginTransaction();				
		try {
			//Operacion de modificación
			session.update(lector);				
			//Confirmación
			tx.commit();
								
		}catch(Exception e) {
			//En caso de error hace RollBack
			tx.rollback();				
		}		
	}
	//Crear Préstamo
	public static void nuevoPrestamo(Session session, Prestamo prestamo, Libro libro) {
		
		Transaction tx =session.beginTransaction();
		session.save(prestamo);
		session.save(libro);
		tx.commit();		
		
	}
	//Actualizar Préstamo
	public static void devolverPrestamo(Session session, Prestamo prestamo, Libro libro) {
		Transaction tx = session.beginTransaction();
		session.update(prestamo);
		session.update(libro);
		tx.commit();      
	}
	
	//Eliminar Libro
	public static void eliminarLibro(Session session, Libro libro) {
		Transaction tx = session.beginTransaction();		
		try {			
			//Verifica que se encontró la instancia del  alumno
			if(libro != null) {
				session.delete(libro);
				tx.commit();
			}
		}catch(HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		}
		
	}
	
	//Eliminar Lector
	public static void eliminarLector(Session session, Lector lector) {
		Transaction tx = session.beginTransaction();				
		try {				
			//Verifica que se encontró la instancia del letor
			if(lector != null) {
				session.delete(lector);
				tx.commit();								
			}	
		}catch(HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		}					
	}
	
	//Consulta libros prestados a un lector
	public static List<Prestamo> librosPrestadosLector(Session session, Lector lector) {
		    
		// Consultar préstamos activos del lector
        List<Prestamo> prestamos = session.createQuery("FROM Prestamo WHERE lector = :lector AND fechaDevolucion IS NULL", Prestamo.class)
        		.setParameter("lector", lector)
        		.getResultList();
        return prestamos;
	}
	
	//Libros disponibles para el préstamo
	public static List<Libro> librosNoPrestados(Session session) {
		//Consultar todos los libros no prestados
		List<Libro> libros= session.createQuery("FROM Libro WHERE prestado = false", Libro.class).getResultList();
		
		return libros;		
	}
	
	//Historial de préstamos del lector
	public static List<Prestamo> prestamosLector(Session session, Lector lector) {		
	    //Consulta los prestamos del lector activos y no activos 
	    List<Prestamo> prestamos = session.createQuery("FROM Prestamo WHERE lector = :lector", Prestamo.class)
        		.setParameter("lector", lector)
        		.getResultList();
	    return prestamos;
	    
	}
}
