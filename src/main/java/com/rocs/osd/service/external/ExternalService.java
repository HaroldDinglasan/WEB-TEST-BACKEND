package com.rocs.osd.service.external;

import com.rocs.osd.domain.external.External;

import java.util.List;
/**
 * Service interface for managing external operations, providing methods to retrieve
 * and manage external data.
 */
public interface ExternalService {

    /**
     * Retrieves list of all external.
     *
     * @return list of all external
     */
    List<External> getAllExternal();
}
