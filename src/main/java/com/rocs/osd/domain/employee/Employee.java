package com.rocs.osd.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocs.osd.domain.person.Person;
import com.rocs.osd.domain.station.Station;
import com.rocs.osd.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the employee entity.
 */
@Entity
@Data
public class Employee extends Person implements Serializable {

    @Column(length = 32, nullable = false)
    private String employeeNumber;
    @Column(length = 64, nullable = false)
    private String positionInRc;
    private Date dateEmployed;
    @Column(length = 16, nullable = false)
    private String sssNo;
    @Column(length = 16, nullable = false)
    private String tinNo;
    @Column(length = 16, nullable = false)
    private String pagibigNo;
    @ManyToOne
    private Station station;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
