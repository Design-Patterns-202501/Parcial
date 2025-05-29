package com.patrones.adapters;

import com.patrones.db.DatabaseSystem;
import com.patrones.entities.Employee;

public class DatabaseAdapter implements IAdapter {

	@Override
	public Employee getEmployee(Long id) throws EmployeeNotFound {
        DatabaseSystem system = new DatabaseSystem();
        Employee employee = system.getEmployeeById(id, "tech_employees");

        if (employee.getId() == -1L) throw new EmployeeNotFound(id);

        return employee;
	}

}
