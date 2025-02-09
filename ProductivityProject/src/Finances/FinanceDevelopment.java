package Finances;
import java.io.IOException;

public class FinanceDevelopment {
    static void fundefault(String file1, String file2) throws IOException{
        boolean flag1 = FinanceValidation.itExist(file1);
        boolean flag2 = FinanceValidation.itExist(file2);
        
        if(!flag1){
            FinanceValidation.fileCreate(file1);
        }
        if(!flag2){
            FinanceValidation.fileCreate(file2);
        }
    }
    
    static int menufinance(String file1) throws IOException{
        double num =FinanceValidation.fileHeadReading(file1);
        System.out.println(num);
        int option = 0;
        System.out.println("1. Añadir saldo");
        System.out.println("2. Restar saldo");
        option = FinanceValidation.options();
        return option;
    }
    static void addAmount(String file1) throws IOException{
        int add =0;
        String text = "Ingrese el saldo a agregar";
        int num =FinanceValidation.fileHeadReading(file1);
        System.out.println("Saldo disponible: "+num);
        add=FinanceValidation.valEntry(text);
        num+=add;
        FinanceValidation.fileWriter1(file1, add);
    }
    
    static void subtractAmount(String file1) throws IOException{
        int add =0;
        String text = "Ingrese cuanto saldo quitar";
        int num =FinanceValidation.fileHeadReading(file1);
        System.out.println("Saldo disponible: "+num);
        add=FinanceValidation.valEntry(add,text);
        num-=add;
        FinanceValidation.fileWriter1(file1, add);
    }
}