package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.AgencyMapper;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private final AgencyService agencyService;

    @Autowired
    public AgentController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping(value="/findAgencyByAgentId", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value="/findAgentIdFromToken", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('AGENT')")
    public ResponseEntity<?> findAgentIdFromToken(HttpServletRequest request) {
        try {
            Long id = (Long) request.getAttribute("userId");
            return ResponseEntity.ok(id);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
