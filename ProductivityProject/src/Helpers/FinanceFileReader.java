package Helpers;

import Repositories.Finance;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinanceFileReader {
    
    public static int cantReg(String file){
        int num=0;
        try {
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            read.nextLine();
            while(read.hasNextLine()){
                read.nextLine();
                num++;
            }
            return num;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return num;
    }
    
    public static boolean searchInFile(String text, String file){
        try{
        String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            read.nextLine();
            String[] content= new String[2];
            while(read.hasNextLine()){
                String line=read.nextLine();
                Scanner Line = new Scanner(line);
                content[0]=Line.next();
                content[1]=Line.next();
                if(content[1].equals(text)){
                    content=null;
                    return false;
                }
            }
            content=null;
            return true;
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        return true;
    }
    
    public static double fileHeadReading(String file) {
        double num=0.00;
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
            File arch = new File(path);
            Scanner read = new Scanner(arch);
            num = Double.parseDouble(read.next());
            read.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }
        return num;
    }
    
    public static void fileBodyReading(Scanner input, String file,Finance userMoney) {
        if(file!=null){
        try{
            String path=Paths.get(".").toRealPath().toString()+"/src/Storage/FinancesFiles/"+file;
            File arch = new File(path);
            Scanner read2 = new Scanner(arch);
            read2.nextLine();
            Scanner sepa = input;
            while(read2.hasNextLine()){
                String text=read2.nextLine();
                sepa = new Scanner(text).useDelimiter(" ");
                userMoney.loadDebt(Double.parseDouble(sepa.next()));
                userMoney.loadTitle(sepa.next());
            }
            sepa.close();
            read2.close();
        }catch(IOException i){
            System.out.println("Error: "+i.getMessage());
        }}
    }
    
}
