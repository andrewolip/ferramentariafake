package br.com.faketools.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.faketools.entity.EmprestimoEntity;
import br.com.faketools.entity.FerramentaEntity;
import br.com.faketools.repository.EmprestimoRepository;
import br.com.faketools.service.EmprestimoService;
import br.com.faketools.service.FerramentaService;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private FerramentaService ferramentaService;
	
	private Date calcularDataEntrega() {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(new Date());
		
		calendar.add(Calendar.DATE, 3);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

	@Override
	public EmprestimoEntity emprestarFerramenta(EmprestimoEntity emprestimoEntity) {
		Optional<EmprestimoEntity> emprestimosPendentes = emprestimoRepository.buscarEmprestimosPendentesByIdFuncionario(emprestimoEntity.getFuncionario().getId());
				
		if (emprestimosPendentes.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Há algum emprestimo em andamento.");
	
		this.ferramentaService.verificarSeFerramentaEstaDisponivel(emprestimoEntity.getFerramenta());
		emprestimoEntity.setDataInicio(new Date());
		emprestimoEntity.setDataPrevistaEntrega(calcularDataEntrega());		
		
		EmprestimoEntity emprestimo =  emprestimoRepository.save(emprestimoEntity);
		
		ferramentaService.atualizarQuantidade(emprestimoEntity.getFerramenta().getId());
		
		return emprestimo;
	}

	@Override
	public EmprestimoEntity buscarPorIdFuncionario(Integer id) {
		Optional<EmprestimoEntity> emprestimo = emprestimoRepository.findById(id);
		
		if (!emprestimo.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emprestimo ID não encontrado.");
		
		return emprestimo.get();
	}

	@Override
	public EmprestimoEntity realizarEntrega(Integer idFuncionario) {
		EmprestimoEntity emprestimo = buscarPorIdFuncionario(idFuncionario);
		
		emprestimo.setDataEntrega(new Date());
		
		return emprestimoRepository.save(emprestimo);
	}

	@Override
	public List<EmprestimoEntity> listar() {
		return emprestimoRepository.findAll();
	}

	@Override
	public void remover(Integer idEmprestimo) {
		this.verificarSeEmprestimoExiste(idEmprestimo);
		this.emprestimoRepository.deleteById(idEmprestimo);
	}

	@Override
	public EmprestimoEntity buscarPorId(Integer id) {
		Optional<EmprestimoEntity> emprestimo = this.emprestimoRepository.findById(id);
		return emprestimo.get();
	}
	
	private void verificarSeEmprestimoExiste(Integer idEmprestimo) {
		if (buscarPorId(idEmprestimo) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emprestimo ID não encontrado.");
	}

	@Override
	public EmprestimoEntity devolverFerramenta(EmprestimoEntity emprestimo) {
		this.verificarSeEmprestimoExiste(emprestimo.getId());
		emprestimo.setDataEntrega(new Date());
		
		FerramentaEntity ferramenta = this.ferramentaService.buscarPorId(emprestimo.getFerramenta().getId());
		ferramenta.setQuantidade(ferramenta.getQuantidade() + 1);
		this.ferramentaService.salvar(ferramenta);
		
		return emprestimoRepository.save(emprestimo);
	}
	
	
}
