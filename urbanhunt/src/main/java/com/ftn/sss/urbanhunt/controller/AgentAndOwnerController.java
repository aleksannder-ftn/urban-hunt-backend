package com.ftn.sss.urbanhunt.controller;


import com.ftn.sss.urbanhunt.dto.mapper.TourMapper;
import com.ftn.sss.urbanhunt.dto.tour.TourBasicDTO;
import com.ftn.sss.urbanhunt.model.Tour;
import com.ftn.sss.urbanhunt.service.interfaces.TourService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentAndOwnerController {

    private final TourService tourService;

    @Autowired
    public AgentAndOwnerController(TourService tourService) {
        this.tourService = tourService;
    }
    @GetMapping(value="findCalendarByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('AGENT')")
    public ResponseEntity<?> findCalendarByUserId(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            List<Tour> tours = tourService.findAllByAgentIdAndAcceptedAndFinished(userId);
            List<TourBasicDTO> tourBasicDTOList = tours.stream().toList().stream().map(TourMapper::toTourBasicDTO).toList();
            return new ResponseEntity<>(tourBasicDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="findCalendarByAgencyId")
    @PreAuthorize("hasAnyAuthority('OWNER')")
    public ResponseEntity<?> findCalendarByAgencyId(@RequestParam Long agencyId) {
        try {
            List<Tour> tours = tourService.findAllByAgencyIdAndAcceptedAndFinished(agencyId);
            List<TourBasicDTO> tourBasicDTOList = tours.stream().toList().stream().map(TourMapper::toTourBasicDTO).toList();
            return new ResponseEntity<>(tourBasicDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
