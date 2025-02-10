package Users;

import java.util.Scanner;

public class UsersDevelopment {
    static int loginMenu() {
        int option = 0;
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.print("- Ingrese su opción: ");
        option = UsersValidation.option();
        return option;
    }    
    static String signup() {
        Scanner input = new Scanner(System.in);
        System.out.print("- Nombre de usuario: ");
        String username = UsersValidation.username(false);
        System.out.print("- Contraseña: ");
        String password = input.next();
        UsersValidation.saveUser(username, UsersValidation.encrypt(password));
        return username;
    }
    static String signin() {
        System.out.print("- Nombre de usuario: ");
        String username = UsersValidation.username(true);
        UsersValidation.checkPassword(username);
        return username;
    }
}
