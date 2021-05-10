package com.team5.petmeplus.model;

public interface OwnerDao {

    Owner getOwnerByEmailAndPassword(String email, String password);

    boolean updateOwner(Owner owner);

    boolean insertOwner(Owner owner);

    boolean deleteOwner(Owner owner);
}
