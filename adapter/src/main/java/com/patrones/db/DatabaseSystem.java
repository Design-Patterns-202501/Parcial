package com.patrones.db;

import com.patrones.entities.*;

import java.sql.*;
import java.util.Properties;
import com.patrones.utils.*;

public class DatabaseSystem {


    /*
     * Returns a Employee with id = -1 if do not exist
     **/
    public Employee getEmployeeById(Long id, String table) {
        Employee employee = new Employee();
        Properties props = new PropsUtil().loadProperties();

        String user = props.get("db.user").toString();
        String password = props.get("db.password").toString();
        String url = props.get("db.url").toString();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM " + table + " WHERE id=?");
            query.setLong(1, id);

            ResultSet resultSet = query.executeQuery();

            int results = 0;
            while (resultSet.next()) {
                results++;
                employee.setId(id);
                employee.setName(resultSet.getString("name"));
                employee.setCompany(resultSet.getString("company"));
                employee.setExperience(resultSet.getInt("experience"));
            }

            if (results == 0) employee.setId(-1L);
        } catch (Exception e) {
            employee.setId(-1L);
            e.printStackTrace();
        }

        return employee;
    }
    
}
