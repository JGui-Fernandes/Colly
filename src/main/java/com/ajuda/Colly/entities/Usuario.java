package com.ajuda.Colly.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
    private int idade;
    private String cidade;

    public Usuario() {
    	
    }
    
    public Long getId() {
		return id;
	}
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    public void setIdade(int idade) {
		this.idade = idade;
	}
    
    public int getIdade() {
		return idade;
	}
    
    public void setCidade(String cidade) {
		this.cidade = cidade;
	}
    
    public String getCidade() {
		return cidade;
	}
}
