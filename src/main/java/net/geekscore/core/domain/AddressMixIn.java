package net.geekscore.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AddressMixIn {
    public AddressMixIn(@JsonProperty("street") String street, @JsonProperty("city")String city, @JsonProperty("zip") String zip) { }
}
