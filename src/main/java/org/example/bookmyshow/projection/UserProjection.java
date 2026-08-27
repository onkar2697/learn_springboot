package org.example.bookmyshow.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","email"})  //using to serialize the json output format
public interface UserProjection {
    Long getId();
    String getName();
    String getEmail();

}
