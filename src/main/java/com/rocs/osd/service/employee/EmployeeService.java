package com.rocs.osd.service.employee;

import com.rocs.osd.domain.employee.Employee;

import java.util.List;
/**
 * Service interface for managing employee operations, providing methods to retrieve
 * and manage employee data.
 */
public interface EmployeeService {
    /**
     * Retrieves list of all employees.
     *
     * @return list of all employees
     */
    List<Employee> getAllEmployee();
    /**
     * Retrieves a specific employee based on their employee number.
     *
     * @param employeeNumber employee number
     * @return the employee
     */
    Employee getEmployeeByEmployeeNumber(String employeeNumber);
}
