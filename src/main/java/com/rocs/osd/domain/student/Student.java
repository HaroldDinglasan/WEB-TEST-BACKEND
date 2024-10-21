package com.rocs.osd.domain.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocs.osd.domain.person.Person;
import com.rocs.osd.domain.section.Section;
import com.rocs.osd.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Represents the student entity.
 */
@Entity
@Data
public class Student extends Person implements Serializable {

    @Column(length = 12, nullable = false)
    private String studentNumber;
    @ManyToOne
    private Section section;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
