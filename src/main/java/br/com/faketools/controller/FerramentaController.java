package br.com.faketools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.faketools.service.FerramentaService;

@RestController
@RequestMapping("/ferramentas")
public class FerramentaController {

	@Autowired
	private FerramentaService ferramentaService;
	
	@GetMapping
	public ResponseEntity<?> lista() {			
		return ResponseEntity.ok(ferramentaService.listar());
	}
}
