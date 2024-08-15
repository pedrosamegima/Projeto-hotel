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

import com.HotelTransilvamia.entities.Hospede;
import com.HotelTransilvamia.service.HospedeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hospede")
public class HospedeController {
	private final HospedeService hospedeService;
	@Autowired
	public HospedeController(HospedeService hospedeService) {
		this.hospedeService = hospedeService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza hospede por ID")
	public ResponseEntity<Hospede> getHospedeById(@PathVariable Long id) {
		Hospede hospede = hospedeService.getHospedeById(id);
		if (hospede != null) {
			return ResponseEntity.ok(hospede);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Aprensenta todos os hospedes")
	public ResponseEntity<List<Hospede>> getAllHospede() {
		List<Hospede> hospede = hospedeService.getAllHospede();
		return ResponseEntity.ok(hospede);
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
	@Operation(summary = "Cadastra um hospede")
	public ResponseEntity<Hospede> criarHospede(@RequestBody Hospede hospede) {
		Hospede criarHospede = hospedeService.salvarHospede(hospede);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarHospede);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Altera o hospde")
	public ResponseEntity<Hospede> updateHospede(@PathVariable Long id,@RequestBody Hospede hospede) {
		Hospede updatedHospede = hospedeService.updateHospede(id, hospede);
		if (updatedHospede != null) {
			return ResponseEntity.ok(updatedHospede);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma hospede")
	public ResponseEntity<String> deleteHospede(@PathVariable Long id) {
		boolean deleted = hospedeService.deleteHospede(id);
		if (deleted) {
			return ResponseEntity.ok().body("O hospede foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}

