package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Tour;
import com.ftn.sss.urbanhunt.repository.interfaces.TourRepository;
import com.ftn.sss.urbanhunt.service.interfaces.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }
    @Override
    public Tour save(Tour tour) {
        tour.setAgent(tour.getRealEstate().getAgent());
        tour.setAgency(tour.getRealEstate().getAgency());
        return tourRepository.save(tour);
    }

    @Override
    public int acceptTour(Long tourId) {
        Tour tour = tourRepository.findById(tourId).orElse(null);
        if (tour != null && !(tour.isAccepted())) {
            tour.setAccepted(true);
            tourRepository.save(tour);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int declineTour(Long tourId) {
        Tour tour = tourRepository.findById(tourId).orElse(null);
        if (tour != null && !(tour.isAccepted())) {
            tourRepository.delete(tour);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void updateTourFinished() {
        List<Tour> getAcceptedTours = tourRepository.getAllByAcceptedAndFinished(true, false);
        for (Tour tour : getAcceptedTours) {
            if (tour.getEndTime().isBefore(LocalDateTime.now())) {
                tour.setFinished(true);
                tourRepository.save(tour);
            }
        }

    }
}
