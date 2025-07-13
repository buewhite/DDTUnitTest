package com.bue.test.msg;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResp {
	String httpStatus;
	String errDesc;
}
