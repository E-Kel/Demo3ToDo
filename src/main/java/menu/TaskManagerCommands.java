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
        if (tasks.isEmpty()) {
            logger.info("You have no existing tasks! Please create a task!");
        } else {
            tasks = WriterTasks.deserializeTasks();
            tasks.forEach(task -> logger.info("#" + (tasks.indexOf(task) + 1) +
                    "\n" + task));
            logger.info("End of tasks");
        }
    }

    public void removeTask() {
        if (tasks.isEmpty()) {
            logger.info("You have no existing tasks! Please create a task!");
        } else {
            logger.info("Which task would you like to remove? Please enter a task's number: ");
            String input = scanConsoleInput();

            if (!input.matches("[1-9]+")) {
                logger.info("Please enter a positive number!");
            } else {
                int indexRemoveTask = Integer.parseInt(input) - 1;

                if (indexRemoveTask > tasks.size() - 1) {
                    logger.info("Task " + "#" + input + " doesn't exist. " +
                            "Please a enter number " +
                            ((tasks.size() > 1) ? "from 1 to " + tasks.size() : "1") +
                            "!");
                } else {
                    tasks.remove(indexRemoveTask);
                    WriterTasks.serializeTasks(tasks);
                    logger.info("Task has been removed");
                }
            }
        }
    }

    public void createTask() {
        logger.info("Task title:");
        String title = scanConsoleInput();
        logger.info("Task description:");
        String description = scanConsoleInput();

        tasks.add(new Task(title, description));
        WriterTasks.serializeTasks(tasks);
        logger.info("Task has been created");
    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
        } while (input.isEmpty());

        return input;
    }
}
