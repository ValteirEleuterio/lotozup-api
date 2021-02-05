package com.zup.loto.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Aposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private LocalDateTime dataHora;
	
	@OneToMany(mappedBy = "aposta")
	private List<ApostaNumero> numeros;
	
	public Aposta() {}
	
	public Aposta(final String email) {
		this.email = email;
		this.dataHora = LocalDateTime.now();
	}

}
