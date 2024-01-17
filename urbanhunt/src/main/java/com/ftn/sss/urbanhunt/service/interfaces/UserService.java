package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import com.ftn.sss.urbanhunt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    User registerUser(UserBasicDTO userBasicDTO);
    User getUserById(Long id);
    int deactivateUser(Long id);
    int activateUser(Long id);

    Optional<User> findUserByUsername(String username);
}
