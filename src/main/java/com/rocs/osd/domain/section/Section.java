package com.rocs.osd.domain.section;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
/**
 * Represents the section entity.
 */
@Entity
@Data
public class Section implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 16, nullable = false)
    private Long sectionId;
    @Column(length = 32, nullable = false)
    private String sectionName;
    @Column(length = 64, nullable = false)
    private String sectionCourse;
    @Column(length = 32, nullable = false)
    private String clusterName;
    @Column(length = 64, nullable = false)
    private String clusterHead;
    @Column(length = 16, nullable = false)
    private String division;
}
