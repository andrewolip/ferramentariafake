package br.com.faketools.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.faketools.entity.EmprestimoEntity;

public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Integer> {

	@Query(nativeQuery = true, value = "select * FROM emprestimo WHERE funcionario_id = ?1 and data_prevista_entrega <= now() and data_entrega is null")
	Optional<EmprestimoEntity> buscarEmprestimosPendentesByIdFuncionario(Integer idFuncionario);
	
}
