package com.madhu.core;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Madhukar Reddy
 *
 */
@Data
public class Person {
    
    @JsonProperty
    private int id;
    
    @JsonProperty
    private String name;
    
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Person() {}

}
