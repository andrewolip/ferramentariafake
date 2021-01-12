package br.com.faketools.exceptions;

public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RegraNegocioException(String msg) {
		new RuntimeException(msg);
	}
	
}
