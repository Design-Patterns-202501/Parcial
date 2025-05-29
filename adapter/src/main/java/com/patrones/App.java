package com.patrones;

import com.patrones.adapters.*;
import com.patrones.entities.*;

public class App {
    
    public static void main(String[] args) {

        IAdapter rest = new RestAdapter();
        System.out.println("From Rest adapter...");
        try {
            Employee employee = rest.getEmployee(1L);
            System.out.println(employee);

            Employee second = rest.getEmployee(2L);
            System.out.println(second);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        IAdapter database = new DatabaseAdapter();
        System.out.println("From Database adapter...");
        try {
            Employee employee = database.getEmployee(1L);
            System.out.println(employee);

            Employee second = database.getEmployee(2L);
            System.out.println(second);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return;
    }
}
