package rs.ftn.dto;

import java.util.*;

import rs.ftn.dto.AddressDto;
import rs.ftn.base.child.dto.HomeDto;


public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private AddressDto address;
    private Boolean active;
    private HomeDto home;

    public UserDto() {

    }

    public UserDto(Integer id,String firstName,String lastName,String username,String password,AddressDto address,Boolean active,HomeDto home) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.active = active;
        this.home = home;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public HomeDto getHome() {
        return home;
    }

    public void setHome(HomeDto home) {
        this.home = home;
    }

    public String getDisplayName() {
        String displayName = "";

        displayName += firstName + " ";
        displayName += lastName + " ";

        return displayName.trim();
    }

    @Override
    public String toString() {
        return "User[" + getId() + "]";
    }

}
