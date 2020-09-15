package rs.ftn.base.child.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rs.ftn.base.child.converter.HomeToHomeDtoConverter;
import rs.ftn.base.child.converter.HomeDtoToHomeConverter;
import rs.ftn.base.child.repository.HomeRepository;
import rs.ftn.base.child.bom.Home;
import rs.ftn.base.child.dto.HomeDto;

@Service
public class HomeServiceImpl implements HomeService {

    private HomeRepository homeRepository;

    private HomeToHomeDtoConverter homeToHomeDtoConverter;

    private HomeDtoToHomeConverter homeDtoToHomeConverter;

    @Autowired
    public void setHomeRepository(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Autowired
    public void setHomeToHomeDtoConverter(HomeToHomeDtoConverter homeToHomeDtoConverter) {
        this.homeToHomeDtoConverter = homeToHomeDtoConverter;
    }

    @Autowired
    public void setHomeDtoToHomeConverter(HomeDtoToHomeConverter homeDtoToHomeConverter) {
        this.homeDtoToHomeConverter = homeDtoToHomeConverter;
    }


    @Override
    public List<HomeDto> getAll() {
        List<HomeDto> homeDtoList = new ArrayList<>();
        for(Home home : homeRepository.findAll()) {
            homeDtoList.add(homeToHomeDtoConverter.convert(home));
        }
        return homeDtoList;
    }

    @Override
    public HomeDto save(HomeDto homeDto) {
        Home home = homeRepository.save(homeDtoToHomeConverter.convert(homeDto));
        return homeToHomeDtoConverter.convert(home);
    }

    @Override
    public HomeDto getOne(Integer id) {
        Optional<Home> home = homeRepository.findById(id);
        HomeDto homeDto = null;
        if(home.isPresent()){
            homeDto = homeToHomeDtoConverter.convert(home.get());
        }
        return homeDto;
    }

    @Override
    public void delete(HomeDto homeDto) {
        Home home = homeDtoToHomeConverter.convert(homeDto);
        if(home != null) {
            homeRepository.delete(home);
        }
    }

    @Override
    public void deleteById(Integer id) {
        homeRepository.deleteById(id);
    }


}