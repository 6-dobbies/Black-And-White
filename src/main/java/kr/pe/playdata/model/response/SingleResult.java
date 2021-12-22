package kr.pe.playdata.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// T == 모든 Object를 받을수있는 와일드카드 
public class SingleResult<T> extends CommonResult {

	private T data;

}
