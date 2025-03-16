package Tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class TasksDevelopment {
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
                option = TasksValidation.option(input, 1, 4);
                ArrayList<String[]> tasks = TasksValidation.getTasks(username);
                if (option == 1) createTask(input, username, tasks);
                if (option == 2) searchTask(input, username, tasks);
            }
        }
    }
    private static void createTask(Scanner input, String username, ArrayList<String[]> tasks) {
        if (input != null && username != null && tasks != null) {
            System.out.print("- Ingrese el titulo de la tarea: ");
            String title = TasksValidation.title(input);
            System.out.print("- Ingrese la descripcion de la tarea: ");
            String description = TasksValidation.description(input);
            String[] task = {title, description, "No Hecha"};
            tasks.add(task);
            TasksValidation.updateFile(tasks, input, username);
        }
    }
    private static void searchTask(Scanner input, String username, ArrayList<String[]> tasks) {
        if (input != null && username != null && tasks != null) {
            int field  = TasksValidation.getField(input);
            ArrayList<String[]> finded = new ArrayList<String[]>();
            if (field == 1) {
                System.out.print("- Ingrese el titulo: ");
                String query = TasksValidation.title(input);
                finded = TasksValidation.findTasksTitle(tasks, query, finded, 0);
            } else if (field == 2) {
                System.out.print("- Ingrese el estado: ");
                String query = TasksValidation.estatus(input);
                finded = TasksValidation.findTasksStatus(tasks, query, finded, 0);
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
