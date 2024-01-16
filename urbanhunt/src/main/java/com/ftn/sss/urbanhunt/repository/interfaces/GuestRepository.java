package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Guest;
import java.util.List;
public interface GuestRepository {

    // Basic CRUD
    List<Guest> getAllGuests();

    Guest getGuestById(Long id);

    int deactivateGuest(Guest guest);

    int activateGuest(Guest guest);

}
