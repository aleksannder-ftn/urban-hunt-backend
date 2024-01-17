package com.ftn.sss.urbanhunt.controller;


import com.ftn.sss.urbanhunt.dto.mapper.UserMapper;
import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
public class GuestController {

    private final UserService userService;

    @Autowired
    public GuestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAllGuests")
    public ResponseEntity<List<UserBasicDTO>> getAllGuests() {
        List<User> users = userService.getAllUsers();

        List<User> guests = users.stream()
                .filter(user -> user.getRole() == Role.GUEST)
                .toList();

        List<UserBasicDTO> guestsBasicDTO = guests.stream()
                .map(UserMapper:: toUserBasicDTO)
                .toList();
        return new ResponseEntity<>(guestsBasicDTO, HttpStatus.OK);
    }
}
