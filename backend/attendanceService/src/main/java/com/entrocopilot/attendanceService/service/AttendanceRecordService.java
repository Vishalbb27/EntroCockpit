package com.entrocopilot.attendanceService.service;

import java.util.List;

import com.entrocopilot.attendanceService.dto.AttendanceRecordDto;

public interface AttendanceRecordService {
	public AttendanceRecordDto createAttendanceRecord(AttendanceRecordDto attendanceRecordDto);
	
	public AttendanceRecordDto updateAttendanceRecord(AttendanceRecordDto attendanceRecordDto);
	
	public List<AttendanceRecordDto> getAllAttendanceRecordByEmployeeId(String id);
	
	public List<AttendanceRecordDto> getAllAttendanceRecordByManagerId(String id);
	
	public void deleteAttandanceRecordById(String id);
}
