package com.care.model.users;

import com.care.model.Role;
import com.care.model.enums.Gender;
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
    private Gender gender;
    private String email;
    private String password;
    private String number;
    private String postalCode;
    private String city;
    private String address;
    private boolean hasProfilePicture;
    private String profilePicture;
    private boolean enable;
    private Set<Role> roles;

    public User() {
        super();
        this.enable=false;
        this.hasProfilePicture=false;
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
            groups = {User.RegisterValidator.class, ResetPasswordValidator.class})
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
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isHasProfilePicture() {
        return hasProfilePicture;
    }

    public void setHasProfilePicture(boolean hasProfilePicture) {
        this.hasProfilePicture = hasProfilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    public String profilePictureUrl() {
        String profilePictureUrl = "";
        if(!this.hasProfilePicture) {
            if (this.gender == Gender.Male)
                profilePictureUrl = "/resources/static/image/male.png";
            if (this.gender == Gender.Female)
                profilePictureUrl = "/resources/static/image/female.png";
        } else {
            profilePictureUrl = "/files/profile/picture/"+this.profilePicture;
        }
        return profilePictureUrl;
    }

    public interface RegisterValidator{}
    public interface EditValidator{}
    public interface ResetPasswordValidator{}
}