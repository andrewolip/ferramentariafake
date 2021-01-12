package br.com.faketools.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.faketools.entity.FuncionarioEntity;
import br.com.faketools.repository.FuncionarioRepository;
import br.com.faketools.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public FuncionarioEntity salvar(FuncionarioEntity funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	@Override
	public List<FuncionarioEntity> listar() {
		return funcionarioRepository.findAll();
	}

	@Override
	public void remover(Integer id) {
		funcionarioRepository.deleteById(id);
	}

}
