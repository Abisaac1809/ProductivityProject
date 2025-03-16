package Validations;

import java.util.Scanner;

public class UsersValidations {
    public static String username(boolean isLogin, Scanner input) {
        String value = "";
        if (input != null) {
            while (true) {
                value = input.next();
                if (Helpers.UsersFileReader.userExists(value) && !isLogin) {
                    System.out.println("Error: [Nombre de usuario ya en uso]");
                    System.out.print("- Ingrese otro nombre de usuario: ");
                } else if (!Helpers.UsersFileReader.userExists(value) && isLogin) {
                    System.out.println("Error: [Nombre de usuario no existe]");
                    System.out.print("- Ingrese otro nombre de usuario: ");
                } else if (DataValidations.stringCharByChar(value, 0)) {
                    return value;
                } else {
                    System.out.println("Error: [Caracter invalido encontrado]");
                    System.out.print("- Ingrese un nombre correcto: ");
                }
            }
        }
        return value;
    }    
}
