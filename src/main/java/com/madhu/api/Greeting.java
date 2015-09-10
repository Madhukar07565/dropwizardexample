package com.madhu.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Madhukar Reddy
 *
 */
public class Greeting {

    @JsonProperty
    private String text;

    @JsonProperty
    private Long count;

    public Greeting(String text, Long count) {
        this.text = text;
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
