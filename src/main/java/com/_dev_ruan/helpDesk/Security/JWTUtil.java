package com._dev_ruan.helpDesk.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Utilitário atualizado para operações com JWT usando JJWT 0.11.x
 */
@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret; // Pode ser um Base64 seguro

    /**
     * Gera um token JWT para o e-mail do usuário.
     */
    @SuppressWarnings("deprecation")
	public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                   .setSubject(email)
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(SignatureAlgorithm.HS256 ,secret.getBytes())
                   .compact();
    }

    /**
     * Valida se o token é válido.
     */
    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims == null) return false;

        String username = claims.getSubject();
        Date expirationDate = claims.getExpiration();
        Date now = new Date();

        return username != null && expirationDate != null && now.before(expirationDate);
    }

    /**
     * Extrai o username (subject) do token.
     */
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * Obtém as claims do token.
     */
    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                       .setSigningKey(secret.getBytes())
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    
}
