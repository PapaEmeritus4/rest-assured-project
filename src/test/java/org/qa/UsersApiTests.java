package org.qa;

import io.restassured.http.ContentType;
import org.qa.model.User;
import org.qa.utils.UsersEndPoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsersApiTests {

    private final String baseUri = "https://reqres.in/api/";

    @Test(testName = "Test create user functionality")
    public void givenUserToCreate_whenCreateUser_thenSuccessfulResponse() {
        User user = new User("Volodymyr", "Java Developer");

        given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(UsersEndPoints.createUser)
                .then()
                .statusCode(201)
                .body("name", equalTo("Volodymyr"))
                .body("job", equalTo("Java Developer"))
                .body(matchesJsonSchemaInClasspath("json-schema/create_user_json_schema.json"));
    }

    @Test(testName = "Test get user functionality")
    public void givenUser_whenGetUser_thenSuccessfulResponse() {
        given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .get(UsersEndPoints.getUser)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("json-schema/get_user_json_schema.json"));
    }

    @Test(testName = "Test get user not found functionality")
    public void givenUser_whenGetUser_thenNotFoundResponse() {
        given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .get(UsersEndPoints.getNotFoundUser)
                .then()
                .statusCode(404);
    }

    @Test(testName = "Test get users functionality")
    public void givenUsers_whenGetUsers_thenSuccessfulResponse() {
        given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .get(UsersEndPoints.getAllUsers)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("json-schema/get_users_json_schema.json"));
    }

    @Test(testName = "Test delete user functionality")
    public void givenUserToDelete_whenDeleteUser_thenSuccessfulResponse() {
        given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .delete(UsersEndPoints.deleteUser)
                .then()
                .statusCode(204);
    }

    @Test(testName = "Test update user functionality")
    public void givenUserToUpdate_whenUpdateUser_thenSuccessfulResponse() {
        given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(new User("Volodymyr", "Java QA"))
                .when()
                .put(UsersEndPoints.updateUser)
                .then()
                .statusCode(200)
                .body("name", equalTo("Volodymyr"))
                .body("job", equalTo("Java QA"))
                .body(matchesJsonSchemaInClasspath("json-schema/update_user_json_schema.json"));
    }

}