package org.jsp.employeeDepartment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.employeeDepartment.dto.Department;
import org.jsp.employeeDepartment.dto.Employee;

public class EmployeeDao
{
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	public Employee Addemployee(Employee e,int d_id)
	{
		Department d=manager.find(Department.class, d_id);
		if(d!=null)
		{
			
			d.getEmployees().add(e);
			e.setDepartments(d);
			manager.persist(e);
			transaction.begin();
			transaction.commit();
			return e;
			
		}
		return null;
	}
	public Employee updateemployee(Employee e,int d_id)
	{
		Department d=manager.find(Department.class, d_id);
		if(d!=null)
		{
			
			d.getEmployees().add(e);
			e.setDepartments(d);
			manager.merge(e);
			transaction.begin();
			transaction.commit();
			return e;
			
		}
		return null;
	}
public List<Employee> viewEmployee(int d_id)
{ 
	String qry="select d.employees from Department d where d.id=?1";
	Query q=manager.createQuery(qry);
	q.setParameter(1, d_id);
	return q.getResultList();
	
}
public List<Employee> viewEmployee(String d_name)
{ 
	String qry="select d.employees from Department d where d.name=?1";
	Query q=manager.createQuery(qry);
	q.setParameter(1, d_name);
	return q.getResultList();
	
}
public void deleteEmp(int d_id,int e_id) {
	Department d=manager.find(Department.class, d_id);
	if(d!=null) {
		Employee e=manager.find(Employee.class, e_id);
		if(e!=null) {
			
			manager.remove(e);
			transaction.begin();
			transaction.commit();
			System.out.println("employee deleted successfully");
		}
		else {
			System.out.println("department not found");
		}
	}
	
}
}
