package org.jsp.employeeDepartment.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.employeeDepartment.dao.DepartmentDao;
import org.jsp.employeeDepartment.dao.EmployeeDao;
import org.jsp.employeeDepartment.dto.Department;
import org.jsp.employeeDepartment.dto.Employee;

public class EmployeeDepartmentAllOperation 
{
	static Scanner sc=new Scanner(System.in);
	static DepartmentDao ddao=new DepartmentDao();
	static EmployeeDao edao=new EmployeeDao();
	static EntityManager manager= Persistence.createEntityManagerFactory("dev").createEntityManager();
	static EntityTransaction transaction=manager.getTransaction();
	public static void main(String[] args) 
	{
		System.out.println("===============================================");
		System.out.println("        Welcome to employee-Department app     ");
		System.out.println("===============================================");
		
		System.out.println("Enter 1 to save Deparment");
		System.out.println("Enter 2 to save Employee for Depoartment");
		System.out.println("Enter 3 to update Deparment");
		System.out.println("Enter 4 to update Employee in Depoartment");
		System.out.println("Enter 5 to view Employee present in Depoartment by department id");
		System.out.println("Enter 6 to view Employee present in Depoartment by department name");
		System.out.println("Enter 7 to delete the employee in the Department");
		
		int choice=sc.nextInt();
		switch (choice) {
		case 1:
			SaveDepartment();
			break;
		case 2:
			addEmployee();
			break;
		case 3:
		  UpdateDepartment();
			break;
		case 4:
			  updateEmployee();
				break;
		case 5:
			  viewEmployeeByDepartmentid();
				break;
		case 6:
			 viewEmployeeByDepartmentname();
				break;
				
		case 7:
			deleteEmp();
				break;

		default:
			break;
		}
	}
	
	public static void SaveDepartment()
	{
		System.out.println("Enter Name and location tomsave Department");
		String name=sc.next();
		String location=sc.next();
		Department d=new Department();
		d.setName(name);
		d.setLocation(location);
		
		d=ddao.saveDepartment(d);
		System.out.println(d);
		System.out.println("Department is saved with id :-"+d.getId());	
		
	}
	public static void UpdateDepartment()
	{
		System.out.println("Enter id to update Department");
		int id=sc.nextInt();
		System.out.println("Enter Name and location tomsave Department");
		String name=sc.next();
		String location=sc.next();
		Department d=new Department();
		d.setId(id);
		d.setName(name);
		d.setLocation(location);
		
		d=ddao.updateDepartment(d);
		System.out.println(d);
		System.out.println("Department is update with id :-"+d.getId());	
		
	}
	public static void addEmployee()
	{
		System.out.println("enter department id to save Employee");
		int id=sc.nextInt();
		System.out.println("Enter name,designation salary phone and password to save employee");
		String name=sc.next();
		String desgnation=sc.next();
		double salary=sc.nextDouble();
		long phone=sc.nextLong();
		String password=sc.next();
		
		
		Employee e=new Employee();
		e.setName(name);
		e.setDesgnation(desgnation);
		e.setSalary(salary);
		e.setPhone(phone);
		e.setPassword(password);
		e=edao.Addemployee(e,id);
		
		System.out.println("Employee is saved with id "+e.getId());
		
	}
	public static void updateEmployee()
	{
		System.out.println("enter department id to save Employee");
		int d_id=sc.nextInt();
		
		Department d=manager.find(Department.class, d_id);
	if(d!=null)
	{
		System.out.println("Enter id ,name,designation salary phone and password to update employee");
		int e_id=sc.nextInt();
		String name=sc.next();
		String desgnation=sc.next();
		double salary=sc.nextDouble();
		long phone=sc.nextLong();
		String password=sc.next();
		
		
		Employee e=new Employee();
		e.setId(e_id);
		e.setName(name);
		e.setDesgnation(desgnation);
		e.setSalary(salary);
		e.setPhone(phone);
		e.setPassword(password);
		
		
		
		e=edao.updateemployee(e,d_id);
		
		System.out.println("Employee is updated with id "+e.getId());
		
	}
	}
	public static void viewEmployeeByDepartmentid()
	{
		System.out.println("Enter department id to view Employee");
		int id=sc.nextInt();
		List<Employee> e=edao.viewEmployee(id);
		if(e.size()>0) {
			for(Employee e1:e)
				System.out.println(e1);
		}
		else {
			System.out.println("employee not found");
		}
		
		
	}
	public static void viewEmployeeByDepartmentname()
	{
		System.out.println("Enter department name to view Employee");
		String name=sc.next();
		List<Employee> e=edao.viewEmployee(name);
		if(e.size()>0) {
			for(Employee e1:e)
				System.out.println(e1);
		}
		else {
			System.out.println("employee not found");
		}
		
		
	}
	public static void deleteEmp() {
		System.out.println("enter the employee id to delete emp");
		int did=sc.nextInt();
		System.out.println("enter the employee id to delete the employee");
		int eid=sc.nextInt();
		edao.deleteEmp(did,eid);
	}

}
