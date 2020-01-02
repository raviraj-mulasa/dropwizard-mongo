package net.geekscore.core;

import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name="person"
        , uniqueConstraints=@UniqueConstraint(columnNames={"code", "uid"})
        , indexes={@Index(name = "ssd", columnList="sdsd,wwewe", unique = true)}
        )
public final class Person extends BaseEntity {

    private String name;
    private int age;
    private Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }
}
