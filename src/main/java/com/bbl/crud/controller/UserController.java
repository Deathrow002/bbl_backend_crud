package com.bbl.crud.controller;

import com.bbl.crud.model.UserDTO;
import com.bbl.crud.model.UserModel;
import com.bbl.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserModel user = new UserModel(userDTO.getId(), userDTO.getName(), userDTO.getUsername(),
                userDTO.getEmail(), userDTO.getPhone(), userDTO.getWebsite(),
                userDTO.getAddress() != null ? new UserModel.Address(userDTO.getAddress().getStreet(),
                        userDTO.getAddress().getSuite(), userDTO.getAddress().getCity(), userDTO.getAddress().getZipcode(),
                        new UserModel.Geo(userDTO.getAddress().getGeo().getLat(), userDTO.getAddress().getGeo().getLng())) : null,
                userDTO.getCompany() != null ? new UserModel.Company(userDTO.getCompany().getNameCompany(),
                        userDTO.getCompany().getCatchPhrase(), userDTO.getCompany().getBs()) : null);

        UserModel savedUser = userService.saveUser(user);
        return ResponseEntity.ok(new UserDTO(savedUser));
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        Optional<UserModel> user = userService.getUserById(id);
        return user.map(u -> ResponseEntity.ok(new UserDTO(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDetails) {
        try {
            UserModel updatedUser = userService.updateUser(id, new UserModel(userDetails.getId(), userDetails.getName(),
                    userDetails.getUsername(), userDetails.getEmail(), userDetails.getPhone(), userDetails.getWebsite(),
                    userDetails.getAddress() != null ? new UserModel.Address(userDetails.getAddress().getStreet(),
                            userDetails.getAddress().getSuite(), userDetails.getAddress().getCity(),
                            userDetails.getAddress().getZipcode(), new UserModel.Geo(userDetails.getAddress().getGeo().getLat(),
                            userDetails.getAddress().getGeo().getLng())) : null,
                    userDetails.getCompany() != null ? new UserModel.Company(userDetails.getCompany().getNameCompany(),
                            userDetails.getCompany().getCatchPhrase(), userDetails.getCompany().getBs()) : null));
            return ResponseEntity.ok(new UserDTO(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
