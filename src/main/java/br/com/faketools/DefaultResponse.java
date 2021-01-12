package br.com.faketools;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Component
public class DefaultResponse {
	
	@JsonInclude(Include.NON_NULL)
	private Integer codigo;
	
	@JsonInclude(Include.NON_NULL)
	private String mensagem;

	@JsonInclude(Include.NON_NULL)
	private Object payload;

}