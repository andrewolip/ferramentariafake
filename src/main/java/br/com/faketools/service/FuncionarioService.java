package br.com.faketools.service;

import java.util.List;

import br.com.faketools.entity.FuncionarioEntity;

public interface FuncionarioService {

	FuncionarioEntity salvar(FuncionarioEntity funcionario);
	
	List<FuncionarioEntity> listar();
	
	void remover(Integer id);
	
}
