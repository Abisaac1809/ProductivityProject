package Finances;
import java.io.IOException;

public class FinanceMain {
    public static void main(String username) throws IOException{
        double option=0;
        if (username != null) {
            String file1 = "Finances."+username+".txt";
            String file2 = "History."+username+".txt";
            FinanceDevelopment.funDefault(file1, file2);
            System.out.println("\n");
            while(option>=0 && option<=2){
                option=FinanceDevelopment.menuFinance(file1);
                if(option==1){
                    FinanceDevelopment.addAmount(file1);
                }
                if(option==2){
                    FinanceDevelopment.subtractAmount(file1);
                }
            }
        }
    } 
}
