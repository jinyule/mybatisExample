package com.monitor.mapper;

import com.monitor.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by terminator on 2016/8/2.
 * User: terminator
 * Date: 2016/8/2
 * Time: 20:54
 * Copyright
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/spring.xml"})
public class EmployeeMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMapperTest.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testGetEmployee() throws Exception {

        List<Employee> employees = employeeMapper.getEmployees();
        LOGGER.debug("employ  = {}", employees);
        assertEquals(4, employees.size());
    }

    @Test
    public void testUpdateEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setId(4);
        employee.setSalary(employeeMapper.getEmployee(4).getSalary() + 1000);
        int before = employeeMapper.check();
        int updateRow = employeeMapper.updateEmployee(employee);
        assertEquals(1, updateRow);

        int after = employeeMapper.check();
        assertEquals(1000, after - before);
    }

    @Test
    public void testUpdateOrSaveEmployee() throws Exception {

        assertEquals(1000, employeeMapper.getEmployee(1).getSalary());

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Pocoyo");
        employee.setSalary(9000);
        int updateRow = employeeMapper.updateOrSaveEmployee(employee);
        assertEquals(1, updateRow);

        employee = employeeMapper.getEmployee(1);
        assertEquals(9000, employee.getSalary());
    }
}