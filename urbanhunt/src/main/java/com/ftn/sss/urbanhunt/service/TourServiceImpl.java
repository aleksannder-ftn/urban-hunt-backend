package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Tour;
import com.ftn.sss.urbanhunt.repository.interfaces.TourRepository;
import com.ftn.sss.urbanhunt.security.MyWebSocketClient;
import com.ftn.sss.urbanhunt.service.interfaces.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final MyWebSocketClient webSocketClient;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, MyWebSocketClient webSocketClient) {
        this.tourRepository = tourRepository;
        this.webSocketClient = webSocketClient;
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
                String message = String.format("{\"type\":\"FINISHED_TOUR_RATE_AGENT\", \"id\":%d, \"guestId\":%d}",
                        tour.getAgent().getId(), tour.getGuest().getId());
                webSocketClient.sendMessage(message);
            }
        }
    }

    @Override
    public List<Tour> findAllByAgentIdAndAcceptedAndFinished(Long agentId) {
        return tourRepository.findAllByAgentIdAndAcceptedAndFinished(agentId, false, false);
    }

    @Override
    public List<Tour> findAllByAgencyIdAndAcceptedAndFinished(Long agencyId) {
        return tourRepository.findAllByAgencyIdAndAcceptedAndFinished(agencyId, false, false);
    }
}
