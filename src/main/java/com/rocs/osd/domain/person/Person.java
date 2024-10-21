package com.rocs.osd.domain.person;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
/**
 * Represents the person entity.
 */
@MappedSuperclass
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 32)
    private String lastName;
    @Column(length = 64)
    private String firstName;
    @Column(length = 32)
    private String middleName;
    private Date birthdate;
    @Column(length = 128, nullable = false)
    private String birthplace;
    @Column(length = 8, nullable = false)
    private String sex;
    @Column(length = 16, nullable = false)
    private String civilStatus;
    @Column(length = 32, nullable = false)
    private String citizenship;
    @Column(length = 32, nullable = false)
    private String religion;
    @Column(length = 64, nullable = false)
    private String email;
    @Column(length = 128, nullable = false)
    private String address;
    @Column(length = 11)
    private String contactNumber;
}
