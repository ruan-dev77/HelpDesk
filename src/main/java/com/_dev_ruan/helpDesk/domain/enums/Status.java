package com._dev_ruan.helpDesk.domain.enums;

public enum Status {

	
	ABERTO(1,"ABERTO"),
	
	ANDAMENTO(2,"ANDAMENTO"),
	
	ENCERRADO(3,"ENCERRADO");
	
	private Integer codigo;
	
	private String descrição;

	


	private Status(Integer codigo, String descrição) {
		this.codigo = codigo;
		this.descrição = descrição;
	}


	public Integer getCodigo() {
		return codigo;
	}

	
	public String getDescrição() {
		return descrição;
	}

	public static Status toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		/*
		 * Esta exceção trata de argumentos ilegais.
		 */
		throw new IllegalArgumentException("Perfil Inválido");
	}	
}
