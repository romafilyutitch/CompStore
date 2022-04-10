package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Role;
import com.bsac.CompStore.model.User;
import com.bsac.CompStore.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User user1 = new User();
    private User user2 = new User();
    private User user3 = new User();

    @BeforeEach
    public void setUp() {
        user1.setId(1);
        user1.setUserRole(Role.CUSTOMER);
        user1.setUsername("user");
        user1.setPassword("password");
        user1.setEmail("email@email.com");

        user2.setId(2);
        user2.setUserRole(Role.ADMIN);
        user2.setUsername("admin");
        user2.setPassword("admin");
        user2.setEmail("admin@admin.com");

        user3.setId(3);
        user3.setUserRole(Role.CUSTOMER);
        user3.setUsername("customer");
        user3.setPassword("customer");
        user3.setEmail("customer@cusomer.com");
    }

    @Test
    public void findAll_shouldFindAllRecords() throws Exception {
        List<User> userList = Arrays.asList(user1, user2, user3);

        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].password", is(user2.getPassword())));
    }

    @Test
    public void findById_shouldFindRecordWithId() throws Exception {
        when(userService.findById(user1.getId())).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(user1.getId())));
    }

    @Test
    public void findById_shouldReturnNotFound_IfThereIsNoId() throws Exception {
        when(userService.findById(user1.getId())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void save_shouldSaveRecord() throws Exception {
        when(userService.save(user1)).thenReturn(user1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(user1.getId())));
    }

    @Test
    public void update_shouldUpdateRecord() throws Exception {
        user1.setPassword("newPassword");
        when(userService.update(user1.getId(), user1)).thenReturn(user1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/users/" + user1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(user1.getId())))
                .andExpect(jsonPath("$.password", is(user1.getPassword())));
    }

    @Test
    public void delete_shouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + user1.getId()))
                .andExpect(status().isNoContent());
    }
}
