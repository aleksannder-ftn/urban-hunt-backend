package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.repository.interfaces.AgentRepository;
import com.ftn.sss.urbanhunt.repository.interfaces.GuestRepository;
import com.ftn.sss.urbanhunt.repository.interfaces.RealEstateRepository;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final RealEstateRepository realEstateRepository;
    private final AgentRepository agentRepository;
    private final EntityManager entityManager;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, RealEstateRepository realEstateRepository, AgentRepository agentRepository, EntityManager entityManager) {
        this.guestRepository = guestRepository;
        this.realEstateRepository = realEstateRepository;
        this.agentRepository = agentRepository;
        this.entityManager = entityManager;
    }

    @Override
    public int rateRealEstate(Long realEstateId, Long userId, Boolean isLiked) {
        try {
            RealEstate realEstate = realEstateRepository.findRealEstateById(realEstateId);
            Guest guest = guestRepository.findById(userId).orElse(null);
            assert guest != null;
            if (isLiked) {
                guest.getRealEstateRating().put(realEstate, true);
            } else {
                guest.getRealEstateRating().put(realEstate, false);
            }
            guestRepository.save(guest);
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

    @SneakyThrows
    @Override
    public Boolean checkIsLiked(RealEstate realEstate, User user) {
        try {
            Guest guest = guestRepository.findById(user.getId()).orElse(null);
            assert guest != null;
            return guest.getRealEstateRating().get(realEstate);
        } catch (Exception e) {
            throw new Exception("Real estate not found");
        }
    }

    @Override
    @Transactional
    public void rateAgent(Guest guest, Agent agent, Integer rating, String message, Long realEstateId) {
        try {
            Guest existingGuest = guestRepository.findById(guest.getId()).orElse(null);

            if (existingGuest != null) {
                Query query = entityManager.createNativeQuery("INSERT INTO agent_rating " +
                        " (guest_id, agent_id, rating, message, real_estate_id) VALUES (?, ?, ?, ?, ?)");

                query.setParameter(1, guest.getId());
                query.setParameter(2, agent.getId());
                query.setParameter(3, rating);
                query.setParameter(4, message);
                query.setParameter(5, realEstateId);

                query.executeUpdate();

                Query avgRatingQuery = entityManager.createNativeQuery
                        ("SELECT AVG(rating) FROM agent_rating WHERE agent_id = ?");

                avgRatingQuery.setParameter(1, agent.getId());

                BigDecimal avgRatingBD = (BigDecimal) avgRatingQuery.getSingleResult();
                Float avgRating = avgRatingBD.floatValue();

                agent.setAverageRating(avgRating);
                agentRepository.save(agent);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
