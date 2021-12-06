package kr.pe.playdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/Board/categoryAll")
	public List<ResponseDTO.BoardListResponse> getBoardAll(){
		return boardService.findCategoryAll();
	}
	
	@GetMapping("/Board/category")
	public ResponseDTO.BoardResponse getBoard(String categoryname){
		try {
			return boardService.findOneByCategory(categoryname);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	
	
	
	
	
}