package com.care.model.users;

import com.care.model.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name = "users")
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String number;
    private String postalCode;
    private String city;
    private String address;
    private boolean enable;
    private Set<Role> roles;

    public User() {
        super();
        this.enable=false;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty(message = "*Please enter your first name",
            groups = {User.RegisterValidator.class, User.EditValidator.class})
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "*Please enter your last name",
            groups = {User.RegisterValidator.class, User.EditValidator.class})
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email(message = "*Please enter a valid email",
            groups = {User.RegisterValidator.class})
    @NotEmpty(message = "*Please enter your email",
            groups = {User.RegisterValidator.class})
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "*Please enter a password",
            groups = {User.RegisterValidator.class, User.EditValidator.class})
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "*Please enter your number",
            groups = {User.RegisterValidator.class, User.EditValidator.class})
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @NotEmpty(message = "*Please enter your postal code",
            groups = {User.RegisterValidator.class, User.EditValidator.class})
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @NotEmpty(message = "*Please specify your city of residence",
            groups = {User.RegisterValidator.class, User.EditValidator.class})
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotEmpty(message = "*Please enter your address",
            groups = {User.RegisterValidator.class, User.EditValidator.class})
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public interface RegisterValidator{}

    public interface EditValidator{}
}