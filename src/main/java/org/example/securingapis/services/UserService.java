package org.example.securingapis.services;

import org.example.securingapis.dto.CredentialsDto;
import org.example.securingapis.dto.RegistrationDto;
import org.example.securingapis.entities.User;
import org.example.securingapis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(CredentialsDto credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        if (user == null || !user.getPassword().equals(credentials.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user; // userMapper.toUserDto(user);
    }

    public User register(RegistrationDto registration) {
        if (userRepository.findByUsername(registration.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User(registration.getUsername(), registration.getPassword(), registration.getRoles());
        userRepository.addUser(user);
        return user;
    }
}