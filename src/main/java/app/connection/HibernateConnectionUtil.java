package app.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectionUtil {

	private static SessionFactory factory;
	
	public static SessionFactory getConnectionFactory(){
		try{
			if(factory == null){
				factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			}
			return factory;
		}
		catch(Throwable ex){
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public static void closeConnection(){
		factory.close();
		factory = null;
	}
}
