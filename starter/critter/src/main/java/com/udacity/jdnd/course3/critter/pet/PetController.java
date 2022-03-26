package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.data.PetDTO;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return petService.save(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        return petService.byId(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.all();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        return petService.byOwner(ownerId);
    }
}
