package Helpers;

import Process.*;
import Repositories.Node;
import Repositories.Queue;
import Repositories.Stack;
import Validations.DataValidations;
import java.io.IOException;
import java.util.Scanner;

public class ConsultMain<T> {
    
    public void consultMenu(String user, Scanner input) throws IOException{
        int option = 0;
        if (input != null) {
            Queue cola = new Queue();
            Stack pila = new Stack();
            String username = user;
            if (username != "") {
                while (option != 6 && username != "") {
                    System.out.printf("\n-----------------------\n\n");
                    System.out.println("Que tipo de información  desea buscar");
                    System.out.println("1. Hábitos");
                    System.out.println("2. Tareas");
                    System.out.println("3. Finanzas");
                    System.out.println("------------------------");
                    System.out.println("4. Mostrar y Guardar");
                    System.out.println("5. Regresar al menu principal");
                    System.out.print("\n- Ingrese su opción: ");
                    option = DataValidations.option(input);
                    if (option == 1) cola.addNode(new Node(ConsultData.financeSearchMenu(input, username)));
                    if (option == 2) ;
                    if (option == 3) ;
                    if (option == 4){
                        ConsultData.stacking(cola, pila);
                        ConsultData.showStack(pila);
                    }
                }
            }
        }
    }
}
