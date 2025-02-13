package Main;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MainValidation {
    static int option(Scanner input) {
        int value;
        while (true) {
            try {
                value = input.nextInt();

                if (value >= 1 && value <= 5) {
                    return value;
                } else {
                    System.out.println("Error: [Opción no válida]");
                    System.out.print("- Ingrese el valor correcto: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: [Solo se admiten valores numéricos enteros]");
                System.out.print("- Ingrese el valor correcto: ");
                input.nextLine();
            }
        }
    }
}
