package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.RealEstateMapper;
import com.ftn.sss.urbanhunt.dto.realEstate.RealEstateBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;
import com.ftn.sss.urbanhunt.service.interfaces.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class RealEstateController {

    private final UserService userService;
    private final AgencyService agencyService;
    private final ImageService imageService;
    private final RealEstateService realEstateService;
    private final GuestService guestService;

    @Autowired
    public RealEstateController(UserService userService, AgencyService agencyService, ImageService imageService, RealEstateService realEstateService, GuestService guestService) {
        this.userService = userService;
        this.agencyService = agencyService;
        this.imageService = imageService;
        this.realEstateService = realEstateService;
        this.guestService = guestService;
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
    public ResponseEntity<?> findAllRealEstates(
            @RequestParam(name="location", required = false) String location,
            @RequestParam(name="surfaceFrom", required = false) Float surfaceFrom,
            @RequestParam(name="surfaceTo", required = false) Float surfaceTo,
            @RequestParam(name="priceFrom", required = false) Float priceFrom,
            @RequestParam(name="priceTo", required = false) Float priceTo,
            @RequestParam(name="transactionType", required = false) String transactionType,
            @RequestParam(name="realEstateType", required = false) String realEstateType,
            HttpServletRequest request) {
        try {
            RealEstateType realEstateTypeSearch = (realEstateType != null && !realEstateType.isEmpty()) ? RealEstateType.valueOf(realEstateType.toUpperCase(Locale.ROOT)) : null;
            TransactionType transactionTypeSearch = (transactionType != null && !transactionType.isEmpty()) ? TransactionType.valueOf(transactionType.toUpperCase(Locale.ROOT)) : null;
            List<RealEstate> realEstates;
            if(request.getAttribute("userId").equals("default")) {
                realEstates = realEstateService.find(null, location, surfaceFrom, surfaceTo,
                        priceFrom, priceTo, realEstateTypeSearch,transactionTypeSearch);
                return ResponseEntity.ok(RealEstateMapper.toRealEstateListDTO(realEstates, new ArrayList<RealEstateBasicDTO>()));
            }
            Long id = (Long) request.getAttribute("userId");
            User user = userService.findUserById(id);

            if(user.getRole().equals(Role.AGENT)) {
                realEstates = realEstateService.find(user, location, surfaceFrom, surfaceTo,
                        priceFrom, priceTo, realEstateTypeSearch, transactionTypeSearch);
            } else {
                realEstates = realEstateService.find(null, location, surfaceFrom, surfaceTo,
                        priceFrom, priceTo, realEstateTypeSearch, transactionTypeSearch);
            }
            return ResponseEntity.ok(RealEstateMapper.toRealEstateListDTO(realEstates, new ArrayList<RealEstateBasicDTO>()));
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

    @PostMapping(value="/agent/editRealEstate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('AGENT')")
    public ResponseEntity<?> editRealEstate(@RequestBody RealEstateBasicDTO realEstateBasicDTO) {
        try {
            RealEstate realEstate = realEstateService.editRealEstate(realEstateBasicDTO, agencyService, imageService);
            return ResponseEntity.ok(RealEstateMapper.getRealEstateBasicDTO(realEstate, realEstateBasicDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="sendLike", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('GUEST')")
    public ResponseEntity<?> likeRealEstate(@RequestBody Map<String, Object> payload, HttpServletRequest req) {
        try {
            Long realEstateId = Long.parseLong(payload.get("realEstateId").toString());
            Long userId = (Long) req.getAttribute("userId");
            Boolean isLiked = (Boolean) payload.get("isLiked");

            boolean success = guestService.rateRealEstate(realEstateId, userId, isLiked) == 1;

            if(success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="checkIsLiked")
    public ResponseEntity<Boolean> checkIsLiked(@RequestParam Long realEstateId, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            RealEstate realEstate = realEstateService.findRealEstateById(realEstateId);
            User user = userService.findUserById(userId);

            Boolean isLiked = guestService.checkIsLiked(realEstate, user);
            return ResponseEntity.ok(isLiked);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/guest/rentOrBuyRealEstate")
    @PreAuthorize("hasAuthority('GUEST')")
    public ResponseEntity<?> rentOrBuyRealEstate(@RequestParam String caseString, @RequestParam Long realEstateId) {
        try {
            RealEstate realEstate = realEstateService.findRealEstateById(realEstateId);
            if(!realEstate.isActive() || (realEstate.getRented() || realEstate.getSold())) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(caseString.equals("RENT")) {
                realEstateService.rentRealEstate(realEstateId);
            } else if(caseString.equals("SALE")) {
                realEstateService.buyRealEstate(realEstateId);
            }
            return ResponseEntity.ok(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
