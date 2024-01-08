package com.example.lab9.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String phone;
    private UserFullName name;
    private Address address;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class UserFullName {
        @JsonProperty("firstname")
        private String firstName;
        @JsonProperty("lastname")
        private String lastName;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class Address {
        String city;
        String street;
        Integer number;
        String zipcode;
    }
}
