package com.monitor.mapper;

import com.monitor.model.Employee;

import java.util.List;

/**
 * Created by terminator on 2016/8/2.
 * User: terminator
 * Date: 2016/8/2
 * Time: 19:36
 * Copyright
 */
public interface EmployeeMapper {

    List<Employee> getEmployees();

    int updateEmployee(Employee employee);

    int check();

    int updateOrSaveEmployee(Employee employee);

    Employee getEmployee(int id);
}
