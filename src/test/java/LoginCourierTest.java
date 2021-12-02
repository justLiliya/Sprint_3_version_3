import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


public class LoginCourierTest {

    private LoginClient loginClient;
    private int courierId;

    @Step
    @Before
    public void setUp() {

        loginClient = new LoginClient();
    }

    @Step
    @After
    public void tearDown(){
        loginClient.delete(courierId);
    }

    @Test
    @DisplayName("Checking of successful courier login")
    public void successLoginCourier(){
        //Arrange
        Courier courier = Courier.getRandom();
        loginClient.create(courier);
        //Act
        ValidatableResponse courierLogined = loginClient.login(new Couriercredentials(courier.login, courier.password));
        courierId = courierLogined.extract().path("id");
        //Assert
        assertThat("Айди пустой",courierId, notNullValue());
    }


}
