package Habits;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HabitsValidation {
    
    public static int validateInt(String text) {
        
        if (text != null) {
            Scanner input = new Scanner(System.in);
            int number = 0;
        
            while (true) {
                try {
                    System.out.print(text);
                    number = input.nextInt();
                    if (number > 0) {
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
    
    
    public static boolean userHasHabits(String route)  {
        
        if (route != null) {
            try (Scanner fileReader = new Scanner(new File(route))) {
                return fileReader.hasNext();
            }
            catch (FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
                return false;
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
    
    
    public static void fillVectors(String route, String[] habitsVector, int[] minutesVector) {
        
        if (route != null && habitsVector != null && minutesVector != null
            && habitsVector.length == minutesVector.length) {
            int i = 0;
            try (Scanner fileReader = new Scanner(new File(route))){
                while (fileReader.hasNextLine()) {
                    habitsVector[i] = fileReader.next();
                    minutesVector[i] = fileReader.nextInt();
                    i++;
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }
    }
    
    
    public static void saveData(String route, String[] habitsVector, int[] minutesVector) throws IOException {
        
        if (route != null && habitsVector != null && minutesVector != null) {
            String line = "";
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(route))) {
                for (int i = 0; i < habitsVector.length; i++) {
                    line = String.format("%s %d", habitsVector[i], minutesVector[i]);
                    fileWriter.write(line);
                    if (i != (habitsVector.length - 1)) {
                        fileWriter.newLine();
                    }
                }
            }
            catch(FileNotFoundException e) {  
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha podido guardar los datos en el archivo]");
            }
        }  
    }
}
