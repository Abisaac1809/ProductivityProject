package Users;

import java.util.Scanner;

public class UsersMain {
    public static String main(Scanner input) {
        String username = UsersValidation.getSession();
        if (input != null) {
            if (username == "") {
                int option = UsersDevelopment.loginMenu(input);
                username = "";
                if (option == 1) {
                    username = UsersDevelopment.signin(input);
                } else if (option == 2) {
                    username = UsersDevelopment.signup(input);
                }
            }
        }
        return username;
    }   
}
