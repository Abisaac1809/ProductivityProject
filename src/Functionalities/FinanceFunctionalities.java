package Functionalities;

import Composables.FileManager;
import Helpers.FinanceFileReader;
import Repositories.ArchiveUtil;
import Repositories.Finance;
import Validations.FinanceValidation;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FinanceFunctionalities {

    public static void menu(String username, Finance userMoney, Scanner input, ArchiveUtil archiveUtil) {
        try {
            double option = 0;
            if (username != null) {
                LocalDateTime actualDateTime = LocalDateTime.now();
                int rand = (int) (Math.random() * 20000) + 10000;
                String file1 = username + "finances_" + actualDateTime.now().toString().replace(':', '-') +"_"+rand;
                file1=FileManager.getToFile(file1, archiveUtil.getRouter());
                FinanceFunctionalities.funDefault(file1, archiveUtil);
                userMoney.setMoney(FinanceFileReader.fileHeadReading(file1, archiveUtil));
                FinanceFileReader.fileBodyReading(input, file1, userMoney, archiveUtil);
                System.out.println("\n");
                while (option >= 0 && option <= 6) {
                    option = FinanceFunctionalities.menuFinance(userMoney, input);
                    if (option == 1) {
                        FinanceFunctionalities.addAmount(userMoney);
                    }
                    if (option == 2) {
                        FinanceFunctionalities.subtractAmount(userMoney);
                    }
                    if (option == 3) {
                        FinanceFunctionalities.addDebt(userMoney);
                    }
                    if (option == 4) {
                        FinanceFunctionalities.subtractDebt(userMoney);
                    }
                    if (option == 5) {
                        FinanceFunctionalities.showDebts(username, userMoney);
                    }
                    if (option == 6) {
                        FileManager.fileWriter1(file1, userMoney.getMoney(), userMoney.getDebtsList(),
                                userMoney.getTitlesList(), userMoney.debtLength());
                        return;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void funDefault(String file1, ArchiveUtil archiveUtil) throws IOException {
        if (file1 != null) {
            if (!FileManager.createFileIfNotExists(archiveUtil.getRouter()+file1)) {
                FileManager.emptyFile(archiveUtil.getRouter() + file1);
                archiveUtil.setCreateArchive("0.00", file1, false);
            }
        }
    }

    public static double menuFinance(Finance userMoney, Scanner input) throws IOException {
        double option = 0;
        double num = userMoney.getMoney();
        DecimalFormat df = new DecimalFormat("#0.00");
        String numd = df.format(num);
        System.out.print("-------Finanzas-------\n");
        System.out.println("\nSu saldo es de- " + numd + "\n");
        System.out.println("1. Añadir saldo");
        System.out.println("2. Restar saldo");
        System.out.println("3. Agregar Deuda");
        System.out.println("4. Pagar Deuda");
        System.out.println("5. Mostrar Deudas");
        System.out.println("-----------------------");
        System.out.println("6. Regresar al menú principal y guardar\n");
        System.out.print("-Ingrese su opción: ");
        option = FinanceValidation.options(input);
        return option;
    }

    public static void addAmount(Finance userMoney) throws IOException {
        Double num = userMoney.getMoney();
        String text = "Ingrese el saldo a agregar (Se tomará el valor absoluto): ";
        System.out.println(num);
        DecimalFormat df = new DecimalFormat("#0.00");
        String numd = df.format(num);
        System.out.println("-----------------------");
        System.out.println("\nSaldo disponible: " + numd);
        userMoney.addMoney(text);
    }

    public static void subtractAmount(Finance userMoney) throws IOException {
        String text = "Ingrese cuanto saldo quitar (Se tomará el valor absoluto): ";
        double num = userMoney.getMoney();
        DecimalFormat df = new DecimalFormat("#0.00");
        String numd = df.format(num);
        System.out.println("-----------------------");
        System.out.println("\nSaldo disponible: " + numd);
        userMoney.subtractMoney(text);
    }

    public static void addDebt(Finance userMoney) {
        String text = "Ingrese el monto de la deuda: ";
        userMoney.setDebt(text);
        text = "ingrese el concepto de la deuda: ";
        userMoney.setTitle(text);

    }

    public static void subtractDebt(Finance userMoney) {
        String text = "Ingrese el concepto de la deuda a pagar: ";
        String debt = userMoney.valConcept(text, false);
        userMoney.setMoney(userMoney.debtSearching(debt));
    }

    public static void showDebts(String username, Finance userMoney) {
        System.out.println("-----------------------\nDeudas de " + username + ": ");
        System.out.println("Monto:                Concepto:");
        for (int i = 0; i < userMoney.debtLength(); i++) {
            System.out.printf("%8s %20s", userMoney.getDebt(i), userMoney.getTitle(i) + "\n");
        }
    }
}
