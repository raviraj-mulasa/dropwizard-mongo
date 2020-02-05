package net.geekscore.core.entities;

import net.geekscore.core.BaseEntity;
import net.geekscore.core.annotations.Entity;

import javax.validation.constraints.Min;
import java.util.List;

@Entity(name = "person")
public final class Person extends BaseEntity {

    private String name;

    @Min(1)
    private int age;

    private Address address;

    private List<String> employerIds;

    public Person() {
    }

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
