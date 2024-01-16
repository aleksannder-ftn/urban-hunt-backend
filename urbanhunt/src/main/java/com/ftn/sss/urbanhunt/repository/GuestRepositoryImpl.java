package com.ftn.sss.urbanhunt.repository;

import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.repository.interfaces.GuestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Guest getGuestById(Long id) {
        return entityManager.find(Guest.class, id);
    }

    @Override
    @Transactional
    public int deactivateGuest(Guest guest) {
        Query query = entityManager.createQuery("UPDATE Guest SET active = false WHERE id = :id");
        query.setParameter("id", guest.getId());
        return query.executeUpdate();
    }
}
