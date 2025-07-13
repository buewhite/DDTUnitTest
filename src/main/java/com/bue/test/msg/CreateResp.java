package com.bue.test.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CreateResp {

	Long cdrId;
	String recordDtm;
	String orgMSISDN;
	String destMSISDN;
	Integer duration;
	String charge;
	
}
