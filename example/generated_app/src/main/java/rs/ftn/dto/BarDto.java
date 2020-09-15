package rs.ftn.dto;

import java.util.*;

import rs.ftn.dto.FooDto;


public class BarDto {

    private Integer id;
    private String pff;
    private List<FooDto> fooici;

    public BarDto() {

    }

    public BarDto(Integer id,String pff,List<FooDto> fooici) {
        this.id = id;
        this.pff = pff;
        this.fooici = fooici;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPff() {
        return pff;
    }

    public void setPff(String pff) {
        this.pff = pff;
    }

    public List<FooDto> getFooici() {
        return fooici;
    }

    public void setFooici(List<FooDto> fooici) {
        this.fooici = fooici;
    }

    public String getDisplayName() {
        String displayName = "";

        displayName += pff + " ";

        return displayName.trim();
    }

    @Override
    public String toString() {
        return "Bar[" + getId() + "]";
    }

}
