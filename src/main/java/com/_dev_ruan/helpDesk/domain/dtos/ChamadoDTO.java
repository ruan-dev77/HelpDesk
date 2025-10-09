package com._dev_ruan.helpDesk.domain.dtos;

import com._dev_ruan.helpDesk.domain.Chamado;
import com._dev_ruan.helpDesk.domain.enums.Prioridade;
import com._dev_ruan.helpDesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull; // Importa a anotação

import java.io.Serializable;
import java.util.Date;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataAbertura = new Date(System.currentTimeMillis());

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataFechamento;

    @NotNull(message = "O campo PRIORIDADE é requerido")
    private Prioridade prioridade;
    
    @NotNull(message = "O campo STATUS é requerido")
    private Status status;

    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;

    @NotNull(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;

    @NotNull(message = "O campo TECNICO é requerido")
    private Integer tecnico;
    
    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;
    
    private String nomeTecnico;
    private String nomeCliente;
    
   

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = new Date(System.currentTimeMillis());
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade();
        this.status = obj.getStatus();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeCliente = obj.getCliente().getNome();
        this.nomeTecnico = obj.getTecnico().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
