package com.patrones.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.patrones.entities.Employee;
import com.patrones.rest.RestActions;
import com.patrones.rest.RestResponse;
import com.patrones.rest.RestSystem;

public class RestAdapter implements IAdapter {

    private final String path = "/parcial/patrones/employees";
	@Override
	public Employee getEmployee(Long id) throws EmployeeNotFound {
        
        RestSystem system = new RestSystem();
        RestResponse response = system.sendRestRequest(path, "", RestActions.GET);

        if (response.getStatus() != 200) throw new EmployeeNotFound(id);

        JsonArray array = JsonParser.parseString(response.getPayload()).getAsJsonArray();

        Gson gsonParser = new Gson();
        for (int i = 0; i < array.size(); i++) {
            Employee employee = gsonParser.fromJson(array.get(i), Employee.class);
            if (employee.getId() == id) return employee;
        }

        throw new EmployeeNotFound(id);
	}
    
}
