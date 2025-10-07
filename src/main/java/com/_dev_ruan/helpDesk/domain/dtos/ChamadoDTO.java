package com._dev_ruan.helpDesk.domain.dtos;

import java.io.Serializable;
import java.util.Date;

import com._dev_ruan.helpDesk.domain.Chamado;
import com._dev_ruan.helpDesk.domain.enums.Prioridade;
import com._dev_ruan.helpDesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer cliente;   
    private String nomeCliente;
    private Integer tecnico;   
    private String nomeTecnico;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataFechamento;

    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.cliente = obj.getCliente() != null ? obj.getCliente().getId() : null;
        this.nomeCliente = obj.getCliente() != null ? obj.getCliente().getNome() : null;
        this.tecnico = obj.getTecnico() != null ? obj.getTecnico().getId() : null;
        this.nomeTecnico = obj.getTecnico() != null ? obj.getTecnico().getNome() : null;
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamentno();
        this.prioridade = obj.getPrioridade();
        this.status = obj.getStatus();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCliente() {
        return cliente;
    }
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public Integer getTecnico() {
        return tecnico;
    }
    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }
    public String getNomeTecnico() {
        return nomeTecnico;
    }
    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
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
}
