package Process;

import java.io.IOException;

import Composables.FileManager;
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
				Helpers.HabitsDisplay.showHabits(dailyHabits, dailyHabitMinutes);
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

			for (int i = 0; i < newDailyHabits.length; i++) {
				String line = String.format("%s#%d", newDailyHabits[i], newDailyHabitMinutes[i]);
				archiveUtil.setCreateArchive(line, routes[0], i != (newDailyHabits.length - 1));
			}
			for (int i = 0; i < newHabitTimeSpentDaily.length; i++) {
				for (int j = 0; j < newHabitTimeSpentDaily[i].length; j++) {
					for (int k = 0; k < newHabitTimeSpentDaily[i][j].length; k++) {
						archiveUtil.setCreateArchive(String.format("%s ", newHabitTimeSpentDaily[i][j][k]), routes[1],
								false);
					}
					FileManager.addNewLine(archiveUtil.getRouter() + routes[1] + ".txt");
				}
				FileManager.addNewLine(archiveUtil.getRouter() + routes[1] + ".txt");
			}

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

			System.out.println(newHabitsVector.length);
			for (int i = 0; i < newHabitsVector.length; i++) {
				String line = String.format("%s#%d", newHabitsVector[i], newMinutesVector[i]);
				archiveUtil.setCreateArchive(line, routes[0], i != (newHabitsVector.length - 1));
			}
			for (int i = 0; i < newHabitTimeSpentDaily.length; i++) {
				for (int j = 0; j < newHabitTimeSpentDaily[i].length; j++) {
					for (int k = 0; k < newHabitTimeSpentDaily[i][j].length; k++) {
						archiveUtil.setCreateArchive(String.format("%s ", newHabitTimeSpentDaily[i][j][k]), routes[1],
								false);
					}
					FileManager.addNewLine(archiveUtil.getRouter() + routes[1] + ".txt");
				}
				FileManager.addNewLine(archiveUtil.getRouter() + routes[1] + ".txt");
			}

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
			int[] date = Dates.getDate();

			day = date[0] - 1;
			month = date[1] - 1;
			habitPosition = Validations.DataValidations.chosePosition(dailyHabits);
			minutes = Validations.DataValidations.validateInt("Ingresa los minutos que realizaste: ", 1, 1440);

			habitTimeSpentDaily[month][day][habitPosition] += minutes;
			// Composables.HabitsFileWriter.savePerformance(route + ".txt", habitTimeSpentDaily, archiveUtil);
			for (int i = 0; i < habitTimeSpentDaily.length; i++) {
				for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
					for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++) {
						archiveUtil.setCreateArchive(String.format("%s ", habitTimeSpentDaily[i][j][k]), route,
								false);
					}
					FileManager.addNewLine(archiveUtil.getRouter() + route + ".txt");
				}
				FileManager.addNewLine(archiveUtil.getRouter() + route + ".txt");
			}
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

			monthsDays = Dates.getMonthsDays();
			monthPosition = Validations.DataValidations.chosePosition(months);
			month = months[monthPosition];
			monthLastDay = monthsDays[monthPosition];

			completedDays = new int[monthLastDay][dailyHabits.length];
			Initializer.initializeArray(completedDays);
			HabitsArrays.getCompletedDays(monthPosition, monthLastDay, dailyHabitsMinutes, habitTimeSpentDaily,
					completedDays);

			Helpers.HabitsDisplay.showMonthlyHabitTracker(month, dailyHabits, completedDays);

			completedDays = null;
			monthsDays = null;
			months = null;
		} else {
			System.out.println("\n¡Lo sentimos!. Esta función no está disponible en estos momentos");
			System.out.println("Vuelva a intentarlo más tarde\n");
		}
	}
}
