package com.bbl.crud;

import com.bbl.crud.controller.UserController;
import com.bbl.crud.model.UserModel;
import com.bbl.crud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private UserModel userModel;

    @BeforeEach
    void setUp() {
        // Initialize MockMvc and inject controller dependencies
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Sample Geo object
        UserModel.Geo geo = new UserModel.Geo("12.34", "56.78");  // Assuming Geo has latitude and longitude fields

        // Sample Address object
        UserModel.Address address = new UserModel.Address("123 Main St", "Suite 1", "Some City", "12345", geo);

        // Sample Company object
        UserModel.Company company = new UserModel.Company("Company ABC", "We make things happen", "Technology solutions");

        // Sample UserModel
        userModel = new UserModel("John Doe", "john_doe", "john@example.com", "123-456-7890", "johndoe.com", address, company);
    }


    @Test
    void testCreateUser() throws Exception {
        when(userService.saveUser(any(UserModel.class))).thenReturn(userModel);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"name\":\"John Doe\",\"username\":\"john_doe\",\"email\":\"john@example.com\",\"phone\":\"123-456-7890\",\"website\":\"johndoe.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.username").value("john_doe"));

        verify(userService, times(1)).saveUser(any(UserModel.class));
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(userModel));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].username").value("john_doe"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1)).thenReturn(Optional.of(userModel));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.username").value("john_doe"));

        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        when(userService.getUserById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void testUpdateUser() throws Exception {
        // Ensure the getUserById method returns a non-empty Optional with the mock user model
        when(userService.getUserById(1)).thenReturn(Optional.of(userModel));

        // Mock updateUser to return the updated userModel
        when(userService.updateUser(eq(1), any(UserModel.class))).thenReturn(userModel);

        mockMvc.perform(put("/api/users/1")
                        .contentType("application/json")
                        .content("{\"name\":\"John Doe Updated\",\"username\":\"john_doe_updated\",\"email\":\"john@example.com\",\"phone\":\"123-456-7890\",\"website\":\"johndoe.com\"}"))
                .andExpect(status().isOk())  // Expect status 200 OK
                .andExpect(jsonPath("$.name").value("John Doe Updated"))
                .andExpect(jsonPath("$.username").value("john_doe_updated"));

        // Verify that updateUser was called once with the correct parameters
        verify(userService, times(1)).updateUser(eq(1), any(UserModel.class));
        // Also verify that getUserById was called once
        verify(userService, times(1)).getUserById(1);
    }


    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(1);
    }
}
