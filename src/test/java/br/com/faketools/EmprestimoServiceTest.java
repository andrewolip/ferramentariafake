package br.com.faketools;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.faketools.entity.EmprestimoEntity;
import br.com.faketools.entity.FerramentaEntity;
import br.com.faketools.entity.FuncionarioEntity;
import br.com.faketools.exceptions.RegraNegocioException;
import br.com.faketools.repository.EmprestimoRepository;
import br.com.faketools.repository.FerramentaRepository;
import br.com.faketools.service.impl.EmprestimoServiceImpl;
import br.com.faketools.service.impl.FerramentaServiceImpl;

@ExtendWith(SpringExtension.class)
public class EmprestimoServiceTest {

	@InjectMocks
	EmprestimoServiceImpl emprestimoService;
	
	@InjectMocks
	FerramentaServiceImpl ferramnetaService;
	
	@Mock
	EmprestimoRepository emprestimoRepository;
	
	@Mock
	FerramentaRepository ferramentaRepository;
	
	@Test
	public void emprestandoUmaFerramentaDisponivel() {

		FuncionarioEntity funcionario = gerarFuncionario();
		FerramentaEntity ferramenta = gerarFerramenta(10);
		
		EmprestimoEntity emprestimo = new EmprestimoEntity();
		emprestimo.setFerramenta(ferramenta);
		emprestimo.setFuncionario(funcionario);
		
		when(emprestimoRepository.buscarEmprestimosPendentesByIdFuncionario(1)).thenReturn(Optional.empty());
		
		when(ferramentaRepository.findById(1)).thenReturn(Optional.of(ferramenta));
		
		Assertions.assertDoesNotThrow(() -> emprestimoService.emprestarFerramenta(emprestimo));
	}
	
	@Test
	public void emprestandoUmaFerramentaIndisponivel() {
		FuncionarioEntity funcionario = gerarFuncionario();
		FerramentaEntity ferramenta = gerarFerramenta(0);
		
		EmprestimoEntity emprestimo = new EmprestimoEntity();
		emprestimo.setFerramenta(ferramenta);
		emprestimo.setFuncionario(funcionario);
		
		when(emprestimoRepository.buscarEmprestimosPendentesByIdFuncionario(1)).thenReturn(Optional.empty());
		
		when(ferramentaRepository.findById(1)).thenReturn(Optional.of(ferramenta));
		
		Assertions.assertThrows(RegraNegocioException.class, () -> emprestimoService.emprestarFerramenta(emprestimo), "Ferramenta indisponível.");
	}
	
	
	@Test
	public void emprestandoUmaFerramentaPossuindoUmEmprestimoPendentes() {
		FuncionarioEntity funcionario = gerarFuncionario();
		FerramentaEntity ferramenta = gerarFerramenta(0);
		
		EmprestimoEntity emprestimo = new EmprestimoEntity();
		emprestimo.setFerramenta(ferramenta);
		emprestimo.setFuncionario(funcionario);	
		
		when(emprestimoRepository.buscarEmprestimosPendentesByIdFuncionario(1)).thenReturn(Optional.of(emprestimo));
		
		when(ferramentaRepository.findById(1)).thenReturn(Optional.of(ferramenta));
		
		Assertions.assertThrows(RegraNegocioException.class, () -> emprestimoService.emprestarFerramenta(emprestimo), "Há algum emprestimo não devolvido.");
	}
	
	private FuncionarioEntity gerarFuncionario() {
		FuncionarioEntity funcionario = new FuncionarioEntity();
		funcionario.setId(1);
		funcionario.setDepartamento("Manutenção");
		funcionario.setNome("Joaquim");
		
		return funcionario;
	}
	
	private FerramentaEntity gerarFerramenta(Integer quantidadeEmEstoque) {
		FerramentaEntity ferramenta = new FerramentaEntity();
		ferramenta.setId(1);
		ferramenta.setNome("Ferramenta");
		ferramenta.setQuantidade(quantidadeEmEstoque);
		
		return ferramenta;
	}
	
	
	
}
