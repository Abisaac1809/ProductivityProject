package Process;

import java.util.Scanner;

public class UsersMain {
    public static String main(Scanner input) {
        String username = Helpers.UsersFileReader.getSession();
        if (input != null) {
            if (username == "") {
                int option = UsersFunctionalities.loginMenu(input);
                username = "";
                if (option == 1) {
                    username = UsersFunctionalities.signin(input);
                } else if (option == 2) {
                    username = UsersFunctionalities.signup(input);
                }
            }
        }
        return username;
    }   
}
