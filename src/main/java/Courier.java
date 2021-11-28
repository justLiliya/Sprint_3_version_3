import org.apache.commons.lang3.RandomStringUtils;

//Класс с данными для создания курьера
public class Courier {

    public final String login;
    public final String password;
    public final String firstname;

    public Courier(String login, String password, String firstname){
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }
    //Хелпер-метод, генерирующий данные
    public static Courier getRandom() {

        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstname = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstname);

    }
}
