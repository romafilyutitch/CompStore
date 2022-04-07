package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Role;
import com.bsac.CompStore.model.User;
import com.bsac.CompStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        user.setUserRole(Role.CUSTOMER);
        return userRepository.save(user);
    }

    public User update(int id, User user) {
        User savedUser = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(user.getPassword());
        savedUser.setOrders(user.getOrders());
        return savedUser;
    }
}
