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

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Embedded
    private Address address;

    @Embedded
    private Company company;

    public UserModel(String name, String username, String email, String phone, String website, Address address, Company company) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.company = company;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Address {
        @Column(name = "street")
        private String street;

        @Column(name = "suite")
        private String suite;

        @Column(name = "city")
        private String city;

        @Column(name = "zipcode")
        private String zipcode;

        @Embedded
        private Geo geo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Geo {
        @Column(name = "lat")
        private String lat;

        @Column(name = "lng")
        private String lng;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Company {
        @Column(name = "company_name")
        private String nameCompany;

        @Column(name = "company_catchphrase")
        private String catchPhrase;

        @Column(name = "company_bs")
        private String bs;
    }
}