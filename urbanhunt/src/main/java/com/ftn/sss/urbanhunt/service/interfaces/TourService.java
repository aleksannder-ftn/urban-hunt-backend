package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Tour;

public interface TourService {
    Tour save(Tour tour);
    int acceptTour(Long tourId);
    int declineTour(Long tourId);
    void updateTourStatus();

}
