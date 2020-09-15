package rs.ftn.bom;

import javax.persistence.*;

import java.util.*;

import rs.ftn.bom.Foo;


@Entity
@Table(name = "Bar")
public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pff;

    @OneToMany(mappedBy = "bar")
    private List<Foo> fooici;

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

    public List<Foo> getFooici() {
        return fooici;
    }

    public void setFooici(List<Foo> fooici) {
        this.fooici = fooici;
    }

}