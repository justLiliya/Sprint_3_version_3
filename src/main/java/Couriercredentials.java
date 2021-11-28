//Класс с данными для логина
public class Couriercredentials {

    public final String login;
    public final String password;

    public Couriercredentials(String login, String password){
        this.login = login;
        this.password = password;
    }
    public static Couriercredentials from (Courier courier){
        return new Couriercredentials(courier.login, courier.password);
    }
}
