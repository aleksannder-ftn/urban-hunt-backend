package com.ftn.sss.urbanhunt.repository;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.repository.interfaces.AgentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AgentRepositoryImpl implements AgentRepository {
    private final EntityManager entityManager;

    @Autowired
    public AgentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



}
