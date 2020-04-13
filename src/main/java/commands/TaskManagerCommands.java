package commands;

import constants.Constants;
import entity.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManagerCommands {
    private static String fileName;
    public List<Task> tasks = new ArrayList<>();

    public void showTask() {
        try {
            tasks = deserializeTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tasks);
        System.out.println("show");
    }

    public void removeTask() {
        System.out.println("remove");
    }

    public void createTask() {
        tasks.add(new Task("EXAMPLE", "to do"));
        tasks.add(new Task("to do", "EXAMPLE"));
        try {
            serializeTasks(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("create");
    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void serializeTasks(List<Task> tasks) throws IOException {
        File file = rename(getFile());
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(tasks);
        }
        catch (FileNotFoundException e){
            createNewFile();
            //log error message
        }
        catch (IOException e){
            //log error message
        }
    }

    private static List<Task> deserializeTasks() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Objects.requireNonNull(getFile())))){
            return (ArrayList) in.readObject();
        }
        catch (FileNotFoundException e){
            if (fileName == null) {
                setFileName();
            }
            createNewFile();
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

    private static File createNewFile() throws IOException {
        setFileName();
        File file =  new File(fileName);
        file.createNewFile();
        return file;
    }

    private static void setFileName(){
        LocalDateTime localDateTime = LocalDateTime.now();
        fileName = Constants.PATH + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                "," + localDateTime.getHour() + "-" + localDateTime.getMinute() +
                "-" + localDateTime.getSecond() + "," + Constants.FILE_NAME + ".bin";
    }

    private static File getFile() throws IOException {
        File file = new File(Constants.PATH);
        if(!file.isDirectory()) {
            file.mkdir();
        }
        File[] files = file.listFiles();
        assert files != null;
        if(files.length == 0){
            return createNewFile();
        }
        else {
            return files[0];
        }
    }


    private static File rename(File file){
        setFileName();
        File newFile = new File(fileName);
        file.renameTo(newFile);
        file.delete();
        return newFile;
    }
}
