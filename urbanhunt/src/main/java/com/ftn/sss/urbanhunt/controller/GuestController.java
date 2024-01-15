package com.ftn.sss.urbanhunt.controller;


import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.dto.mapper.GuestMapper;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/findAllGuests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List<GuestBasicDTO>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        List<GuestBasicDTO> guestsBasicDTO = guests.stream()
                .map(GuestMapper:: toGuestBasicDTO)
                .toList();
        return new ResponseEntity<>(guestsBasicDTO, HttpStatus.OK);
    }
}
