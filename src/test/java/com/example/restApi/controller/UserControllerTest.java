package com.example.restApi.controller;

import com.example.restApi.entities.User;
import com.example.restApi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllUsers() throws Exception {
        User user1 = new User(1L, "alex", "alex.al@example.com");
        User user2 = new User(2L, "jean", "jean.je@example.com");
        List<User> users = Arrays.asList(user1, user2);

        given(userService.getAllUsers()).willReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("alex")))
                .andExpect(jsonPath("$[0].email", is("alex.al@example.com")))
                .andExpect(jsonPath("$[1].name", is("jean")))
                .andExpect(jsonPath("$[1].email", is("jean.je@example.com")));
    }

    @Test
    public void shouldAddUser() throws Exception {
        User user = new User(1L, "alex", "alex.al@example.com");

        given(userService.createUser(any(User.class))).willReturn(user);

        mockMvc.perform(post("/users/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("alex")))
                .andExpect(jsonPath("$.email", is("alex.al@example.com")));
    }
}