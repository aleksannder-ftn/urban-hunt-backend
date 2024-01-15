package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.dto.mapper.GuestMapper;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.service.interfaces.AuthService;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestBasicDTO> registerGuest(@RequestBody(required = true) GuestBasicDTO guestBasicDTO) {
        try {
            Guest guest = GuestMapper.toGuestEntity(guestBasicDTO);
            guest.setActive(true);
            Guest newGuest = authService.registerGuest(guest);
            return ResponseEntity.status(HttpStatus.CREATED).body(GuestMapper.toGuestBasicDTO(newGuest));
        } catch (InvalidParameterException | NullPointerException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
