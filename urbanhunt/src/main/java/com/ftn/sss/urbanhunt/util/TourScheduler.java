package com.ftn.sss.urbanhunt.util;

import com.ftn.sss.urbanhunt.service.interfaces.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TourScheduler {

    private final TourService tourService;

    @Autowired
    public TourScheduler(TourService tourService) {
        this.tourService = tourService;
    }

    @Scheduled(fixedRate = 1800000)
    public void updateTourStatus() {
        tourService.updateTourStatus();
    }
}
