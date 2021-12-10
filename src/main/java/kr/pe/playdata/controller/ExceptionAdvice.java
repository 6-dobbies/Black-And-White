package kr.pe.playdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.pe.playdata.exception.CAuthenticationEntryPointException;
import kr.pe.playdata.exception.CEmailSigninFailedException;
import kr.pe.playdata.exception.CUserNotFoundException;
import kr.pe.playdata.model.response.CommonResult;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

	private final ResponseService rs;

	@Value("${userNotFoundException.code}")
	private Integer userErrorcode;

	@Value("${userNotFoundException.msg}")
	private String userErrormsg;

	@Value("${emailSigninFailedException.code}")
	private Integer loginErrorcode;

	@Value("${emailSigninFailedException.msg}")
	private String loginErrormsg;

	@Value("${entryPointException.code}")
	private Integer entryPointErrorcode;

	@Value("${entryPointException.msg}")
	private String entryPointErrormsg;

	@Value("${accessDenied.code}")
	private Integer accessDeniedErrorcode;

	@Value("${accessDenied.msg}")
	private String accessDeniedErrormsg;

	@ExceptionHandler(CUserNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		return rs.getFailResult(userErrorcode, userErrormsg);
	}

	@ExceptionHandler(CEmailSigninFailedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult emailSigninFailed(HttpServletRequest request, CEmailSigninFailedException e) {
		return rs.getFailResult(loginErrorcode, loginErrormsg);
	}

	@ExceptionHandler(CAuthenticationEntryPointException.class)
	public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
		return rs.getFailResult(entryPointErrorcode, entryPointErrormsg);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public CommonResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
		return rs.getFailResult(accessDeniedErrorcode, accessDeniedErrormsg);
	}
	
}
