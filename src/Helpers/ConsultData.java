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
import java.util.Scanner;

public class ConsultData {

	public static void stacking(Queue<Finance> cola, Queue<Task> cola2, Queue<DailyHabit> cola3, Stack<Finance> pila, Stack<Task> pila2, Stack<DailyHabit> pila3) throws IOException {
		if(cola.getSize()!=0){
			int i = cola.getSize();
			for (int j = 0; j < i; i++) {
				pila.top(cola.getFront());
				cola.cutNode();
			}
		}
		if(cola2.getSize()!=0){
			int i = cola2.getSize();
			for (int j = 0; j < i; i++) {
				pila2.top(cola2.getFront());
				cola2.cutNode();
			}
		}
		if(cola3.getSize()!=0){
			int i = cola3.getSize();
			for (int j = 0; j < i; i++) {
				pila3.top(cola3.getFront());
				cola3.cutNode();
			}
		}
		LocalDateTime actualDateTime = LocalDateTime.now();
		String path = Paths.get(".").toRealPath().toString() + "/src/Storage/" + "datosusuario_" + actualDateTime + "_serial" + Math.random();
		File file = new File(path);
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

	public static void showQueue(Queue<Finance> cola, Queue<Task> cola2, Queue<DailyHabit> cola3){
		if(cola.getSize()!=0){
			int i = cola.getSize();
			for (int j = 0; j < i; i++) {
				String data = cola.getFront().toStringContent();
				cola.cutNode();
				System.out.println(data);
			}
		}
		if(cola2.getSize()!=0){
			int i = cola2.getSize();
			for (int j = 0; j < i; i++) {
				String data = cola2.getFront().toStringContent();
				cola.cutNode();
				System.out.println(data);
			}
		}
		if(cola3.getSize()!=0){
			int i = cola3.getSize();
			for (int j = 0; j < i; i++) {
				String data = cola3.getFront().toStringContent();
				cola.cutNode();
				System.out.println(data);
			}
		}
	}

	public static Node<Finance> financeSearchMenu(Scanner lectura, String username) {
		try {
			String file = username + "finances1.txt";
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/FinancesFiles/";
			FileManager archive = new FileManager(path + file);
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
							Double[] debts = getFinanceDebtsAmount(archive, files, lectura, monto);
							for (int i = 0; i < debts.length; i++) {
								userMoney.setDebt("" + debts[i]);
							}
						}
						if (option == 3) {
							String files = path + file;
							String text = "Ingrese el concepto de la deuda a buscar";
							String concept = FinanceValidation.valConcept(text, files, true);
							userMoney.setTitle(getFinanceDebtsTitle(archive, files, lectura, concept));
						}
						if (option == 4) {
							return new Node<>(userMoney);
						}
					} while (option != 4 && username != "");
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
		Double num = Double.parseDouble(lectura.next());
		return num;
	}

	public static Double[] getFinanceDebtsAmount(FileManager archive, String file, Scanner lectura, Double amount)
			throws FileNotFoundException {
		lectura = archive.getFile(file);
		String line;
		Double[] content = new Double[FinanceFileReader.cantReg(file)];
		Scanner sep = null;
		int n = 0;
		Double aux;
		lectura.nextLine();
		while (lectura.hasNextLine()) {
			line = lectura.nextLine();
			sep = new Scanner(line).useDelimiter(" ");
			aux = Double.parseDouble(sep.next());
			if (aux == amount) {
				content[n] = Double.parseDouble(sep.next());
				n++;
			}
		}
		sep.close();
		return content;
	}

	public static String getFinanceDebtsTitle(FileManager archive, String file, Scanner lectura, String title) {
		try {
			lectura = archive.getFile(file);
			String line;
			String aux;
			Scanner sep = null;
			lectura.nextLine();
			while (lectura.hasNextLine()) {
				line = lectura.nextLine();
				sep = new Scanner(line).useDelimiter(" ");
				sep.next();
				aux = sep.next();
				if (aux.equals(title)) {
					return aux;
				}
			}
			sep.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

}
