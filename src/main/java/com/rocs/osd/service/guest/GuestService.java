package com.rocs.osd.service.guest;

import com.rocs.osd.domain.guest.Guest;

import java.util.List;

/**
 * Service interface for managing guest operations, providing methods to retrieve
 * and manage guest data.
 */
public interface GuestService {
    /**
     * Retrieves list of all guest.
     *
     * @return a list of all guests
     */
    List<Guest> getAllGuest();
    /**
     * Add a new guest
     *
     * @param guest guest to add
     */
    void addGuest(Guest guest);
    /**
     * Retrieves the beneficiaries of a guest by guest ID
     *
     * @param guestId  guest ID
     * @return list of guest beneficiaries
     */
    List<Guest> getGuestBeneficiaries(Long guestId);
    /**
     * Retrieves a guest by student number.
     *
     * @param studentNumber student number
     * @return guest
     */
    Guest getStudentByStudentNumber(String studentNumber);
}
