package com.bue.test.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bue.test.db.entity.CDRRecord;
import com.bue.test.db.repo.CDRRecordRepository;
import com.bue.test.msg.CreateResp;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CDRService {
	
	@Autowired CDRRecordRepository cdrRepo;
	
	public static final float voiceFee = 3f/60f;

	@Transactional(rollbackOn = Exception.class)
	public CreateResp insertCDRRecport(String recordDtm, String orgMSISDN, String destMSISDN, Integer duration) throws Exception {
		CDRRecord entity = CDRRecord.builder().recordDtm(recordDtm).orgMSISDN(orgMSISDN).destMSISDN(destMSISDN).duration(duration).build();
		entity.setCharge(duration.floatValue()*voiceFee);
		log.info("duration="+duration+", fee="+voiceFee+" charge="+entity.getCharge());
		cdrRepo.saveAndFlush(entity);
		log.info("insert CDR id="+entity.getCdrId());
		DecimalFormat chargeFormat = new DecimalFormat("0.00");
		return CreateResp.builder().cdrId(entity.getCdrId().longValue()).recordDtm(recordDtm).orgMSISDN(orgMSISDN).destMSISDN(destMSISDN).duration(duration).charge(chargeFormat.format(entity.getCharge())).build();
	}
}
