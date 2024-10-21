package com.rocs.osd.domain.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rocs.osd.domain.person.Person;
import com.rocs.osd.domain.station.Station;
import com.rocs.osd.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.io.Serializable;

/**
 * Represents the external entity.
 */
@Entity
@Data
public class External extends Person implements Serializable {

    private String externalNumber;
    @OneToOne
    private Station station;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
