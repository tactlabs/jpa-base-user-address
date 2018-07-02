package org.qwan.jpa.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ControllerAdvice
public class ErrorController {
	
	private static Logger _log = LoggerFactory.getLogger(ErrorController.class);
	
	private MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class, Exception.class})
	public <T> T handleError(Exception ex) {		
		return (T) new ModelAndView(jsonView, convertExceptionMap(ex));
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public <T> T handleError404(HttpServletRequest request, Exception e)   {
        _log.info("Request: " + request.getRequestURL() + " raised " + e);
        
        return (T) new ModelAndView(jsonView, convertExceptionMap(e));
    }
	
	private static Map<String, Object> convertExceptionMap(Exception ex) {		
		ex.printStackTrace();
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();		
		map.put("apimessage", ex.getMessage());	
		
		return map;
	}
}
