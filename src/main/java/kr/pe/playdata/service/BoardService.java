package kr.pe.playdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Transactional(readOnly = true)
	public List<ResponseDTO.BoardListResponse> findCategoryAll(){
		return boardRepository.findAll().stream().map(ResponseDTO.BoardListResponse::new).collect(Collectors.toList());
	}
	
}