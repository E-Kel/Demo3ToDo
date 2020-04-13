package menu;

import entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerCommands {
    private List<Task> tasks = new ArrayList<>();

    public void showTask() {
        tasks = WriterTasks.deserializeTasks();
        tasks.forEach(task -> System.out.println("#" + (tasks.indexOf(task) + 1) +
                "\n" + task));
        System.out.println("show");
    }

    public void removeTask() {
//need validate input data
        String input = scanConsoleInput("Which task's number do you wont to remove");
        int indexRemoveTask = Integer.parseInt(input) - 1;
        tasks.remove(indexRemoveTask);
        WriterTasks.serializeTasks(tasks);

        System.out.println("remove");
    }

    public void createTask() {
        tasks.add(new Task("EXAMPLE", "to do"));
        tasks.add(new Task(scanConsoleInput("Task title:"), scanConsoleInput("Task description:")));
        WriterTasks.serializeTasks(tasks);
        System.out.println("create");
    }

    public static String scanConsoleInput(String title) {
        System.out.println(title);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
