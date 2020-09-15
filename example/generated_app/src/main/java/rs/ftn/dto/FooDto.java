package rs.ftn.dto;

import java.util.*;

import rs.ftn.dto.BarDto;


public class FooDto {

    private Integer id;
    private String data;
    private BarDto bar;

    public FooDto() {

    }

    public FooDto(Integer id,String data,BarDto bar) {
        this.id = id;
        this.data = data;
        this.bar = bar;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BarDto getBar() {
        return bar;
    }

    public void setBar(BarDto bar) {
        this.bar = bar;
    }

    public String getDisplayName() {
        String displayName = "";

        displayName += data + " ";

        return displayName.trim();
    }

    @Override
    public String toString() {
        return "Foo[" + getId() + "]";
    }

}
