package Finances;
import java.io.IOException;
import java.text.DecimalFormat;

public class FinanceDevelopment {
    static void fundefault(String file1, String file2) throws IOException{
        boolean flag1 = FinanceValidation.itExist(file1);
        boolean flag2 = FinanceValidation.itExist(file2);
        System.out.println(flag1);
        if(!flag1){
            FinanceValidation.fileCreate(file1);
        }
        if(!flag2){
            FinanceValidation.fileCreate(file2);
        }
    }
    
    static double menufinance(String file1) throws IOException{
        double num =FinanceValidation.fileHeadReading(file1);
        DecimalFormat df = new DecimalFormat("#.00");
        String numd = df.format(num);
        System.out.print("-------Finanzas-------\n");
        System.out.println("\nSu saldo es de: "+numd+"\n");
        double option = 0;
        System.out.println("1. Añadir saldo");
        System.out.println("2. Restar saldo");
        System.out.println("3. Regresar al menú principal\n");
        System.out.print("-Ingrese su opción: ");
        option = FinanceValidation.options();
        return option;
    }
    static void addAmount(String file1) throws IOException{
        boolean flag = FinanceValidation.itExist(file1);
        if(!flag){
            FinanceValidation.fileCreate(file1);
        }
        double add =0;
        String text = "Ingrese el saldo a agregar: ";
        double num =FinanceValidation.fileHeadReading(file1);
        DecimalFormat df = new DecimalFormat("#.00");
        String numd = df.format(num);
        System.out.println("-----------------------");
        System.out.println("\nSaldo disponible: "+numd);
        add=FinanceValidation.valEntry(text);
        num+=add;
        FinanceValidation.fileWriter1(file1, num);
    }
    
    static void subtractAmount(String file1) throws IOException{
        boolean flag = FinanceValidation.itExist(file1);
        if(!flag){
            FinanceValidation.fileCreate(file1);
        }
        double add =0;
        String text = "Ingrese cuanto saldo quitar: ";
        double num =FinanceValidation.fileHeadReading(file1);
        DecimalFormat df = new DecimalFormat("#.00");
        String numd = df.format(num);
        System.out.println("-----------------------");
        System.out.println("\nSaldo disponible: "+numd);
        add=FinanceValidation.valEntry(num,text);
        num-=add;
        FinanceValidation.fileWriter1(file1, num);
    }
}