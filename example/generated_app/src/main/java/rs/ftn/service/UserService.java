package rs.ftn.service;

import java.util.List;
import rs.ftn.dto.UserDto;

public interface UserService {
    List<UserDto> getAll();
    UserDto save(UserDto userDto);
    UserDto getOne(Integer id);
    void delete(UserDto userDto);
    void deleteById(Integer id);

}