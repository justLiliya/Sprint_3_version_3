import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RequestClient{

    private static final String COURIER_PATH = "/api/v1/orders";

    @Step
    public ValidatableResponse create(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(COURIER_PATH)
                .then();



    }

    @Step
    public boolean cancel(int track) {
        return given()
                .spec(getBaseSpec())
                .body(track)
                .when()
                .put(COURIER_PATH + "cancel")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");

    }

    @Step
    public ValidatableResponse getOrders(){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(COURIER_PATH)
                .then();

    }

}
