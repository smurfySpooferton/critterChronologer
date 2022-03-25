package com.udacity.jdnd.course3.critter.pet.data;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.user.data.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private long id;
    private PetType type;
    private String name;
    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;
    private LocalDate birthDate;
    private String notes;
}
