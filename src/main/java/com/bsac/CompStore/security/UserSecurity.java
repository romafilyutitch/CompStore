package com.bsac.CompStore.security;

import com.bsac.CompStore.model.User;
import com.bsac.CompStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserSecurity {

    private final UserRepository userRepository;

    @Autowired
    public UserSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean hasUserId(Authentication authentication, int id) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            Optional<User> optionalUser = this.userRepository.findByUsername(userDetails.getUsername());
            return optionalUser.isPresent() && optionalUser.get().getId() == id;
        }
        return false;
    }
}
