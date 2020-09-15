package rs.ftn.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.Address;
import rs.ftn.dto.*;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;



@Component
@RequiredArgsConstructor
public class AddressToAddressDtoConverter implements Converter<Address,AddressDto> {

    @Override
    public AddressDto convert(Address address) {


        return new AddressDto(address.getId(), address.getStreet(), address.getNumber(), address.getApartment(), address.getDescription(), address.getZipCode());
    }
}