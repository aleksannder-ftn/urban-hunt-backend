package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.repository.interfaces.GuestRepository;
import com.ftn.sss.urbanhunt.repository.interfaces.RealEstateRepository;
import com.ftn.sss.urbanhunt.service.interfaces.GuestService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final RealEstateRepository realEstateRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, RealEstateRepository realEstateRepository) {
        this.guestRepository = guestRepository;
        this.realEstateRepository = realEstateRepository;
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
}
