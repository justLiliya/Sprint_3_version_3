import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class GettingListOfOrdersTest {

    private OrderClient orderClient;
    private CourierClient courierClient;
    private int listHaveElements;
    ArrayList<String> lists;

    @Step
    @Before
    public void setUp() {

        courierClient = new CourierClient();
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check checking receipt of order list without courierId")
    public void getOrderListTest(){
        //Act
        ValidatableResponse listoforders = orderClient.getOrders();
        lists = listoforders.extract().path("orders");
        listHaveElements = lists.size();
        //Assert
        assertThat("Список пуст!", listHaveElements,notNullValue());

    }

}
