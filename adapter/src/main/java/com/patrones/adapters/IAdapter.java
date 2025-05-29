package com.patrones.adapters;

import com.patrones.entities.*;

public interface IAdapter {
    public Employee getEmployee(Long id) throws EmployeeNotFound;
}
