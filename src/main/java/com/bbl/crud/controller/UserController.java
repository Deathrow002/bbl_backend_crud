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
        // Create UserModel instance, setting the attributes based on the UserDTO
        UserModel user = new UserModel();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setWebsite(userDTO.getWebsite());

        // Handle Address mapping
        if (userDTO.getAddress() != null) {
            UserModel.Address address = new UserModel.Address();
            address.setStreet(userDTO.getAddress().getStreet());
            address.setSuite(userDTO.getAddress().getSuite());
            address.setCity(userDTO.getAddress().getCity());
            address.setZipcode(userDTO.getAddress().getZipcode());

            if (userDTO.getAddress().getGeo() != null) {
                UserModel.Geo geo = new UserModel.Geo();
                geo.setLat(userDTO.getAddress().getGeo().getLat());
                geo.setLng(userDTO.getAddress().getGeo().getLng());
                address.setGeo(geo);
            }
            user.setAddress(address);
        }

        // Handle Company mapping
        if (userDTO.getCompany() != null) {
            UserModel.Company company = new UserModel.Company();
            company.setNameCompany(userDTO.getCompany().getNameCompany());
            company.setCatchPhrase(userDTO.getCompany().getCatchPhrase());
            company.setBs(userDTO.getCompany().getBs());
            user.setCompany(company);
        }

        // Save User
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
            // Fetch user from DB and set updated fields
            Optional<UserModel> existingUserOpt = userService.getUserById(id);
            if (existingUserOpt.isPresent()) {
                UserModel existingUser = existingUserOpt.get();

                // Update attributes based on the provided UserDTO
                existingUser.setName(userDetails.getName());
                existingUser.setUsername(userDetails.getUsername());
                existingUser.setEmail(userDetails.getEmail());
                existingUser.setPhone(userDetails.getPhone());
                existingUser.setWebsite(userDetails.getWebsite());

                // Update Address
                if (userDetails.getAddress() != null) {
                    UserModel.Address address = existingUser.getAddress();
                    address.setStreet(userDetails.getAddress().getStreet());
                    address.setSuite(userDetails.getAddress().getSuite());
                    address.setCity(userDetails.getAddress().getCity());
                    address.setZipcode(userDetails.getAddress().getZipcode());

                    if (userDetails.getAddress().getGeo() != null) {
                        UserModel.Geo geo = address.getGeo();
                        geo.setLat(userDetails.getAddress().getGeo().getLat());
                        geo.setLng(userDetails.getAddress().getGeo().getLng());
                    }
                }

                // Update Company
                if (userDetails.getCompany() != null) {
                    UserModel.Company company = existingUser.getCompany();
                    company.setNameCompany(userDetails.getCompany().getNameCompany());
                    company.setCatchPhrase(userDetails.getCompany().getCatchPhrase());
                    company.setBs(userDetails.getCompany().getBs());
                }

                // Save updated user
                UserModel updatedUser = userService.updateUser(id, existingUser);
                return ResponseEntity.ok(new UserDTO(updatedUser));
            } else {
                return ResponseEntity.notFound().build();
            }
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
