package com.zup.loto.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
	private Random gerador;
	
	public RandomUtil() {
		this.gerador = new Random();
	}
	
	public List<Integer> gerarNumeros() {
		List<Integer> numeros = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			int num = getProximoNumero(numeros);
			numeros.add(num);
		}
		
		return numeros;
	}
	
	private int getProximoNumero(List<Integer> numeros) {
		int num = gerador.nextInt(60) + 1;
		
		if (numeros.contains(num))
			return getProximoNumero(numeros);
		
		return num;
	}
	

}
