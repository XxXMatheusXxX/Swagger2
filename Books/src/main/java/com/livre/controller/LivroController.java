package com.livre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livre.entities.Livro;
import com.livre.services.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag( name = "Livro", description = "API REST DE GERENCIAMENTO DE LIVROS")
@RestController
@RequestMapping("/livro")
public class LivroController {

	private final LivroService LivroService;

	@Autowired
	public LivroController(LivroService LivroService) {
		this.LivroService = LivroService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza por id")
	public ResponseEntity<Livro> buscaLivroControlId(@PathVariable Long id) {
		Livro Livro = LivroService.getLivroById(id);
		if (Livro != null) {
			return ResponseEntity.ok(Livro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	@Operation (summary = "Apresenta todos os livros")
	public ResponseEntity<List<Livro>> buscaTodasLigacoesControl() {
		List<Livro> Livro = LivroService.getAllLivros();
		return ResponseEntity.ok(Livro);
	}

	@PostMapping("/")
	@Operation (summary = "Cadastro Usuário")
	public ResponseEntity<Livro> saveLivroControl(@RequestBody @Valid Livro Livro) {
		Livro saveLivro = LivroService.saveLivro(Livro);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveLivro);
	}

	@PutMapping("/{id}")
	@Operation (summary ="Altera um user")
	public ResponseEntity<Livro> alteraLivroControl(@PathVariable Long id, @RequestBody @Valid Livro Livro) {
		Livro alteraLivro = LivroService.changeLivro(id, Livro);

		if (alteraLivro != null) {
			return ResponseEntity.ok(Livro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um user")
	public ResponseEntity<String> deleteLivroControl(@PathVariable Long id) {
		boolean delete = LivroService.deleteLivro(id);
		if (delete) {
			return ResponseEntity.ok().body("O livro foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
