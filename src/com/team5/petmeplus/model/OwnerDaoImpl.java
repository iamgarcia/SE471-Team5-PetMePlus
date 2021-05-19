package com.team5.petmeplus.model;

import com.team5.petmeplus.util.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerDaoImpl implements OwnerDao {
    private Owner extractOwnerFromResultSet(ResultSet rs) throws SQLException {
        String email = rs.getString("email");
        String password = rs.getString("password");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String phone = rs.getString("phone");
        String address = rs.getString("address");

        return new Owner.OwnerBuilder(email, password, firstName, lastName)
                .phone(phone)
                .address(address)
                .build();
    }

    @Override
    public Owner getOwnerByEmailAndPassword(String email, String password) {
        try (Connection conn = DatabaseConnectionPool.getInstance().checkoutObject();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM owner WHERE email=? AND password=?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractOwnerFromResultSet(rs);
                }
            } finally {
                DatabaseConnectionPool.getInstance().checkinObject(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateOwner(Owner owner) {
        try (Connection conn = DatabaseConnectionPool.getInstance().checkoutObject();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE owner SET phone=?,  address=? WHERE email=? AND password=?")) {
            stmt.setString(1, owner.getPhone());
            stmt.setString(2, owner.getAddress());
            stmt.setString(3, owner.getEmail());
            stmt.setString(4, owner.getPassword());
            int i = stmt.executeUpdate();

            DatabaseConnectionPool.getInstance().checkinObject(conn);

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
        try (Connection conn = DatabaseConnectionPool.getInstance().checkoutObject();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO owner (email, password, first_name, last_name) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, owner.getEmail());
            stmt.setString(2, owner.getPassword());
            stmt.setString(3, owner.getFirstName());
            stmt.setString(4, owner.getLastName());
            int i = stmt.executeUpdate();

            DatabaseConnectionPool.getInstance().checkinObject(conn);

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
        try (Connection conn = DatabaseConnectionPool.getInstance().checkoutObject();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM owner WHERE email=? AND password=?")) {
            stmt.setString(1, owner.getEmail());
            stmt.setString(2, owner.getPassword());
            int i = stmt.executeUpdate();

            DatabaseConnectionPool.getInstance().checkinObject(conn);

            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
