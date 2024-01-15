package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.model.Administrator;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthRepository {

    // Guest
    GuestBasicDTO registerGuest(GuestBasicDTO guest);

    @Query("SELECT g FROM Guest g WHERE g.username = :username AND g.password = :password AND g.active = true")
    Guest loginGuest(@Param("username") String username, @Param("password") String password);

    // Agent
    Agent registerAgent(Agent agent);
    @Query("SELECT a FROM Agent a WHERE a.username = :username AND a.password = :password AND a.active = true")
    Agent loginAgent(@Param("username") String username, @Param("password") String password);

    // Owner
    Owner registerOwner(Owner owner);

    @Query("SELECT o FROM Owner o WHERE o.username = :username AND o.password = :password AND o.active = true")
    Owner loginOwner(@Param("username") String username, @Param("password") String password);

    // Admin
    Administrator registerAdmin(Administrator admin);
    @Query("SELECT ad FROM Administrator ad WHERE ad.username = :username AND ad.password = :password AND ad.active = true")
    Administrator loginAdmin(@Param("username") String username, @Param("password") String password);

}
