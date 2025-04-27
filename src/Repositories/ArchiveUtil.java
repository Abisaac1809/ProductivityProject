package Repositories;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class ArchiveUtil {
    private String router;

    public ArchiveUtil(String router) throws FileNotFoundException, IllegalArgumentException {
        if (router == null || router.isEmpty()) {
            throw new IllegalArgumentException("La ruta asignada no es válida.");
        }
        this.utilDirectory(router);
    }

    //getters
    public String getRouter(){
        return this.router;
    }

    public Scanner getArchive(String nameArchive) {
        try {
            if (nameArchive == null || nameArchive.trim().isEmpty()) {
                throw new IllegalArgumentException(" EL nombre del archivo es requerido. ");
            }

            File archive = new File(this.router+nameArchive);

            // Verificar si es un archivo y no un directorio
            if (!archive.isFile()) {
                throw new FileNotFoundException(" EL nombre del archivo es requerido. ");
            }

            return new Scanner(archive);

        } catch (IOException | IllegalArgumentException e) {
            // Manejo de excepciones al abrir el archivo
            System.out.println("ReadArchive-Error: " + e.getMessage());
            return null;
        }
    }

    //setters
    public void setRouter(String router) throws FileNotFoundException {
        this.utilDirectory(router);
    }

    public void setCreateArchive(String content, String nameArchive, boolean bool) {

        try (BufferedWriter addArchive = new BufferedWriter(new FileWriter(this.router+nameArchive+".txt", true))) { // 'true' permite agregar al archivo existente
            if (content.trim().isEmpty() || nameArchive.isEmpty()){
                throw new NullPointerException(" EL contenido es requerido. ");
            }

            addArchive.write(content);  // Escribir en el archivo
            if (bool){
                addArchive.newLine(); // Agregar nueva línea opcional
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("- CreateOrWriteArchive-Error: " + e.getMessage());
        }
    }

    public String[] getDirectories() {
        try {
            File directories = new File(this.router);

            if (Objects.requireNonNull(directories.list()).length >= 1) {
                return directories.list();
            }
            throw new FileNotFoundException(" No se encontraron archivos. ");

        } catch (FileNotFoundException e) {
            System.out.println("Directory-Error: "+ e.getMessage());
            return null;
        }
    }

    public boolean directoriesExist() {
        File directories = new File(this.router);

        return Objects.requireNonNull(directories.list()).length >= 1;
    }

    //utilitarias
    private void utilDirectory(String router) throws FileNotFoundException {

        File directories = new File(router);
        if (!directories.exists()) {
            throw new FileNotFoundException("El directorio a guardar no existe. ");
        }
        this.router = router;

    }



}

