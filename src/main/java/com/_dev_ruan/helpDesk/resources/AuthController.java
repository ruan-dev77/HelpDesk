package com._dev_ruan.helpDesk.resources;



import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._dev_ruan.helpDesk.Security.JWTUtil;
import com._dev_ruan.helpDesk.domain.dtos.CredenciaisDTO;
import com._dev_ruan.helpDesk.domain.dtos.TokenDTO;


@RestController
@RequestMapping("/auth")

public class AuthController {

    private  AuthenticationManager authenticationManager;
    private  JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredenciaisDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            String token = jwtUtil.generateToken(authentication.getName());
            return ResponseEntity.ok(new TokenDTO(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("{\"error\":\"Credenciais inválidas\"}");
        }
    }
}


