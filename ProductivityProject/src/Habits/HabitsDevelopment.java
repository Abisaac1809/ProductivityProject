package Habits;

import java.io.IOException;
import java.nio.file.Paths;

public class HabitsDevelopment {
    
    public static void HabitsManagment(String user) throws IOException{
        
        if (user != null) {
            String userRoute = "";
            String route = "";
            int rows = 0;
            String[] habitsVector;
            int[] minutesVector;
        
            userRoute = "//src//Habits//HabitsDataBase//Habits." + user + ".txt";
            route = Paths.get("").toAbsolutePath().toString() + userRoute;
            rows = (Habits.HabitsValidation.userHasHabits(route)) ? (Habits.HabitsValidation.determineRows(route)) : 0;      
        
            habitsVector = new String[rows];
            minutesVector = new int[rows];
        
            Habits.HabitsValidation.initializeVector(habitsVector);
            Habits.HabitsValidation.initializeVector(minutesVector);
            
            Habits.HabitsValidation.fillVectors(route, habitsVector, minutesVector);
        
            habitsManagmentMenu(route, habitsVector, minutesVector);
        
            habitsVector = null;
            minutesVector = null;
        }
    }
    
    
    private static void habitsManagmentMenu(String route, String[] habitsVector, int[] minutesVector) throws IOException {
        
        if (route != null && habitsVector != null && minutesVector != null) {
            int opcion = 0;
        
            System.out.printf("%30s", "Gestión de Hábitos\n\n");
            System.out.println("1. Mostrar Hábitos Fijados");
            System.out.println("2. Fijar Hábito");
            System.out.println("3. Eliminar Hábito");
        
            opcion = Habits.HabitsValidation.validateInt("Ingrese la opción: ");
        
            switch(opcion) {
                case 1:
                    showHabits(habitsVector, minutesVector);
                    break;
            
                case 2: 
                    addHabit(route, habitsVector, minutesVector);
                    break;
                
                case 3:
                    deleteHabit(route, habitsVector, minutesVector);
                    break;
    
            }
        }
    }
    
    private static void addHabit(String route, String[] habitsVector, int[] minutesVector) throws IOException {
        
        if (route != null && habitsVector != null && minutesVector != null) {
            String newHabit = "";
            int newMinutes = 0;
            String[] newHabitsVector;
            int[] newMinutesVector;
        
            newHabit = Habits.HabitsValidation.validateString("Ingresa el nuevo hábito: ");
            newMinutes = Habits.HabitsValidation.validateInt("Ingresa los minutos que quieres realizar: ");
        
            newHabitsVector = new String[habitsVector.length + 1];
            newMinutesVector = new int[minutesVector.length + 1];
        
            Habits.HabitsValidation.initializeVector(newHabitsVector);
            Habits.HabitsValidation.initializeVector(newMinutesVector);
        
            appendHabit(habitsVector, minutesVector, newHabitsVector, newMinutesVector, newHabit, newMinutes);
            Habits.HabitsValidation.saveData(route, newHabitsVector, newMinutesVector);

            newHabitsVector = null;
            newMinutesVector = null;
        }

    }
    
    
    private static void appendHabit(String[] habitsVector, int[] minutesVector,
                                    String[] newHabitsVector, int[] newMinutesVector,
                                    String newHabit, int newMinutes){
        
        if (habitsVector != null && minutesVector != null
            && newHabitsVector != null && newMinutesVector != null
            && newHabit != null && newMinutes > 0) {
            
                 
            for (int i = 0; i < (newHabitsVector.length - 1); i++) {
                newHabitsVector[i] = habitsVector[i];
                newMinutesVector[i] = minutesVector[i];
            }
        
        newHabitsVector[newHabitsVector.length - 1] = newHabit;
        newMinutesVector[newMinutesVector.length - 1] = newMinutes;   
        }
    }
    
    
    private static void showHabits(String[] habitsVector, int[] minutesVector) {
        
        if (habitsVector != null && minutesVector != null) {
            System.out.printf("%30s\n\n", "Hábitos Fijados");
            System.out.printf("%-40s %-40s\n", "Hábito", "Minutos");
        
            for (int i = 0; i < habitsVector.length; i++) {
                System.out.printf("%-40s %-40d\n", habitsVector[i], minutesVector[i]);
            }   
        }
    }
    
    
    private static void deleteHabit(String route, String[] habitsVector, int[] minutesVector) throws IOException {
        
        if (route != null && habitsVector != null && minutesVector != null) {
            String habit = "";
            String[] newHabitsVector;
            int[] newMinutesVector;
        
            habit = Habits.HabitsValidation.validateString("Ingresa el hábito que deseas eliminar: ");
        
            newHabitsVector = new String[habitsVector.length - 1];
            newMinutesVector = new int[minutesVector.length - 1];
        
            Habits.HabitsValidation.initializeVector(newHabitsVector);
            Habits.HabitsValidation.initializeVector(newMinutesVector);
        
            popHabit(habit, habitsVector, minutesVector, newHabitsVector, newMinutesVector);
            Habits.HabitsValidation.saveData(route, newHabitsVector, newMinutesVector);
        
            newHabitsVector = null;
            newMinutesVector = null;
        }
    }
    
    
    private static void popHabit(String habit, String[] habitsVector, int[] minutesVector,
                                 String[] newHabitsVector, int[] newMinutesVector) {
        
        if (habit != null && habitsVector != null && minutesVector != null
            && newHabitsVector != null && newMinutesVector!= null) {
            int i = 0;
            int j = 0;
            while (i < habitsVector.length) {
                if (habit.equals(habitsVector[i])) {
                    i++;
                }
                else {
                    newHabitsVector[j] = habitsVector[i];
                    newMinutesVector[j] = minutesVector[i];
                    i++;
                    j++;  
                }
            }
        }
    }
}
