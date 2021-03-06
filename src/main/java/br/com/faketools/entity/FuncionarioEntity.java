package br.com.faketools.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "FUNCIONARIO")
@Entity
@Data
public class FuncionarioEntity implements Serializable {

	private static final long serialVersionUID = -6640528237031563132L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;	
	
	private String departamento;
	
}
