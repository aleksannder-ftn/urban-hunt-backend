package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Guest;

import java.util.List;

public interface GuestService {

    List<Guest> getAllGuests();

    Guest getGuestById(Long id);

    int deactivateGuest(Guest guest);

    int activateGuest(Guest guest);
}
