package com.bbl.crud.model;

import com.bbl.crud.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private AddressDTO address;
    private CompanyDTO company;

    // DTO constructor from UserModel (if needed)
    public UserDTO(UserModel userModel) {
        this.id = userModel.getId();
        this.name = userModel.getName();
        this.username = userModel.getUsername();
        this.email = userModel.getEmail();
        this.phone = userModel.getPhone();
        this.website = userModel.getWebsite();
        this.address = new AddressDTO(userModel.getAddress());
        this.company = new CompanyDTO(userModel.getCompany());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressDTO {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private GeoDTO geo;

        public AddressDTO(UserModel.Address address) {
            this.street = address.getStreet();
            this.suite = address.getSuite();
            this.city = address.getCity();
            this.zipcode = address.getZipcode();
            this.geo = new GeoDTO(address.getGeo());  // Assuming Address has a Geo object
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GeoDTO {
        private String lat;
        private String lng;

        public GeoDTO(UserModel.Geo geo) {
            this.lat = geo.getLat();
            this.lng = geo.getLng();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompanyDTO {
        private String nameCompany;
        private String catchPhrase;
        private String bs;


        public CompanyDTO(UserModel.Company company) {
            this.nameCompany = company.getNameCompany();
            this.catchPhrase = company.getCatchPhrase();
            this.bs = company.getBs();
        }
    }
}
