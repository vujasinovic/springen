package rs.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rs.ftn.converter.UserToUserDtoConverter;
import rs.ftn.converter.UserDtoToUserConverter;
import rs.ftn.repository.UserRepository;
import rs.ftn.bom.User;
import rs.ftn.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserToUserDtoConverter userToUserDtoConverter;

    private UserDtoToUserConverter userDtoToUserConverter;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserToUserDtoConverter(UserToUserDtoConverter userToUserDtoConverter) {
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @Autowired
    public void setUserDtoToUserConverter(UserDtoToUserConverter userDtoToUserConverter) {
        this.userDtoToUserConverter = userDtoToUserConverter;
    }


    @Override
    public List<UserDto> getAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userRepository.findAll()) {
            userDtoList.add(userToUserDtoConverter.convert(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userRepository.save(userDtoToUserConverter.convert(userDto));
        return userToUserDtoConverter.convert(user);
    }

    @Override
    public UserDto getOne(Integer id) {
        Optional<User> user = userRepository.findById(id);
        UserDto userDto = null;
        if(user.isPresent()){
            userDto = userToUserDtoConverter.convert(user.get());
        }
        return userDto;
    }

    @Override
    public void delete(UserDto userDto) {
        User user = userDtoToUserConverter.convert(userDto);
        if(user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


}