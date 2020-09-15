package rs.ftn.base.child.service;

import java.util.List;
import rs.ftn.base.child.dto.HomeDto;

public interface HomeService {
    List<HomeDto> getAll();
    HomeDto save(HomeDto homeDto);
    HomeDto getOne(Integer id);
    void delete(HomeDto homeDto);
    void deleteById(Integer id);

}