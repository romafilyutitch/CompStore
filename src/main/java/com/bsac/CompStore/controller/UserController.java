package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ErrorDetails;
import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.User;
import com.bsac.CompStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("/me")
    public User findMe(Authentication authentication) {
        System.out.println("find me");
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return this.userService.findByUsername(userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @PostMapping
    public User save(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable int id, @Valid @RequestBody User user) {
        return userService.update(id, user);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleError(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
