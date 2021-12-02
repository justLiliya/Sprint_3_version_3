import org.apache.commons.lang3.RandomStringUtils;

public class CourierWithoutFirstName {

    public final String login;
    public final String password;
    public final String firstname;

    public CourierWithoutFirstName(String login, String password, String firstname){
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }
    //Хелпер-метод, генерирующий данные
    public static CourierWithoutFirstName getRandomWithoutFirstName() {

        final String login = RandomStringUtils.randomAlphabetic(10);;
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstname = "";
        return new CourierWithoutFirstName(login, password, firstname);

    }
}
