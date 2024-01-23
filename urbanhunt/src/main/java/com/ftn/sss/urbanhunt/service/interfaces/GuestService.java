package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;

public interface GuestService {

    int rateRealEstate(Long realEstateId, Long userId, Boolean isLiked);
    Boolean checkIsLiked(RealEstate realEstate, User user);
}
