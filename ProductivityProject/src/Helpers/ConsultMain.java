package Helpers;

import Repositories.Queue;
import Repositories.Stack;
import Repositories.Finance;
import Validations.DataValidations;
import java.io.IOException; import java.util.Scanner;

public class ConsultMain {

	public static void consultMenu(String user, Scanner input) throws IOException {
		int option = 0;
		if (input != null) {
			String username = user;
			Queue<Finance> cola = new Queue<Finance>();
			Stack<Finance> pila = new Stack<Finance>();
			if (username != "") {
				while (option != 6 && username != "") {
					System.out.printf("\n-----------------------\n\n");
					System.out.println("Que tipo de información  desea buscar");
					System.out.println("1. Finanzas");
					System.out.println("------------------------");
					System.out.println("2. Mostrar y Guardar");
					System.out.println("3. Regresar al menu principal");
					System.out.print("\n- Ingrese su opción: ");
					option = DataValidations.option(input);
					if (option == 1) {
						cola.addNode(ConsultData.financeSearchMenu(input, username));
					}
					if (option == 2) {
						ConsultData.stacking(cola, pila);
						ConsultData.showStack(pila);
					}
				}
			}
		}
	}
}
