package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.data.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import com.udacity.jdnd.course3.critter.user.data.Employee;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        List<Optional<Employee>> optionalEmployeeList = scheduleDTO.getEmployeeIds().stream().map(employeeRepository::findById).collect(Collectors.toList());
        List<Employee> employeeList = optionalEmployeeList.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        List<Optional<Pet>> optionalPetList = scheduleDTO.getPetIds().stream().map(petRepository::findById).collect(Collectors.toList());
        List<Pet> petList = optionalPetList.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        return ScheduleMapper.fromSchedule(scheduleRepository.save(ScheduleMapper.fromDto(scheduleDTO, employeeList, petList)));
    }

    public List<ScheduleDTO> all() {
        return scheduleRepository.findAll().stream().map(ScheduleMapper::fromSchedule).collect(Collectors.toList());
    }

    public List<ScheduleDTO> allForPetId(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.findAllByPetsContaining(pet).stream().map(ScheduleMapper::fromSchedule).collect(Collectors.toList());
    }

    public List<ScheduleDTO> allForEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.findAllByEmployeesContaining(employee).stream().map(ScheduleMapper::fromSchedule).collect(Collectors.toList());
    }

    public List<ScheduleDTO> allForCustomerId(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return new ArrayList();
        }
        Customer customer = optionalCustomer.get();
        List<Pet> customerPets = petRepository.findByCustomer(customer);
        List<Schedule> result = new ArrayList();
        for (Pet pet : customerPets) {
            List<Schedule> resultsForPet = scheduleRepository.findAllByPetsContaining(pet);
            result = mergeSchedules(result, resultsForPet);
        }
        return result.stream().map(ScheduleMapper::fromSchedule).collect(Collectors.toList());
    }

    private List<Schedule> mergeSchedules(List<Schedule> listA, List<Schedule> listB) {
        Set<Schedule> set = new LinkedHashSet<>(listA);
        set.addAll(listB);
        return new ArrayList(set);
    }
}
