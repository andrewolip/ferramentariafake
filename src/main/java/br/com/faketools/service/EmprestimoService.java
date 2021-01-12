package br.com.faketools.service;

import java.util.List;

import br.com.faketools.entity.EmprestimoEntity;

public interface EmprestimoService {

	EmprestimoEntity emprestarFerramenta(EmprestimoEntity emprestimoEntity);
	
	EmprestimoEntity realizarEntrega(Integer idEmprestimo);
	
	EmprestimoEntity buscarPorIdFuncionario(Integer id);
	
	List<EmprestimoEntity> listar();
	
	void remover(Integer idEmprestimo);
	
	EmprestimoEntity buscarPorId(Integer id);
	
	EmprestimoEntity devolverFerramenta(EmprestimoEntity emprestimo);
}
