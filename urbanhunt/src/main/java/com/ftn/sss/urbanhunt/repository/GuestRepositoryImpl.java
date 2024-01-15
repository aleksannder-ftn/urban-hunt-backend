package com.ftn.sss.urbanhunt.repository;

import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.repository.interfaces.GuestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestRepositoryImpl implements GuestRepository {

    private EntityManager entityManager;

    @Autowired
    public GuestRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Guest> getAllGuests() {
        TypedQuery<Guest> query = entityManager.createQuery("SELECT g FROM Guest g", Guest.class);
        return query.getResultList();
    }
}
