package com.team5.petmeplus.model;

public class Owner {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private String phone;
    private String address;

    public Owner(OwnerBuilder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class OwnerBuilder {
        private final String email;
        private final String password;
        private final String firstName;
        private final String lastName;
        private String phone;
        private String address;

        public OwnerBuilder(String email, String password, String firstName, String lastName) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public OwnerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public OwnerBuilder address(String address) {
            this.address = address;
            return this;
        }

        public Owner build() {
            Owner owner = new Owner(this);
            return owner;
        }
    }
}
