package kr.pe.playdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	// 게시판 1개 조회 - boardIdx
	@Transactional(readOnly = true)
	public ResponseDTO.BoardResponse findByBoardIdx(Long boardIdx) throws NotFoundException {
		Board review = boardRepository.findByBoardIdx(boardIdx)
									  .orElseThrow(() -> new IllegalArgumentException("Board with idx: " + boardIdx + " is not valid"));
		
		return new ResponseDTO.BoardResponse(review);
	}
	
	// 게시판 1개 조회 - category
	@Transactional(readOnly = true)
	public ResponseDTO.BoardResponse findByCategory(String category) throws NotFoundException {
		Board review = boardRepository.findByCategory(category)
									  .orElseThrow(() -> new IllegalArgumentException("Board with category: " + category + " is not valid"));
		
		return new ResponseDTO.BoardResponse(review);
	}
	
	// 게시판 전체 조회 - category
	@Transactional(readOnly = true)
	public List<ResponseDTO.BoardListResponse> findAllByCategory() {
		return boardRepository.findAll()
							  .stream()
							  .map(ResponseDTO.BoardListResponse::new)
							  .collect(Collectors.toList());
	}

}
