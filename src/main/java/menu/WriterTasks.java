package menu;

import constants.Constants;
import entity.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WriterTasks {

    private static String fileName;
    public static void serializeTasks(List<Task> tasks)  {
        File file = rename(getFile());
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
        } catch (FileNotFoundException e) {
            createNewFile();
            //log error message
        } catch (IOException e) {
            //log error message
        }
    }

    public static List<Task> deserializeTasks()  {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Objects.requireNonNull(getFile())))) {
            return (ArrayList) in.readObject();
        } catch (FileNotFoundException e) {
            if (fileName == null) {
                setFileName();
            }
            createNewFile();
            //log error message
        } catch (IOException e) {
            e.printStackTrace();
            //log error message
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File createNewFile() {
        setFileName();
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            //log
        }
        return file;
    }

    private static void setFileName() {
        LocalDateTime localDateTime = LocalDateTime.now();
        fileName = Constants.PATH + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                "," + localDateTime.getHour() + "-" + localDateTime.getMinute() +
                "-" + localDateTime.getSecond() + "," + Constants.FILE_NAME + ".bin";
    }

    private static File getFile()  {
        File file = new File(Constants.PATH);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        File[] files = file.listFiles();
        assert files != null;
        if (files.length == 0) {
            return createNewFile();
        } else {
            return files[0];
        }
    }


    private static File rename(File file) {
        setFileName();
        File newFile = new File(fileName);
        file.renameTo(newFile);
        file.delete();
        return newFile;
    }
}
