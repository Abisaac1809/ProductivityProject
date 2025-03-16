package Process;

import java.util.ArrayList;
import java.util.Scanner;

public class TasksFunctionalities {
    static void menu(Scanner input, String username) {
        if (input != null && username != null) {
            int option = 0;
            while (option != 4) {
                System.out.println("\n-----TAREAS-----\n");
                System.out.println("1. Crear Tarea");
                System.out.println("2. Buscar Tarea");
                System.out.println("---------------------");
                System.out.println("4. Menu Principal");
                System.out.print("\n- Ingrese su opción: ");
                option = Validations.DataValidations.option(input, 1, 4);
                ArrayList<String[]> tasks = Helpers.TasksFileReader.getTasks(username);
                if (option == 1) createTask(input, username, tasks);
                if (option == 2) searchTask(input, username, tasks);
            }
        }
    }
    private static void createTask(Scanner input, String username, ArrayList<String[]> tasks) {
        if (input != null && username != null && tasks != null) {
            System.out.print("- Ingrese el titulo de la tarea: ");
            String title = Validations.TasksValidations.title(input);
            System.out.print("- Ingrese la descripcion de la tarea: ");
            String description = Validations.TasksValidations.description(input);
            String[] task = {title, description, "No Hecha"};
            tasks.add(task);
            Composables.TasksFileWriter.updateFile(tasks, input, username);
        }
    }
    private static void searchTask(Scanner input, String username, ArrayList<String[]> tasks) {
        if (input != null && username != null && tasks != null) {
            int field  = Validations.TasksValidations.getField(input);
            ArrayList<String[]> finded = new ArrayList<String[]>();
            if (field == 1) {
                System.out.print("- Ingrese el titulo: ");
                String query = Validations.TasksValidations.title(input);
                finded = Helpers.TasksFileReader.findTasksTitle(tasks, query, finded, 0);
            } else if (field == 2) {
                System.out.print("- Ingrese el estado: ");
                String query = Validations.TasksValidations.estatus(input);
                finded = Helpers.TasksFileReader.findTasksStatus(tasks, query, finded, 0);
            }
            if (finded.size() > 0) {
                System.out.println("TAREAS ENCONTRADAS:");
                for (int i = 0; i < finded.size(); i++) {
                    System.out.printf("%2d.  %-20s  [%s]\n    %s\n", i + 1, finded.get(i)[0], finded.get(i)[2], finded.get(i)[1]);
                }
            } else {
                System.out.println("TAREAS NO ENCONTRADAS!");
            }
        }
    }
}
