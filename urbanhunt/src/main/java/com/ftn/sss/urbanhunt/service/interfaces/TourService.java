package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Tour;

import java.util.List;

public interface TourService {
    Tour save(Tour tour);
    int acceptTour(Long tourId);
    int declineTour(Long tourId);
    void updateTourFinished();
    List<Tour> findAllByAgentId(Long agentId);
    List<Tour> findAllByAgencyId(Long agencyId);

}
