package kr.pe.chess.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;

import kr.pe.chess.service.ChessBoardService;
import kr.pe.playdata.model.response.SingleResult;

public class ChessBoardController {

	private final ChessBoardService cbs;
	
	public SingleResult<Long> A(@RequestBody String Data) throws ParseException {
		
		return cbs.B(Data);
	}
	

}
