package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.AgencyMapper;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.AgentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    private final AgencyService agencyService;
    private final AgentService agentService;

    @Autowired
    public AgentController(AgencyService agencyService, AgentService agentService) {
        this.agencyService = agencyService;
        this.agentService = agentService;
    }

    @GetMapping(value="/agent/findAgencyByAgentId", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('AGENT')")
    public ResponseEntity<?> findAgencyByOwnerId(HttpServletRequest request) {
        try {
            Long id = (Long) request.getAttribute("userId");
            Agency agency = agencyService.findAgencyByAgentId(id);
            if (agency == null) {
                return ResponseEntity.ok(null);
            }
            return ResponseEntity.ok(AgencyMapper.toAgencyDetailedDTO(agency));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/agent/findAgentIdFromToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAgentIdFromToken(HttpServletRequest request) {
        try {
            Long id = (Long) request.getAttribute("userId");
            return ResponseEntity.ok(id);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/findAgentIdFromRealEstateId", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> findAgentIdFromRealEstateId(@RequestParam Long realEstateId) {
        try {
            Object object = agentService.findAgentByRealEstate(realEstateId);
            return ResponseEntity.ok(object);
        } catch(Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
