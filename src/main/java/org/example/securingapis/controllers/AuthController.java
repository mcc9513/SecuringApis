package org.example.securingapis.controllers;



import org.example.securingapis.dto.CredentialsDto;
import org.example.securingapis.dto.RegistrationDto;
import org.example.securingapis.entities.User;
import org.example.securingapis.config.UserAuthProvider;
import org.example.securingapis.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody CredentialsDto credentials) {
        User user = userService.login(credentials);

        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegistrationDto registration) {
        User user = userService.register(registration);

        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }
}