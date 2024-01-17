package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.dto.mapper.AgencyMapper;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/owner")
public class OwnerController {

    private final OwnerService ownerService;

    private final AgencyService agencyService;

    @Autowired
    public OwnerController(OwnerService ownerService, AgencyService agencyService) {
        this.agencyService = agencyService;
        this.ownerService = ownerService;
    }

    @PostMapping(value="/createAgency", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('OWNER')")
    public ResponseEntity<AgencyBasicDTO> createAgency(@RequestBody AgencyBasicDTO agencyBasicDTO) {
        try {
            Agency agency = AgencyMapper.toAgencyEntity(agencyBasicDTO);
            Agency newAgency = agencyService.createAgency(agency);
            return ResponseEntity.ok(AgencyMapper.toAgencyBasicDTO(newAgency));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
