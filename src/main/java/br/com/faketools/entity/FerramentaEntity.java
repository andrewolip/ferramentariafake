package br.com.faketools.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "FERRAMENTA")
@Entity
@Data
public class FerramentaEntity implements Serializable {

	private static final long serialVersionUID = 8326357202356019515L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	
	private Integer quantidade;
	
}
