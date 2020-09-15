package rs.ftn.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.User;
import rs.ftn.dto.*;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.dto.AddressDto;
import rs.ftn.base.child.dto.HomeDto;


@Component
@RequiredArgsConstructor
public class UserToUserDtoConverter implements Converter<User,UserDto> {

    @Override
    public UserDto convert(User user) {

        AddressDto _addressDto = new AddressDto();
        _addressDto.setId(user.getAddress().getId());
        _addressDto.setStreet(user.getAddress().getStreet());
        _addressDto.setNumber(user.getAddress().getNumber());
        _addressDto.setApartment(user.getAddress().getApartment());
        HomeDto _homeDto = new HomeDto();
        _homeDto.setId(user.getHome().getId());
        _homeDto.setTitle(user.getHome().getTitle());

        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), _addressDto, user.getActive(), _homeDto);
    }
}