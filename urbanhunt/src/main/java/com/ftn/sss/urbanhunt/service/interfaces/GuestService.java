package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;

public interface GuestService {

    int rateRealEstate(Long realEstateId, Long userId, Boolean isLiked);
    Boolean checkIsLiked(RealEstate realEstate, User user);

    void rateAgent(Guest guest, Agent agent, Integer rating, String message, Long realEstateId);
}
