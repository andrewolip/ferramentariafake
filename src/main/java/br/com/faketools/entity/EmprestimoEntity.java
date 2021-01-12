package br.com.faketools.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "EMPRESTIMO")
@Entity
@Data
public class EmprestimoEntity implements Serializable {

	private static final long serialVersionUID = -303078582869540204L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne
	private FerramentaEntity ferramenta;
	
	@OneToOne
	private FuncionarioEntity funcionario;
	
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Column(name = "data_prevista_entrega")
	private Date dataPrevistaEntrega;

	@Column(name = "data_entrega")
	private Date dataEntrega;
	
}
