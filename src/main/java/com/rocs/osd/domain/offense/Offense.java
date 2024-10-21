package com.rocs.osd.domain.offense;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Represents the offense entity.
 */
@Entity
@Data
public class Offense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 8)
    private String type;
    @Column(length = 32)
    private String description;
}
