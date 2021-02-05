package com.zup.loto.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApostaResponseDTO {
	
	private String email;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	private List<Integer> numeros;
	

}
