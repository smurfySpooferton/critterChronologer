package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.pet.data.PetDTO;
import com.udacity.jdnd.course3.critter.pet.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.data.Customer;
import com.udacity.jdnd.course3.critter.user.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public PetDTO save(PetDTO petDTO) {
        Customer owner = customerRepository.getOne(petDTO.getOwnerId());
        List<Pet> pets = owner.getPets();
        Pet pet = petRepository.save(PetMapper.fromDto(petDTO, owner));
        pets.add(pet);
        owner.setPets(pets);
        customerRepository.save(owner);
        return PetMapper.fromPet(pet);
    }

    public PetDTO byId(Long petId) {
        return PetMapper.fromPet(petRepository.getOne(petId));
    }

    public List<PetDTO> byOwner(Long ownerId) {
        Customer owner = customerRepository.getOne(ownerId);
        return petRepository.findByCustomer(owner).stream().map(PetMapper::fromPet).collect(Collectors.toList());
    }

    public List<PetDTO> all() {
        return petRepository.findAll().stream().map(PetMapper::fromPet).collect(Collectors.toList());
    }
}
