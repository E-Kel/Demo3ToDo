package menu;

import entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TaskManagerCommands {
    private List<Task> tasks = new ArrayList<>();

    public void showTask() {
        tasks = WriterTasks.deserializeTasks();
        tasks.forEach(task -> System.out.println("#" + (tasks.indexOf(task) + 1) +
                "\n" + task));
        System.out.println("show");
    }

    private Pattern onlyNumbers = Pattern.compile("[0-9]");
    public void removeTask() {
        System.out.println("Which task's number do you want to remove");
        String input = scanConsoleInput();
        if (input.matches(String.valueOf(onlyNumbers))){
            int indexRemoveTask = Integer.parseInt(input) - 1;
            try {
                tasks.remove(indexRemoveTask);
                WriterTasks.serializeTasks(tasks);
                System.out.println("Successfully removed");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a correct number. You currently have " + tasks.size() + " tasks");
            }
        }else System.out.println("Please use numbers only");
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
