package rs.ftn.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rs.ftn.converter.FooToFooDtoConverter;
import rs.ftn.converter.FooDtoToFooConverter;
import rs.ftn.repository.FooRepository;
import rs.ftn.bom.Foo;
import rs.ftn.dto.FooDto;

@Service
public class FooServiceImpl implements FooService {

    private FooRepository fooRepository;

    private FooToFooDtoConverter fooToFooDtoConverter;

    private FooDtoToFooConverter fooDtoToFooConverter;

    @Autowired
    public void setFooRepository(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Autowired
    public void setFooToFooDtoConverter(FooToFooDtoConverter fooToFooDtoConverter) {
        this.fooToFooDtoConverter = fooToFooDtoConverter;
    }

    @Autowired
    public void setFooDtoToFooConverter(FooDtoToFooConverter fooDtoToFooConverter) {
        this.fooDtoToFooConverter = fooDtoToFooConverter;
    }


    @Override
    public List<FooDto> getAll() {
        List<FooDto> fooDtoList = new ArrayList<>();
        for(Foo foo : fooRepository.findAll()) {
            fooDtoList.add(fooToFooDtoConverter.convert(foo));
        }
        return fooDtoList;
    }

    @Override
    public FooDto save(FooDto fooDto) {
        Foo foo = fooRepository.save(fooDtoToFooConverter.convert(fooDto));
        return fooToFooDtoConverter.convert(foo);
    }

    @Override
    public FooDto getOne(Integer id) {
        Optional<Foo> foo = fooRepository.findById(id);
        FooDto fooDto = null;
        if(foo.isPresent()){
            fooDto = fooToFooDtoConverter.convert(foo.get());
        }
        return fooDto;
    }

    @Override
    public void delete(FooDto fooDto) {
        Foo foo = fooDtoToFooConverter.convert(fooDto);
        if(foo != null) {
            fooRepository.delete(foo);
        }
    }

    @Override
    public void deleteById(Integer id) {
        fooRepository.deleteById(id);
    }


}