package kr.pe.playdata.controller;

import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.service.BoardService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private final BoardService boardService;
	private final ResponseService rs;

	
	@GetMapping("/Board/categoryAll")
	public ListResult<ResponseDTO.BoardListResponse> getBoardAll() {
		return rs.getListResult(boardService.findCategoryAll());
	}

	@GetMapping("/Board/category")
	public SingleResult<ResponseDTO.BoardResponse> getBoard(String categoryname) {
		try {
			return rs.getSingleResult(boardService.findOneByCategory(categoryname));
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}