package ntua.softeng28.evcharge.user;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ntua.softeng28.evcharge.car.Car;

@Entity
public class User {
  private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
  private String firstName;
  private String lastName;
  private String email;
  private @Column(unique = true) String username;
  private String password;
  private boolean isLoggedIn;
  private String role;

  @OneToMany
  Set<Car> cars;

  User() {
  }

  public User(String firstName, String lastName, String email, String username, String password,
      boolean isLoggedIn, String role, Set<Car> cars) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.isLoggedIn = isLoggedIn;
    this.role = role;
    this.cars = cars;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  public void setLoggedIn(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Set<Car> getCars() {
    return cars;
  }

  public void setCars(Set<Car> cars) {
    this.cars = cars;
  }

  @Override
  public String toString() {
    return "User [cars=" + cars + ", email=" + email + ", firstName=" + firstName + ", id=" + id + ", isLoggedIn="
        + isLoggedIn + ", lastName=" + lastName + ", password=" + password + ", role=" + role + ", username=" + username
        + "]";
  }

}
