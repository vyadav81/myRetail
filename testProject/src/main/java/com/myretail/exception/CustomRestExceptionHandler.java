package com.myretail.exception;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is class to retrive localised error message.
 * 
 * @author vyadav
 *
 */
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(CustomRestExceptionHandler.class);

	@Autowired
	public CustomRestExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * This will use to localise any exception
	 * 
	 * @param ex Exception which needs to be localised - it will catch all exception
	 * @param local - locale of exception message
	 * @return ResponseEntity for exception error message detail
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiError> handleAllExceptions(Exception ex,
			Locale local) {
		String errorMessage = messageSource.getMessage(ex.getMessage(), null,
				LocaleContextHolder.getLocale());
		logger.error(errorMessage);
		ApiError apiError = new ApiError(new Date(), errorMessage);
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This will use to localise any ProductNotFoundException custom exception
	 * 
	 * @param ex ProductNotFoundException custom exception
	 * @param local - locale of exception message
	 * @return ResponseEntity for exception error message detail
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ApiError> handleUserNotFoundException(
			ProductNotFoundException ex, Locale local) {

		String errorMessage = messageSource.getMessage(ex.getMessage(), null,
				local);
		logger.error(errorMessage);
		ApiError apiError = new ApiError(new Date(), errorMessage);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

}
