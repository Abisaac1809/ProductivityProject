package Users;

public class UsersMain {
    public static String main() {
        int option = UsersDevelopment.loginMenu();
        String username = "";
        if (option == 1) {
            username = UsersDevelopment.signin();
        } else if (option == 2) {
            username = UsersDevelopment.signup();
        }
        return username;
    }   
}
