package ui;

import constants.Constants;
import entity.Task;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TasksManager {
    private static LocalDateTime localDateTime = LocalDateTime.now();
    private static String fileName;
    public List<Task> tasks = new ArrayList<>();

    public void menu() {
        System.out.println("Hello, dear User!");
        String input;
        do {
            do {
                System.out.println("What do you want to do?(You can input \"h\" for help) ");
                input = scanConsoleInput();
            } while (input.length() == 0);

            chooseAction(input.charAt(0));
        } while (input.charAt(0) != 'q');
    }

    private void chooseAction(char input) {
        switch (input) {
            case ('c'):
                createTask();
                break;
            case ('r'):
                removeTask();
                break;
            case ('h'):
                System.out.println("Operations available to you: \n" +
                        "c - create new entity.Task;\n" +
                        "s - show exist task;\n" +
                        "r - remove some task;\n" +
                        "q - quit;");
                break;
            case ('s'):
                showTask();
                break;
            case ('q'):
                System.exit(0);
                break;
            default:
                System.out.println("Wrong command! ");
        }
    }

    private void showTask() {
        tasks = deserializeTasks();
        System.out.println(tasks);
        System.out.println("show");
    }

    private void removeTask() {
        System.out.println("remove");
    }

    private void createTask() {
        tasks.add(new Task("EXAMPLE", "to do"));
        tasks.add(new Task("to do", "EXAMPLE"));
        serializeTasks(tasks);
        System.out.println("create");
    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private static void serializeTasks(List<Task> tasks) {
        setFileName();
        File file = createNewFile(fileName);
        assert file != null;
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(tasks);
        }
        catch (FileNotFoundException e){
            createNewFile(fileName);
            //log error message
        }
        catch (IOException e){
            //log error message
        }
    }

    private static List<Task> deserializeTasks(){
        File file = new File(Constants.PATH);
        File[] files = file.listFiles();
        assert files != null;
        File temp = getLastModifiedFile(files);

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(temp))){
            return (ArrayList) in.readObject();
        }
        catch (FileNotFoundException e){
            if (fileName == null) {
                setFileName();
            }
            createNewFile(fileName);
            //log error message
        }
        catch (IOException e){
            e.printStackTrace();
            //log error message
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File createNewFile(String fileName){
        File file = new File(Constants.PATH);
        if(!file.isDirectory()) {
            file.mkdir();
        }
        file = new File( Constants.PATH + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                "," + localDateTime.getHour() + "-" + localDateTime.getMinute() + "," + fileName + ".bin");
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File getLastModifiedFile(File[] files){
        if(files.length > 0){
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        }
        return files[files.length - 1];
    }

    private static void setFileName(){
        System.out.println("Enter file name");
        //logger message about enter file name
        fileName = scanConsoleInput();
    }
}
