package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.dto.mapper.AgencyMapper;
import com.ftn.sss.urbanhunt.dto.mapper.UserMapper;
import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.service.interfaces.AgencyService;
import com.ftn.sss.urbanhunt.service.interfaces.OwnerService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/owner")
@CrossOrigin(origins = {"http://localhost:5173"})
public class OwnerController {

    private final UserService userService;
    private final OwnerService ownerService;

    private final AgencyService agencyService;

    @Autowired
    public OwnerController(UserService userService, AgencyService agencyService, OwnerService ownerService) {
        this.agencyService = agencyService;
        this.userService = userService;
        this.ownerService = ownerService;
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
            System.out.println(e);
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
}
