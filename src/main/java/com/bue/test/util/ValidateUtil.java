package com.bue.test.util;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ValidateUtil {

	public boolean isValidDTMFormat(String dtm, String format) {
		try {
			SimpleDateFormat dtmF = new SimpleDateFormat(format);
			dtmF.parse(dtm);
			return true;
		}catch(Exception e) {
			log.error("dtm:"+dtm+", formatCheck:"+format);
			return false;
		}
	}
}
