package com.bue.test.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bue.test.constants.Constant;
import com.bue.test.msg.CreateResp;
import com.bue.test.service.CDRService;
import com.bue.test.util.BusinessException;
import com.bue.test.util.ValidateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@Data
public class TestController {
	
	@Autowired ObjectMapper jsonMapper;
	@Autowired CDRService cdrService;
	@Autowired ValidateUtil validateUtil;

	@GetMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object createCDRRecord(@RequestParam(name="recordDtm", required = true) String recordDtm,
						@RequestParam(name = "orgMSISDN", required = true) String orgMSISDN,
						@RequestParam(name = "destMSISDN", required = true) String destMSISDN,
						@RequestParam(name = "duration", required = true) Integer durationSec) throws Exception {
		validate(recordDtm,orgMSISDN,destMSISDN,durationSec);
		CreateResp resp = cdrService.insertCDRRecport(recordDtm, orgMSISDN, destMSISDN, durationSec);
		log.info("receive:"+ jsonMapper.writeValueAsString(resp));
		return resp;
	}

	private void validate(String recordDtm, String orgMSISDN, String destMSISDN, Integer durationSec) throws BusinessException{
		if(!validateUtil.isValidDTMFormat(recordDtm, Constant.GLOBAL_DTM_FORMAT)) {
			throw new BusinessException("400", "recordDtm:"+recordDtm+" invalid format ("+Constant.GLOBAL_DTM_FORMAT+")");
		}
		
		if(!Pattern.matches("66\\d{9}", orgMSISDN)) {
			throw new BusinessException("400", "orgMSISDN:"+orgMSISDN+" invalid format (66xxxxxxxx)");
		}
		
		if(!Pattern.matches("66\\d{9}", destMSISDN)) {
			throw new BusinessException("400", "destMSISDN:"+destMSISDN+" invalid format (66xxxxxxxx)");
		}
		
	}
}
