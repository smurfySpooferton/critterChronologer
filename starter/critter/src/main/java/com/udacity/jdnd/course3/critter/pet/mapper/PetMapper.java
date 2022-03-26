package com.udacity.jdnd.course3.critter.pet.mapper;

import com.udacity.jdnd.course3.critter.pet.data.Pet;
import com.udacity.jdnd.course3.critter.pet.data.PetDTO;
import com.udacity.jdnd.course3.critter.user.data.Customer;

public class PetMapper {
    public static Pet fromDto(PetDTO petDTO, Customer owner) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setCustomer(owner);
        pet.setNotes(petDTO.getNotes());
        pet.setType(petDTO.getType());
        return pet;
    }

    public static PetDTO fromPet(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setNotes(pet.getNotes());
        petDTO.setType(pet.getType());
        return petDTO;
    }
}
