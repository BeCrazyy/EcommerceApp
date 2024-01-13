package models;

import java.util.UUID;

public class User {
  private final String userId;
  private String name;
  private String email;
  private String password; // hashed password
  private String phoneNumber;
  private Address address;

  public User(String email, String name, String password, Address address, String phoneNumber) {
    this.userId = UUID.randomUUID().toString(); // auto-generated
    this.email = email.toLowerCase();
    this.name = name;
    this.password = password; // <todo> hashed it
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public String getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }
}
