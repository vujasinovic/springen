package rs.ftn.service;

import java.util.List;
import rs.ftn.dto.FooDto;

public interface FooService {
    List<FooDto> getAll();
    FooDto save(FooDto fooDto);
    FooDto getOne(Integer id);
    void delete(FooDto fooDto);
    void deleteById(Integer id);

}