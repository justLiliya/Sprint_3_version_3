import org.apache.commons.lang3.RandomStringUtils;

public class СourierWithoutPassword {

    public final String login;
    public final String password;
    public final String firstname;

    public СourierWithoutPassword(String login, String password, String firstname){
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }
    //Хелпер-метод, генерирующий данные
    public static СourierWithoutPassword getRandomWithoutPassword() {

        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = "";
        final String firstname = RandomStringUtils.randomAlphabetic(10);
        return new СourierWithoutPassword(login, password, firstname);

    }
}
