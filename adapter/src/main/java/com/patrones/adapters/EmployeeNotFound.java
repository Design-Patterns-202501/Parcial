package com.patrones.adapters;

public class EmployeeNotFound extends Exception {
    public EmployeeNotFound(Long id) {
        super("Employee with ID: " + id + " do not found");
    }
}
