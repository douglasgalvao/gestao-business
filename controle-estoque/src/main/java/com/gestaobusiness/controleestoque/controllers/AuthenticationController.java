package com.gestaobusiness.controleestoque.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gestaobusiness.controleestoque.auth.security.TokenService;
import com.gestaobusiness.controleestoque.dtos.AuthenticationDTO;
import com.gestaobusiness.controleestoque.dtos.LoginResponseDTO;
import com.gestaobusiness.controleestoque.dtos.RegisterDTO;
import com.gestaobusiness.controleestoque.models.User;
import com.gestaobusiness.controleestoque.repository.UserRepository;

@RestController
@CrossOrigin
@Order(1)
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestHeader("Authorization") String token) {
        try {
            tokenService.validateToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

    }

}
