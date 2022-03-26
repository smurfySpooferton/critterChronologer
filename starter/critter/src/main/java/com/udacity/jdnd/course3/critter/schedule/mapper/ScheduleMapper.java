package com.udacity.jdnd.course3.critter.schedule.mapper;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.schedule.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.data.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.data.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleMapper {
    public static Schedule fromDto(ScheduleDTO scheduleDTO, List<Employee> employees, List<Pet> pets) {
        Schedule schedule = new Schedule();
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setId(scheduleDTO.getId());
        return schedule;
    }

    public static ScheduleDTO fromSchedule(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }
}
