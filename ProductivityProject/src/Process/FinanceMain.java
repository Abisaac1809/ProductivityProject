package Process;
import java.io.IOException;
import java.util.ArrayList;

public class FinanceMain {
    public static void main(String username) throws IOException{
        ArrayList<String> debts = new ArrayList<String>();
        FinanceDevelopment.menu(username,debts);
        debts=null;
    } 
}
