package io.github.kassymbekoff.springjwt.controllers;

import io.github.kassymbekoff.springjwt.dto.request.SignInRequest;
import io.github.kassymbekoff.springjwt.dto.request.SignUpRequest;
import io.github.kassymbekoff.springjwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        return authService.signUp(signUpRequest);
    }
}
