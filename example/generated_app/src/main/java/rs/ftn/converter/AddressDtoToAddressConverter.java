package rs.ftn.converter;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.*;
import rs.ftn.dto.AddressDto;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;



@Component
public class AddressDtoToAddressConverter implements Converter<AddressDto,Address> {

    @Override
    public Address convert(AddressDto addressDto) {
		Address address = new Address();
        address.setId(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setApartment(addressDto.getApartment());
        address.setDescription(addressDto.getDescription());
        address.setZipCode(addressDto.getZipCode());
        return address;
    }
}