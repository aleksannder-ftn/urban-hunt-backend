package com.ftn.sss.urbanhunt.controller;


import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
public class GuestController {

    private final UserService userService;

    @Autowired
    public GuestController(UserService userService) {
        this.userService = userService;
    }


}
