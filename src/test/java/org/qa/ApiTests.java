package org.qa;

import org.qa.utils.pojo.User;
import org.qa.utils.Specifications;
import org.qa.utils.UsersEndPoints;
import org.qa.utils.pojo.*;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class ApiTests {

    @Test
    public void given_whenGetUsers_thenAvatarContainsIdAndAllEmailsEndWith() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UsersEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusOk200()
        );
        List<UserData> users = given()
                .when()
                .get(UsersEndPoints.GET_USERS)
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(user -> {
            assertTrue(user.getAvatar().contains(user.getId().toString()));
            assertTrue(user.getEmail().endsWith("@reqres.in"));
        });
    }

    @Test
    public void givenUser_whenRegisterUser_thenSuccessfulResponse() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UsersEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusOk200()
        );
        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";
        RegisterRequest user = new RegisterRequest("eve.holt@reqres.in", "pistol");
        RegisterResponse response = given()
                .body(user)
                .when()
                .post(UsersEndPoints.REGISTER_USER)
                .then().log().all()
                .extract().as(RegisterResponse.class);

        assertNotNull(response);
        assertEquals(response.getId(), expectedId);
        assertEquals(response.getToken(), expectedToken);
    }

    @Test
    public void givenUser_whenRegisterUser_thenBadRequestResponse() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UsersEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusBadRequest400()
        );
        BadRequestResponse response = given()
                .when()
                .post(UsersEndPoints.REGISTER_USER)
                .then().log().all()
                .extract().as(BadRequestResponse.class);

        assertNotNull(response);
    }

    @Test
    public void given_whenGetResources_thenResourcesSortedByYear() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UsersEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusOk200()
        );
        List<ResourceData> resources= given()
                .when()
                .get(UsersEndPoints.GET_RESOURCES)
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourceData.class);
        List<Integer> years = resources.stream().map(ResourceData::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();
        assertEquals(years, sortedYears);
    }

    @Test
    public void given_whenDeleteUser_thenSuccessfulResponse204() {
        Specifications.installSpecification(
                Specifications.requestSpecification(UsersEndPoints.BASE_URL),
                Specifications.responseSpecificationStatusNoContent204()
        );
        given()
                .when()
                .delete(UsersEndPoints.DELETE_USER)
                .then().log().all();
    }
}











