package Finances;
import java.io.IOException;
import java.text.DecimalFormat;

public class FinanceDevelopment {
    static void funDefault(String file1, String file2) throws IOException{
        if (file1 != null && file2 != null) {
            FinanceValidation.fileCreate(file1);
            FinanceValidation.fileCreate(file2);
        }
    }
    
    static double menuFinance(String file1) throws IOException{
        double option = 0;
        if (file1 != null) {
            double num =FinanceValidation.fileHeadReading(file1);
            DecimalFormat df = new DecimalFormat("#0.00");
            String numd = df.format(num);
            System.out.print("-------Finanzas-------\n");
            System.out.println("\nSu saldo es de: "+numd+"\n");
            System.out.println("1. Añadir saldo");
            System.out.println("2. Restar saldo");
            System.out.println("3. Regresar al menú principal\n");
            System.out.print("-Ingrese su opción: ");
            option = FinanceValidation.options();
        }
        return option;
    }
    static void addAmount(String file1) throws IOException{
        if (file1 != null) {
            FinanceValidation.fileCreate(file1);
            double add =0;
            String text = "Ingrese el saldo a agregar (Se tomará el valor absoluto): ";
            double num =FinanceValidation.fileHeadReading(file1);
            System.out.println(num);
            DecimalFormat df = new DecimalFormat("#0.00");
            String numd = df.format(num);
            System.out.println("-----------------------");
            System.out.println("\nSaldo disponible: "+numd);
            add=FinanceValidation.valEntry(text);
            num+=add;
            FinanceValidation.fileWriter1(file1, num);
        }
    }
    
    static void subtractAmount(String file1) throws IOException{
        if (file1 != null) {
            FinanceValidation.fileCreate(file1);
            double subtract =0;
            String text = "Ingrese cuanto saldo quitar: ";
            double num =FinanceValidation.fileHeadReading(file1);
            DecimalFormat df = new DecimalFormat("#0.00");
            String numd = df.format(num);
            System.out.println("-----------------------");
            System.out.println("\nSaldo disponible: "+numd);
            subtract=FinanceValidation.valEntry(num,text);
            num-=subtract;
            FinanceValidation.fileWriter1(file1, num);
        }
    }
}
