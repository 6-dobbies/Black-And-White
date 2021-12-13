package kr.pe.playdata.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.exception.CAuthenticationEntryPointException;
import kr.pe.playdata.model.response.CommonResult;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

	@GetMapping(value = "/entrypoint")
	public CommonResult entrypointException() {
		throw new CAuthenticationEntryPointException("");
	}

	@GetMapping(value = "/accessdenied")
	public CommonResult accessdeniedException() {
		throw new AccessDeniedException("");
	}

}
