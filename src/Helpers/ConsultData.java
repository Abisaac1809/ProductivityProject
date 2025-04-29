package Helpers;

import Composables.FileManager;
import Functionalities.HabitsArrays;
import Process.Initializer;
import Repositories.ArchiveUtil;
import Repositories.DailyHabit;
import Repositories.Finance;
import Repositories.Task;
import Structures.List;
import Structures.Node;
import Structures.Queue;
import Structures.Stack;
import Validations.DataValidations;
import Validations.FinanceValidation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class ConsultData {

	public static void saving(Stack<Finance> pila, Stack<Task> pila2, Stack<DailyHabit> pila3, String[] serials) throws IOException {
		LocalDateTime actualDateTime = LocalDateTime.now();
		int rand = (int) (Math.random()*20000)+10000;
		String text="";
		Scanner se = null;
		for(int i=0;i<serials.length;i++){
			if(serials[i]!=null){
				 se = new Scanner(serials[i]).useDelimiter(".txt");
				text+=se.next();
			}	
		}
		se.close();
		String path = Paths.get(".").toRealPath().toString() + "/src/Storage/SearchResults/"+text+"_"+actualDateTime.toLocalDate().toString()+"_"+rand+".txt";
		File file = new File(path);
		file.createNewFile();
		FileWriter writer = new FileWriter(file, false);
		if(pila.getSize()!=0){
			int i = pila.getSize();
			for (int j = 0; j < i; j++) {
				writer.write(pila.getTop().toStringContent());
				pila.pop();
			}
		}
		if(pila2.getSize()!=0){
			int i = pila2.getSize();
			writer.write("\n");
			for (int j = 0; j < i; j++) {
				writer.write(pila2.getTop().toStringContent());
				pila2.pop();
			}
		}
		if(pila3.getSize()!=0){
			int i = pila3.getSize();
			writer.write("\n");
			for (int j = 0; j < i; j++) {
				writer.write(pila3.getTop().toStringContent());
				pila3.pop();
			}
		}
		writer.close();
	}

	public static void showQueueAndStacking(Queue<Finance> cola, Queue<Task> cola2, Queue<DailyHabit> cola3, Stack<Finance> pila, Stack<Task> pila2, Stack<DailyHabit> pila3){
		System.out.println("\n-----------------------");
		System.out.println("Resultados de la busqueda\n");
		if(cola.getSize()!=0){
			int i = cola.getSize();
			for (int j = 0; j < i; j++) {
				String data = cola.getFront().toStringContent();
				System.out.println(data);
				pila.top(cola.getFront());
				cola.cutNode();
			}
		}
		if(cola2.getSize()!=0){
			int i = cola2.getSize();
			for (int j = 0; j < i; j++) {
				String data = cola2.getFront().toStringContent();
				System.out.println(data);
				pila2.top(cola2.getFront());
				cola2.cutNode();
			}
		}
		if(cola3.getSize()!=0){
			int i = cola3.getSize();
			for (int j = 0; j < i; j++) {
				String data = cola3.getFront().toStringContent();
				System.out.println(data);
				pila3.top(cola3.getFront());
				cola3.cutNode();
			}
		}
	}

	public static Node<Finance> financeSearchMenu(Scanner lectura, String username, String[] serials) {
		try {
			LocalDateTime actualDateTime = LocalDateTime.now();
            int rand = (int) (Math.random() * 20000) + 10000;
            String file = username + "finances_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/FinanceFiles/";
			file = FileManager.getToFile(file, path);
			serials[0] = FileManager.getFileSerial(file);
			ArchiveUtil archive = new ArchiveUtil(path);
			Finance userMoney = new Finance();
			int option = 0;
			if (lectura != null) {
				if (username != "") {
					do {
						System.out.printf("\n-----------------------\n\n");
						System.out.println("Que dato desea buscar?");
						System.out.println("1. Saldo");
						System.out.println("2. Deudas (Por Monto)");
						System.out.println("3. Deudas (Por Concepto)");
						System.out.println("------------------------");
						System.out.println("4. Regresar");
						System.out.print("\n- Ingrese su opción: ");
						option = DataValidations.option(lectura);
						if (option == 1) {
							userMoney.setMoney(getFinanceBalance(archive, file, lectura));
							System.out.println("\n Hecho!");
						}
						if (option == 2) {
							String files = path + file;
							String text = "Ingrese el monto de la deuda- ";
							Double monto = FinanceValidation.valEntry(text);
							String[] debts = getFinanceDebtsAmount(archive, file, lectura, monto);
							for (int i = 0; i < debts.length; i++) {
								userMoney.loadTitle("" + debts[i]);
							}
							System.out.println("\n Hecho!");
						}
						if (option == 3) {
							String files = path + file;
							String text = "Ingrese el concepto de la deuda";
							String concept = FinanceValidation.valConcept(text, file);
							userMoney.setSpeDebt(getFinanceDebtsTitle(archive, file, lectura, concept));
							System.out.println("\n Hecho!");
						}
						if (option == 4) {
							archive = null;
							return new Node<>(userMoney);
						}
					} while (option != 5);
				}
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public static double getFinanceBalance(ArchiveUtil archive, String file, Scanner lectura) throws FileNotFoundException {
		if(new File(archive.getRouter()+file).exists()){
			lectura = archive.getArchive(file);
			Double num = Double.valueOf(lectura.nextLine());
			return num;
		}
		System.out.println("\nError: El archivo a buscar no existe");
		return 0;
	}

	public static String[] getFinanceDebtsAmount(ArchiveUtil archive, String file, Scanner lectura, Double amount) throws FileNotFoundException {
		if(new File(archive.getRouter()+file).exists()){
			lectura = archive.getArchive(file);
			String line;
			String[] content = new String[FinanceFileReader.cantReg(file)];
			Scanner sep = null;
			int n = 0;
			Double aux;
			lectura.nextLine();
			while (lectura.hasNextLine()) {
				line = lectura.nextLine();
				sep = new Scanner(line).useDelimiter(" ");
				aux = Double.valueOf(sep.next());
				if (Objects.equals(aux, amount)) {
					content[n] = sep.next();
					n++;
				}
			}
		return content;
		}
		System.out.println("\nError: El archivo a buscar no existe");
		return null;
	}

	public static String getFinanceDebtsTitle(ArchiveUtil archive, String file, Scanner lectura, String title) throws FileNotFoundException{
		if(new File(archive.getRouter()+file).exists()){
			lectura = archive.getArchive(file);
			String line;
			String aux;
			Scanner sepa = null;
			lectura.nextLine();
			while (lectura.hasNextLine()) {
				line = lectura.nextLine();
				sepa = new Scanner(line).useDelimiter(" ");
				sepa.next();
				aux = sepa.next();
				if (aux.equals(title)) {
					sepa.close();
					return line;
				}
			}
		}
		System.out.println("\nError: El archivo a buscar no existe");
		return "";
	}

	public static Queue taskSearchMenu(Scanner lectura, String username, Queue<Task> cola2, String[] serials) throws IOException {
			LocalDateTime actualDateTime = LocalDateTime.now();
            int rand = (int) (Math.random() * 20000) + 10000;
			String file = username + "task_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/TaskFiles/";
			file = FileManager.getToFile(file, path);
			serials[2] = FileManager.getFileSerial(file);
			ArchiveUtil archive = new ArchiveUtil(path);
			List tasks = Helpers.TasksFileReader.getTasks(file, archive);
			List finded = new List();
			int option = 0;
			if (lectura != null) {
				if (username != "") {
					do {
						System.out.printf("\n-----------------------\n\n");
						System.out.println("Como desea buscar?");
						System.out.println("1. Nombre");
						System.out.println("2. Estatus");
						System.out.println("------------------------");
						System.out.println("3. Regresar");
						System.out.print("\n- Ingrese su opción: ");
						option = DataValidations.option(lectura);
						if (option == 1) {
							searchTaskByTitle(lectura, username, tasks, finded);
							Scanner sep=null;
							for(int i=0;i<finded.size();i++){
								cola2.addNode(new Node<Task>((Task) finded.get(i)));
							}
							System.out.println("\n Hecho!");
						}
						if (option == 2) {
							searchTaskByStatus(lectura, username, tasks, finded);
							Scanner sep=null;
							for(int i=0;i<finded.size();i++){
								cola2.addNode(new Node<Task>((Task) finded.get(i)));
							}
							System.out.println("\n Hecho!");
						}
						if (option == 3) {
							tasks = null;
							finded=null;
							return cola2;
						}
					} while (option != 5);
				}
			}
			return cola2;
	}

	private static void searchTaskByTitle(Scanner input, String username, List tasks, List finded) {
		if (input != null && username != null && tasks != null) {
			System.out.print("Ingrese el nombre: ");
			String query = Validations.TasksValidations.title(input);
			finded = Helpers.TasksFileReader.findTasksTitle(tasks, query, finded, 0);
		}
	}

	private static void searchTaskByStatus(Scanner input, String username, List tasks, List finded){
		System.out.print("- Ingrese el estado: ");
		String query = Validations.TasksValidations.estatus(input);
		finded = Helpers.TasksFileReader.findTasksStatus(tasks, query, finded, 0);
	}

    public static Node<DailyHabit> habitSearchMenu(String user, String[] serials) throws IOException {
        
        if (user != null) {
            String route = Paths.get("").toAbsolutePath().toString()+ "/src/Storage/HabitsFiles/";
            LocalDateTime actualDateTime = LocalDateTime.now();
            int rand = (int) (Math.random() * 20000) + 10000;
            String file1 = user + "habits_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
            file1 = FileManager.getToFile(file1, route);
            serials[1]= FileManager.getFileSerial(file1);
            String habitsRoute = file1;
            file1 = user + "perfomance_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
            file1 = FileManager.getToFile(file1, route);
			String performanceUserRoute = file1;
            int rows = 0;
            try {
                ArchiveUtil archiveUtil = new ArchiveUtil(route);
            }
            catch (FileNotFoundException | IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
            }
            ArchiveUtil archiveUtil;
            String[] dailyHabits;
            String[] routes;
            int[] dailyHabitMinutes;
            int[][][] habitTimeSpentDaily;
            
            rows = FileManager.determineRows(route+habitsRoute);

            FileManager.createFileIfNotExists(route+habitsRoute);
            FileManager.createFileIfNotExists(route+performanceUserRoute);

            archiveUtil = new ArchiveUtil(route);
            dailyHabits = new String[rows];
            routes = new String[2];
            dailyHabitMinutes = new int[rows];
            habitTimeSpentDaily = new int[12][31][rows];
            
            Initializer.initializeVector(routes);
            Initializer.initializeVector(dailyHabits);
            Initializer.initializeVector(dailyHabitMinutes);
            Initializer.initializeArray(habitTimeSpentDaily);
        
            HabitsFileReader.getDailyHabits(habitsRoute, archiveUtil, dailyHabits, dailyHabitMinutes);
            HabitsFileReader.getHabitTimeSpentDaily(performanceUserRoute, archiveUtil, habitTimeSpentDaily);
            
            int[] completedDays = HabitsArrays.countCompletedDays(dailyHabitMinutes, habitTimeSpentDaily);
            
            DailyHabit habit = menu(dailyHabits, dailyHabitMinutes, completedDays);
            return new Node<>(habit);
        }
        return null;
    }
    
    private static DailyHabit menu(String[] dailyHabits, int[] dailyHabitMinutes, int[] completedDays) {
        if (dailyHabits == null || dailyHabitMinutes == null || completedDays == null) {
            throw new IllegalArgumentException("Los argumentos no pueden ser nulos");
        }
        int option;
        System.out.printf("\n-----------------------\n\n");
        System.out.println("Inserta el tipo de búsqueda de tu rendimiento");
        System.out.println("1. Búsqueda por hábito");
        System.out.println("------------------------");
        option = DataValidations.validateInt("Ingrese su opción- ", 1, 1);
        
        if (option == 1) {
            DailyHabit habit = searchByHabit(dailyHabits, dailyHabitMinutes, completedDays);
            return habit;
        }
        return null;
    }
    
    private static DailyHabit searchByHabit(String[] dailyHabits, int[] dailyHabitMinutes, int[] completedDays) {
        if (dailyHabits == null || dailyHabitMinutes == null || completedDays == null) {
            throw new IllegalArgumentException("Los argumentos no pueden ser nulos");
        }
        int option = DataValidations.chosePosition(dailyHabits);
        for (int i = 0; i < dailyHabits.length; i++) {
            if (i == option) {
                String habit = dailyHabits[i];
                int minutes = dailyHabitMinutes[i];
                int completedDaysInYear = completedDays[i];
                DailyHabit newHabit = new DailyHabit(habit, minutes, completedDaysInYear);
                return newHabit;
            }
        }
        return null;
    }
}
