package com.rocs.osd.domain.register;

import com.rocs.osd.domain.employee.Employee;
import com.rocs.osd.domain.external.External;
import com.rocs.osd.domain.guest.Guest;
import com.rocs.osd.domain.student.Student;
import com.rocs.osd.domain.user.User;
import lombok.Data;
/**
 * Represents the register entity.
 */
@Data
public class Register {
    private User user;
    private Employee employee;
    private External external;
    private Guest guest;
    private Student student;
}
