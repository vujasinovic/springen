package rs.ftn.converter;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.*;
import rs.ftn.dto.UserDto;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.bom.Address;
import rs.ftn.base.child.bom.Home;


@Component
public class UserDtoToUserConverter implements Converter<UserDto,User> {

    @Override
    public User convert(UserDto userDto) {
		User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        Address _address = new Address();
        _address.setId(userDto.getAddress().getId());
        user.setAddress(_address);
        user.setActive(userDto.getActive());

        Home _home = new Home();
        _home.setId(userDto.getHome().getId());
        user.setHome(_home);
        return user;
    }
}