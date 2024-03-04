package com.ftn.sss.urbanhunt.controller;


import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
public class GuestController {

    private final UserService userService;

    private final GuestService guestService;

    @Autowired
    public GuestController(UserService userService, GuestService guestService) {
        this.userService = userService;
        this.guestService = guestService;
    }

    @GetMapping(value="/guest/findGuestIdFromToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findGuestIdFromToken(HttpServletRequest request) {
        try {
            Long id = (Long) request.getAttribute("userId");
            return ResponseEntity.ok(id);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/guest/rateAgent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rateAgent(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
        try {
            Guest guest = (Guest) userService.findUserById((Long) request.getAttribute("userId"));
            Agent agent = (Agent) userService.findUserById(Long.parseLong(payload.get("agentId").toString()));

            Integer rating = Integer.parseInt(payload.get("rating").toString());
            String message = payload.get("message").toString();
            Long realEstateId = Long.parseLong(payload.get("realEstateId").toString());


            guestService.rateAgent(guest, agent, rating, message, realEstateId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
