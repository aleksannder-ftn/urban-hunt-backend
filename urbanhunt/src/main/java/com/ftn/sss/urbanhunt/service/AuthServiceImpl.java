package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.model.Administrator;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.repository.interfaces.AuthRepository;
import com.ftn.sss.urbanhunt.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public GuestBasicDTO registerGuest(GuestBasicDTO guest) {
        try {
            return authRepository.registerGuest(guest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Guest loginGuest(String username, String password) {
        return null;
    }

    @Override
    public Agent registerAgent(Agent agent) {
        return null;
    }

    @Override
    public Agent loginAgent(String username, String password) {
        return null;
    }

    @Override
    public Owner registerOwner(Owner owner) {
        return null;
    }

    @Override
    public Owner loginOwner(String username, String password) {
        return null;
    }

    @Override
    public Administrator registerAdmin(Administrator admin) {
        return null;
    }

    @Override
    public Administrator loginAdministrator(String username, String password) {
        return null;
    }
}
