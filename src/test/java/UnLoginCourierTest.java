import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnLoginCourierTest {

    private LoginClient loginClient;
    private int courierId;
    Courier courier = Courier.getRandom();
    String mes400 = "Недостаточно данных для входа";
    String mes404 = "Учетная запись не найдена";

    @Step
    @Before
    public void setUp() {

        loginClient = new LoginClient();
        loginClient.create(courier);
        ValidatableResponse courierLogined = loginClient.login(new Couriercredentials(courier.login, courier.password));//для успешного тирдауна, чтоб получить айди
        courierId = courierLogined.extract().path("id");
    }

    @Step
    @After
    public void tearDown(){
        loginClient.delete(courierId);
    }


    @Test
    @DisplayName("checking the login without password unsuccessful")
    public void unLoginCourierWithWrongPassword(){
        //Act
        ValidatableResponse wrongPassword = loginClient.login(new Couriercredentials(courier.login, courier.password+"1"));
        //Assert
        assertEquals("Статус не 404 Not Found!",404,wrongPassword.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes404, wrongPassword.extract().path("message"));

    }

    @Test
    @DisplayName("checking the login without wrong login unsuccessful")
    public void UnLoginCourierWithWrongLogin(){
        //Act
        ValidatableResponse wrongLogin = loginClient.login(new Couriercredentials(courier.login+"1", courier.password));
        //Assert
        assertEquals("Статус не 404 Not Found!",404,wrongLogin.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes404, wrongLogin.extract().path("message"));

    }

    @Test
    @DisplayName("checking the login without login unsuccessful")
    public void unLoginCourierWithoutRequiredLogin(){
        //Act
        ValidatableResponse emptyLogin = loginClient.login(new Couriercredentials("", courier.password));
        //Assert
        assertEquals("Статус не 400 Bad Request!",400,emptyLogin.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes400, emptyLogin.extract().path("message"));

    }

    @Test
    @DisplayName("checking the login without password unsuccessful")
    public void unLoginCourierWithoutRequiredPassword(){
        //Act
        ValidatableResponse emptyPassword = loginClient.login(new Couriercredentials(courier.login, ""));
        //Assert
        assertEquals("Статус не 400 Bad Request!",400,emptyPassword.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes400, emptyPassword.extract().path("message"));
    }

    @Test
    @DisplayName("checking the login without nonexistent user unsuccessful")
    public void UnLoginCourierWithNonExistentUser(){
        //Act
        ValidatableResponse nonExistent = loginClient.login(new Couriercredentials("abirvalg010101", "abirvalg010101"));
        //Assert
        assertEquals("Статус не 404 Not Found!",404,nonExistent.extract().statusCode());
        assertEquals("Ошибка в сообщении ответа!", mes404, nonExistent.extract().path("message"));
    }
}
