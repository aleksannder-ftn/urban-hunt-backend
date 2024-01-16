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

    @Override
    public List<Agent> getAllAgents() {
        TypedQuery<Agent> query = entityManager.createQuery("SELECT a FROM Agent a", Agent.class);
        return query.getResultList();
    }

    @Override
    public Agent getAgentById(Long id) {
        return entityManager.find(Agent.class, id);
    }

    @Override
    @Transactional
    public int deactivateAgent(Agent agent) {
        Query query = entityManager.createQuery("UPDATE Agent a SET a.active = false WHERE a.id = :id");
        query.setParameter("id", agent.getId());
        return query.executeUpdate();
    }

    @Override
    @Transactional
    public int activateAgent(Agent agent) {
        Query query = entityManager.createQuery("UPDATE Agent a SET a.active = true WHERE a.id = :id");
        query.setParameter("id", agent.getId());
        return query.executeUpdate();
    }


}
