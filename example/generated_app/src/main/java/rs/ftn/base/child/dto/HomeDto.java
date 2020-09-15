package rs.ftn.base.child.dto;

import java.util.*;

import rs.ftn.dto.AddressDto;
import rs.ftn.dto.UserDto;


public class HomeDto {

    private Integer id;
    private String title;
    private String description;
    private AddressDto address;
    private List<UserDto> users;

    public HomeDto() {

    }

    public HomeDto(Integer id,String title,String description,AddressDto address,List<UserDto> users) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.users = users;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public String getDisplayName() {
        String displayName = "";

        displayName += title + " ";

        return displayName.trim();
    }

    @Override
    public String toString() {
        return "Home[" + getId() + "]";
    }

}
