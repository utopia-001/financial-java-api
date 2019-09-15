package io.github.mariazevedo88.financialjavaapi.exception;

import io.github.mariazevedo88.financialjavaapi.model.APIError;

/**
 * Class that implements TransactionNotFoundException
 * 
 * @author Mariana Azevedo
 * @since 14/09/2019
 */
public class TransactionNotFoundException extends Exception{

	private static final long serialVersionUID = -2586209354700102349L;
	
	public TransactionNotFoundException(){
		super();
	}
	
	public TransactionNotFoundException(String msg){
		super(msg);
	}
	
	public TransactionNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}
	
	public TransactionNotFoundException(APIError error){
		super(error.getMessage());
	}

}
