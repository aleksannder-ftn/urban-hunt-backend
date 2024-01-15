package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.model.Administrator;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Owner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

public interface AuthService {

    Guest registerGuest(Guest guest);
    Guest loginGuest(String username, String password);

    Agent registerAgent(Agent agent);
    Agent loginAgent(String username, String password);


    Owner registerOwner(Owner owner);
    Owner loginOwner(String username, String password);


    Administrator registerAdmin(Administrator admin);
    Administrator loginAdministrator(String username, String password);


}
