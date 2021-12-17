package kr.pe.playdata.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.pe.playdata.model.response.CommonResult;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;

@Service
public class ResponseService {

	// enum으로 api 요청 결과에 대한 code, msg 정의
	public enum CommonResponse {
		
		SUCCESS(0, "성공하였습니다."), FAIL(-1, "실패하였습니다.");

		int code;
		String msg;

		CommonResponse(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}

	// 단일건 결과 처리
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}

	// 다중건 결과 처리
	public <T> ListResult<T> getListResult(List<T> list) {
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSuccessResult(result);
		return result;
	}

	// 성공 결과만 처리
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSuccessResult(result);
		return result;
	}

	// 실패 결과만 처리
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
		return result;
	}
	
	// 에러 결과만 처리
	public CommonResult getFailResult(Integer errorcode, String errormsg) {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(errorcode);
		result.setMsg(errormsg);
		return result;
	}

	// 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
	private void setSuccessResult(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}

}
