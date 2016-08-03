package app.hibernate1;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	   private static SessionFactory factory; 
	   public static void main1(String[] args) {
	      try{
	         factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	       
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }

	      /*Session session = factory.openSession();
	      Transaction tx = session.beginTransaction();
	      Employee employee = 
                  (Employee)session.get(Employee.class, 1);
	      System.out.println(employee);*/
	      
	     /* Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
	      	Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
	      	Integer empID3 = ME.addEmployee("John", "Paul", 10000);*/
	      
	     /* ME.listEmployees();
		 	ME.updateEmployee(empID1, 5000);
		 	ME.deleteEmployee(empID2);
			ME.listEmployees();*/
	      
	      Session session = factory.openSession();
	      String hql = "select * from EMPLOYEE e join STD_FACT s on e.ID = s.ID";
	      //select * from EMPLOYEE e join STD_FACT s on e.ID = s.ID
	      SQLQuery  query = session.createSQLQuery(hql);
	      
	      query.addEntity(Employee.class).addJoin("e", "e.id");
	      @SuppressWarnings("rawtypes")
		List results = query.list();
	      System.out.println(results);
	   }
	 
	   public Integer addEmployee(String fname, String lname, int salary){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = new Employee();
	         employee.setFirstName(fname);
	         employee.setLastName(lname);
	         employee.setSalary(salary);
	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return employeeID;
	   }
	
	   @SuppressWarnings("rawtypes")
	public void listEmployees( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM Employee").list(); 
	         for (Iterator iterator = 
	                           employees.iterator(); iterator.hasNext();){
	            Employee employee = (Employee) iterator.next(); 
	            System.out.print("First Name: " + employee.getFirstName()); 
	            System.out.print("  Last Name: " + employee.getLastName()); 
	            System.out.println("  Salary: " + employee.getSalary()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	 
	    public void updateEmployee(Integer EmployeeID, int salary ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = 
	                    (Employee)session.get(Employee.class, EmployeeID); 
	         employee.setSalary( salary );
			 session.update(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	  
	      public void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = 
	                   (Employee)session.get(Employee.class, EmployeeID); 
	         session.delete(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	}
