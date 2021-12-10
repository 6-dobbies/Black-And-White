package kr.pe.chess.controller;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.chess.service.ChessBoardService;
import kr.pe.playdata.model.response.SingleResult;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class ChessBoardController {

	private final ChessBoardService cbs;
	
	public SingleResult<Long> A(@RequestBody String Data) throws ParseException {
		
		return cbs.B(Data);
	}
	

}
