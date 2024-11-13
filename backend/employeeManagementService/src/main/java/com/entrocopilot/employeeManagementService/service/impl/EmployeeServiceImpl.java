package com.entrocopilot.employeeManagementService.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.entrocopilot.employeeManagementService.dto.EmployeeDto;
import com.entrocopilot.employeeManagementService.exception.AlreadyExists;
import com.entrocopilot.employeeManagementService.exception.NotFoundException;
import com.entrocopilot.employeeManagementService.mapper.EmployeeMapper;
import com.entrocopilot.employeeManagementService.model.Department;
import com.entrocopilot.employeeManagementService.model.Employee;
import com.entrocopilot.employeeManagementService.repository.DepartmentRepository;
import com.entrocopilot.employeeManagementService.repository.EmployeeRepository;
import com.entrocopilot.employeeManagementService.service.EmployeeService;
import com.entrocopilot.employeeManagementService.service.client.UserFeignClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRepository;
	private UserFeignClient userFeignClient;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
			UserFeignClient userFeignClient) {
		super();
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
		this.userFeignClient = userFeignClient;
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		if (!userFeignClient.getUserDetails(employeeDto.getUserId()).getBody()
				&& !userFeignClient.getUserDetails(employeeDto.getReportingManagerId()).getBody()) {
			throw new NotFoundException("User Not found");
		}
		if (!departmentRepository.existsById(employeeDto.getDepartment())) {
			throw new NotFoundException("Department Not found");
		}
		if(employeeRepository.existsByUserId(employeeDto.getUserId())) {
			throw new AlreadyExists("User Already an Employee");
		}
		Employee employee = new Employee();
		employee = EmployeeMapper.mapToEmployee(employee, employeeDto);
		Department department = departmentRepository.findById(employeeDto.getDepartment()).get();
		employee.setDepartment(department);
		employee.setCreatedAt(LocalDateTime.now());
		employee.setUpdatedAt(LocalDateTime.now());
		employee = employeeRepository.save(employee);
		employeeDto = EmployeeMapper.mapToEmployeeDto(employee, employeeDto);
		return employeeDto;
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		if (!employeeRepository.existsById(employeeDto.getEmployeeId())) {
			throw new NotFoundException("Employee Not Found");
		}
		if (!userFeignClient.getUserDetails(employeeDto.getUserId()).getBody()
				&& !userFeignClient.getUserDetails(employeeDto.getReportingManagerId()).getBody()) {
			throw new NotFoundException("User Not Found");
		}
		if (!departmentRepository.existsById(employeeDto.getDepartment())) {
			throw new NotFoundException("Department Not Found");
		}
		Employee employee = new Employee();
		employee = EmployeeMapper.mapToEmployee(employee, employeeDto);
		Department department = departmentRepository.findById(employeeDto.getDepartment()).get();
		employee.setDepartment(department);
		employee.setUpdatedAt(LocalDateTime.now());
		employee = employeeRepository.save(employee);
		employeeDto = EmployeeMapper.mapToEmployeeDto(employee, employeeDto);
		return employeeDto;
	}

	@Override
	public void deleteEmployee(String id) {
		if (!employeeRepository.existsById(id)) {
			throw new NotFoundException("Employee Not Found");
		}
		employeeRepository.deleteById(id);
	}

	@Override
	public EmployeeDto getEmployeeDetails(String id) {
		if (!employeeRepository.existsById(id)) {
			throw new NotFoundException("Employee Not Found");
		}

		Employee employee = employeeRepository.findById(id).get();
		EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());

		return employeeDto;
	}

	@Override
	public EmployeeDto getEmployeeByUserId(String id) {
		 
		if (!userFeignClient.getUserDetails(id).getBody()) {
			throw new NotFoundException("User Not found");
		}

		Employee employee = employeeRepository.findByUserId(id);
		EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());

		return employeeDto;
	}

	@Override
	public List<EmployeeDto> getEmployeesByReportingManagerId(String id) {
		if (!userFeignClient.getUserDetails(id).getBody()) {
			throw new NotFoundException("User Not found");
		}

		List<Employee> employees = employeeRepository.findByReportingManagerId(id);

		List<EmployeeDto> employeeDtos = employees.stream()
				.map(employee -> EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto()))
				.collect(Collectors.toList());
		return employeeDtos;
	}

	@Override
	public List<EmployeeDto> getEmployeesByDepartment(String id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Employee not found"));

		List<Employee> employees = employeeRepository.findByDepartment(department);

		List<EmployeeDto> employeeDtos = employees.stream()
				.map(employee -> EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto()))
				.collect(Collectors.toList());
		return employeeDtos;
	}

}
