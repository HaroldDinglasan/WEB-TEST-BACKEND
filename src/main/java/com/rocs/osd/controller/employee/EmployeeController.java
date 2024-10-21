package com.rocs.osd.controller.employee;

import com.rocs.osd.domain.employee.Employee;
import com.rocs.osd.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 *RestController for managing employee operation such as GET request.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    /**
     * Constructs a new EmployeeController with EmployeeService.
     *
     * @param employeeService handles employee operations
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /**
     * Retrieves the list of all employees.
     *
     * @return The list of all employees
     */
    @GetMapping("/employeeList")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }
    /**
     * Retrieves an employee by employee number.
     *
     * @param employeeNumber employee number of the employee to retrieve
     * @return the employee
     */
    @GetMapping("employeeNumber/{employeeNumber}")
    public ResponseEntity<Employee> getEmployeeByEmployeeNumber(@PathVariable String employeeNumber) {
        Employee employee = employeeService.getEmployeeByEmployeeNumber(employeeNumber);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
