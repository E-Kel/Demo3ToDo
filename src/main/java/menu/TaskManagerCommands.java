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
        System.out.println("Which task's number do you wont to remove");
        String input = scanConsoleInput();
        int indexRemoveTask = Integer.parseInt(input) - 1;
        tasks.remove(indexRemoveTask);
        WriterTasks.serializeTasks(tasks);

        System.out.println("remove");
    }

    public void createTask() {
        System.out.println("Task title:");
        String title = scanConsoleInput();
        System.out.println("Task description:");
        String description = scanConsoleInput();

        tasks.add(new Task(title, description));
        WriterTasks.serializeTasks(tasks);
        System.out.println("create");
    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
