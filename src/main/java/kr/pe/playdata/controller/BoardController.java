package kr.pe.playdata.controller;

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

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;
	private final ResponseService responseService;

	// 게시판 1개 조회 - boardIdx
	@GetMapping("/Board/boardIdx")
	public SingleResult<ResponseDTO.BoardResponse> getBoardByBoardIdx(Long boardIdx) {

		try {
			return responseService.getSingleResult(boardService.findByBoardIdx(boardIdx));
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	// 게시판 1개 조회 - category
	@GetMapping("/Board/category")
	public SingleResult<ResponseDTO.BoardResponse> getBoardByCategory(String categoryname) {

		try {
			return responseService.getSingleResult(boardService.findByCategory(categoryname));
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	// 게시판 전체 조회 - category
	@GetMapping("/Board/categoryAll")
	public ListResult<ResponseDTO.BoardListResponse> getBoardAllByCategory() {
		return responseService.getListResult(boardService.findAllByCategory());
	}

}
