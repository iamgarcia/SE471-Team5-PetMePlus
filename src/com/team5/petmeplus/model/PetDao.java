package com.team5.petmeplus.model;

import java.util.Set;

public interface PetDao {
    Set<Pet> getPetsByOwnerEmail(String email);

    boolean insertPet(Pet pet);

    boolean deletePet(Pet pet);
}
