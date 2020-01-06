package net.geekscore.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.geekscore.core.domain.Address;

public abstract class PersonMixIn {
    public PersonMixIn(@JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("address") Address address) {
    }
}
