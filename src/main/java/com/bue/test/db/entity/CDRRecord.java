package com.bue.test.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity(name = "CDR")
@Data
@Builder
public class CDRRecord {

	@Column(name = "cdr_id")
	@Id
	@GeneratedValue
	Integer cdrId;
	
	
	@Column(name = "record_dtm",nullable = true)
	String recordDtm;
	
	@Column(name="org_MSISDN",nullable = true)
	String orgMSISDN;
	
	@Column(name = "dest_MSISDN",nullable = true)
	String destMSISDN;
	
	@Column(name = "duration",nullable = true)
	Integer duration;
	
	@Column(name = "charge",nullable = true)
	Float charge;
}
