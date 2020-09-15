package rs.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rs.ftn.converter.BarToBarDtoConverter;
import rs.ftn.converter.BarDtoToBarConverter;
import rs.ftn.repository.BarRepository;
import rs.ftn.bom.Bar;
import rs.ftn.dto.BarDto;

@Service
public class BarServiceImpl implements BarService {

    private BarRepository barRepository;

    private BarToBarDtoConverter barToBarDtoConverter;

    private BarDtoToBarConverter barDtoToBarConverter;

    @Autowired
    public void setBarRepository(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    @Autowired
    public void setBarToBarDtoConverter(BarToBarDtoConverter barToBarDtoConverter) {
        this.barToBarDtoConverter = barToBarDtoConverter;
    }

    @Autowired
    public void setBarDtoToBarConverter(BarDtoToBarConverter barDtoToBarConverter) {
        this.barDtoToBarConverter = barDtoToBarConverter;
    }


    @Override
    public List<BarDto> getAll() {
        List<BarDto> barDtoList = new ArrayList<>();
        for(Bar bar : barRepository.findAll()) {
            barDtoList.add(barToBarDtoConverter.convert(bar));
        }
        return barDtoList;
    }

    @Override
    public BarDto save(BarDto barDto) {
        Bar bar = barRepository.save(barDtoToBarConverter.convert(barDto));
        return barToBarDtoConverter.convert(bar);
    }

    @Override
    public BarDto getOne(Integer id) {
        Optional<Bar> bar = barRepository.findById(id);
        BarDto barDto = null;
        if(bar.isPresent()){
            barDto = barToBarDtoConverter.convert(bar.get());
        }
        return barDto;
    }

    @Override
    public void delete(BarDto barDto) {
        Bar bar = barDtoToBarConverter.convert(barDto);
        if(bar != null) {
            barRepository.delete(bar);
        }
    }

    @Override
    public void deleteById(Integer id) {
        barRepository.deleteById(id);
    }


}