package kr.pe.playdata.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.exception.CAuthenticationEntryPointException;
import kr.pe.playdata.model.response.CommonResult;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

	private final ResponseService rs;
	
	@GetMapping(value = "/entrypoint")
	public CommonResult entrypointException() throws CAuthenticationEntryPointException {
	    throw new CAuthenticationEntryPointException();
	}
	
	@GetMapping(value = "/accessdenied")
	public CommonResult accessdeniedException() {
	        throw new AccessDeniedException("");
	}
 
	
}
