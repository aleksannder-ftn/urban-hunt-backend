package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.dto.agent.AgentBasicDTO;
import com.ftn.sss.urbanhunt.dto.mapper.AgencyMapper;
import com.ftn.sss.urbanhunt.dto.mapper.AgentMapper;
import com.ftn.sss.urbanhunt.dto.mapper.UserMapper;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateDetailedDTO;
import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.service.interfaces.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/owner")
@CrossOrigin(origins = {"http://localhost:5173"})
public class OwnerController {

    private final UserService userService;
    private final OwnerService ownerService;
    private final AgencyService agencyService;
    private final AgentService agentService;
    private final RealEstateService realEstateService;

    @Autowired
    public OwnerController(UserService userService, AgencyService agencyService, OwnerService ownerService, AgentService agentService, RealEstateService realEstateService) {
        this.agencyService = agencyService;
        this.userService = userService;
        this.ownerService = ownerService;
        this.agentService = agentService;
        this.realEstateService = realEstateService;
    }

    @PostMapping(value="/createAgency", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<AgencyBasicDTO> createAgency(@RequestBody AgencyBasicDTO agencyBasicDTO) {
        try {
            Owner owner = (Owner) userService.findUserById(agencyBasicDTO.getOwnerId());
            Agency agency = AgencyMapper.toAgencyEntity(agencyBasicDTO, ownerService);
            agency.setOwner(owner);
            Agency newAgency = agencyService.createAgency(agency);
            return ResponseEntity.ok(AgencyMapper.toAgencyBasicDTO(newAgency));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/findAgencyByOwnerId", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<?> findAgencyByOwnerId(HttpServletRequest request) {
        try {
            Long id = (Long) request.getAttribute("userId");
            Agency agency = agencyService.findAgencyByOwnerId(id);
            if (agency == null) {
                return ResponseEntity.ok(null);
            }
            return ResponseEntity.ok(AgencyMapper.toAgencyDetailedDTO(agency));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="findOwnerById", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<UserBasicDTO> findOwnerByUsername(HttpServletRequest request) {
        try {
            Long id = (Long) request.getAttribute("userId");
            Owner owner = (Owner) userService.findUserById(id);
            return ResponseEntity.ok(UserMapper.toUserBasicDTO(owner));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="findAllAgentsByAgencyId", produces= MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<List<AgentBasicDTO>> findAllAgentsByAgencyId(@RequestParam Long agencyId) {
        try {
            List<Agent> agents = agentService.findAllAgentsByAgencyId(agencyId);

            List<AgentBasicDTO> dtoList = agents.stream()
                    .map(AgentMapper::toAgentBasicDTO)
                    .toList();
            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="createAgent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<?> addAgent(@RequestBody AgentBasicDTO agentBasicDTO) {
        try {
            Agent agent = AgentMapper.toAgentEntity(agentBasicDTO, agencyService);
            agentService.saveAgent(agent);
            return ResponseEntity.ok(UserMapper.toUserBasicDTO(agent));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="deleteAgent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<?> deleteAgent(@RequestParam Long agentId) {
        try {
            Agent agent = agentService.findAgentById(agentId).orElse(null);
            agentService.deleteAgent(agent);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/findMostPopularAgentsByAgencyId", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<List<AgentBasicDTO>> findMostPopularAgentsByAgencyId(@RequestParam Long agencyId) {
        try {
            List<Agent> agents = agentService.findMostPopularAgentsByAgencyId(agencyId);
            List<AgentBasicDTO> dtoList = agents.stream()
                    .map(AgentMapper::toAgentBasicDTO)
                    .toList();
            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findMostPopularRealEstatesByAgencyId", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<List<RealEstateDetailedDTO>> findMostPopularRealEstatesByAgencyId(@RequestParam Long agencyId) {
        try {
            List<RealEstateDetailedDTO> dtoList = realEstateService.findAllByPopularity(agencyId);
            return ResponseEntity.ok(dtoList);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
