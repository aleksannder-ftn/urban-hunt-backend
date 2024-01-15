package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.repository.interfaces.GuestRepository;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Guest> getAllGuests() {
        try {
            return guestRepository.getAllGuests();
        } catch (Exception e) {
            throw null;
        }
    }
}
