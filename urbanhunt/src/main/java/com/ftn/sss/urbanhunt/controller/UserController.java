package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.UserMapper;
import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.dto.user.UserTokenState;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.security.TokenUtils;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
public class UserController {

    private final UserService userService;
    private final TokenUtils tokenUtils;
    private final AgencyService agencyService;


    @Autowired
    public UserController(UserService userService,AgencyService agencyService, TokenUtils tokenUtils) {
        this.userService = userService;
        this.agencyService = agencyService;
        this.tokenUtils = tokenUtils;
    }

    @PostMapping(value="/deactivateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public ResponseEntity<String> deactivateUser(@RequestBody Map<String, Object> payload) {
        User user = userService.findUserById(Long.valueOf((Integer) payload.get("id")));

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        boolean success = userService.deactivateUser(user.getId()) == 1;

        if (success) {
            return new ResponseEntity<>("User deactivated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not deactivated", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value="/activateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    public ResponseEntity<String> activateUser(@RequestBody Map<String, Object> payload) {
        User user = userService.findUserById(Long.valueOf((Integer) payload.get("id")));

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        boolean success = userService.activateUser(user.getId()) == 1;

        if (success) {
            return new ResponseEntity<>("User activated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not activated", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/auth/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBasicDTO> registerUser(@RequestBody UserBasicDTO userBasicDTO) {
        try {
            User user = UserMapper.toUserEntity(userBasicDTO);
            User newUser = userService.registerUser(UserMapper.toUserBasicDTO(user));
            return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserBasicDTO(newUser));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/auth/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserTokenState> loginUser(@RequestBody Map<String, Object> payload) {
        String jwt = "";
        Optional<User> user = userService.findUserByUsername(payload.get("username").toString());
        String password = payload.get("password").toString();
        if (user.isPresent()) {
            if (password.matches(user.get().getPassword())) { // No password encoding for now. Password is stored as raw string.
                jwt = tokenUtils.generateToken(user.get().getId());
                long expiresIn = tokenUtils.getExpiredIn();
                Role role = (Role) user.get().getAuthorities().stream().findFirst().orElse(null);
                return new ResponseEntity<>(new UserTokenState(jwt, expiresIn, role), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new UserTokenState(jwt, 18000), HttpStatus.OK);
    }
}
