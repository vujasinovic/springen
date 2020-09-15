package rs.ftn.bom;

import javax.persistence.*;

import java.util.*;

import rs.ftn.bom.Bar;


@Entity
@Table(name = "Foo")
public class Foo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String data;

    @ManyToOne
    private Bar bar;

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

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

}