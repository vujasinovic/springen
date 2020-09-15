package rs.ftn.converter;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import rs.ftn.bom.*;
import rs.ftn.dto.BarDto;

import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import rs.ftn.bom.Foo;


@Component
public class BarDtoToBarConverter implements Converter<BarDto,Bar> {

    @Override
    public Bar convert(BarDto barDto) {
		Bar bar = new Bar();
        bar.setId(barDto.getId());
        bar.setPff(barDto.getPff());

        if(nonNull(barDto.getFooici())) {
            List<Foo> _fooici = barDto.getFooici().stream()
                    .map(_dto -> {
                        Foo __fooici = new Foo();
                        __fooici.setId(_dto.getId());
                        return __fooici;
                    })
                    .collect(toList());
            bar.setFooici(_fooici);
        }
        return bar;
    }
}