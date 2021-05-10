package com.team5.petmeplus.model;

import com.team5.petmeplus.Main;
import com.team5.petmeplus.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PetDaoImpl implements PetDao {
    private Pet extractPetFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String specie = rs.getString("specie");
        String breed = rs.getString("breed");
        Date birthDate = rs.getDate("birth_date");

        return new Pet(name, specie, breed, birthDate);
    }

    @Override
    public Set<Pet> getPetsByOwnerEmail(String email) {
        Set<Pet> pets = new HashSet<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pet WHERE owner_email=?")) {
            stmt.setString(1, Main.owner.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pet pet = extractPetFromResultSet(rs);
                    pets.add(pet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    @Override
    public boolean insertPet(Pet pet) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO pet (name, specie, breed, birth_date) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecie());
            stmt.setString(3, pet.getBreed());
            java.sql.Date sqlDate = new java.sql.Date(pet.getBirthDate().getTime());
            stmt.setDate(4, sqlDate);
            int i = stmt.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePet(Pet pet) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM pet WHERE owner_email=?")) {
            stmt.setString(1, Main.owner.getEmail());
            int i = stmt.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
