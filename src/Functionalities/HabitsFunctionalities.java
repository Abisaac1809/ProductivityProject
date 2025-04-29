package Functionalities;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Process.Initializer;
import Repositories.ArchiveUtil;

public class HabitsFunctionalities {
	public static void habitsManagmentMenu(String[] routes, String[] dailyHabits, int[] dailyHabitMinutes,
			int[][][] habitTimeSpentDaily, ArchiveUtil archiveUtil) throws IOException {

		if (routes != null && dailyHabits != null && dailyHabitMinutes != null && habitTimeSpentDaily != null) {

			int opcion = 0;

			System.out.printf("\n%30s", "Gestión de Hábitos\n\n");
			System.out.println("1. Mostrar Hábitos Fijados");
			System.out.println("2. Fijar Hábito");
			System.out.println("3. Eliminar Hábito");
			System.out.println("4. Registrar progreso");
			System.out.println("5. Mostrar Rendimiento Mensual");
			System.out.println("------------------------------");
			System.out.println("6. Salir");

			opcion = Validations.DataValidations.validateInt("Ingrese la opción: ", 1, 6);

			if (opcion == 1)
				showHabits(dailyHabits, dailyHabitMinutes);
			if (opcion == 2)
				addHabit(routes, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily, archiveUtil);
			if (opcion == 3)
				deleteHabit(routes, dailyHabits, dailyHabitMinutes, habitTimeSpentDaily, archiveUtil);
			if (opcion == 4)
				registerHabit(routes[1], dailyHabits, dailyHabitMinutes, habitTimeSpentDaily, archiveUtil);
			if (opcion == 5)
				monthlyHabitTracker(dailyHabits, dailyHabitMinutes, habitTimeSpentDaily);
			if (opcion == 6)
				;
		} else {
			System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
			System.out.println("Vuelva a intentarlo más tarde\n");
		}
	}

	private static void addHabit(String[] routes, String[] dailyHabits, int[] dailyHabitMinutes,
			int[][][] habitTimeSpentDaily, ArchiveUtil archiveUtil) throws IOException {

		if (routes != null && dailyHabits != null && dailyHabitMinutes != null
				&& habitTimeSpentDaily != null) {

			String newHabit = "";
			int newMinutes = 0;
			String[] newDailyHabits;
			int[] newDailyHabitMinutes;
			int[][][] newHabitTimeSpentDaily;

			newHabit = Validations.DataValidations.validateString("Ingresa el nuevo hábito: ").toUpperCase();
			newMinutes = Validations.DataValidations.validateInt("Ingresa los minutos que quieres realizar: ", 1, 1440);

			if (Validations.HabitsValidations.habitNameExist(dailyHabits, 0, newHabit)) {
				System.out.println("\nError: [El hábito " + newHabit + " ya existe]\n");
				return;
			}
			newDailyHabits = new String[dailyHabits.length + 1];
			newDailyHabitMinutes = new int[dailyHabitMinutes.length + 1];
			newHabitTimeSpentDaily = new int[12][31][dailyHabits.length + 1];

			Initializer.initializeVector(newDailyHabits);
			Initializer.initializeVector(newDailyHabitMinutes);
			Initializer.initializeArray(newHabitTimeSpentDaily);

			HabitsArrays.appendHabit(dailyHabits, dailyHabitMinutes, newDailyHabits, newDailyHabitMinutes, newHabit,
					newMinutes);
			HabitsArrays.appendHabitInPerformance(habitTimeSpentDaily, newHabitTimeSpentDaily);

			Composables.FileManager.saveHabits(archiveUtil, routes[0], newDailyHabits, newDailyHabitMinutes);
			Composables.FileManager.savePerformance(archiveUtil, routes[1], newHabitTimeSpentDaily);

			newDailyHabits = null;
			newDailyHabitMinutes = null;
			newHabitTimeSpentDaily = null;
		} else {
			System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
			System.out.println("Vuelva a intentarlo más tarde\n");
		}
	}

	private static void deleteHabit(String[] routes, String[] dailyHabits, int[] dailyHabitMinutes,
			int[][][] habitTimeSpentDaily, ArchiveUtil archiveUtil) throws IOException {

		if (routes != null && dailyHabits != null && dailyHabitMinutes != null) {

			String[] newHabitsVector;
			int habitPosition = 0;
			int[] newMinutesVector;
			int[][][] newHabitTimeSpentDaily;

			habitPosition = Validations.DataValidations.chosePosition(dailyHabits);

			newHabitsVector = new String[dailyHabits.length - 1];
			newMinutesVector = new int[dailyHabitMinutes.length - 1];
			newHabitTimeSpentDaily = new int[12][31][dailyHabits.length - 1];

			Initializer.initializeVector(newHabitsVector);
			Initializer.initializeVector(newMinutesVector);
			Initializer.initializeArray(newHabitTimeSpentDaily);

			HabitsArrays.popHabit(habitPosition, dailyHabits, dailyHabitMinutes, newHabitsVector, newMinutesVector);
			HabitsArrays.popHabitInPerformance(habitPosition, dailyHabits, habitTimeSpentDaily, newHabitTimeSpentDaily);

			Composables.FileManager.saveHabits(archiveUtil, routes[0], newHabitsVector, newMinutesVector);
			Composables.FileManager.savePerformance(archiveUtil, routes[1], newHabitTimeSpentDaily);

			newHabitsVector = null;
			newMinutesVector = null;
			newHabitTimeSpentDaily = null;
		} else {
			System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
			System.out.println("Vuelva a intentarlo más tarde\n");
		}
	}

