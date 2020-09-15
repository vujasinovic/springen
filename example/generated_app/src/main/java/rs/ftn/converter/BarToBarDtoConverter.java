package rs.ftn.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.Bar;
import rs.ftn.dto.*;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.dto.FooDto;


@Component
@RequiredArgsConstructor
public class BarToBarDtoConverter implements Converter<Bar,BarDto> {

    @Override
    public BarDto convert(Bar bar) {

        List<FooDto> _fooiciDto = new ArrayList<>();
        if(nonNull(bar.getFooici())) {
            _fooiciDto = bar.getFooici().stream()
                .map(_entity -> {
                    FooDto __dto = new FooDto();
                    __dto.setId(_entity.getId());
                    __dto.setData(_entity.getData());
                    return __dto;
                 })
                .collect(toList());
         }

        return new BarDto(bar.getId(), bar.getPff(), _fooiciDto);
    }
}