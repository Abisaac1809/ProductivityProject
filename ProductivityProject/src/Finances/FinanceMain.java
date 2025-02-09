package Finances;
import java.io.IOException;

public class FinanceMain {
    public static void main(String username) throws IOException{
        int option=0;
        String file1 = username+"finances1.txt";
        String file2 = username+"finances2.txt";
        System.out.println(file1);
        while(option>=0 && option<=2){
            option=FinanceDevelopment.menufinance(file1);
            switch (option){
                case 1: 
                    FinanceDevelopment.addAmount(file1);
                    break;
                case 2: 
                    FinanceDevelopment.subtractAmount(file1);
            }
        }
    } 
}
