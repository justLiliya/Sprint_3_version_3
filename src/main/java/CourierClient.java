import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends RequestClient{

    private static final String COURIER_PATH = "/api/v1/courier/";


    @Step
    public ValidatableResponse create(Object object){

        return given()
                .spec(getBaseSpec())
                .body(object)
                .when()
                .post(COURIER_PATH)
                .then();

    }
    @Step
    public int login(Couriercredentials credentials){

        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login/")
                .then()
                .extract()
                .path("id");

    }
    @Step
    public boolean delete(int courierId){
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }


    @Step
    public boolean notCreateWithoutSomeParameters(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .extract()
                .path("message");

    }


}
