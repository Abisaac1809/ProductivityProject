package Habits;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HabitsValidation {
    
    public static int validateInt(String text, int min, int max) {
        
        if (text != null) {
            Scanner input = new Scanner(System.in);
            int number = 0;
        
            while (true) {
                try {
                    System.out.print(text);
                    number = input.nextInt();
                    if (number >= min && number <= max) {
                        return number;
                    }
                    System.out.println("Error: [Opcion no válida]\n");
                }
                catch (InputMismatchException e) {
                    System.out.println("Error: [No se pueden usar caracteres]\n");
                    input.nextLine();
                }
            }
        }
        return 0;
        
    }
    
    
    public static String validateString(String text) {
        
        if (text != null) {
            Scanner input = new Scanner(System.in);
            String newText = "";
        

            while (true) {
                System.out.print(text);
                newText = input.nextLine();
            
                if (checkChars(newText)) {
                    return newText;
                }
                System.out.println("Error: [Uso de caracteres no soportados]\n");
            }    
        }
        return null;
    }
    
    private static boolean checkChars(String value) {
        
        if (value != null) {
            String unsupported = "1234567890.,?/:;'!@#$%^&*()[]{}\\|=-+_~";
            for (int i = 0; i < value.length(); i++) {
                for (int j = 0; j < unsupported.length(); j++) {
                    if (unsupported.charAt(j) == value.charAt(i)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    
    public static void initializeVector(int[] vector) {
        if (vector != null) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] = 0;
            }    
        }
    }
    
    public static void initializeVector(String[] vector) {
        if (vector != null) {
            for (int i = 0; i < vector.length; i++) {
                vector[i] = "e";
            }
        }
    }
    
    public static void initializeArray(int[][][] array) {
        
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    for (int k = 0; k < array[i][j].length; k++) {
                        array[i][j][k] = 0;
                    }
                }
            }
        }
    }
    
    public static void initializeArray(int[][] array) {
        
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    array[i][j] = 0;
                }
            }
        }
    }
    
    
    private static void createArchive(String route) throws IOException {
        if (route != null) {
            try {
                File file = new File(route);
                file.createNewFile();
            }
            catch (IOException e) {
                System.out.println("Error: [" + e.getMessage() + "]");
            }
        }
    }
    
    
    public static boolean userHasHabits(String habitsRoute, String performanceRoute) throws IOException  {
        
        if (habitsRoute != null && performanceRoute != null) {
            try (Scanner fileReader = new Scanner(new File(habitsRoute))) {
                return fileReader.hasNext();
            }
            catch (FileNotFoundException e) {
                createArchive(habitsRoute);
                createArchive(performanceRoute);
                return true;
            }
        }
        return false;
    }
    

    public static int determineRows(String route) {
            
        if (route != null) { 
            int count = 0;
            try (Scanner fileReader = new Scanner(new File(route))) {
                while (fileReader.hasNextLine()) {
                    fileReader.nextLine();
                    count++;
                }
                return count; 
            }
            catch (FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
                return 0;
            }
        }
        return 0;
    }
    
    
    public static void getDailyHabits(String route, String[] dailyHabits, int[] dailyHabitMinutes) {
        
        if (route != null && dailyHabits != null && dailyHabitMinutes != null
            && dailyHabits.length == dailyHabitMinutes.length) {
            
            String line = ""; 
            int i = 0;
            try (Scanner fileReader = new Scanner(new File(route))){
                while (fileReader.hasNextLine() && i < dailyHabits.length ) {
                    
                    line = fileReader.nextLine();
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("#");
                    
                    while(lineReader.hasNext()){
                        dailyHabits[i] = lineReader.next();
                        dailyHabitMinutes[i] = lineReader.nextInt();
                    }
                    i++;
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }
    }
    
    
    public static void getHabitTimeSpentDaily(String route, int[][][] habitTimeSpentDaily){
        
        if (route != null && habitTimeSpentDaily != null) {
            int i = 0;
            
            try(Scanner fileReader = new Scanner(new File(route))){
                while (fileReader.hasNextLine()) {
                    for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
                        for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++) {
                            habitTimeSpentDaily[i][j][k] = fileReader.nextInt();
                        }
                    }
                    fileReader.nextLine();
                    if (!fileReader.hasNextInt()) return;
                    i++;
                }
            } catch (FileNotFoundException ex) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }
    }
    
    
    public static void saveHabits(String route, String[] dailyHabits, int[] dailyHabitMinutes) throws IOException {
        
        if (route != null && dailyHabits != null && dailyHabitMinutes != null) {
            String line = "";
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(route))) {
                for (int i = 0; i < dailyHabits.length; i++) {
                    line = String.format("%s#%d", dailyHabits[i], dailyHabitMinutes[i]);
                    fileWriter.write(line);
                    if (i != (dailyHabits.length - 1)) {
                        fileWriter.newLine();
                    }
                }
            }
            catch(FileNotFoundException e) {  
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }  
    }
    
    
    public static void savePerformance(String route, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (route != null && habitTimeSpentDaily != null) {
            
            try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(route))) {
                for (int i = 0; i < habitTimeSpentDaily.length; i++) {
                    for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
                        for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++) {
                            fileWriter.write(String.format("%s ", habitTimeSpentDaily[i][j][k]));
                        }
                        fileWriter.newLine();
                    }
                    fileWriter.newLine();
                }
            }
            catch(FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");    
            }
        }
    }
    
    
    public static int chosePosition(String[] options) {
        
        if (options != null) {
            
            int position = 0;
        
            System.out.println("\nOpciones:\n");
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d. %s\n", (i+1), options[i]);
            }
            position = validateInt("Elige una opción: ", 1, options.length + 1);
        
            return (position - 1);
        }    
        return 0;
    }   
}
