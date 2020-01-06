package net.geekscore.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PersonMixIn {
    public PersonMixIn(@JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("address") Address address) {
    }
}
