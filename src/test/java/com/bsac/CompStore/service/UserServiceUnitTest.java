package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Role;
import com.bsac.CompStore.model.User;
import com.bsac.CompStore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceUnitTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user1 = new User();
    private User user2 = new User();

    @BeforeEach
    public void setUp() {
        user1.setId(1);
        user1.setUsername("username");
        user1.setPassword("password");
        user1.setUserRole(Role.CUSTOMER);
        user1.setEmail("email@email.ru");

        user2.setId(2);
        user2.setUsername("username");
        user2.setPassword("password");
        user2.setUserRole(Role.ADMIN);
        user2.setEmail("email@email.com");
    }

    @Test
    public void findAll_shouldReturnAllRecords() {
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> allUsers = userService.findAll();

        assertEquals(userList, allUsers);
    }

    @Test
    public void findById_shouldReturnRecordWithId() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        User foundUser = userService.findById(user1.getId());

        assertEquals(user1,foundUser);
    }

    @Test
    public void findById_shouldThrowException_whenThereIsNoId() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(user1.getId()));
    }

    @Test
    public void save_shouldReturnSavedId() {
        when(userRepository.save(user1)).thenReturn(user1);

        User savedUser = userService.save(user1);

        assertEquals(user1, savedUser);
    }

    @Test
    public void update_shouldReturnUpdatedUser() {
        user1.setUsername("username");
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        User updatedUser = userService.update(user1.getId(), user1);

        assertEquals(user1, updatedUser);
        assertEquals("username", updatedUser.getUsername());
    }
}
