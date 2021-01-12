package br.com.faketools.service;

import java.util.List;

import br.com.faketools.entity.FerramentaEntity;

public interface FerramentaService {

	FerramentaEntity salvar(FerramentaEntity ferramenta);
	
	List<FerramentaEntity> listar();
	
	FerramentaEntity atualizarQuantidade(Integer idFerramenta);
	
	void verificarSeFerramentaEstaDisponivel(FerramentaEntity ferramentaFake);
	
	FerramentaEntity buscarPorId(Integer id);
}
