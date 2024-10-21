package com.rocs.osd.service.external.impl;

import com.rocs.osd.domain.external.External;
import com.rocs.osd.repository.external.ExternalRepository;
import com.rocs.osd.service.external.ExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementation of External interface that interacts with the ExternalRepository
 * to retrieve and manage external data from the database
 */
@Service
public class ExternalServiceImpl implements ExternalService {
    private ExternalRepository externalRepository;
    /**
     *  This is a constructor for injecting the ExternalRepository.
     *
     * @param externalRepository external repository
     */
    public ExternalServiceImpl(ExternalRepository externalRepository) {
        this.externalRepository = externalRepository;
    }

    @Override
    public List<External> getAllExternal() {
        return externalRepository.findAll();
    }
}
