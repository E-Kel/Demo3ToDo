package ui;

import constants.Constants;
import entity.Task;


import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class TasksManager {
    private static LocalDateTime localDateTime = LocalDateTime.now();
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
        System.out.println(tasks);
        System.out.println("show");
    }

    private void removeTask() {
        System.out.println("remove");
    }

    private void createTask() {
        tasks.add(new Task("XUI", "ZALUPA"));
        tasks.add(new Task("ZALUPA", "XUI"));
        serializeTasks(tasks);
        System.out.println("create");
    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private static void serializeTasks(List<Task> tasks) {
        System.out.println("Enter file name");
        //logger message about enter file name
        String fileName = scanConsoleInput();

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(localDateTime.toString()
                + "-"
                + fileName
                + ".out"))){
            tasks.forEach(task -> {
                try {
                    out.writeObject(task);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (FileNotFoundException e){
            new File(Constants.PATH, localDateTime.toString()
                    + "-"
                    + fileName
                    + ".out");
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
        File temp = (File) Arrays.stream(files).map(File::lastModified);
        String serializedFile =  temp.getName();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(serializedFile + ".out"))){
            return (ArrayList)in.readObject();
        }
        catch (FileNotFoundException e){
            //log error message
        }
        catch (IOException e){

            //log error message
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
