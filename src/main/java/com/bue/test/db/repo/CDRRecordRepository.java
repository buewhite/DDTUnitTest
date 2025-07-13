package com.bue.test.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bue.test.db.entity.CDRRecord;

@Repository
public interface CDRRecordRepository extends JpaRepository<CDRRecord, Integer> {

}
