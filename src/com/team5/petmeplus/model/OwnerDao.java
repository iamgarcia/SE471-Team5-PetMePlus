package com.team5.petmeplus.model;

import java.util.Set;

public interface OwnerDao {
    Set<Owner> getAllOwners();

    Owner getOwnerByEmailAndPassword(String email, String password);

    void updateOwner(Owner owner);

    void insertOwner(Owner owner);

    void deleteOwner(Owner owner);
}
