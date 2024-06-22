package org.qa.reqres;

import io.restassured.http.ContentType;
import org.qa.utils.pojo.reqres.User;
import org.qa.utils.UrlAndEndPoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsersApiTests {

    @Test(testName = "Test create user functionality")
    public void givenUserToCreate_whenCreateUser_thenSuccessfulResponse() {
        User user = new User("Volodymyr", "Java Developer");

        given()
                .baseUri(UrlAndEndPoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(UrlAndEndPoints.CREATE_USER)
                .then()
                .statusCode(201)
                .body("name", equalTo("Volodymyr"))
                .body("job", equalTo("Java Developer"))
                .body(matchesJsonSchemaInClasspath("json-schema/create_user_json_schema.json"));
    }

    @Test(testName = "Test get user functionality")
    public void givenUser_whenGetUser_thenSuccessfulResponse() {
        given()
                .baseUri(UrlAndEndPoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(UrlAndEndPoints.GET_USER)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("json-schema/get_user_json_schema.json"));
    }

    @Test(testName = "Test get user not found functionality")
    public void givenUser_whenGetUser_thenNotFoundResponse() {
        given()
                .baseUri(UrlAndEndPoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(UrlAndEndPoints.GET_NOT_FOUND_USER)
                .then()
                .statusCode(404);
    }

    @Test(testName = "Test get users functionality")
    public void givenUsers_whenGetUsers_thenSuccessfulResponse() {
        given()
                .baseUri(UrlAndEndPoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(UrlAndEndPoints.GET_USERS)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("json-schema/get_users_json_schema.json"));
    }

    @Test(testName = "Test delete user functionality")
    public void givenUserToDelete_whenDeleteUser_thenSuccessfulResponse() {
        given()
                .baseUri(UrlAndEndPoints.BASE_URL)
                .contentType(ContentType.JSON)
                .delete(UrlAndEndPoints.DELETE_USER)
                .then()
                .statusCode(204);
    }

    @Test(testName = "Test update user functionality")
    public void givenUserToUpdate_whenUpdateUser_thenSuccessfulResponse() {
        given()
                .baseUri(UrlAndEndPoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(new User("Volodymyr", "Java QA"))
                .when()
                .put(UrlAndEndPoints.UPDATE_USER)
                .then()
                .statusCode(200)
                .body("name", equalTo("Volodymyr"))
                .body("job", equalTo("Java QA"))
                .body(matchesJsonSchemaInClasspath("json-schema/update_user_json_schema.json"));
    }

}