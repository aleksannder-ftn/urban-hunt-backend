package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.repository.interfaces.GuestRepository;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
}
