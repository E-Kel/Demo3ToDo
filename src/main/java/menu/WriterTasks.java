package menu;

import constants.Constants;
import entity.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.logging.log4j.*;


public class WriterTasks {
    private static String fileName;
    private static final Logger logger = LogManager.getRootLogger();

    public static void serializeTasks(List<Task> tasks) {
        File file = rename(getFile());
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
        } catch (FileNotFoundException e) {
            logger.error("File not found", e);
        } catch (IOException e) {
            logger.error("Something wrong with file", e);
        }
    }

    public static List<Task> deserializeTasks() {
        if (!verifyFileExists()) {
            createNewFile();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Objects.requireNonNull(getFile())))) {
            return (ArrayList) in.readObject();
        } catch (FileNotFoundException e) {
            logger.error("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Something wrong with file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("Class not found");
        }
        return null;
    }

    private static File createNewFile() {
        setFileName();
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            logger.error("File can't be created");
        }
        return file;
    }

    private static void setFileName() {
        LocalDateTime localDateTime = LocalDateTime.now();
        fileName = Constants.PATH + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                "," + localDateTime.getHour() + "-" + localDateTime.getMinute() +
                "-" + localDateTime.getSecond() + "," + Constants.FILE_NAME + ".bin";
    }

    private static File getFile() {
        if (!verifyFileExists()) {
            return createNewFile();
        } else {
            return getAllFilesInFolder()[0];
        }
    }

    private static File rename(File file) {
        setFileName();
        File newFile = new File(fileName);
        file.renameTo(newFile);
        file.delete();
        return newFile;
    }

    private static boolean verifyFileExists() {
        return getAllFilesInFolder().length > 0;
    }

    private static File[] getAllFilesInFolder() {
        File file = new File(Constants.PATH);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        return file.listFiles();
    }
}
