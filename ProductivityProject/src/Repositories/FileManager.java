package Repositories;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private String directoryRoute;
    
    public FileManager(String directoryRoute) throws MissingArgumentException, FileNotFoundException {
        if (directoryRoute ==  null || directoryRoute.trim().isEmpty()) {
            throw new MissingArgumentException("Ruta del directorio");
        }
        if (!directoryExist(directoryRoute)) {
            throw new FileNotFoundException("El directorio no existe");
        }
        else {
            this.directoryRoute = directoryRoute;
        }
    }
    
    public String getDirectoryRoute() {
        return directoryRoute;
    }
    
    public void setDirectoryRoute(String newDirectoryRoute) throws FileNotFoundException {
        if (newDirectoryRoute == null || newDirectoryRoute.trim().isEmpty()) {
            throw new NullPointerException("No se ha ingresado una ruta válida");
        }
        if (!directoryExist(newDirectoryRoute)) {
            throw new FileNotFoundException("El directorio no existe");
        }
        this.directoryRoute = newDirectoryRoute;
    }
    
    public void createFile(String fileName) throws IOException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del archivo es inválido");
        }
        
        String routeOfNewFile = getFileRouteByName(fileName);
        File newFile = new File(routeOfNewFile);
        newFile.createNewFile();
    }
    
    public Scanner getFile(String fileName) throws FileNotFoundException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del archivo es inválido");
        }
        
        if (!fileExist(fileName)) {
            throw new FileNotFoundException("El archivo no existe o la ruta es inválida");
        }
        String fileRoute = getFileRouteByName(fileName);
        File file = new File(fileRoute);
        return new Scanner(file);
    }
    
    public void writeTextInFile(String text, String fileName, boolean append) throws FileNotFoundException, IOException {
        if (fileName == null || fileName.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del archivo es inválido");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto que has ingresado es inválido");
        }
        if (!fileExist(fileName)) {
            throw new FileNotFoundException("El archivo no existe o la ruta es inválida");
        }
        
        String fileRoute = getFileRouteByName(fileName);
        
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileRoute, append))) {
            fileWriter.write(text);
        }    
    }
    
    public void writeEmptyLineInFile(String fileName) throws FileNotFoundException, IOException{
        if (fileName == null || fileName.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del archivo es inválido");
        }
        String fileRoute = getFileRouteByName(fileName);
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileRoute, true))) {
            fileWriter.newLine();
        }    
    }
    
    private boolean directoryExist(String directoryRoute) {
        File directories = new File(directoryRoute);
        return directories.exists();
    }
    
    private boolean fileExist(String fileName) {
        String fileRoute = getFileRouteByName(fileName);
        File file = new File(fileRoute);
        return file.isFile();
    }

    private String getFileRouteByName(String fileName) {
        return this.directoryRoute + fileName + ".txt";
    }
}
