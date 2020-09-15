package rs.ftn.base.child.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.base.child.bom.Home;
import rs.ftn.base.child.dto.*;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.dto.AddressDto;
import rs.ftn.dto.UserDto;


@Component
@RequiredArgsConstructor
public class HomeToHomeDtoConverter implements Converter<Home,HomeDto> {

    @Override
    public HomeDto convert(Home home) {

        AddressDto _addressDto = new AddressDto();
        _addressDto.setId(home.getAddress().getId());
        _addressDto.setStreet(home.getAddress().getStreet());
        _addressDto.setNumber(home.getAddress().getNumber());
        _addressDto.setApartment(home.getAddress().getApartment());
        List<UserDto> _usersDto = new ArrayList<>();
        if(nonNull(home.getUsers())) {
            _usersDto = home.getUsers().stream()
                .map(_entity -> {
                    UserDto __dto = new UserDto();
                    __dto.setId(_entity.getId());
                    __dto.setFirstName(_entity.getFirstName());
                    __dto.setLastName(_entity.getLastName());
                    return __dto;
                 })
                .collect(toList());
         }

        return new HomeDto(home.getId(), home.getTitle(), home.getDescription(), _addressDto, _usersDto);
    }
}