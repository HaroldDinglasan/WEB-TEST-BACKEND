package com.rocs.osd.repository.offense;

import com.rocs.osd.domain.offense.Offense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * An interface to the Guest repository.
 */
public interface OffenseRepository extends JpaRepository<Offense, String> {

    /**
     * Finds list of offenses by their type.
     *
     * @param type type of the offense
     * @return a list of offenses with the specified type
     */
    List<Offense> findByType(String type);
    /**
     * Finds a single offense by its description.
     *
     * @param description the description of the offense
     * @return the offense with the description
     */ Offense findByDescription(String description);
}
