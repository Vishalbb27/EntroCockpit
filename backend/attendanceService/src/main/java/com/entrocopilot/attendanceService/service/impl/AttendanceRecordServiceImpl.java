package com.entrocopilot.attendanceService.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entrocopilot.attendanceService.dto.AttendanceRecordDto;
import com.entrocopilot.attendanceService.exception.AlreadyExists;
import com.entrocopilot.attendanceService.exception.NotFoundException;
import com.entrocopilot.attendanceService.mapper.AttendanceRecordMapper;
import com.entrocopilot.attendanceService.model.AttendanceRecord;
import com.entrocopilot.attendanceService.repository.AttendanceRecordRepository;
import com.entrocopilot.attendanceService.service.AttendanceRecordService;
import com.entrocopilot.attendanceService.service.client.EmployeeFeignClient;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
	
	private AttendanceRecordRepository attendanceRecordRepository;
	private EmployeeFeignClient employeeFeignClient;

	public AttendanceRecordServiceImpl(AttendanceRecordRepository attendanceRecordRepository,EmployeeFeignClient employeeFeignClient) {
		super();
		this.attendanceRecordRepository = attendanceRecordRepository;
		this.employeeFeignClient = employeeFeignClient;
	}

	@Override
	public AttendanceRecordDto createAttendanceRecord(AttendanceRecordDto attendanceRecordDto) {
		List<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findAllByDate(attendanceRecordDto.getDate());
		if(!attendanceRecords.isEmpty()) {
			for (AttendanceRecord record : attendanceRecords) {
	            if(record.getEmployeeId() == attendanceRecordDto.getEmployeeId()) {
	            	throw new AlreadyExists("You have already applied for the attendance for this date.");
	            }
	        }
		}
		Boolean employeeExists = employeeFeignClient.getEmployeeDetails(attendanceRecordDto.getEmployeeId()).getBody();
		Boolean managerExists = employeeFeignClient.getEmployeeDetails(attendanceRecordDto.getManagerId()).getBody();
		if(!(employeeExists && managerExists)) {
			throw new NotFoundException("Employee or Manager does not exists!!");
		}
		AttendanceRecord attendanceRecord = AttendanceRecordMapper.mapToAttandanceRecord(new AttendanceRecord(), attendanceRecordDto);
		attendanceRecord.setCreatedAt(LocalDateTime.now());
		attendanceRecord.setUpdatedAt(LocalDateTime.now());
		attendanceRecord = attendanceRecordRepository.save(attendanceRecord);
		attendanceRecordDto = AttendanceRecordMapper.mapToAttandanceRecordDto(attendanceRecord, attendanceRecordDto);
		return attendanceRecordDto;
	}

	@Override
	public AttendanceRecordDto updateAttendanceRecord(AttendanceRecordDto attendanceRecordDto) {
		AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(attendanceRecordDto.getId()).orElseThrow(()-> new NotFoundException("Attendance Record not found."));
		attendanceRecord.setUpdatedAt(LocalDateTime.now());
		attendanceRecord.setClockInTime(attendanceRecordDto.getClockInTime());
		attendanceRecord.setClockOutTime(attendanceRecordDto.getClockOutTime());
		attendanceRecord.setHoursWorked(attendanceRecordDto.getHoursWorked());
		attendanceRecord = attendanceRecordRepository.save(attendanceRecord);
		attendanceRecordDto = AttendanceRecordMapper.mapToAttandanceRecordDto(attendanceRecord, attendanceRecordDto);
		return attendanceRecordDto;
	}

	@Override
	public List<AttendanceRecordDto> getAllAttendanceRecordByEmployeeId(String id) {
		Boolean employeeExists = employeeFeignClient.getEmployeeDetails(id).getBody();
		if(!(employeeExists)) {
			throw new NotFoundException("Employee does not exists!!");
		}
		List<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findAllByEmployeeId(id);
		List<AttendanceRecordDto> attendanceRecordDtos = attendanceRecords.stream().map(attendanceRecord -> AttendanceRecordMapper.mapToAttandanceRecordDto(attendanceRecord, new AttendanceRecordDto())).collect(Collectors.toList());
		return attendanceRecordDtos;
	}

	@Override
	public List<AttendanceRecordDto> getAllAttendanceRecordByManagerId(String id) {
		Boolean managerExists = employeeFeignClient.getEmployeeDetails(id).getBody();
		if(!(managerExists)) {
			throw new NotFoundException("Manager does not exists!!");
		}
		List<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findAllByManagerId(id);
		List<AttendanceRecordDto> attendanceRecordDtos = attendanceRecords.stream().map(attendanceRecord -> AttendanceRecordMapper.mapToAttandanceRecordDto(attendanceRecord, new AttendanceRecordDto())).collect(Collectors.toList());
		return attendanceRecordDtos;
	}

	@Override
	public void deleteAttandanceRecordById(String id) {
		AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(id).orElseThrow(()-> new NotFoundException("Attendance Record not found."));
		attendanceRecordRepository.delete(attendanceRecord);
	}

}
