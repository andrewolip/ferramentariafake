package br.com.faketools.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.faketools.entity.FerramentaEntity;
import br.com.faketools.repository.FerramentaRepository;
import br.com.faketools.service.FerramentaService;

@Service
public class FerramentaServiceImpl implements FerramentaService {

	@Autowired
	private FerramentaRepository ferramentaRepository;
	
	@Override
	public List<FerramentaEntity> listar() {
		return ferramentaRepository.findAll();
	}

	@Override
	public FerramentaEntity atualizarQuantidade(Integer idFerramenta) {
		Optional<FerramentaEntity> ferramenta = ferramentaRepository.findById(idFerramenta);
		
		if (!ferramenta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta não encontrada.");
		
		ferramenta.get().setQuantidade(ferramenta.get().getQuantidade() -1);
		
		return ferramentaRepository.save(ferramenta.get());
	}
	
	public void verificarSeFerramentaEstaDisponivel(FerramentaEntity ferramentaFake) {
		Optional<FerramentaEntity> ferramenta = ferramentaRepository.findById(ferramentaFake.getId());
		
		if (!ferramenta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta ID não encontrada.");
		
		if (ferramenta.get().getQuantidade() < 1)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ferramenta indisponível.");
	}

	@Override
	public FerramentaEntity buscarPorId(Integer id) {
		Optional<FerramentaEntity> ferramenta = ferramentaRepository.findById(id);
		
		if (ferramenta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ferramenta não encontrada.");
		
		return ferramenta.get();
	}

	@Override
	public FerramentaEntity salvar(FerramentaEntity ferramenta) {
		return ferramentaRepository.save(ferramenta);
	}

}
