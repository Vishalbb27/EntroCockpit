package com.entrocopilot.attendanceService.mapper;

import com.entrocopilot.attendanceService.dto.AttendanceRecordDto;
import com.entrocopilot.attendanceService.model.AttendanceRecord;

public class AttendanceRecordMapper {
	public static AttendanceRecordDto mapToAttandanceRecordDto(AttendanceRecord attendanceRecord, AttendanceRecordDto attendanceRecordDto) {
		attendanceRecordDto.setEmployeeId(attendanceRecord.getEmployeeId());
		attendanceRecordDto.setId(attendanceRecord.getId());
		attendanceRecordDto.setManagerId(attendanceRecord.getManagerId());
		attendanceRecordDto.setDate(attendanceRecord.getDate());
		attendanceRecordDto.setStatus(attendanceRecord.getStatus());
		attendanceRecordDto.setApprovalStatus(attendanceRecord.getApprovalStatus());
		attendanceRecordDto.setClockInTime(attendanceRecord.getClockInTime());
		attendanceRecordDto.setClockOutTime(attendanceRecord.getClockOutTime());
		attendanceRecordDto.setHoursWorked(attendanceRecord.getHoursWorked());
		attendanceRecordDto.setRemarks(attendanceRecord.getRemarks());
		attendanceRecordDto.setCreatedAt(attendanceRecord.getCreatedAt());
		attendanceRecordDto.setUpdatedAt(attendanceRecord.getUpdatedAt());
		return attendanceRecordDto;
	}
	
	public static AttendanceRecord mapToAttandanceRecord(AttendanceRecord attendanceRecord, AttendanceRecordDto attendanceRecordDto) {
		attendanceRecord.setEmployeeId(attendanceRecordDto.getEmployeeId());
		attendanceRecord.setId(attendanceRecordDto.getId());
		attendanceRecord.setManagerId(attendanceRecordDto.getManagerId());
		attendanceRecord.setDate(attendanceRecordDto.getDate());
		attendanceRecord.setStatus(attendanceRecordDto.getStatus());
		attendanceRecord.setApprovalStatus(attendanceRecordDto.getApprovalStatus());
		attendanceRecord.setClockInTime(attendanceRecordDto.getClockInTime());
		attendanceRecord.setClockOutTime(attendanceRecordDto.getClockOutTime());
		attendanceRecord.setHoursWorked(attendanceRecordDto.getHoursWorked());
		attendanceRecord.setRemarks(attendanceRecordDto.getRemarks());
		attendanceRecord.setCreatedAt(attendanceRecordDto.getCreatedAt());
		attendanceRecord.setUpdatedAt(attendanceRecordDto.getUpdatedAt());
		return attendanceRecord;
	}
}
