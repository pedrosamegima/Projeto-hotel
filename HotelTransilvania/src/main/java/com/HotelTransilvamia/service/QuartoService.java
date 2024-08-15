package com.HotelTransilvamia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.HotelTransilvamia.entities.Quarto;
import com.HotelTransilvamia.repository.QuartoRepository;


@Service
public class QuartoService {
	private final QuartoRepository quartoRepository;
	
	@Autowired
	public QuartoService(QuartoRepository quartoRepository) {
		this.quartoRepository = quartoRepository;
	}

	public List<Quarto> getAllQuarto() {
		return quartoRepository.findAll();
	}

	public Quarto getQuartoById(Long id) {
		Optional<Quarto> Quarto = quartoRepository.findById(id);
		return Quarto.orElse(null);
	}
	//Query Method
	//public List<Consulta> getConsultaPorData(String data){
		//return consultaRepository.findByData(data);
	//}
	//@Query
	//public List<Consulta> findByHora(String hora){
		//return consultaRepository.findByHora(hora);
	//}
	//@Query
	//public List<Consulta> findByDescricao(String descricao){
		//return consultaRepository.findByHora(descricao);
	//}

	public Quarto salvarQuarto(Quarto quarto) {
		return quartoRepository.save(quarto);
	}

	public Quarto updateQuarto(Long id, Quarto updatedQuarto) {
		Optional<Quarto> existingQuarto = quartoRepository.findById(id);
		if (existingQuarto.isPresent()) {
			updatedQuarto.setId(id);
			return quartoRepository.save(updatedQuarto);
		}
		return null;
	}

	public boolean deleteQuarto(Long id) {
		Optional<Quarto> existingQuarto = quartoRepository.findById(id);
		if (existingQuarto.isPresent()) {
			quartoRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

