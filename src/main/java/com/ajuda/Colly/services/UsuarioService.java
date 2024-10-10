package com.ajuda.Colly.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ajuda.Colly.dto.ApagarUsuarioRequest;
import com.ajuda.Colly.dto.CadastrarUsuarioRequest;
import com.ajuda.Colly.dto.EditarUsuarioRequset;
import com.ajuda.Colly.dto.MensagemResponse;
import com.ajuda.Colly.dto.UsuarioResponse;
import com.ajuda.Colly.entities.Usuario;
import com.ajuda.Colly.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<UsuarioResponse> listarUsuarios() {
		var usuarios = repository.findAll();
		return usuarios.stream().map(UsuarioResponse::new).collect(Collectors.toList());

	}

	public UsuarioResponse cadastrarUsuario(CadastrarUsuarioRequest dados) {
		Usuario u = new Usuario();
		u.setNome(dados.nome());
		u.setIdade(dados.idade());
		u.setCidade(dados.cidade());

		repository.save(u);

		return new UsuarioResponse(u);
	}

	public ResponseEntity apagarUsuario(ApagarUsuarioRequest dados) {
		Optional<Usuario> usuarioOptional = repository.findById(dados.id());

		if (usuarioOptional.isPresent()) {
			Usuario u = usuarioOptional.get();
			repository.delete(u);
			return ResponseEntity.status(204).body(new MensagemResponse("Usuário deletado"));
		} else {
			return ResponseEntity.status(404).body(new MensagemResponse("Usuário não encontrado"));
		}
	}

	public ResponseEntity<MensagemResponse> editarUsuario(Long id, EditarUsuarioRequset dados) {
		Optional<Usuario> usuarioOptional = repository.findById(id);

		if (usuarioOptional.isPresent()) {
			Usuario u = usuarioOptional.get();

			if (dados.nome() != null && !dados.nome().isBlank()) {
				u.setNome(dados.nome());
			}

			if (dados.idade() > 0) {
				u.setIdade(dados.idade());
			}

			if (dados.cidade() != null && !dados.cidade().isBlank()) {
				u.setCidade(dados.cidade());
			}

			repository.save(u);

			return ResponseEntity.status(200).body(new MensagemResponse("Usuário editado com sucesso"));
		} else {
			return ResponseEntity.status(404).body(new MensagemResponse("Usuário não encontrado"));
		}
	}

}
