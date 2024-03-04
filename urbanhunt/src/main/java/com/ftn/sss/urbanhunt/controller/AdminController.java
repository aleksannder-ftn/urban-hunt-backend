package com.ftn.sss.urbanhunt.controller;

import com.ftn.sss.urbanhunt.dto.mapper.UserMapper;
import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.User;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/admin")
public class AdminController {

    private final RealEstateService realEstateService;
    private final UserService userService;
    private final UserController userController;

    @Autowired
    public AdminController(RealEstateService realEstateService, UserService userService, UserController userController) {
        this.realEstateService = realEstateService;
        this.userService = userService;
        this.userController = userController;
    }

    @GetMapping(value="findAllUsers")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<UserBasicDTO>> findAllUsers() {
        List<User> allUsers = userService.getAllUsers();

        List<UserBasicDTO> usersBasicDTO = allUsers.stream()
                .map(UserMapper:: toUserBasicDTO)
                .toList();

        return new ResponseEntity<>(usersBasicDTO, HttpStatus.OK);
    }

    @PostMapping(value="deactivateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> deactivateUser(@RequestBody Map<String, Object> payload) {
        User user = userService.findUserById(Long.valueOf((Integer) payload.get("id")));

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        if (user.getRole() == Role.ADMINISTRATOR) {
            return new ResponseEntity<>("Administrator can't be deactivated", HttpStatus.BAD_REQUEST);
        }

        boolean success = userService.deactivateUser(user.getId()) == 1;

        if (success) {
            return new ResponseEntity<>("User deactivated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not deactivated", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value="activateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> activateUser(@RequestBody Map<String, Object> payload) {
        User user = userService.findUserById(Long.valueOf((Integer) payload.get("id")));

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        boolean success = userService.activateUser(user.getId()) == 1;

        if (success) {
            return new ResponseEntity<>("User activated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not activated", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="createNewOwner", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<UserBasicDTO> createNewOwner(@RequestBody UserBasicDTO userBasicDTO) {
        try {
            return userController.registerUser(userBasicDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("findAllGuests")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<UserBasicDTO>> getAllGuests() {
        List<User> users = userService.getAllUsers();

        List<User> guests = users.stream()
                .filter(user -> user.getRole() == Role.GUEST)
                .toList();

        List<UserBasicDTO> guestsBasicDTO = guests.stream()
                .map(UserMapper:: toUserBasicDTO)
                .toList();
        return new ResponseEntity<>(guestsBasicDTO, HttpStatus.OK);
    }

    @GetMapping("generateReport")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> generateReport() {
        try {
            Float soldPrice = realEstateService.sumPricesForSoldRealEstates();
            Float rentedPrice = realEstateService.sumPricesForRentedRealEstates();
            Float totalPrice = Float.valueOf(0);
            if(soldPrice != null) {
                totalPrice += soldPrice;
            }
            if(rentedPrice != null) {
                totalPrice += rentedPrice;
            }
            Map<String, Float> result = new HashMap<>();
            result.put("soldPrice", soldPrice);
            result.put("rentedPrice", rentedPrice);
            result.put("totalPrice", totalPrice);
            return ResponseEntity.ok(result);
        } catch(Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
