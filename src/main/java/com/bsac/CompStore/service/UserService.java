package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Role;
import com.bsac.CompStore.model.User;
import com.bsac.CompStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User wasn't found by id %d", id));
        }
        return optionalUser.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && username.equals("user")) {
            return new org.springframework.security.core.userdetails.User("user", "$2a$12$thk8YxBDbfk46h3pmegsau8SRc8KARQXMaUWAl6695j85nepw69N6", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(int id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User wasn't found by id %d", id));
        }
        User savedUser = optionalUser.get();
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(user.getPassword());
        savedUser.setOrders(user.getOrders());
        return savedUser;
    }
}
