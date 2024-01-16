package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.dto.mapper.GuestMapper;
import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.dto.user.UserTokenState;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.security.TokenUtils;
import com.ftn.sss.urbanhunt.service.interfaces.AuthService;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;

    private TokenUtils tokenUtils;

    @Autowired
    public AuthenticationController(AuthService authService, TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
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

    @GetMapping(value="loginUser")
    public ResponseEntity<UserTokenState> loginUser(@RequestBody(required = true) Map<String, Object> payload) {
        String jwt = "";
        Role userRole = Role.valueOf(payload.get("role").toString());

        switch (userRole) {
            case GUEST:
                Guest guest = authService.loginGuest(payload.get("username").toString(), payload.get("password").toString());
                if (guest != null) {
                    jwt = tokenUtils.generateToken(guest.getId(), userRole);
                    Long expiresIn = (long) tokenUtils.getExpiredIn();
                    Role role = (Role) guest.getAuthorities().stream().findFirst().orElse(null);
                    return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn, role), HttpStatus.OK);
                }
                break;
            case AGENT:
                Agent agent = authService.loginAgent(payload.get("username").toString(), payload.get("password").toString());
                if (agent != null) {
                    jwt = tokenUtils.generateToken(agent.getId(), userRole);
                    Long expiresIn = (long) tokenUtils.getExpiredIn();
                    Role role = (Role) agent.getAuthorities().stream().findFirst().orElse(null);
                    return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn, role), HttpStatus.OK);
                }
                break;
            case OWNER:
                Owner owner = authService.loginOwner(payload.get("username").toString(), payload.get("password").toString());
                if (owner != null) {
                    jwt = tokenUtils.generateToken(owner.getId(), userRole);
                    Long expiresIn = (long) tokenUtils.getExpiredIn();
                    Role role = (Role) owner.getAuthorities().stream().findFirst().orElse(null);
                    return new ResponseEntity<UserTokenState>(new UserTokenState(jwt, expiresIn, role), HttpStatus.OK);
                }
                break;
            case ADMINISTRATOR:
                break;
            default:
                return new ResponseEntity<>(new UserTokenState(jwt, 18000), HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserTokenState(jwt, 18000), HttpStatus.OK);
    }
}
