package com.ftn.sss.urbanhunt.repository;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.repository.interfaces.AgentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgentRepositoryImpl implements AgentRepository {
    private EntityManager entityManager;

    @Autowired
    public AgentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Agent> getAllAgents() {
        TypedQuery<Agent> query = entityManager.createQuery("SELECT a FROM Agent a", Agent.class);
        return query.getResultList();
    }


}
