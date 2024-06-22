package org.qa.reqres;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.qa.utils.Specifications;
import org.qa.utils.UrlAndEndPoints;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.*;

public class ApiTestsWithoutPojo {

    @Test
    public void given_whenGetUsers_thenAvatarContainsIdAndAllEmailsEndWith() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UrlAndEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusOk200()
        );
        Response response = given()
                .when()
                .get(UrlAndEndPoints.GET_USERS)
                .then().log().all()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.getList("data.email");
        List<Integer> id = jsonPath.getList("data.id");
        List<String> avatars = jsonPath.getList("data.avatar");

        for (int i = 0; i < avatars.size(); i++) {
            assertTrue(avatars.get(i).contains(id.get(i).toString()));
        }
        assertTrue(emails.stream().allMatch(email -> email.endsWith("@reqres.in")));

    }

    @Test
    public void givenUser_whenRegisterUser_thenSuccessfulResponse1() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UrlAndEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusOk200()
        );
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");

        given()
                .body(user)
                .when()
                .post(UrlAndEndPoints.REGISTER_USER)
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void givenUser_whenRegisterUser_thenSuccessfulResponse2() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UrlAndEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusOk200()
        );
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");

        Response response = given()
                .body(user)
                .when()
                .post(UrlAndEndPoints.REGISTER_USER)
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        String token = jsonPath.getString("token");
        assertEquals(4, id);
        assertEquals("QpwL5tke4Pnpja7X4", token);
    }

    @Test
    public void givenUser_whenRegisterUser_thenBadRequestResponse() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UrlAndEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusBadRequest400()
        );
        Response response = given()
                .when()
                .post(UrlAndEndPoints.REGISTER_USER)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String errorMessage = jsonPath.getString("error");

        assertNotNull(response);
        assertEquals("Missing email or username", errorMessage);
    }
}