package com.udacity.jdnd.course3.critter.user.data;


import com.udacity.jdnd.course3.critter.pet.data.Pet;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(targetEntity = Pet.class)
    private List<Pet> pets;


}
