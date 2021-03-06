package kr.pe.playdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.pe.playdata.exception.CAuthenticationEntryPointException;
import kr.pe.playdata.exception.CBoardNotFoundException;
import kr.pe.playdata.exception.CIdSigninFailedException;
import kr.pe.playdata.exception.CPostNotFoundException;
import kr.pe.playdata.exception.CUserNotFoundException;
import kr.pe.playdata.model.response.CommonResult;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "http://ec2-13-125-131-254.ap-northeast-2.compute.amazonaws.com:8081")
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	
	private final ResponseService responseService;
    private final MessageSource messageSource;
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        // 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }
    
    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
    }
    
    @ExceptionHandler(CIdSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult idSigninFailedException(HttpServletRequest request, CIdSigninFailedException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("idSigninFailed.code")), getMessage("idSigninFailed.msg"));
    }
    
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
    }
    
    @ExceptionHandler(CBoardNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult boardNotFoundException(HttpServletRequest request, CBoardNotFoundException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("boardNotFound.code")), getMessage("boardNotFound.msg"));
    }
    
    @ExceptionHandler(CPostNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult postNotFoundException(HttpServletRequest request, CPostNotFoundException e) {
    	return responseService.getFailResult(Integer.valueOf(getMessage("postNotFound.code")), getMessage("postNotFound.msg"));
    }

//    @ExceptionHandler(CCommunicationException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public CommonResult communicationException(HttpServletRequest request, CCommunicationException e) {
//        return responseService.getFailResult(Integer.valueOf(getMessage("communicationError.code")), getMessage("communicationError.msg"));
//    }
//
//    @ExceptionHandler(CUserExistException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public CommonResult communicationException(HttpServletRequest request, CUserExistException e) {
//        return responseService.getFailResult(Integer.valueOf(getMessage("existingUser.code")), getMessage("existingUser.msg"));
//    }

    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }
    
    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
	
}
