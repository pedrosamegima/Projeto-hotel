package com.HotelTransilvamia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HotelTransilvamia.entities.Reserva;
import com.HotelTransilvamia.repository.ReservaRepository;

@Service
public class ReservaService {
	private final ReservaRepository reservaRepository;
	
	@Autowired
	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}

	public List<Reserva> getAllReserva() {
		return reservaRepository.findAll();
	}

	public Reserva getReservaById(Long id) {
		Optional<Reserva> Reserva = reservaRepository.findById(id);
		return Reserva.orElse(null);
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

	public Reserva salvarReserva(Reserva reserva) {
		return reservaRepository.save(reserva);
	}

	public Reserva updateReserva(Long id, Reserva updatedReserva) {
		Optional<Reserva> existingReserva = reservaRepository.findById(id);
		if (existingReserva.isPresent()) {
			updatedReserva.setId(id);
			return reservaRepository.save(updatedReserva);
		}
		return null;
	}

	public boolean deleteReserva(Long id) {
		Optional<Reserva> existingReserva = reservaRepository.findById(id);
		if (existingReserva.isPresent()) {
			reservaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
