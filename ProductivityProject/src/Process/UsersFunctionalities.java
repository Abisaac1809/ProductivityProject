package Process;

import java.util.Scanner;

public class UsersFunctionalities {
    static int loginMenu(Scanner input) {
        int option = 0;
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.println("---------------------");
        System.out.println("3. Salir del Programa");
        System.out.print("- Ingrese su opción: ");
        option = Validations.DataValidations.option(input);
        return option;
    }    
    static String signup(Scanner input) {
        System.out.print("- Nombre de usuario: ");
        String username = Validations.UsersValidations.username(false, input);
        System.out.print("- Contraseña: ");
        String password = input.next();
        Composables.UsersFileWriter.saveUser(username, Encrypt.encrypt(password));
        Validations.FileManager.createHabitsFile(username);
        
        Validations.FileManager.createSessionFile(username);
        return username;
    }
    static String signin(Scanner input) {
        System.out.print("- Nombre de usuario: ");
        String username = Validations.UsersValidations.username(true, input);
        Helpers.UsersFileReader.checkPassword(username, input);
        Validations.FileManager.createSessionFile(username);
        return username;
    }    
}
