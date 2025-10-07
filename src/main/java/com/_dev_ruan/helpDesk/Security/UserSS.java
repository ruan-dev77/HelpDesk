package com._dev_ruan.helpDesk.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com._dev_ruan.helpDesk.domain.enums.Perfil;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Collection;
import java.util.Collections;


public class UserSS implements UserDetails {

    
    private static final long serialVersionUID = 1L;
    // ... id, nome, email, etc.
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Perfil tipo;

    // Métodos da Interface UserDetails

    public UserSS() {
    }

    public UserSS(String username, String password, Perfil tipo) {
        this.email = username;
        this.password = password;
        this.tipo = tipo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // É AQUI QUE A MÁGICA ACONTECE!
        // O Spring Security espera o prefixo "ROLE_" por padrão.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.tipo.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    // Você pode implementar lógicas mais complexas aqui se precisar
    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta não expirou
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As credenciais não expiraram
    }

    @Override
    public boolean isEnabled() {
        return true; // O usuário está habilitado
    }

    // Seus getters e setters ...
}