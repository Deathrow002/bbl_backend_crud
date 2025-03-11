package com.bbl.crud;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UserApiTest {

    @BeforeAll
    public static void setup() {
        // Set the base URL for the API
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    // Test for "Create User"
    @Test
    public void testCreateUser() {
        String requestBody = "{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"username\": \"johndoe\",\n" +
                "  \"email\": \"john@example.com\",\n" +
                "  \"phone\": \"123-456-7890\",\n" +
                "  \"website\": \"johndoe.com\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"123 Main St\",\n" +
                "    \"suite\": \"Apt 4B\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"zipcode\": \"10001\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"40.7128\",\n" +
                "      \"lng\": \"-74.0060\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"company\": {\n" +
                "    \"nameCompany\": \"Tech Corp\",\n" +
                "    \"catchPhrase\": \"Innovative Solutions\",\n" +
                "    \"bs\": \"Technology\"\n" +
                "  }\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)  // Assuming it returns 201 for created
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john@example.com"));
    }

    // Test for "Get All Users"
    @Test
    public void testGetAllUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));  // Assuming there is at least one user
    }

    // Test for "Get User by ID"
    @Test
    public void testGetUserById() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe"));
    }

    // Test for "Update User"
    @Test
    public void testUpdateUser() {
        String requestBody = "{\n" +
                "  \"name\": \"John Doe Updated\",\n" +
                "  \"username\": \"johndoe\",\n" +
                "  \"email\": \"john_updated@example.com\",\n" +
                "  \"phone\": \"987-654-3210\",\n" +
                "  \"website\": \"johndoe-updated.com\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"456 Main St\",\n" +
                "    \"suite\": \"Apt 5C\",\n" +
                "    \"city\": \"New York\",\n" +
                "    \"zipcode\": \"10002\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"40.7128\",\n" +
                "      \"lng\": \"-74.0059\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"company\": {\n" +
                "    \"nameCompany\": \"Updated Company\",\n" +
                "    \"catchPhrase\": \"Updated Solutions\",\n" +
                "    \"bs\": \"Tech\"\n" +
                "  }\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe Updated"))
                .body("email", equalTo("john_updated@example.com"));
    }

    // Test for "Delete User"
    @Test
    public void testDeleteUser() {
        given()
                .when()
                .delete("/users/1")
                .then()
                .statusCode(204);  // Assuming it returns 204 for no content on delete
    }
}
