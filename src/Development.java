
import Composables.FileManager;
import Helpers.ConsultMain;
import Process.FinanceMain;
import Process.HabitsMain;
import Process.TasksMain;
import Process.UsersMain;
import Repositories.ArchiveUtil;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Development {
    static void mainMenu(Scanner input, ArchiveUtil archiveUtil) throws IOException {
        int option = 0;
        if (input != null) {
            String username = UsersMain.main(input, archiveUtil);
            if (username != "") {
                while (option != 6 && username != "") {
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
                    if (option == 1) FinanceMain.main(username, input, archiveUtil);
                    if (option == 2) HabitsMain.HabitsMain(username, archiveUtil);
                    if (option == 3) TasksMain.main(username, input, archiveUtil);
					if (option == 4) ConsultMain.consultMenu(username, input);
                    if (option == 5) {
                        System.out.println("");
						String route = Paths.get("").toAbsolutePath().toString() + "/src/Storage/UsersFiles/session.txt";
						FileManager.emptyFile(route);
                        username = UsersMain.main(input, archiveUtil);
                    }
                }
            }
        }
    }
}
