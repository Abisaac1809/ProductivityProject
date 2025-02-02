package Users;

public class UsersMain {
    public static String main() {
        int option = UsersDevelopment.loginMenu();
        String username = "";
        if (option == 1) {
            System.out.println("iniciaste sesion");
        } else if (option == 2) {
            username = UsersDevelopment.signup();
        }
        return username;
    }   
}
