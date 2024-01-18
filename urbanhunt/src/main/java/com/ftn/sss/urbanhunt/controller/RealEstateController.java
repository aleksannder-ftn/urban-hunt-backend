package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.RealEstateMapper;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
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
    private final RealEstateService realEstateService;

    @Autowired
    public RealEstateController(UserService userService, AgencyService agencyService, RealEstateService realEstateService) {
        this.userService = userService;
        this.agencyService = agencyService;
        this.realEstateService = realEstateService;
    }

    @PostMapping(value="/agentAndOwner/createRealEstate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('AGENT', 'OWNER')")
    public ResponseEntity<RealEstateBasicDTO> createRealEstate(@RequestBody RealEstateBasicDTO realEstateBasicDTO) {
        try {
            Agent agent = (Agent) userService.findUserById(realEstateBasicDTO.getAgentId());
            Agency agency = agencyService.findAgencyById(realEstateBasicDTO.getAgencyId());
            RealEstate realEstate = RealEstateMapper.toRealEstateEntity(realEstateBasicDTO, userService, agencyService);
            realEstate.setAgent(agent);
            realEstate.setAgency(agency);
            RealEstate newRealEstate = realEstateService.createRealEstate(realEstate);
            return ResponseEntity.ok(RealEstateMapper.getRealEstateBasicDTO(newRealEstate, realEstateBasicDTO));
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
