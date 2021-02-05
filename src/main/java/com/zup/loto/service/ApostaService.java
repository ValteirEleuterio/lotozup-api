package com.zup.loto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zup.loto.dto.request.ApostaRequestDTO;
import com.zup.loto.dto.response.ApostaResponseDTO;
import com.zup.loto.model.Aposta;
import com.zup.loto.model.ApostaNumero;
import com.zup.loto.repository.ApostaNumeroRepository;
import com.zup.loto.repository.ApostaRepository;
import com.zup.loto.utils.RandomUtil;

@Service
public class ApostaService {
	
	@Autowired
	private ApostaRepository apostaRepository;	
	
	@Autowired
	private ApostaNumeroRepository apostaNumeroRepository;
	
	@Transactional
	public ApostaResponseDTO apostar(ApostaRequestDTO apostaRequestDTO) {
		Aposta aposta = new Aposta(apostaRequestDTO.getEmail());
		aposta = apostaRepository.save(aposta);
		
		List<ApostaNumero> numeros = gerarNumerosAposta();
		
		for(ApostaNumero num : numeros) {
			num.setAposta(aposta);
		}
		
		apostaNumeroRepository.saveAll(numeros);
		
		aposta.setNumeros(numeros);		
		
		return entidadeParaResponseDTO(aposta);
	}
	
	@Transactional
	public List<ApostaResponseDTO> listarApostas(final String email) {
		List<Aposta> apostas = apostaRepository.findByEmailOrderByDataHora(email);
		
		List<ApostaResponseDTO> apostasResponseDTO = new ArrayList<>();	
		
		apostas.forEach(ap -> apostasResponseDTO.add(entidadeParaResponseDTO(ap)));
		
		return apostasResponseDTO;
	}
	
	private List<ApostaNumero> gerarNumerosAposta() {
		RandomUtil gerador = new RandomUtil();
		List<ApostaNumero> numeros = new ArrayList<>();
		
		gerador.gerarNumeros().forEach(n -> {
			ApostaNumero num = new ApostaNumero();
			num.setNumero(n);
			numeros.add(num);
		});
		
		return numeros;
	}
	
	private ApostaResponseDTO entidadeParaResponseDTO(final Aposta aposta) {
		return ApostaResponseDTO.builder()
				.email(aposta.getEmail())
				.dataHora(aposta.getDataHora())
				.numeros(aposta.getNumeros().stream().map(n -> n.getNumero()).collect(Collectors.toList()))
				.build();		
	}	
}
