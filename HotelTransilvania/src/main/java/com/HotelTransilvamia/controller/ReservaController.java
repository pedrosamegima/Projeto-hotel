package com.HotelTransilvamia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HotelTransilvamia.entities.Reserva;
import com.HotelTransilvamia.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reserva")
public class ReservaController {
	private final ReservaService reservaService;
	@Autowired
	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza consulta por ID")
	public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
		Reserva reserva = reservaService.getReservaById(id);
		if (reserva != null) {
			return ResponseEntity.ok(reserva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Aprensenta todas as reserva")
	public ResponseEntity<List<Reserva>> getAllReserva() {
		List<Reserva> reserva = reservaService.getAllReserva();
		return ResponseEntity.ok(reserva);
	}
	//@Query
	//@GetMapping("/data/{data}")
	//@Operation(summary = "Aprensenta a data da consulta")
	//public ResponseEntity<List<Consulta>> getConsultaPorData(@PathVariable String data){
		//List<Consulta> consultas = consultaService.getConsultaPorData(data);
		//return ResponseEntity.ok(consultas);
	//}
	//@Query
	//@GetMapping("/hora/{hora}")
	//@Operation(summary = "Aprensenta a hora consulta")
	//public List<Consulta> findConsultasPorHora(@PathVariable String hora){
		//return consultaService.findByHora(hora);
	//}

	//@Query
	//@GetMapping("/descricao/{descricao}")
	//@Operation(summary = "Aprensenta a descricao consulta")
	//public List<Consulta> findPorDescricao(@PathVariable String descricao){
		//return consultaService.findByDescricao(descricao);
	//}


	@PostMapping("/")
	@Operation(summary = "Cadastra uma reserva")
	public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
		Reserva criarReserva = reservaService.salvarReserva(reserva);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarReserva);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Altera a reserva")
	public ResponseEntity<Reserva> updateReserva(@PathVariable Long id,@RequestBody Reserva reserva) {
		Reserva updatedReserva = reservaService.updateReserva(id, reserva);
		if (updatedReserva != null) {
			return ResponseEntity.ok(updatedReserva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma reserva")
	public ResponseEntity<String> deleteReserva(@PathVariable Long id) {
		boolean deleted = reservaService.deleteReserva(id);
		if (deleted) {
			return ResponseEntity.ok().body("A reserva foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
