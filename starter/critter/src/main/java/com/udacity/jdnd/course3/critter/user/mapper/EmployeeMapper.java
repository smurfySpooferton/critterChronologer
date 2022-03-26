package com.udacity.jdnd.course3.critter.user.mapper;

import com.udacity.jdnd.course3.critter.user.data.Employee;
import com.udacity.jdnd.course3.critter.user.data.EmployeeDTO;

public class EmployeeMapper {
    public static Employee fromDto(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setName(employeeDTO.getName());
        employee.setId(employeeDTO.getId());
        employee.setSkills(employeeDTO.getSkills());
        return employee;
    }

    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setDaysAvailable(employee.getDaysAvailable());
        employeeDto.setName(employee.getName());
        employeeDto.setId(employee.getId());
        employeeDto.setSkills(employee.getSkills());
        return employeeDto;
    }
}
