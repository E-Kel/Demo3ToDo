package menu;

import entity.Task;
import org.apache.logging.log4j.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerCommands {
    static final Logger infoLogger = LogManager.getLogger(TaskManagerCommands.class);
    private List<Task> tasks = new ArrayList<>();

    public void showTask() {
        tasks = WriterTasks.deserializeTasks();
        tasks.forEach(task -> infoLogger.info("#" + (tasks.indexOf(task) + 1) +
                "\n" + task));
        infoLogger.info("show");
    }

    public void removeTask() {
//need validate input data
        infoLogger.info("Which task's number do you wont to remove");
        String input = scanConsoleInput();
        int indexRemoveTask = Integer.parseInt(input) - 1;
        tasks.remove(indexRemoveTask);
        WriterTasks.serializeTasks(tasks);

        infoLogger.info("remove");
    }

    public void createTask() {
        infoLogger.info("Task title:");
        String title = scanConsoleInput();

        infoLogger.info("Task description:");
        String description = scanConsoleInput();

        tasks.add(new Task(title, description));
        WriterTasks.serializeTasks(tasks);
        infoLogger.info("create");
    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
