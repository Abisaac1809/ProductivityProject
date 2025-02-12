package Finances;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.InputMismatchException;

public class FinanceValidation {
    static double valEntry(double balance,String text){
        double amount;
        Scanner enter =new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextDouble();
                amount=Math.abs(amount);
                if(amount>balance){
                    System.out.println("Error: No tienes suficiente dinero si quieres pagar esa deuda");
                }
                else{
                    return amount;
                }
            }
            catch(InputMismatchException ime){
                System.out.println("Error: Por favor, ingrese un numero");
                enter.nextLine();
            }
        }
    }
    
    static double valEntry(String text){
        double amount;
        Scanner enter=new Scanner(System.in);
        while(true){
            try{
                System.out.println(text);
                amount=enter.nextDouble();
                amount=Math.abs(amount);
                return amount;
            }
            catch(InputMismatchException ime){
                System.out.print("Error: Ingrese solo numeros");
                enter.nextLine();
            }
        }
    }
    
    static boolean itExist(String file1) throws IOException{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file1;
        File file = new File(path);
        return file.exists();
    }
    
    static void fileCreate(String file) throws IOException{
        try{
            String dfault= "0.0";
            String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
            File newfile = new File(path);
            newfile.createNewFile();
            FileWriter writer = new FileWriter(path);
            writer.write(dfault);
            writer.close();
        } catch(IOException i){
            
        }
    }
    
    static double fileHeadReading(String file) throws IOException{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
        File arch = new File(path);
        Scanner read = new Scanner(arch);
        double num = Double.parseDouble(read.next());
        return num;
    }
    
    static int cantReg(String file) throws IOException{
        int cr =0;
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
        File arch = new File(path);
        Scanner read = new Scanner(arch);
        while (read.hasNextLine()){
            read.nextLine();
            cr++;
        }
        return cr;
    }
    
    static void fileWriter1(String file, double add) throws IOException{
        String path=Paths.get(".").toRealPath().toString()+"/src/Finances/"+file;
        File oldfile = new File(path);
        Scanner read = new Scanner(oldfile);
        double num = Double.parseDouble(read.next());
        int cr=cantReg(file);
        int i=0;
        String x = Double.toString(add);
        String[] concept = new String[cr-1];
        double[] mun = new double[cr-1];
        while(read.hasNext()){
            mun[i]=read.nextInt();
            concept[i]=read.next();
            i++;
        }
        oldfile.delete();
        read.close();
        File newfile = new File(path);
        newfile.createNewFile();
        FileWriter writer = new FileWriter(newfile,false);
        for(int j=0; j<cr;j++){
            if(j==0){
                writer.write(x);
            }else{
                String n=mun[j]+"";
                writer.write("\n");
                writer.write(n+" "+concept[j]);
            }
        }
        writer.close();
    }
    
    static double options(){
        Scanner input = new Scanner(System.in);
        double option=0;
        while(true){
            try{
                option=input.nextDouble();
                if(option==1 || option == 2 || option ==3){
                    return option;
                }else{
                    System.out.println("Error: Opcion no valida");
                    System.out.print("-Ingrese su opcion:");
                }
            } catch(InputMismatchException e){
                System.out.println("Error: Por favor ingrese un caracter válido");
                input.nextLine();
            }
        }
    }
}
