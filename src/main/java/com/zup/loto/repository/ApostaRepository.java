package com.zup.loto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.loto.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

	List<Aposta> findByEmailOrderByDataHora(String email);
}
