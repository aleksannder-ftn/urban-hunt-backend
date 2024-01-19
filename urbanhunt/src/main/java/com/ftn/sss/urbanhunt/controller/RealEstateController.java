package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.RealEstateMapper;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.ImageService;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealEstateController {

    private final UserService userService;
    private final AgencyService agencyService;
    private final ImageService imageService;
    private final RealEstateService realEstateService;

    @Autowired
    public RealEstateController(UserService userService, AgencyService agencyService, ImageService imageService, RealEstateService realEstateService) {
        this.userService = userService;
        this.agencyService = agencyService;
        this.imageService = imageService;
        this.realEstateService = realEstateService;
    }

    @PostMapping(value="/agent/createRealEstate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('AGENT')")
    public ResponseEntity<RealEstateBasicDTO> createRealEstate(@RequestBody RealEstateBasicDTO realEstateBasicDTO, HttpServletRequest req) {
        try {
            Long id = (Long) req.getAttribute("userId");
            realEstateBasicDTO.setAgentId(id);
            RealEstate newRealEstate = realEstateService.addRealEstateWithImages(realEstateBasicDTO, userService, agencyService, imageService);
            return ResponseEntity.ok(RealEstateMapper.getRealEstateBasicDTO(newRealEstate, realEstateBasicDTO));
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
