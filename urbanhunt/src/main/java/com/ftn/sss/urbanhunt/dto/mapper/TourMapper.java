package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.tour.TourBasicDTO;
import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Tour;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import com.ftn.sss.urbanhunt.util.TourScheduler;

public class TourMapper {

    public static TourBasicDTO toTourBasicDTO(Tour tour) {
        TourBasicDTO tourBasicDTO = new TourBasicDTO();
        tourBasicDTO.setId(tour.getId());
        tourBasicDTO.setStartTime(TourScheduler.convertLocalDateTimeToTimestamp(tour.getStartTime()));
        tourBasicDTO.setAccepted(tour.isAccepted());
        tourBasicDTO.setFinished(tour.isFinished());
        tourBasicDTO.setRealEstateId(tour.getRealEstate().getId());
        tourBasicDTO.setAgentId(tour.getAgent().getId());
        tourBasicDTO.setAgencyId(tour.getAgency().getId());
        tourBasicDTO.setGuestId(tour.getGuest().getId());
        return tourBasicDTO;
    }

    public static Tour toTourEntity(TourBasicDTO tourBasicDTO, RealEstateService realEstateService, UserService userService, AgencyService agencyService) {
        Tour tour = new Tour();
        tour.setId(tourBasicDTO.getId());
        tour.setStartTime(TourScheduler.convertTimestampToLocalDateTime(tourBasicDTO.getStartTime()));
        tour.setEndTime(tour.getStartTime().plusMinutes(30));
        tour.setAccepted(tourBasicDTO.isAccepted());
        tour.setFinished(tourBasicDTO.isFinished());
        tour.setRealEstate(realEstateService.findRealEstateById(tourBasicDTO.getRealEstateId()));
        tour.setAgent(tour.getRealEstate().getAgent());
        tour.setAgency(tour.getRealEstate().getAgency());
        tour.setGuest((Guest) userService.findUserById(tourBasicDTO.getGuestId()));
        return tour;
    }
}
