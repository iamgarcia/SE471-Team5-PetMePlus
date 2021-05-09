package com.team5.petmeplus.model;

import com.team5.petmeplus.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OwnerDaoImpl implements OwnerDao {
    private Owner extractOwnerFromResultSet(ResultSet rs) throws SQLException {
        Owner owner = new Owner();

        owner.setFirstName(rs.getString("first_name"));
        owner.setLastName(rs.getString("last_name"));
        owner.setEmail(rs.getString("email"));
        owner.setPassword(rs.getString("password"));

        return owner;
    }

    @Override
    public Set<Owner> getAllOwners() {
        Set<Owner> owners = new HashSet<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM owner")) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Owner owner = extractOwnerFromResultSet(rs);
                    owners.add(owner);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owners;
    }

    @Override
    public Owner getOwnerByEmailAndPassword(String email, String password) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM owner WHERE email=? AND password=?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractOwnerFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateOwner(Owner owner) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "UPDATE owner SET first_name=?, last_name=? WHERE email=? AND password=?")) {
            stmt.setString(1, owner.getFirstName());
            stmt.setString(2, owner.getLastName());
            stmt.setString(3, owner.getEmail());
            stmt.setString(4, owner.getPassword());
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
    public boolean insertOwner(Owner owner) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO owner (email, password, first_name, last_name) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, owner.getEmail());
            stmt.setString(2, owner.getPassword());
            stmt.setString(3, owner.getFirstName());
            stmt.setString(4, owner.getLastName());
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
    public boolean deleteOwner(Owner owner) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "DELETE FROM owner WHERE email=? AND password=?")) {
            stmt.setString(1, owner.getEmail());
            stmt.setString(2, owner.getPassword());
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
