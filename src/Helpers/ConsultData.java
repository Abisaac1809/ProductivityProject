package Helpers;

import Repositories.DailyHabit;
import Repositories.FileManager;
import Repositories.Finance;
import Repositories.Node;
import Repositories.Queue;
import Repositories.Stack;
import Repositories.Task;
import Validations.DataValidations;
import Validations.FinanceValidation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class ConsultData {

	public static void stacking(Stack<Finance> pila, Stack<Task> pila2, Stack<DailyHabit> pila3) throws IOException {
		LocalDateTime actualDateTime = LocalDateTime.now();
		System.out.println(actualDateTime);
		String path = Paths.get(".").toRealPath().toString() + "/src/Storage/" + "datosusuario_" + actualDateTime + "_serial.txt";
		File file = new File(path);
		file.createNewFile();
		FileWriter writer = new FileWriter(file, false);
		if(pila.getSize()!=0){
			int i = pila.getSize();
			for (int j = 0; j < i; j++) {
				writer.write(pila.getTop().toStringContent());
				pila.pop();
			}
		}
		if(pila2.getSize()!=0){
			int i = pila2.getSize();
			for (int j = 0; j < i; j++) {
				writer.write(pila2.getTop().toStringContent());
				pila2.pop();
			}
		}
		if(pila3.getSize()!=0){
			int i = pila3.getSize();
			for (int j = 0; j < i; j++) {
				writer.write(pila3.getTop().toStringContent());
				pila3.pop();
			}
		}
		writer.close();
	}

	public static void showQueue(Queue<Finance> cola, Queue<Task> cola2, Queue<DailyHabit> cola3, Stack<Finance> pila, Stack<Task> pila2, Stack<DailyHabit> pila3){
		if(cola.getSize()!=0){
			int i = cola.getSize();
			for (int j = 0; j < i; j++) {
				String data = cola.getFront().toStringContent();
				System.out.println(data);
				pila.top(cola.getFront());
				cola.cutNode();
			}
		}
		if(cola2.getSize()!=0){
			int i = cola2.getSize();
			for (int j = 0; j < i; j++) {
				String data = cola2.getFront().toStringContent();
				System.out.println(data);
				pila2.top(cola2.getFront());
				cola2.cutNode();
			}
		}
		if(cola3.getSize()!=0){
			int i = cola3.getSize();
			for (int j = 0; j < i; j++) {
				String data = cola3.getFront().toStringContent();
				System.out.println(data);
				pila3.top(cola3.getFront());
				cola3.cutNode();
			}
		}
	}

	public static Node<Finance> financeSearchMenu(Scanner lectura, String username) {
		try {
			String file = username + "finances1.txt";
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/FinancesFiles/";
			FileManager archive = new FileManager(path);
			Finance userMoney = new Finance();
			int option = 0;
			if (lectura != null) {
				if (username != "") {
					do {
						System.out.printf("\n-----------------------\n\n");
						System.out.println("Que dato desea buscar?");
						System.out.println("1. Saldo");
						System.out.println("2. Deudas (Por Monto)");
						System.out.println("3. Deudas (Por Concepto)");
						System.out.println("------------------------");
						System.out.println("4. Regresar");
						System.out.print("\n- Ingrese su opción: ");
						option = DataValidations.option(lectura);
						if (option == 1) {
							userMoney.setMoney(getFinanceBalance(archive, file, lectura));
						}
						if (option == 2) {
							String files = path + file;
							String text = "Ingrese el monto por el cual va a buscar: ";
							Double monto = FinanceValidation.valEntry(text);
							String[] debts = getFinanceDebtsAmount(archive, file, lectura, monto);
							for (int i = 0; i < debts.length; i++) {
								userMoney.setTitle("" + debts[i]);
							}
						}
						if (option == 3) {
							String files = path + file;
							String text = "Ingrese el concepto de la deuda a buscar";
							String concept = FinanceValidation.valConcept(text, files, true);
							userMoney.setSpeDebt(getFinanceDebtsTitle(archive, file, lectura, concept));
						}
						if (option == 4) {
							return new Node<>(userMoney);
						}
					} while (option != 5 && username != "");
				}
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public static double getFinanceBalance(FileManager archive, String file, Scanner lectura)
			throws FileNotFoundException {
		lectura = archive.getFile(file);
		Double num = Double.valueOf(lectura.nextLine());
		return num;
	}

	public static String[] getFinanceDebtsAmount(FileManager archive, String file, Scanner lectura, Double amount)
			throws FileNotFoundException {
		lectura = archive.getFile(file);
		String line;
		String[] content = new String[FinanceFileReader.cantReg(file)];
		Scanner sep = null;
		int n = 0;
		Double aux;
		lectura.nextLine();
		while (lectura.hasNextLine()) {
			line = lectura.nextLine();
			sep = new Scanner(line).useDelimiter(" ");
			aux = Double.valueOf(sep.next());
			if (Objects.equals(aux, amount)) {
				content[n] = sep.next();
				n++;
			}
		}
		return content;
	}

	public static String getFinanceDebtsTitle(FileManager archive, String file, Scanner lectura, String title) {
		try {
			lectura = archive.getFile(file);
			String line;
			String aux;
			Scanner sepa = null;
			lectura.nextLine();
			while (lectura.hasNextLine()) {
				line = lectura.nextLine();
				sepa = new Scanner(line).useDelimiter(" ");
				sepa.next();
				aux = sepa.next();
				if (aux.equals(title)) {
					return line;
				}
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

}
