package com.ftn.sss.urbanhunt.controller;


import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
public class GuestController {

    private final UserService userService;

    @Autowired
    public GuestController(UserService userService) {
        this.userService = userService;
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
}
