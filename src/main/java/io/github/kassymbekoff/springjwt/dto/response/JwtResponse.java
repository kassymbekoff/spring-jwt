package io.github.kassymbekoff.springjwt.dto.response;

import io.github.kassymbekoff.springjwt.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String username;
    private String email;
    private List<Role> roles;

    public JwtResponse(String token, int id, String username, String email, List<Role> roles){
        this.token = token;
        this.id    = id;
        this.username = username;
        this.email    = email;
        this.roles    = roles;
    }
}
