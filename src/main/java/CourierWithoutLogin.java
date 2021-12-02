import org.apache.commons.lang3.RandomStringUtils;

public class CourierWithoutLogin {

    public final String login;
    public final String password;
    public final String firstname;

    public CourierWithoutLogin(String login, String password, String firstname){
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }
    //Хелпер-метод, генерирующий данные
    public static CourierWithoutLogin getRandomWithoutLogin() {

        final String login = "";
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstname = RandomStringUtils.randomAlphabetic(10);
        return new CourierWithoutLogin(login, password, firstname);

    }
}
