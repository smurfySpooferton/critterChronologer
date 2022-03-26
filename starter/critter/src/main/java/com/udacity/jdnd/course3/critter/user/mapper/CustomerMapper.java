package com.udacity.jdnd.course3.critter.user.mapper;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import com.udacity.jdnd.course3.critter.user.data.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static Customer fromDto(CustomerDTO customerDTO, List<Pet> pets) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        customer.setPets(pets);
        return customer;
    }

    public static CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setId(customer.getId());
        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }
}
