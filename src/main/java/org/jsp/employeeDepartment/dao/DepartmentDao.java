package org.jsp.employeeDepartment.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.employeeDepartment.dto.Department;

public class DepartmentDao 
{
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	public Department saveDepartment(Department d)
	{
		manager.persist(d);
		transaction.begin();
		transaction.commit();
		return d;
	}
	public Department updateDepartment(Department d)
	{
		manager.merge(d);
		transaction.begin();
		transaction.commit();
		return d;
	}

}
