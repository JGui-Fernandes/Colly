package com.ajuda.Colly.dto;

import com.ajuda.Colly.entities.Usuario;

public record UsuarioResponse(
		String nome,
		int idade) {
	
	public UsuarioResponse(Usuario u) {
		this(u.getNome(), u.getIdade());
	}

}
