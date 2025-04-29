package Composables;

import Repositories.ArchiveUtil;
import Structures.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FileManager {
	public static boolean createFileIfNotExists(String path) {
		if (path != null) {
			try {
				File file = new File(path);
				if (!file.exists()) {
					file.createNewFile();
					return false;
				}
				return true;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		return false;
	}

	public static void emptyFile(String path) {
		if (path != null) {
			try {
				File file = new File(path);
				FileWriter fileWriter = new FileWriter(file, false);
				if (file.exists()) {
					fileWriter.write("");
				}
				fileWriter.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

	}

	public static void fileWriter1(String file, double add, List debts, List title, int i) {
		try {
			String path = Paths.get(".").toRealPath().toString() + "/src/Storage/FinanceFiles/" + file;
			File file1 = new File(path);
			file1.createNewFile();
			FileWriter writer = new FileWriter(file1, false);
			writer.write(Double.toString(add));
			writer.close();
			fileWriter1(file1, debts, title, i, 0);
			debts.removeAll();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void fileWriter1(File file, List debts, List title, int i, int j) {
		try {
			if (j == i) {
				return;
			} else {
				File file1 = file;
				file1.createNewFile();
				FileWriter writer2 = new FileWriter(file1, true);
				writer2.write("\n" + debts.get(j) + " " + title.get(j));
				writer2.close();
				fileWriter1(file, debts, title, i, j + 1);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void addNewLine(String path) {
		if (path != null) {
			try {
				File file = new File(path);
				FileWriter fileWriter = new FileWriter(file, true);
				fileWriter.write("\n");
				fileWriter.close();
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

    public static int determineRows(String route) {
            
        if (route != null) { 
            int count = 0;
            try (Scanner fileReader = new Scanner(new File(route))) {
                while (fileReader.hasNextLine()) {
                    fileReader.nextLine();
                    count++;
                }
                return count; 
            }
            catch (Exception e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
                return 0;
            }
        }
        return 0;
    }    

    public static void saveHabits(ArchiveUtil archiveUtil, String route, String[] dailyHabits, int[] dailyHabitMinutes) throws IOException {
        
        if (route != null && dailyHabits != null && dailyHabitMinutes != null) {
			String fileRoute = archiveUtil.getRouter() + route;
            String line = "";
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileRoute))) {
                for (int i = 0; i < dailyHabits.length; i++) {
                    line = String.format("%s#%d", dailyHabits[i], dailyHabitMinutes[i]);
                    fileWriter.write(line);
                    if (i != (dailyHabits.length - 1)) {
                        fileWriter.newLine();
                    }
                }
            }
            catch(FileNotFoundException e) {  
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");
            }
        }  
    }
    
    public static void savePerformance(ArchiveUtil archiveUtil, String route, int[][][] habitTimeSpentDaily) throws IOException {
        
        if (route != null && habitTimeSpentDaily != null) {
            String fileRoute = archiveUtil.getRouter() + route;
            try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileRoute))) {
                for (int i = 0; i < habitTimeSpentDaily.length; i++) {
                    for (int j = 0; j < habitTimeSpentDaily[i].length; j++) {
                        for (int k = 0; k < habitTimeSpentDaily[i][j].length; k++) {
                            fileWriter.write(String.format("%s ", habitTimeSpentDaily[i][j][k]));
                        }
                        fileWriter.newLine();
                    }
                    fileWriter.newLine();
                }
            }
            catch(FileNotFoundException e) {
                System.out.println("¡Lo sentimos!, ha ocurrido un error");
                System.out.println("Error: [No se ha encontrado el archivo]");    
            }
        }
    }  
    public static void createHabitsFile(String username) {
        if (username != null) {
            try {
				LocalDateTime actualDateTime = LocalDateTime.now();
        		int rand = (int) (Math.random() * 20000) + 10000;
				String file = username + "habits_" + actualDateTime.toLocalDate().toString() + "-" + actualDateTime.toLocalTime().toString() +"_"+rand;
                String path1 = Paths.get(".").toRealPath().toString() + "/src/Storage/HabitsFiles/" + file;
				file = username + "perfomance_" + actualDateTime.toLocalDate().toString() + "-" + actualDateTime.toLocalTime().toString() +"_"+rand;
                String path2 = Paths.get(".").toRealPath().toString() + "/src/Storage/HabitsFiles/" + file;
                File file1 = new File(path1);
                file1.createNewFile();
                File file2 = new File(path2);
                file2.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }  

	public static String getToFile(String file, String path) {
		Scanner separ = new Scanner(file).useDelimiter("_");
		String line = separ.next();
		File fileTo = new File(path);
		String[] directories = fileTo.list();
		for(int i=0;i<directories.length;i++){
			separ = new Scanner(directories[i]).useDelimiter("_");
			String line2 = separ.next();
			if(line2.equals(line)){
				separ.close();
				return directories[i];
			}
		}
		separ.close();
		return file;
	}

	public static String getFileSerial(String file){
		Scanner separ = new Scanner(file).useDelimiter("_");
		separ.next();
		separ.next();
		String line = separ.next();
		return line;
	}
}
