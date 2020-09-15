package rs.ftn.service;

import java.util.List;
import rs.ftn.dto.AddressDto;

public interface AddressService {
    List<AddressDto> getAll();
    AddressDto save(AddressDto addressDto);
    AddressDto getOne(Integer id);
    void delete(AddressDto addressDto);
    void deleteById(Integer id);

}