package com.bbl.crud;

import com.bbl.crud.controller.UserController;
import com.bbl.crud.model.UserModel;
import com.bbl.crud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private UserModel user;

	@BeforeEach
	public void setup() {
		UserModel user = new UserModel(
				1,
				"John Doe",
				"johndoe",  // username
				"john.doe@example.com",  // email
				"123-456-7890",  // phone
				"johndoe.com",  // website
				new UserModel.Address(
						"123 Main St",  // street
						"Apt 101",  // suite
						"Some City",  // city
						"12345",  // zipcode
						new UserModel.Geo("37.7749", "-122.4194")  // geo coordinates
				),
				new UserModel.Company(
						"Doe Inc.",  // company name
						"Innovative Solutions",  // catchphrase
						"Building a better tomorrow"  // bs (business strategy)
				)
		);

	}

	@Test
	public void testCreateUser() throws Exception {
		when(userService.saveUser(Mockito.any(UserModel.class))).thenReturn(user);

		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/users")
						.contentType("application/json")
						.content("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("John Doe")))
				.andExpect(jsonPath("$.email", is("john.doe@example.com")));
	}

	@Test
	public void testGetAllUsers() throws Exception {
		List<UserModel> users = Arrays.asList(user);
		when(userService.getAllUsers()).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is("John Doe")));
	}

	@Test
	public void testGetUserById_Success() throws Exception {
		when(userService.getUserById(1)).thenReturn(Optional.of(user));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("John Doe")))
				.andExpect(jsonPath("$.email", is("john.doe@example.com")));
	}

	@Test
	public void testGetUserById_NotFound() throws Exception {
		when(userService.getUserById(1)).thenReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateUser() throws Exception {
		UserModel updatedUser = new UserModel(1, "John Smith", "john.smith@example.com");
		when(userService.updateUser(1, updatedUser)).thenReturn(updatedUser);

		mockMvc.perform(MockMvcRequestBuilders
						.put("/api/users/1")
						.contentType("application/json")
						.content("{\"name\": \"John Smith\", \"email\": \"john.smith@example.com\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("John Smith")))
				.andExpect(jsonPath("$.email", is("john.smith@example.com")));
	}

	@Test
	public void testUpdateUser_NotFound() throws Exception {
		UserModel updatedUser = new UserModel(1, "John Smith", "john.smith@example.com");
		when(userService.updateUser(1, updatedUser)).thenThrow(new RuntimeException("User not found"));

		mockMvc.perform(MockMvcRequestBuilders
						.put("/api/users/1")
						.contentType("application/json")
						.content("{\"name\": \"John Smith\", \"email\": \"john.smith@example.com\"}"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
				.andExpect(status().isNoContent());
	}
}
