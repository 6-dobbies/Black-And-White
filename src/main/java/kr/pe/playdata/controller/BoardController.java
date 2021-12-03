package kr.pe.playdata.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.service.BoardService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private BoardService boardService;
	
	@GetMapping("/Board/category")
	public List<ResponseDTO.BoardListResponse> getBoardAll(){
		return boardService.findCategoryAll();
	}
	
	//=====================자유게시판의 글 리스트 불러오기=================================//
	
	
	
}