package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.data.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public CustomerService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            for (Long petId : petIds) {
                pets.add(petRepository.getOne(petId));
            }
        }
        return CustomerMapper.fromCustomer(customerRepository.save(CustomerMapper.fromDto(customerDTO, pets)));
    }

    public CustomerDTO byPet(Long petId) {
        return CustomerMapper.fromCustomer(petRepository.getOne(petId).getCustomer());
    }

    public List<CustomerDTO> all() {
        return customerRepository.findAll().stream().map(CustomerMapper::fromCustomer).collect(Collectors.toList());
    }
}
