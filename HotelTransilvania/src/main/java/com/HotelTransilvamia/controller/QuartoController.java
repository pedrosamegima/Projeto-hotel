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

import com.HotelTransilvamia.entities.Quarto;
import com.HotelTransilvamia.service.QuartoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/quarto")
public class QuartoController {
	private final QuartoService quartoService;
	@Autowired
	public QuartoController(QuartoService quartoService) {
		this.quartoService = quartoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza consulta por ID")
	public ResponseEntity<Quarto> getQuartoById(@PathVariable Long id) {
		Quarto quarto = quartoService.getQuartoById(id);
		if (quarto != null) {
			return ResponseEntity.ok(quarto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Aprensenta todos os quartos")
	public ResponseEntity<List<Quarto>> getAllQuarto() {
		List<Quarto> quarto = quartoService.getAllQuarto();
		return ResponseEntity.ok(quarto);
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
	@Operation(summary = "Cadastra uma quarto")
	public ResponseEntity<Quarto> criarQuarto(@RequestBody Quarto quarto) {
		Quarto criarQuarto = quartoService.salvarQuarto(quarto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarQuarto);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Altera o quarto")
	public ResponseEntity<Quarto> updateQuarto(@PathVariable Long id,@RequestBody Quarto quarto) {
		Quarto updatedQuarto = quartoService.updateQuarto(id, quarto);
		if (updatedQuarto != null) {
			return ResponseEntity.ok(updatedQuarto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um quarto")
	public ResponseEntity<String> deleteQuarto(@PathVariable Long id) {
		boolean deleted = quartoService.deleteQuarto(id);
		if (deleted) {
			return ResponseEntity.ok().body("O quarto foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}
