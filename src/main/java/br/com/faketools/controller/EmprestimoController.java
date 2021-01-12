package br.com.faketools.controller;

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

import br.com.faketools.entity.EmprestimoEntity;
import br.com.faketools.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

	@Autowired
	private EmprestimoService emprestimoService;
	
	@GetMapping("/{idEmprestimo}")
	public ResponseEntity<?> buscarPorIdEmprestimo(@PathVariable Integer idEmprestimo) {
		return ResponseEntity.ok(emprestimoService.buscarPorId(idEmprestimo));
	}
	
	@PostMapping
	public ResponseEntity<?> emprestarFerramenta(@RequestBody EmprestimoEntity emprestimo) {
		return new ResponseEntity<>(emprestimoService.emprestarFerramenta(emprestimo), HttpStatus.CREATED);
	}
	
	@PutMapping("/devolverFerramenta")
	public ResponseEntity<?> devolver(@RequestBody EmprestimoEntity emprestimo) {
		return new ResponseEntity<>(emprestimoService.devolverFerramenta(emprestimo), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(emprestimoService.listar());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Integer id) {
		this.emprestimoService.remover(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}	
	
}
