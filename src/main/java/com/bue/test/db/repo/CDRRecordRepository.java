package com.bue.test.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bue.test.db.entity.CDRRecord;

@Repository
public interface CDRRecordRepository extends JpaRepository<CDRRecord, Integer> {

	
	List<CDRRecord> findByRecordDtmBetween( String startDate,String endDate);
}
