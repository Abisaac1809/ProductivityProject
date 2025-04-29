package Functionalities;

import Composables.FileManager;
import Repositories.ArchiveUtil;
import Repositories.Task;
import Structures.List;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TasksFunctionalities {
	public static void menu(Scanner input, String username, ArchiveUtil archiveUtil) {

		if (input != null && username != null) {
			int option = 0;
			LocalDateTime actualDateTime = LocalDateTime.now();
            int rand = (int) (Math.random() * 20000) + 10000;
            String file1 = username + "task_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
			file1=FileManager.getToFile(file1, archiveUtil.getRouter());
			while (option != 4) {
				System.out.println("\n-----TAREAS-----\n");
				System.out.println("1. Crear Tarea");
				System.out.println("2. Buscar Tarea");
				System.out.println("---------------------");
				System.out.println("4. Menu Principal");
				System.out.print("\n- Ingrese su opción: ");
				option = Validations.DataValidations.option(input, 1, 4);
				FileManager.createFileIfNotExists(archiveUtil.getRouter() + file1+".txt");
				List tasks = Helpers.TasksFileReader.getTasks(file1+".txt", archiveUtil);
				if (option == 1)
					createTask(input, username, tasks, archiveUtil, file1);
				if (option == 2)
					searchTask(input, username, tasks);
			}
		}
	}

	private static void createTask(Scanner input, String username, List tasks, ArchiveUtil archiveUtil, String file1) {
		if (input != null && username != null && tasks != null) {
			System.out.print("- Ingrese el titulo de la tarea: ");
			String title = Validations.TasksValidations.title(input);
			System.out.print("- Ingrese la descripcion de la tarea: ");
			String description = Validations.TasksValidations.description(input);
			Task task = new Task(title, description, "No Hecha");
			tasks.add(task);
			Task currentTask = (Task) tasks.get(tasks.size()-1);
			String text = String.format("%s,%s,%s\n", currentTask.getTitle(), currentTask.getDescription(),
			currentTask.getStatus());
			archiveUtil.setCreateArchive(text, file1, false);
			task=null;
		}
	}

	private static void searchTask(Scanner input, String username, List tasks) {
		if (input != null && username != null && tasks != null) {
			int field = Validations.TasksValidations.getField(input);
			List finded = new List();
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
				System.out.println("TAREAS ENCONTRADAS-");
				for (int i = 0; i < finded.size(); i++) {
					Task task = (Task) finded.get(i);
					System.out.printf("%2d.  %-20s  [%s]\n    %s\n", i + 1, task.getTitle(),
							task.getStatus(), task.getDescription());
				}
			} else {
				System.out.println("TAREAS NO ENCONTRADAS!");
			}
		}
	}
}
