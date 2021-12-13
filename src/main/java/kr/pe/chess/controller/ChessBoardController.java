package kr.pe.chess.controller;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.chess.service.ChessBoardService;
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
	
	@GetMapping("/Chess/move")
	public ListResult<Boolean> A(@RequestBody String data) throws ParseException {
		return rs.getListResult(cbs.B(data));
	}

}
