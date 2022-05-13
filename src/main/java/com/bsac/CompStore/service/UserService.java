package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.OrderStatus;
import com.bsac.CompStore.model.Role;
import com.bsac.CompStore.model.User;
import com.bsac.CompStore.model.UserOrder;
import com.bsac.CompStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User wasn't found by username %s", username));
        }
        return optionalUser.get();
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        user.setUserRole(Role.ROLE_CUSTOMER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public User addOrder(User foundUser, UserOrder userOrder) {
        final List<UserOrder> ordersList = foundUser.getOrders();
        userOrder.setStatus(OrderStatus.NEW);
        ordersList.add(userOrder);
        return this.userRepository.save(foundUser);
    }
}
