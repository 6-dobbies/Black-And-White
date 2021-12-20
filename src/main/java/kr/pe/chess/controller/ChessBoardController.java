package kr.pe.chess.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.chess.service.ChessBoardService;
import kr.pe.chess.service.Locator;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class ChessBoardController {

	private final ChessBoardService cbs;
	private final ResponseService rs;
	private final Locator locator;
	
	// 기보와 명령을 넘겨서 해당 명령을 검증
	@GetMapping("/chess/move")
	public ListResult<Boolean> movePiece(@RequestBody String data) throws ParseException {
		return rs.getListResult(cbs.B(data));
	}
	
	// 일단 AI와의 대전을 위해 자바알고리즘 건너뛰는 메소드
	@GetMapping("/chess/move")
	public SingleResult<String> movePiece2(@RequestBody String data) throws ParseException, IOException {
		return rs.getSingleResult(cbs.PyChessOnly(data));
	}
	
	// 해당 기보를 이용한 체스판 반환
	@GetMapping("/chess/board")
	public SingleResult<String[][]> makeBoard(@RequestBody String data) throws ParseException {
		return rs.getSingleResult(locator.makeChessBoard(data));
	}

}
