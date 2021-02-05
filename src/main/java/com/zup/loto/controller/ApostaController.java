package com.zup.loto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zup.loto.dto.request.ApostaRequestDTO;
import com.zup.loto.dto.response.ApostaResponseDTO;
import com.zup.loto.service.ApostaService;

@RestController
@RequestMapping("/apostas")
public class ApostaController {
	
	@Autowired
	private ApostaService apostaService;
	
	@GetMapping
	public ResponseEntity<List<ApostaResponseDTO>> listarApostas(@RequestParam String email) {
		List<ApostaResponseDTO> apostas = apostaService.listarApostas(email);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(apostas);
	}
	
	@PostMapping
	public ResponseEntity<ApostaResponseDTO> apostar(@RequestBody ApostaRequestDTO apostaRequestDTO) {
		ApostaResponseDTO aposta = apostaService.apostar(apostaRequestDTO);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(aposta);
	}

}
