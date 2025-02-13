package Users;

import java.util.Scanner;

public class UsersDevelopment {
    static int loginMenu(Scanner input) {
        int option = 0;
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.print("- Ingrese su opción: ");
        option = UsersValidation.option(input);
        return option;
    }    
    static String signup(Scanner input) {
        System.out.print("- Nombre de usuario: ");
        String username = UsersValidation.username(false, input);
        System.out.print("- Contraseña: ");
        String password = input.next();
        UsersValidation.saveUser(username, UsersValidation.encrypt(password));
        UsersValidation.createFinancesFile(username);
        UsersValidation.createHabitsFile(username);
        // TODO: create tasks file
        return username;
    }
    static String signin(Scanner input) {
        System.out.print("- Nombre de usuario: ");
        String username = UsersValidation.username(true, input);
        UsersValidation.checkPassword(username, input);
        return username;
    }
}
