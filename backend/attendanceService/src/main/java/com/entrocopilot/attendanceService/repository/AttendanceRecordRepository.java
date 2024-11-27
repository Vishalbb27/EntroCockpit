package com.entrocopilot.attendanceService.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entrocopilot.attendanceService.model.AttendanceRecord;

public interface AttendanceRecordRepository extends MongoRepository<AttendanceRecord, String> {
	List<AttendanceRecord> findAllByEmployeeId(String id);
	
	List<AttendanceRecord> findAllByManagerId(String id);
	
	List<AttendanceRecord> findAllByDate(LocalDate date);
}
