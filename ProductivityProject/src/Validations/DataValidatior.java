package Validations;

import static Habits.HabitsValidation.validateInt;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataValidatior {
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
            
                if (stringCharByChar(newText, 0)) {
                    return newText;
                }
                System.out.println("Error: [Uso de caracteres no soportados]\n");
            }    
        }
        return null;
    }
   
    private static boolean stringCharByChar(String text, int index) {
        if (index == text.length()) {
            return true;
        }
        else {
            return checkChar(text, text.charAt(index), index);
        }
    }
    
    private static boolean checkChar(String text, char x, int index) {
        String unsupported = "1234567890.,?/:;'!@#$%^&*()[]{}\\|=-+_~";
        for (int i = 0; i < unsupported.length(); i++) {
            if (unsupported.charAt(i)== x) {
                return false;
            }
        }
        return stringCharByChar(text, index + 1);
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
