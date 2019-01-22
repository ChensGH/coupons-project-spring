package com.chen.coupons.exceptions;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.coupons.beans.ErrorBeans;

@ControllerAdvice
public class CouponsExceptionsHandler {
	
	@ResponseBody
	@ExceptionHandler
	public void ExceptionHanding(HttpServletResponse response,Throwable exception) {
		
		//if the exception we caught is an exception we thrown
		if (exception instanceof ApplicationException) {
			ApplicationException appException = (ApplicationException) exception;
			String errorMessage = appException.getErrorType().getErrorMessage();
			String internalMessage = appException.getMessage();
			int errorCode = appException.getErrorType().getErrorNumber();
			ErrorBeans errorBeans = new ErrorBeans(errorCode, internalMessage, errorMessage);
			response.setStatus(errorCode);
			response.setHeader(internalMessage, errorMessage);
		}
		
		//here we handle an exception that we didn't caught and warped 
		exception.getStackTrace();
		String internalMessage = exception.getMessage();
		ErrorBeans errorBeans = new ErrorBeans(601, internalMessage, "General Exception");
		//return Response.status(601).entity(errorBeans).build();
		response.setStatus(601);
		response.setHeader("General Exception:", internalMessage);
		
	}
	
	

}
