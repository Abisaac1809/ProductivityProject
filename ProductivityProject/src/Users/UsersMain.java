package Users;

import java.util.Scanner;

public class UsersMain {
    public static String main(Scanner input) {
        int option = UsersDevelopment.loginMenu(input);
        String username = "";
        if (option == 1) {
            username = UsersDevelopment.signin(input);
        } else if (option == 2) {
            username = UsersDevelopment.signup(input);
        }
        return username;
    }   
}
