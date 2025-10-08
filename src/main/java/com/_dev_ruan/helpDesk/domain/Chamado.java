package com._dev_ruan.helpDesk.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com._dev_ruan.helpDesk.domain.dtos.ChamadoDTO;
import com._dev_ruan.helpDesk.domain.enums.Prioridade;
import com._dev_ruan.helpDesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Chamado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer Id;
	
	@ManyToOne
    @JoinColumn(name = "Pessoa_id")
    private Cliente cliente;
	
	@ManyToOne
    @JoinColumn(name = "Tecnico_id")
    private Tecnico tecnico;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected Date dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected Date dataFechamento;
	
	
	protected Prioridade prioridade;
	
	protected Status status;
	
	protected String titulo;
	
	protected String observacoes;
	
	

	public Chamado() {
		super();
	}

	public Chamado(Integer id, Date dataAbertura, Date dataFechamento, Prioridade prioridade, Status status,
			String titulo, String observacoes) {
		super();
		Id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
	}
	
	public static Chamado fromDTO(ChamadoDTO dto) {
        Chamado obj = new Chamado(
                dto.getId(),
                dto.getDataAbertura(),
                dto.getDataFechamento(),
                dto.getPrioridade(),
                dto.getStatus(),
                dto.getTitulo(),
                dto.getObservacoes()
        );
        return obj;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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
	
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
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

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		return Objects.equals(Id, other.Id);
	}
	
	
}
