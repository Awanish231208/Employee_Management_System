package com.hibernate.EMS;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private static Scanner scanner = new Scanner(System.in);
	
    public static void main( String[] args )
    {
        while (true) {
        	
        	System.out.println("\n--------Employee Management System---------");
        	System.out.println("1.Create Employee");
        	System.out.println("2.Read Employee");
        	System.out.println("3.Update Employee");
        	System.out.println("4.Delete Employee");
        	System.out.println("Press any other number to Exit");
        	
        	System.out.println("Enter your Choice:");
        	int choice = scanner.nextInt();
        	
        	switch (choice) {
			case 1: createEmployee();
			        break;
			case 2: readEmployee();
			        break;
			case 3: updateEmployee();
			        break;
			case 4: deleteEmployee();
			        break;
			default: {
				    System.out.println("Exiting...Thank You");
				    return;
				
			         }
			 }
		}
    }
    
    
    
    private static void createEmployee() {
    	System.out.println(".......Create Employee......");
    	System.out.println("Enter Name :");
    	String name = scanner.next();
    	System.out.println("Enter Salary :");
    	Double slaray = scanner.nextDouble();
    	System.out.println("Enter Designation:");
    	String designation = scanner.next();
    	System.out.println("Enter Department:");
    	String department = scanner.next();
    	
    	Employee employee = new Employee();
    	employee.setName(name);
    	employee.setSalary(slaray);
    	employee.setDesignation(designation);
    	employee.setDepartment(department);
    	
    	Session session = sessionFactory.openSession();
    	Transaction transaction = session.beginTransaction();
    	session.persist(employee);
    	transaction.commit();
    	session.close();
    	
    	 System.out.println("Employee created successfully with ID: " + employee.getId());
    }
    
    
    private static void readEmployee() {
    	 System.out.println("-------Read Employee------");
    	 System.out.println("Enter ID: ");
    	 int id = scanner.nextInt();
    	 
    	 Session session = sessionFactory.openSession();
    	 Employee employee = session.get(Employee.class, id);
    	 session.close();
    	 if(employee!=null) {
    		 System.out.println("......Employe Details........");
    		 System.out.println("ID: "+employee.getId());
    		 System.out.println("Name: "+employee.getName());
    		 System.out.println("Salary: "+employee.getSalary());
    		 System.out.println("Designation: "+employee.getDesignation());
    		 System.out.println("Department: "+employee.getDepartment());
    		 
    		 
    	 }
    	 
    	 else {
             System.out.println("Employee with ID " + id + " not found.");
         }
    	
	}
    
    @SuppressWarnings("deprecation")
	private static void updateEmployee() {
    	System.out.println("\n--- Update Employee ---");
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);

        if (employee != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.next();
            System.out.print("Enter New Salary: ");
            double salary = scanner.nextDouble();
            System.out.print("Enter New Designation: ");
            String designation = scanner.next();
            System.out.print("Enter New Department: ");
            String department = scanner.next();

            employee.setName(name);
            employee.setSalary(salary);
            employee.setDesignation(designation);
            employee.setDepartment(department);

            session.update(employee);
            transaction.commit();

            System.out.println("Employee with ID " + id + " updated successfully.");
        } 
        else {
            System.out.println("Employee with ID " + id+" not found."); 
            } 
            session.close(); 
	}
    
    
    private static void deleteEmployee() {
        System.out.println("\n--- Delete Employee ---");
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);

        if (employee != null) {
            session.remove(employee);
            transaction.commit();
            System.out.println("Employee with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
        session.close();
    }
    
    
    
    
}
