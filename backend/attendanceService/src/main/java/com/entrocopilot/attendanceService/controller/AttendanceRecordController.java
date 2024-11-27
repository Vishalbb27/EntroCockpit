package com.entrocopilot.attendanceService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrocopilot.attendanceService.dto.AttendanceRecordDto;
import com.entrocopilot.attendanceService.service.AttendanceRecordService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRecordController {
	
	private AttendanceRecordService attendanceRecordService;
	
	public AttendanceRecordController(AttendanceRecordService attendanceRecordService) {
		super();
		this.attendanceRecordService = attendanceRecordService;
	}

	@PostMapping
	public ResponseEntity<AttendanceRecordDto> createAttendanceRecord(@RequestBody AttendanceRecordDto attendanceRecordDto){
		AttendanceRecordDto attenDto = attendanceRecordService.createAttendanceRecord(attendanceRecordDto);
		return new ResponseEntity<AttendanceRecordDto>(attenDto,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<AttendanceRecordDto> updateAttendanceRecord(@RequestBody AttendanceRecordDto attendanceRecordDto){
		AttendanceRecordDto attenDto = attendanceRecordService.updateAttendanceRecord(attendanceRecordDto);
		return new ResponseEntity<AttendanceRecordDto>(attenDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<AttendanceRecordDto>> getAtendanceRecordByEmployeeId(@RequestParam("id") String id){
		List<AttendanceRecordDto> attendanceRecordDtos = attendanceRecordService.getAllAttendanceRecordByEmployeeId(id);
		return new ResponseEntity<List<AttendanceRecordDto>>(attendanceRecordDtos,HttpStatus.OK);
	}
	
	@GetMapping("/manager")
	public ResponseEntity<List<AttendanceRecordDto>> getAtendanceRecordByManagerId(@RequestParam("id") String id){
		List<AttendanceRecordDto> attendanceRecordDtos = attendanceRecordService.getAllAttendanceRecordByManagerId(id);
		return new ResponseEntity<List<AttendanceRecordDto>>(attendanceRecordDtos,HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAttendanceRecord(@RequestParam("id") String id){
		attendanceRecordService.deleteAttandanceRecordById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
