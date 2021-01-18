package ntua.softeng28.evcharge.user;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
public class User {
  private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String username;
  private String password;
  private boolean isLoggedIn;
  private String role;

  User() {
  }

  User(String firstName, String lastName, String email, String username, String password, boolean isLoggedIn, String role) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.username = username;
      this.password = password;
      this.role = role;
      this.isLoggedIn = isLoggedIn;
    }

    public boolean getLoggedIn() {
      return isLoggedIn;
    }
  
    public void setLoggedIn(boolean isLoggedIn) {
      this.isLoggedIn = isLoggedIn;
    }

    public Long getId() {
      return this.id;
    }

    public String getEmail() {
      return this.email;
    }

    public String getPassword() {
      return this.password;
    }
  
    public void setId(Long id) {
      this.id = id;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
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

    public String getRole() {
      return role;
    }

    public void setRole(String role) {
      this.role = role;
    }

    @Override
    public boolean equals(Object o) {

      if(this == o)
        return true;
      if(!(o instanceof User))
        return false;
      User user = (User) o;
      return Objects.equals(this.id, user.id) 
          && Objects.equals(this.firstName, user.firstName)
          && Objects.equals(this.lastName, user.lastName)
          && Objects.equals(this.email, user.email) 
          && Objects.equals(this.password, user.password)
          && Objects.equals(this.username, user.username)
          && Objects.equals(this.role, user.role);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.id,
                          this.firstName,
                          this.lastName,
                          this.email,
                          this.password,
                          this.username,
                          this.role
                          );
    }

    @Override
    public String toString() {
      return  String.format("Username: %s, FirstName: %s, Lastname: %s, email: %s, password: %s, role: %s", 
              username, firstName, lastName, email, password, role);
    }
}
