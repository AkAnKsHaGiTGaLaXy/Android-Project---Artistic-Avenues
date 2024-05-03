package com.example.artisticavenues;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String Address;

    public Customer() {
        // Default constructor required for Firebase
    }

    public Customer(String id, String name, String email,String mobile,  String password,String Address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password=password;
        this.Address=Address;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getMobile() {
        return mobile;
    }
    public String getPassword() {
        return password;
    }
    public String getLocation() {
        return Address;
    }

}