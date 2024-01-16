package com.ftn.sss.urbanhunt.repository;

import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.repository.interfaces.OwnerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {

    private EntityManager entityManager;

    @Autowired
    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Owner> getAllOwners() {
        TypedQuery<Owner> query = entityManager.createQuery("SELECT o FROM Owner o", Owner.class);
        return query.getResultList();
    }

    @Override
    public Owner getOwnerById(Long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    @Transactional
    public int deactivateOwner(Owner owner) {
        Query query = entityManager.createQuery("UPDATE Owner SET active = false WHERE id = :id");
        query.setParameter("id", owner.getId());
        return query.executeUpdate();
    }

    @Override
    @Transactional
    public int activateOwner(Owner owner) {
        Query query =  entityManager.createQuery("UPDATE Owner SET active = true WHERE id = :id");
        query.setParameter("id", owner.getId());
        return query.executeUpdate();
    }


}
