package com._dev_ruan.helpDesk.domain.enums;

public enum Prioridade {
	
	BAIXA(1,"PRIO_BAIXA"),
	
	MEDIA(2,"PRIO_MEIDA"),
	
	ALTA(3, "PRIO_ALTA");
	
	private Integer codigo;
	
	private String descricao;

	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}


	public String getDescricao() {
		return descricao;
	}

	
	public static Prioridade toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
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
