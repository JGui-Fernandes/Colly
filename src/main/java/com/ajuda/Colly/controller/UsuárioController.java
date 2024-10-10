package com.ajuda.Colly.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajuda.Colly.dto.ApagarUsuarioRequest;
import com.ajuda.Colly.dto.CadastrarUsuarioRequest;
import com.ajuda.Colly.dto.EditarUsuarioRequset;
import com.ajuda.Colly.dto.UsuarioResponse;
import com.ajuda.Colly.entities.Usuario;
import com.ajuda.Colly.services.UsuarioService;

@RequestMapping("/usuario")
@RestController
public class UsuárioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponse>> listaUsuarios() {
		var response = usuarioService.listarUsuarios();
		return ResponseEntity.status(200).body(response);
	}
	
	@PostMapping("/criar")
	public ResponseEntity<UsuarioResponse> criarUsuario(@PathVariable Long id, @RequestBody CadastrarUsuarioRequest dados) {
		
		var response = usuarioService.cadastrarUsuario(dados);
		
		return ResponseEntity.status(201).body(response);
	}
	
	@DeleteMapping("/apagar")
	public ResponseEntity apagarUsuario(@RequestBody ApagarUsuarioRequest dados){
		var response = usuarioService.apagarUsuario(dados);
		return response;
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity editarUsuairo(@PathVariable Long id, @RequestBody EditarUsuarioRequset dados) {
		var response = usuarioService.editarUsuario(id, dados);
		return response;
	}
}
