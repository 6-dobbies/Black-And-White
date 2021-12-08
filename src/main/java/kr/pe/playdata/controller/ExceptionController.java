package kr.pe.playdata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.exception.CAuthenticationEntryPointException;
import kr.pe.playdata.model.response.CommonResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

 @GetMapping(value = "/entrypoint")
 public CommonResult entrypointException() throws CAuthenticationEntryPointException {
     throw new CAuthenticationEntryPointException();
 }
}
