package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public CustomerService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer, long[] petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null) {
            for (long petId : petIds) {
                pets.add(petRepository.getOne(petId));
            }
        }
        customer.setPets(pets);
        return customerRepository.save(customer);
    }

    public Customer byPet(long petId) {
        return petRepository.getOne(petId).getCustomer();
    }

    public List<Customer> all() {
        return customerRepository.findAll();
    }
}
