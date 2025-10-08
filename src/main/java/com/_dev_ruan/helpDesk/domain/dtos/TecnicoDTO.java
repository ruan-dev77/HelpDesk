package com._dev_ruan.helpDesk.domain.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com._dev_ruan.helpDesk.domain.Tecnico;
import com._dev_ruan.helpDesk.domain.enums.Perfil;

import jakarta.validation.constraints.NotNull;

public class TecnicoDTO {

    protected Integer id;
    
    @NotNull(message = "O campo nome é requerido")
    protected String nome;
    
    @NotNull(message = "O campo CPF é requerido")
    protected String cpf;
    
    @NotNull(message = "O campo email é requerido")
    protected String email;
    
    @NotNull(message = "O campo senha é requerido")
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();
    
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO() {
        addPerfil(Perfil.TECNICO);
    }

    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.TECNICO);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
