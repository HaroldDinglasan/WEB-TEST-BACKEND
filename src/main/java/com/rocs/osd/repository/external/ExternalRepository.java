package com.rocs.osd.repository.external;

import com.rocs.osd.domain.external.External;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An interface to the External repository.
 */
public interface ExternalRepository extends JpaRepository<External, Long> {

    /**
     * Checks if an external exists with external number and email.
     *
     * @param externalNumber the unique number of the external
     * @param email the email of the external
     * @return true if externalNumber and email exist
     */
    boolean existsByExternalNumberAndEmail(String externalNumber, String email);

    /**
     * Finds an external by their external id.
     *
     * @param externalId the external ID
     * @return externalId
     */
    External findByExternalNumber(String externalId);

    /**
     * Finds an external employee by ID.
     *
     * @param id the external ID
     * @return ID
     */
    External findByUser_Id(long id);
    /**
     * Finds an external employee by employee email.
     *
     * @param email the external email
     * @return email
     */
    External findByEmail(String email);
}
