package rs.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rs.ftn.converter.AddressToAddressDtoConverter;
import rs.ftn.converter.AddressDtoToAddressConverter;
import rs.ftn.repository.AddressRepository;
import rs.ftn.bom.Address;
import rs.ftn.dto.AddressDto;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    private AddressToAddressDtoConverter addressToAddressDtoConverter;

    private AddressDtoToAddressConverter addressDtoToAddressConverter;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Autowired
    public void setAddressToAddressDtoConverter(AddressToAddressDtoConverter addressToAddressDtoConverter) {
        this.addressToAddressDtoConverter = addressToAddressDtoConverter;
    }

    @Autowired
    public void setAddressDtoToAddressConverter(AddressDtoToAddressConverter addressDtoToAddressConverter) {
        this.addressDtoToAddressConverter = addressDtoToAddressConverter;
    }


    @Override
    public List<AddressDto> getAll() {
        List<AddressDto> addressDtoList = new ArrayList<>();
        for(Address address : addressRepository.findAll()) {
            addressDtoList.add(addressToAddressDtoConverter.convert(address));
        }
        return addressDtoList;
    }

    @Override
    public AddressDto save(AddressDto addressDto) {
        Address address = addressRepository.save(addressDtoToAddressConverter.convert(addressDto));
        return addressToAddressDtoConverter.convert(address);
    }

    @Override
    public AddressDto getOne(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        AddressDto addressDto = null;
        if(address.isPresent()){
            addressDto = addressToAddressDtoConverter.convert(address.get());
        }
        return addressDto;
    }

    @Override
    public void delete(AddressDto addressDto) {
        Address address = addressDtoToAddressConverter.convert(addressDto);
        if(address != null) {
            addressRepository.delete(address);
        }
    }

    @Override
    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }


}