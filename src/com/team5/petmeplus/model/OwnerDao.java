package com.team5.petmeplus.model;

import java.util.Set;

public interface OwnerDao {
    Set<Owner> getAllOwners();

    Owner getOwnerByEmailAndPassword(String email, String password);

    boolean updateOwner(Owner owner);

    boolean insertOwner(Owner owner);

    boolean deleteOwner(Owner owner);
}
