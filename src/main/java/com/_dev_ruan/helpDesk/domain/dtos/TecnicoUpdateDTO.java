package com._dev_ruan.helpDesk.domain.dtos;

import java.io.Serializable;

	public class TecnicoUpdateDTO implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    
	    private String nome;
	    private String cpf;
	    private String email;
	    

	    public TecnicoUpdateDTO() {
	        super();
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

	   
	}




