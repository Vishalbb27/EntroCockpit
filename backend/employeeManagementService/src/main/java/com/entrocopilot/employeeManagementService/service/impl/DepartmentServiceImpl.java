package com.entrocopilot.employeeManagementService.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entrocopilot.employeeManagementService.dto.DepartmentDto;
import com.entrocopilot.employeeManagementService.exception.AlreadyExists;
import com.entrocopilot.employeeManagementService.mapper.DepartmentMapper;
import com.entrocopilot.employeeManagementService.mapper.EmployeeMapper;
import com.entrocopilot.employeeManagementService.model.Department;
import com.entrocopilot.employeeManagementService.repository.DepartmentRepository;
import com.entrocopilot.employeeManagementService.service.DepartmentService;

import jakarta.ws.rs.NotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentRepository;
	

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		if(departmentRepository.existsByDepartmentName(departmentDto.getDepartmentName())) {
			throw new AlreadyExists("Department Already Exists");
		}
		Department department = new Department();
		DepartmentDto deDto = new DepartmentDto();
		department = DepartmentMapper.mapToDepartment(department, departmentDto);
		department.setCreatedAt(LocalDateTime.now());
		department.setUpdatedAt(LocalDateTime.now());
		department = departmentRepository.save(department);
		deDto = DepartmentMapper.mapToDepartmentDto(department, deDto);
		return deDto;
	}

	@Override
	public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
		// TODO Auto-generated method stub
		if(!departmentRepository.existsById(departmentDto.getDepartmentId())) {
			throw new NotFoundException("Department Does not exists");
		}
		Department department = departmentRepository.findById(departmentDto.getDepartmentId()).get();
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setCreatedAt(LocalDateTime.now());
		department = departmentRepository.save(department);
		DepartmentDto deDto = new DepartmentDto();
		deDto = DepartmentMapper.mapToDepartmentDto(department, deDto);
		return deDto;
	}

	@Override
	public String deleteDepartment(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> departments= departmentRepository.findAll();
		List<DepartmentDto> departmentDtos = departments.stream().map(department -> DepartmentMapper.mapToDepartmentDto(department, new DepartmentDto())).collect(Collectors.toList());
		return departmentDtos;
	}
	
	
}
