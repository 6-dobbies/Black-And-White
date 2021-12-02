package kr.pe.playdata.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000") // CORS때문에 교차출처에 표시
@RequiredArgsConstructor
@RestController
// @RequestMapping("/con") // /mcon/method 
public class MemberController {
	
}
