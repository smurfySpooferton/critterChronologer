package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.data.Employee;
import com.udacity.jdnd.course3.critter.user.data.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.data.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        return EmployeeMapper.fromEmployee(employeeRepository.save(EmployeeMapper.fromDto(employeeDTO)));
    }

    public List<EmployeeDTO> byService(EmployeeRequestDTO requestDto) {
        List<Employee> candidates = employeeRepository.findByDaysAvailable(requestDto.getDate().getDayOfWeek());
        candidates.removeIf(candidate -> !candidate.getSkills().containsAll(requestDto.getSkills()));
        return candidates.stream().map(EmployeeMapper::fromEmployee).collect(Collectors.toList());
    }

    public EmployeeDTO byId(Long employeeId) {
        return EmployeeMapper.fromEmployee(employeeRepository.getOne(employeeId));
    }

    public void setAvailability(Set<DayOfWeek> days, Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(days);
        employeeRepository.save(employee);
    }
}
