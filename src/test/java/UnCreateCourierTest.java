import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnCreateCourierTest {

    private CourierClient courierClient;
    private int courierId;
    String mes400 = "Недостаточно данных для создания учетной записи";

    @Step
    @Before
    public void setUp() {

        courierClient = new CourierClient();
    }


    @Test
    @DisplayName("checking the creation of a courier without a login")
    public void cannotCreateCourierWithoutLoginTest(){
        //Arrange
        CourierWithoutLogin courierWithoutLogin = CourierWithoutLogin.getRandomWithoutLogin();
        //Act
        ValidatableResponse courierWithoutLoginNotCreated = courierClient.create(courierWithoutLogin);
        //Assert
        assertEquals("Статус не 400 Bad Request!",400, courierWithoutLoginNotCreated.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes400, courierWithoutLoginNotCreated.extract().path("message"));
    }


    @Test
    @DisplayName("checking the creation of a courier without a password")
    public void cannotCreateCourierWithoutPasswordTest(){
        //Arrange
        СourierWithoutPassword courierWithoutPassword = СourierWithoutPassword.getRandomWithoutPassword();
        //Act
        ValidatableResponse courierWithoutPasswordNotCreated = courierClient.create(courierWithoutPassword);
        //Assert
        assertEquals("Статус не 400 Bad Request!",400, courierWithoutPasswordNotCreated.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes400, courierWithoutPasswordNotCreated.extract().path("message"));
    }

    @Test
    @DisplayName("checking the creation of a courier without a firstname")
    public void cannotCreateCourierWithoutFirstNameTest(){
        //Arrange
        CourierWithoutFirstName courierWithoutFirstName = CourierWithoutFirstName.getRandomWithoutFirstName();
        //Act
        ValidatableResponse courierWithoutFirstNameNotCreated = courierClient.create(courierWithoutFirstName);
        //Assert
        assertEquals("Статус не 400 Bad Request!",400, courierWithoutFirstNameNotCreated.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes400, courierWithoutFirstNameNotCreated.extract().path("message"));
    }

}
