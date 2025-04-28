package Helpers;

import Repositories.DailyHabit;
import Repositories.Finance;
import Repositories.Queue;
import Repositories.Stack;
import Repositories.Task;
import Validations.DataValidations;
import java.io.IOException;
import java.util.Scanner;

public class ConsultMain {

	public static void consultMenu(String user, Scanner input) throws IOException {
		int option = 0;
		if (input != null) {
			String username = user;
			Queue<Finance> cola = new Queue<Finance>();
			Stack<Finance> pila = new Stack<Finance>();
			Queue<Task> cola2 = new Queue<>();
			Stack<Task> pila2 = new Stack<>();
			Queue<DailyHabit> cola3 = new Queue<>();
			Stack<DailyHabit> pila3 = new Stack<>();
			if (username != "") {
				while (option != 6 && username != "") {
					System.out.printf("\n-----------------------\n\n");
					System.out.println("Que tipo de información  desea buscar");
					System.out.println("1. Finanzas");
					System.out.println("2. Habitos");
					System.out.println("3. Tareas");
					System.out.println("------------------------");
					System.out.println("4. Mostrar y Guardar");
					System.out.println("5. Regresar al menu principal");
					System.out.print("\n- Ingrese su opción: ");
					option = DataValidations.option(input);
					if (option == 1) {
						cola.addNode(ConsultData.financeSearchMenu(input, username));
					}
					if (option == 2) {
						cola3.addNode(HabitSearchMenu.habitSearchMenu(user));
					}
					if (option == 4) {
						ConsultData.showQueue(cola, cola2, cola3, pila, pila2, pila3);
						ConsultData.stacking(pila, pila2, pila3);
					}
				}
			}
			cola=null;
			pila=null;
			cola2=null;
			pila2=null;
			cola3=null;
			pila3=null;
		}
	}
}
