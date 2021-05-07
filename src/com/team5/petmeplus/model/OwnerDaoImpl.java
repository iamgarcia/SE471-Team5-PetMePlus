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
        owner.setFirstName(rs.getString("last_name"));
        owner.setFirstName(rs.getString("email"));
        owner.setFirstName(rs.getString("password"));
        owner.setFirstName(rs.getString("phone_number"));

        return owner;
    }

    @Override
    public Set<Owner> getAllOwners() {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt;
        ResultSet rs;

        try {
            stmt = connection.prepareStatement("SELECT * FROM owner");
            rs = stmt.executeQuery();
            Set<Owner> owners = new HashSet<>();

            while (rs.next()) {
                Owner owner = extractOwnerFromResultSet(rs);
                owners.add(owner);
            }

            return owners;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Owner getOwnerByEmailAndPassword(String email, String password) {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM owner WHERE email=? AND password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractOwnerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateOwner(Owner owner) {

    }

    @Override
    public void insertOwner(Owner owner) {

    }

    @Override
    public void deleteOwner(Owner owner) {

    }
}
