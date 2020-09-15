package rs.ftn.base.child.converter;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.base.child.bom.*;
import rs.ftn.base.child.dto.HomeDto;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.bom.Address;
import rs.ftn.bom.User;


@Component
public class HomeDtoToHomeConverter implements Converter<HomeDto,Home> {

    @Override
    public Home convert(HomeDto homeDto) {
		Home home = new Home();
        home.setId(homeDto.getId());
        home.setTitle(homeDto.getTitle());
        home.setDescription(homeDto.getDescription());

        Address _address = new Address();
        _address.setId(homeDto.getAddress().getId());
        home.setAddress(_address);

        if(nonNull(homeDto.getUsers())) {
            List<User> _users = homeDto.getUsers().stream()
                    .map(_dto -> {
                        User __users = new User();
                        __users.setId(_dto.getId());
                        return __users;
                    })
                    .collect(toList());
            home.setUsers(_users);
        }
        return home;
    }
}