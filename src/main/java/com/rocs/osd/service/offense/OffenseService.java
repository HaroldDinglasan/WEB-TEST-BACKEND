package com.rocs.osd.service.offense;

import com.rocs.osd.domain.offense.Offense;

import java.util.List;
/**
 * Service interface for managing offense operations, providing methods to retrieve
 * and manage offense data.
 */
public interface OffenseService {
    /**
     * Retrieves all offenses.
     *
     * @return list of all offense
     */
    List<Offense> getAllOffense();
    /**
     * Retrieves offenses by type.
     *
     * @param type type of offense
     * @return a list of offenses
     */
    List<Offense> getOffenseByType(String type);
    /**
     * Retrieves an offense by description.
     *
     * @param description description of the offense
     * @return the offense
     */
    Offense getOffenseByDescription(String description);
    /**
     * Add a new offense.
     *
     * @param offense offense to be added
     */
    void addOffense(Offense offense);
    /**
     * Updates an existing offense.
     *
     * @param offense offense with updated information
     * @return the updated offense
     */
    Offense updateOffense(Offense offense);
}