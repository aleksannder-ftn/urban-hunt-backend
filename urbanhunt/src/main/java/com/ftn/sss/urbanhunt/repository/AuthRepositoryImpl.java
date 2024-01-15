package com.ftn.sss.urbanhunt.repository;

import com.ftn.sss.urbanhunt.dto.guest.GuestBasicDTO;
import com.ftn.sss.urbanhunt.model.Administrator;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.repository.interfaces.AuthRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final EntityManager entityManager;

    @Autowired
    public AuthRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public GuestBasicDTO registerGuest(GuestBasicDTO guest) {
        entityManager.persist(guest);
        return guest;
    }

    @Override
    public Guest loginGuest(String username, String password) {
        return null;
    }

    @Override
    public Agent registerAgent(Agent agent) {
        entityManager.persist(agent);
        return agent;
    }

    @Override
    public Agent loginAgent(String username, String password) {
        return null;
    }

    @Override
    public Owner registerOwner(Owner owner) {
        entityManager.persist(owner);
        return owner;
    }

    @Override
    public Owner loginOwner(String username, String password) {
        return null;
    }

    @Override
    public Administrator registerAdmin(Administrator admin) {
        entityManager.persist(admin);
        return admin;
    }

    @Override
    public Administrator loginAdmin(String username, String password) {
        return null;
    }
}
