package com.bbl.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    @Embedded
    private Address address;

    @Embedded
    private Company company;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;

        @Embedded
        private Geo geo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Geo {
        private String lat;
        private String lng;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }
}
