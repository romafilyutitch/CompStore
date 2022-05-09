package com.bsac.CompStore.service;

import com.bsac.CompStore.model.User;
import com.bsac.CompStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            User foundUser = optionalUser.get();
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            SimpleGrantedAuthority roleAuthority = new SimpleGrantedAuthority(foundUser.getUserRole().name());
            grantedAuthorityList.add(roleAuthority);
            return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(), grantedAuthorityList);
        }
    }
}
