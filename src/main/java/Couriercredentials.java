//Класс с данными для логина
public class Couriercredentials {

    public String login;
    public String password;

    public Couriercredentials(String login, String password){
        this.login = login;
        this.password = password;
    }

    public Couriercredentials() {

    }
    public static Couriercredentials from (Courier courier){
        return new Couriercredentials(courier.login, courier.password);
    }

    public Couriercredentials setLogin(String login) {
        this.login = login;
        return this;
    }

    public Couriercredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public static Couriercredentials getWithLoginOnly(Courier courier) {
        return new Couriercredentials().setLogin(courier.login);
    }

    public static Couriercredentials getWithPasswordOnly(Courier courier) {
        return new Couriercredentials().setPassword(courier.password);
    }


}
