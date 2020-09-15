package rs.ftn.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.Foo;
import rs.ftn.dto.*;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.dto.BarDto;


@Component
@RequiredArgsConstructor
public class FooToFooDtoConverter implements Converter<Foo,FooDto> {

    @Override
    public FooDto convert(Foo foo) {

        BarDto _barDto = new BarDto();
        _barDto.setId(foo.getBar().getId());
        _barDto.setPff(foo.getBar().getPff());

        return new FooDto(foo.getId(), foo.getData(), _barDto);
    }
}