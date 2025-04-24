
import java.io.IOException;
import java.util.Scanner;
import Process.UsersMain;
import Process.FinanceMain;
import Process.HabitsMain;
import Process.TasksMain;

public class Development {
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
                    System.out.println("4. Buscar");
                    System.out.println("------------------------");
                    System.out.println("5. Cerrar Sesión");
                    System.out.println("6. Salir del Programa");
                    System.out.print("\n- Ingrese su opción: ");
                    option = Validation.option(input);
                    if (option == 1) FinanceMain.main(username);
                    if (option == 2) HabitsMain.HabitsMain(username);
                    if (option == 3) TasksMain.main(username, input);
                    if (option == 4) {
                        System.out.println("");
                        Validation.logout();
                        username = UsersMain.main(input);
                    }
                }
            }
        }
    }
}
