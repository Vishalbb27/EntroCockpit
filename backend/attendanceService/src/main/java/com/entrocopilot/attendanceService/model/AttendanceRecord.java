package com.entrocopilot.attendanceService.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attendanceRecord")
public class AttendanceRecord {
	@Id
	private String id;
	
	private String employeeId;
	
	private LocalDate date;
	
	private LocalTime clockInTime;
	
	private LocalTime clockOutTime;
	
    private AttendanceStatus status; // Enum: PRESENT, ABSENT, LEAVE

    private BigDecimal hoursWorked;
    
    private ApprovalStatus approvalStatus;
    
    private String managerId;

    private String remarks;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(LocalTime clockInTime) {
		this.clockInTime = clockInTime;
	}

	public LocalTime getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(LocalTime clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public AttendanceStatus getStatus() {
		return status;
	}

	public void setStatus(AttendanceStatus status) {
		this.status = status;
	}

	public BigDecimal getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(BigDecimal hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public AttendanceRecord(String id, String employeeId, LocalDate date, LocalTime clockInTime, LocalTime clockOutTime,
			AttendanceStatus status, BigDecimal hoursWorked, ApprovalStatus approvalStatus, String managerId,
			String remarks, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.date = date;
		this.clockInTime = clockInTime;
		this.clockOutTime = clockOutTime;
		this.status = status;
		this.hoursWorked = hoursWorked;
		this.approvalStatus = approvalStatus;
		this.managerId = managerId;
		this.remarks = remarks;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AttendanceRecord() {
		super();
	}

	@Override
	public String toString() {
		return "AttendanceRecord [id=" + id + ", employeeId=" + employeeId + ", date=" + date + ", clockInTime="
				+ clockInTime + ", clockOutTime=" + clockOutTime + ", status=" + status + ", hoursWorked=" + hoursWorked
				+ ", approvalStatus=" + approvalStatus + ", managerId=" + managerId + ", remarks=" + remarks
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
}
