package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.RealEstateMapper;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.model.*;
import com.ftn.sss.urbanhunt.model.enums.Role;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value="/agent/findRealEstateById", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('AGENT')")
    public ResponseEntity<RealEstateBasicDTO> getRealEstateById(@RequestParam Long id) {
        try {
            RealEstate realEstate = realEstateService.findRealEstateById(id);
            RealEstateBasicDTO dto = new RealEstateBasicDTO();
            return ResponseEntity.ok(RealEstateMapper.getRealEstateBasicDTO(realEstate, dto));
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/findAllRealEstates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RealEstateBasicDTO>> findAllRealEstates(HttpServletRequest request) {
        try {
            List<RealEstate> realEstates;
            List<RealEstateBasicDTO> dto = new ArrayList<>();
            if(request.getAttribute("userId").equals("default")) {
                realEstates = realEstateService.findAll();
                return ResponseEntity.ok(RealEstateMapper.toRealEstateListDTO(realEstates, dto));
            }
            Long id = (Long) request.getAttribute("userId");
            User user = userService.findUserById(id);

            if(user.getRole().equals(Role.AGENT)) {
                realEstates = realEstateService.findAllByAgentId(id);
            } else {
                realEstates = realEstateService.findAll();
            }
            return ResponseEntity.ok(RealEstateMapper.toRealEstateListDTO(realEstates, dto));
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/owner/findAllRealEstatesByOwnerId", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('OWNER')")
    public ResponseEntity<List<RealEstateBasicDTO>> findAllRealEstatesByOwnerId(HttpServletRequest request) {
        try {
            Long id = (Long) request.getAttribute("userId");
            Agency agency = agencyService.findAgencyByOwnerId(id);
            List<RealEstate> realEstates = agency.getRealEstates();
            List<RealEstateBasicDTO> dto = new ArrayList<>();
            return ResponseEntity.ok(RealEstateMapper.toRealEstateListDTO(realEstates, dto));
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/agent/createRealEstate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('AGENT')")
    public ResponseEntity<RealEstateBasicDTO> createRealEstate(@RequestBody RealEstateBasicDTO realEstateBasicDTO, HttpServletRequest req) {
        try {
            Long id = (Long) req.getAttribute("userId");
            realEstateBasicDTO.setAgentId(id);
            RealEstate newRealEstate = realEstateService.addRealEstateWithImages(realEstateBasicDTO, userService, agencyService, imageService);
            return ResponseEntity.ok(RealEstateMapper.getRealEstateBasicDTO(newRealEstate, realEstateBasicDTO));
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/deactivateRealEstate", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('OWNER', 'AGENT', 'ADMINISTRATOR')")
    public ResponseEntity<?> deactivateRealEstate(@RequestParam Long id) {
        try {
            boolean success = realEstateService.deactivateRealEstate(id) == 1;
            if(success) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/activateRealEstate", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('OWNER', 'AGENT', 'ADMINISTRATOR')")
    public ResponseEntity<?> activateRealEstate(@RequestParam Long id) {
        try {
            boolean success = realEstateService.activateRealEstate(id) == 1;
            if(success) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/editRealEstate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('AGENT')")
    public ResponseEntity<?> editRealEstate(@RequestBody RealEstateBasicDTO realEstateBasicDTO) {
        try {
            RealEstate realEstate = realEstateService.editRealEstate(realEstateBasicDTO, agencyService, imageService);
            return ResponseEntity.ok(RealEstateMapper.getRealEstateBasicDTO(realEstate, realEstateBasicDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
