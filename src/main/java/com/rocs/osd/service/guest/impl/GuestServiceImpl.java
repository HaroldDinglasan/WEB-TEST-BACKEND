package com.rocs.osd.service.guest.impl;

import com.rocs.osd.domain.guest.Guest;
import com.rocs.osd.repository.guest.GuestRepository;
import com.rocs.osd.service.guest.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of Guest interface that interacts with the GuestRepository
 * to retrieve and manage guest data from the database
 */
@Service
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;
    /**
     *  This is a constructor for injecting the GuestRepository.
     *
     * @param guestRepository guest repository
     */
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Guest> getAllGuest() {
        return guestRepository.findAll();
    }
    @Override
    public List<Guest> getGuestBeneficiaries(Long guestId) {
        return guestRepository.findGuestById(guestId);
    }
    @Override
    public Guest getStudentByStudentNumber(String studentNumber) {
        return guestRepository.findByGuestNumber(studentNumber);
    }
    @Override
    public void addGuest(Guest guest) {
        guestRepository.save(guest);
    }
}
