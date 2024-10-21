package com.rocs.osd.repository.employee;

import com.rocs.osd.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An interface to the Employee repository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    /**
     * Checks if an employee exists with the specified employee number and email.
     *
     * @param employeeNumber the unique number of the employee
     * @param email the email of the employee
     * @return true if employeeNumber and email exist
     */
    boolean existsByEmployeeNumberAndEmail(String employeeNumber, String email);

    /**
     * Finds an employee by employee number.
     *
     * @param employeeNumber the employee number
     * @return employeeNumber
     */
    Employee findByEmployeeNumber(String employeeNumber);

    /**
     * Finds an employee by ID.
     *
     * @param id the employee ID
     * @return ID
     */
    Employee findByUser_Id(long id);

    /**
     * Finds an employee by employee email.
     *
     * @param email the employee email
     * @return email
     */
    Employee findByEmail(String email);
}
