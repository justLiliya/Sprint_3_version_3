import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderWithParametrizeTest {

    private OrderClient orderClient;
    private int track;
    private String[] color;

    @Step
    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    /**@Step Закомментировала потому что тесты падают из-за падающего тирдауна(баг в методе кенсел)
    @After
    public void tearDown(){
        orderClient.cancel(track);
    }**/

    public CreateOrderWithParametrizeTest(String color) {
        this.color = new String[]{color};
    }

    @Parameterized.Parameters
    public static Object[] getColorData() {
        return new Object[][]{
                {"GREY\" , \"BLACK"},
                {"GREY"},
                {"BLACK"},
                {""}
        };
    }

    @Test
    @DisplayName("Check creating orders with different datasets")
    public void orderCanBeCreated() {
        //Arrange
        Order order = Order.getRandom();
        order.setColor(color);
        //Act
        ValidatableResponse createdOrder = orderClient.create(order);
        track = createdOrder.extract().path("track");
        //Assert
        assertEquals("Статус не 201 ок.Заказ не создан!",201, createdOrder.extract().statusCode());
        assertThat("Айди пустой",track, notNullValue());
    }




}
