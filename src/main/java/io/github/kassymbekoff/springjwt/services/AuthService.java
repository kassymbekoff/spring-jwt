package io.github.kassymbekoff.springjwt.services;

import io.github.kassymbekoff.springjwt.config.jwt.JwtUtils;
import io.github.kassymbekoff.springjwt.dto.request.SignInRequest;
import io.github.kassymbekoff.springjwt.dto.request.SignUpRequest;
import io.github.kassymbekoff.springjwt.dto.response.JwtResponse;
import io.github.kassymbekoff.springjwt.dto.response.MessageResponse;
import io.github.kassymbekoff.springjwt.models.Role;
import io.github.kassymbekoff.springjwt.models.User;
import io.github.kassymbekoff.springjwt.repositories.RoleRepository;
import io.github.kassymbekoff.springjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse signIn(SignInRequest signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetail.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponse(jwt, userDetail.getId(), userDetail.getUsername(), userDetail.getEmail(), roles);
    }

    public ResponseEntity<?> signUp(SignUpRequest signUpRequest){
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse(""));
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse(""));
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));
        Set<String> reqRoles = signUpRequest.getRoles();
        Set<Role> roles      = new HashSet<>();
        return null;
    }

}
