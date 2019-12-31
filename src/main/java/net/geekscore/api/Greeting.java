package net.geekscore.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class Greeting {

    private long id;

    @NotEmpty
    private String visitorName;

    public Greeting() {
        // Jackson deserialization
    }

    public Greeting(long id, String visitorName) {
        this.id = id;
        this.visitorName = visitorName;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getVisitorName() {
        return visitorName;
    }

}