	public static void registerHabit(String route, String[] dailyHabits, int[] dailyHabitMinutes,
			int[][][] habitTimeSpentDaily, ArchiveUtil archiveUtil) throws IOException {

		if (route != null && dailyHabits != null && dailyHabitMinutes != null && habitTimeSpentDaily != null
				&& dailyHabits.length == dailyHabitMinutes.length) {

			int day = 0;
			int month = 0;
			int habitPosition = 0;
			int minutes = 0;
			int[] date = getDate();

			day = date[0] - 1;
			month = date[1] - 1;
			habitPosition = Validations.DataValidations.chosePosition(dailyHabits);
			minutes = Validations.DataValidations.validateInt("Ingresa los minutos que realizaste: ", 1, 1440);

			habitTimeSpentDaily[month][day][habitPosition] += minutes;
			// Composables.HabitsFileWriter.savePerformance(route + ".txt", habitTimeSpentDaily, archiveUtil);
			Composables.FileManager.savePerformance(archiveUtil, route, habitTimeSpentDaily);

			System.out.println("Se ha registrado tu progreso");

			date = null;
		} else {
			System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
			System.out.println("Vuelva a intentarlo más tarde\n");
		}
	}

	public static void monthlyHabitTracker(String[] dailyHabits, int[] dailyHabitsMinutes,
			int[][][] habitTimeSpentDaily) {

		if (dailyHabits != null && dailyHabitsMinutes != null && habitTimeSpentDaily != null) {
			int monthPosition = 0;
			int monthLastDay = 0;
			int[][] completedDays;
			int[] monthsDays;
			String month;
			String[] months = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
					"Septiembre", "Octubre", "Noviembre", "Diciembre" };

			monthsDays = getMonthsDays();
			monthPosition = Validations.DataValidations.chosePosition(months);
			month = months[monthPosition];
			monthLastDay = monthsDays[monthPosition];

			completedDays = new int[monthLastDay][dailyHabits.length];
			Initializer.initializeArray(completedDays);
			HabitsArrays.getCompletedDays(monthPosition, monthLastDay, dailyHabitsMinutes, habitTimeSpentDaily,
					completedDays);

			showMonthlyHabitTracker(month, dailyHabits, completedDays);

			completedDays = null;
			monthsDays = null;
			months = null;
		} else {
			System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
			System.out.println("Vuelva a intentarlo más tarde\n");
		}
	}

    public static void showHabits(String[] dailyHabits, int[] dailyHabitMinutes) {
        
        if (dailyHabits != null && dailyHabitMinutes != null) {
            
            if (dailyHabits.length == 0) {
                
                System.out.println("\nTodavía no has fijado ningún hábito\n");
            }
            else {
                
                System.out.printf("%30s\n\n", "HÁBITOS FIJADOS");
                System.out.printf("%-40s %-40s\n", "HÁBITO", "MINUTOS");
        
                for (int i = 0; i < dailyHabits.length; i++) {
                    System.out.printf("%-40s %-40d\n", dailyHabits[i], dailyHabitMinutes[i]);
                }     
            }  
        }
        else {
            System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
            System.out.println("Vuelva a intentarlo más tarde\n");
        }
    }
    
    public static void showMonthlyHabitTracker(String month, String[] dailyHabits, int [][]completedDays) {
        
        if (month != null && dailyHabits != null && completedDays != null) {
            String mark;
        
            System.out.printf("\nRENDIMIENTO DE %s\n", month.toUpperCase());
            printLine(43 + (3 * completedDays.length));
            System.out.printf("%-40s|  ", "HÁBITO");
        
            for (int i = 0; i < completedDays.length; i++){
                System.out.printf("%-3d", (i+1));
            }
            System.out.println("|");
        
            printLine(43 + (3 * completedDays.length));
        
            for (int i = 0; i < dailyHabits.length; i++) {
                System.out.printf("%-40s|  ", dailyHabits[i]);
                for (int j = 0; j < completedDays.length; j++) {
                    mark = (completedDays[j][i] == 1) ? "X" : "-"; 
                    System.out.printf("%-3s", mark);
                }
                System.out.println("|");
            }
            printLine(43 + (3 * completedDays.length));    
        }   
    }
    
    private static void printLine (int numberOfCharacters) {
        
        if (numberOfCharacters > 0) {
            for (int i = 0; i < numberOfCharacters; i++) {
                System.out.print("-");
            }
            System.out.println("|");
        }
    }
	
    public static int[] getDate(){
        String stringDate = "";
        int[] date;
        date = new int[3];
        Initializer.initializeVector(date);
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        stringDate = localTime.format(formater);
        Scanner scanner = new Scanner(stringDate);
        scanner.useDelimiter("-");
        date[0] = scanner.nextInt();
        date[1] = scanner.nextInt();
        date[2] = scanner.nextInt();
        
        localTime = null;
        formater = null;
        scanner.close();
        
        return date;
    }
    
    public static int[] getMonthsDays() {
        int year = 0;
        int[] date = getDate();
        int[] normalYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        year = date[2];        
        
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return leapYear;
        }
        else {
            return normalYear;
        }
    }    
}
