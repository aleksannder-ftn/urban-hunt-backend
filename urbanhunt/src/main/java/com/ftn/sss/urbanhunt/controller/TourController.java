package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.TourMapper;
import com.ftn.sss.urbanhunt.dto.tour.TourBasicDTO;
import com.ftn.sss.urbanhunt.model.Tour;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import com.ftn.sss.urbanhunt.service.interfaces.TourService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourController {

    private final RealEstateService realEstateService;
    private final UserService userService;
    private final AgencyService agencyService;
    private final TourService tourService;
    @Autowired
    public TourController(RealEstateService realEstateService, UserService userService, AgencyService agencyService, TourService tourService) {
        this.realEstateService = realEstateService;
        this.userService = userService;
        this.agencyService = agencyService;
        this.tourService = tourService;
    }
    @PostMapping(value="createTour", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('GUEST')")
    public ResponseEntity<TourBasicDTO> createTour(@RequestBody TourBasicDTO tourBasicDTO, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            tourBasicDTO.setGuestId(userId);

            Tour tour = TourMapper.toTourEntity(tourBasicDTO, realEstateService, userService, agencyService);
            Tour newTour = tourService.save(tour);

            if (newTour != null) {
                return new  ResponseEntity<>(TourMapper.toTourBasicDTO(newTour), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="acceptTour")
    @PreAuthorize("hasAnyAuthority('AGENT', 'OWNER')")
    public ResponseEntity<?> acceptTour(@RequestParam Long tourId) {
        try {
            boolean success = (tourService.acceptTour(tourId)) == 1;
            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="declineTour")
    @PreAuthorize("hasAnyAuthority('AGENT', 'OWNER')")
    public ResponseEntity<?> declineTour(@RequestParam Long tourId) {
        try {
            boolean success = (tourService.declineTour(tourId)) == 1;
            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
