package io.github.mariazevedo88.financialjavaapi.util;

import java.util.Date;

import io.github.mariazevedo88.financialjavaapi.dto.model.transaction.TransactionDTO;
import io.github.mariazevedo88.financialjavaapi.model.transaction.Transaction;

/**
 * Class that implements the FinancialAPI utility methods.
 * 
 * @author Mariana Azevedo
 * @since 28/03/2020
 */
public class FinancialApiUtil {
	
	/**
	 * Field to represent API version on the requests/responses header
	 */
	public static final String HEADER_FINANCIAL_API_VERSION = "financialapi-version";
	
	/**
	 * Field to represent API key on the requests/responses header
	 */
	public static final String HEADER_API_KEY = "X-api-key";
	
	/**
	 * Field to represent API Rate Limit Remaining on the requests/responses header
	 */
	public static final String HEADER_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
    
	/**
	 * Field to represent API Rate Limit Retry After Seconds on the requests/responses header
	 */
	public static final String HEADER_RETRY_AFTER = "X-Rate-Limit-Retry-After-Seconds";
	
	private FinancialApiUtil() {}
	
	/**
	 * Method that check if the Transaction is being created in the future.
	 * 
	 * @author Mariana Azevedo
	 * @since 08/09/2019
	 * 
	 * @param transaction
	 * @return boolean
	 */
	public static boolean isTransactionInFuture(Transaction transaction) {
		return transaction.getTransactionDate().after(new Date());
	}
	
	/**
	 * Method that check if the TransactionDTO is being created in the future.
	 * 
	 * @author Mariana Azevedo
	 * @since 01/04/2020
	 * 
	 * @param dto
	 * @return boolean
	 */
	public static boolean isTransactionDTOInFuture(TransactionDTO dto) {
		return dto.getTransactionDate().after(new Date());
	}
	
}
