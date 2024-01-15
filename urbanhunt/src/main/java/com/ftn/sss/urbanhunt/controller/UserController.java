package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.AgentMapper;
import com.ftn.sss.urbanhunt.dto.mapper.GuestMapper;
import com.ftn.sss.urbanhunt.dto.mapper.OwnerMapper;
import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.service.interfaces.AgentService;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import com.ftn.sss.urbanhunt.service.interfaces.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
public class UserController {
    private final GuestService guestService;
    private final OwnerService ownerService;
    private final AgentService agentService;

    @Autowired
    public UserController(GuestService guestService, OwnerService ownerService, AgentService agentService) {
        this.guestService = guestService;
        this.ownerService = ownerService;
        this.agentService = agentService;
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<UserBasicDTO>> findAllUsers() {
        List<Guest> guests = guestService.getAllGuests();
        List<Owner> owners = ownerService.getAllOwners();
        List<Agent> agents = agentService.getAllAgents();

        List<UserBasicDTO> usersBasicDTO = Stream.concat(
                guests.stream().map(GuestMapper::toGuestBasicDTO),
                Stream.concat(
                        owners.stream().map(OwnerMapper::toOwnerBasicDTO),
                        agents.stream().map(AgentMapper::toAgentBasicDTO)
                )
        ).toList();

        return new ResponseEntity<>(usersBasicDTO, HttpStatus.OK);
    }
}
