package br.com.faketools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faketools.entity.FuncionarioEntity;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer>{

}
