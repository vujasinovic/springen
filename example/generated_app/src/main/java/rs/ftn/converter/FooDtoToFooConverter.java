package rs.ftn.converter;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.*;
import rs.ftn.dto.FooDto;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.bom.Bar;


@Component
public class FooDtoToFooConverter implements Converter<FooDto,Foo> {

    @Override
    public Foo convert(FooDto fooDto) {
		Foo foo = new Foo();
        foo.setId(fooDto.getId());
        foo.setData(fooDto.getData());

        Bar _bar = new Bar();
        _bar.setId(fooDto.getBar().getId());
        foo.setBar(_bar);
        return foo;
    }
}