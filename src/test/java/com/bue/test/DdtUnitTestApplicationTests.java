package com.bue.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bue.test.controller.TestController;
import com.bue.test.msg.CreateResp;
import com.bue.test.service.CDRService;
import com.bue.test.util.BusinessException;
import com.bue.test.util.ValidateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@ExtendWith(MockitoExtension.class)
@Log4j2
class DdtUnitTestApplicationTests {

	@Mock
	CDRService cdrService;
	@Mock
	ValidateUtil validateUtil;
	@Mock ObjectMapper jsonMapper;
	@InjectMocks
	TestController controller;
	
//	@BeforeAll
//	public static void initMock() {
//		MockitoAnnotations.openMocks(DdtUnitTestApplicationTests.class);
//	}

	@ParameterizedTest(name = "createCDRTest #{index}:{0}  {1}, {2}, {3}, {4}, {5}, {6}, {7}")
	@CsvFileSource(resources = "/TestController_CreateCDRRecord.csv")
	void testCreateCDR(String name, String cdrDtm, String org, String dest, String duration, String charge, String id,
			boolean exception) {
		try {
			CreateResp expectedResp = CreateResp.builder().recordDtm(cdrDtm).orgMSISDN(org).destMSISDN(dest)
					.duration(Integer.valueOf(duration)).charge(charge).cdrId(Long.valueOf(id)).build();
			jsonMapper = new ObjectMapper();
			validateUtil = new ValidateUtil();
			controller.setValidateUtil(validateUtil);
			log.info("name="+name);
			log.info("expectedResp : "+jsonMapper.writeValueAsString(expectedResp));
			try {
				controller.createCDRRecord(cdrDtm, org, dest, Integer.valueOf(duration));
				assertEquals(false, exception);
			}catch(BusinessException e) {
				log.error("exception occur:"+e.getErrorDesc());
				assertEquals(true, exception);
			}
			//when(cdrService.insertCDRRecport(cdrDtm, org, dest, Integer.valueOf(duration))).thenReturn(expectedResp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
