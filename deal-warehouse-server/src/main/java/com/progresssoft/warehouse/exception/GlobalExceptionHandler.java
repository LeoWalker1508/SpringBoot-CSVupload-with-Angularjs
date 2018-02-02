/**
 * 
 */
package com.progresssoft.warehouse.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author s727953
 *
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TwiceUploadException.class)
	protected ResponseEntity<ApiException> handleConflict(TwiceUploadException ex, WebRequest request) {
		return buildResponseEntity(new ApiException(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex));
	}

	private ResponseEntity<ApiException> buildResponseEntity(ApiException apiException) {
		return new ResponseEntity<>(apiException, apiException.getStatus());
	}

}
