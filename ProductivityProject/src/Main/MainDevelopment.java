package Main;

import java.io.IOException;
import java.util.Scanner;
import Users.UsersMain;
import Finances.FinanceMain;
import Habits.HabitsMain;

public class MainDevelopment {
    static void mainMenu(Scanner input) throws IOException {
        int option = 0;
        if (input != null) {
            String username = UsersMain.main(input);
            if (username != "") {
                while (option != 5 && username != "") {
                    System.out.printf("\n---BIENVENIDO %s---\n\n", username);
                    System.out.println("1. Finanzas");
                    System.out.println("2. Hábitos");
                    System.out.println("3. Tareas");
                    System.out.println("------------------------");
                    System.out.println("4. Cerrar Sesión");
                    System.out.println("5. Salir del Programa");
                    System.out.print("\n- Ingrese su opción: ");
                    option = MainValidation.option(input);
                    if (option == 1) FinanceMain.main(username);
                    if (option == 2) HabitsMain.HabitsMain(username);
                    // TODO: add tasks main implementation
                    if (option == 4) {
                        System.out.println("");
                        MainValidation.logout();
                        username = UsersMain.main(input);
                    }
                }
            }
        }
    }
}
