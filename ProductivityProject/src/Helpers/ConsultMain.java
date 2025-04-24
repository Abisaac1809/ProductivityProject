package Helpers;

import Process.*;
import Validations.DataValidations;
import java.util.Scanner;

public class ConsultMain<T> {
    
    public void consultMenu(String user, Scanner input){
        int option = 0;
        if (input != null) {
            String username = user;
            if (username != "") {
                while (option != 5 && username != "") {
                    System.out.printf("\n-----------------------\n\n");
                    System.out.println("Que tipo de información  desea buscar");
                    System.out.println("1. Hábitos");
                    System.out.println("2. Tareas");
                    System.out.println("3. Finanzas");
                    System.out.println("------------------------");
                    System.out.println("4. Regresar al menu principal");
                    System.out.print("\n- Ingrese su opción: ");
                    option = DataValidations.option(input);
                    if (option == 1) ConsultData.financeSearchMenu(input, username);
                    if (option == 2) ;
                    if (option == 3) ;
                }
            }
        }
    }
}
