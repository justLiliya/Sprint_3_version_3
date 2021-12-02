import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;

public class CreateCourierTest {

    private CourierClient courierClient;
    private int courierId;
    String mes409 = "Этот логин уже используется";

    @Step
    @Before
    public void setUp() {

        courierClient = new CourierClient();
    }
    @Step
    @After
    public void tearDown(){
        courierClient.delete(courierId);
    }


    @Test
    @DisplayName("Check new courier creation")
    public void createNewCourierAndCheckResponseTest(){
        //Arrange
        Courier courier = Courier.getRandom();
        //Act
        ValidatableResponse CourierCreated = courierClient.create(courier);
        courierId = courierClient.login(new Couriercredentials(courier.login, courier.password));
        /**C использование паттерна фабрика - берет креденшиалы из нашего курьера
        courierId = courierClient.login(Couriercredentials.from(courier));**/
        //Assert
        assertEquals("Статус не 201 ок.Курьер не создан!",201, CourierCreated.extract().statusCode());
        assertThat("Айди пустой",courierId, notNullValue());


    }

    @Test
    @DisplayName("Check two identical courier impossible to create")
    public void checkCannotCreateTwoIdenticalCouriersTest(){
        //Arrange
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        //Act
        ValidatableResponse courierNotCreated = courierClient.create(courier);
        courierId = courierClient.login(new Couriercredentials(courier.login, courier.password));
        //Assert
        assertEquals("Статус не 409 conflict!",409, courierNotCreated.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes409, courierNotCreated.extract().path("message"));
        assertThat("Айди не пустой",courierId, nullValue());

    }



}

