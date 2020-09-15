package rs.ftn.service;

import java.util.List;
import rs.ftn.dto.BarDto;

public interface BarService {
    List<BarDto> getAll();
    BarDto save(BarDto barDto);
    BarDto getOne(Integer id);
    void delete(BarDto barDto);
    void deleteById(Integer id);

}