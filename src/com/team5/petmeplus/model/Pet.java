package com.team5.petmeplus.model;

import java.util.Date;

public class Pet {
    private final String name;
    private final String specie;
    private final String breed;
    private final Date birthDate;

    public Pet(String name, String specie, String breed, Date birthDate) {
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public String getBreed() {
        return breed;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
