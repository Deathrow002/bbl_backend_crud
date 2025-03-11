package com.bbl.crud.service;

import com.bbl.crud.model.UserModel;
import com.bbl.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or Update User
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    // Get All Users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By ID
    public Optional<UserModel> getUserById(int id) {
        return userRepository.findById(id);
    }

    // Update User
    public UserModel updateUser(int id, UserModel userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setUsername(userDetails.getUsername());
                    user.setEmail(userDetails.getEmail());
                    user.setPhone(userDetails.getPhone());
                    user.setWebsite(userDetails.getWebsite());
                    user.setAddress(userDetails.getAddress());
                    user.setCompany(userDetails.getCompany());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Delete User
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
