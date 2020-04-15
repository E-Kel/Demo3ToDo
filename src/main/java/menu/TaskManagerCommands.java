package menu;

import entity.Task;
import org.apache.logging.log4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerCommands {
    private static final Logger logger = LogManager.getRootLogger();
    private List<Task> tasks = new ArrayList<>();

    public void showTask() {
        tasks = WriterTasks.deserializeTasks();
        tasks.forEach(task -> logger.info("#" + (tasks.indexOf(task) + 1) +
                "\n" + task));
        logger.info("show");
    }

    public void removeTask() {
//need validate input data
        logger.info("Which task's number do you wont to remove");
        String input = scanConsoleInput();
        int indexRemoveTask = Integer.parseInt(input) - 1;
        tasks.remove(indexRemoveTask);
        WriterTasks.serializeTasks(tasks);
        logger.info("remove");
    }

    public void createTask() {
        logger.info("Task title:");
        String title = scanConsoleInput();
        logger.info("Task description:");
        String description = scanConsoleInput();
        tasks.add(new Task(title, description));
        WriterTasks.serializeTasks(tasks);
        logger.info("create");
    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
