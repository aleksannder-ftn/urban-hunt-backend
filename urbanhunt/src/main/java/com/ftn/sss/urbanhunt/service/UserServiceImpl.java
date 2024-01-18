package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.*;
import com.ftn.sss.urbanhunt.model.enums.Role;
import com.ftn.sss.urbanhunt.repository.interfaces.UserRepository;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User registerUser(UserBasicDTO userBasicDTO) {
        User user;

        Role role = userBasicDTO.getRole();

        user = switch (role) {
            case GUEST ->  new Guest();
            case ADMINISTRATOR -> new Administrator();
            case OWNER -> new Owner();
            default -> throw new IllegalStateException("Unexpected value: " + role);
        };

        setUserValues(user, userBasicDTO.getFirstName(), userBasicDTO.getLastName(), userBasicDTO.getUsername(),
                userBasicDTO.getPassword(), userBasicDTO.getEmail(), userBasicDTO.getPhoneNumber(),
                userBasicDTO.getAddress());
        user.setRole(userBasicDTO.getRole());
        user.setActive(true);

        userRepository.save(user);
        return user;
    }

    public void setUserValues(User user, String firstName, String lastName, String username, String password, String email, int phoneNumber, String address) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(Integer.parseInt(String.valueOf(phoneNumber)));
        user.setAddress(address);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public int deactivateUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            userRepository.save(user);
            return 1;
        }

        return 0;
    }

    @Override
    public int activateUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(true);
            userRepository.save(user);
            return 1;
        }

        return 0;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
