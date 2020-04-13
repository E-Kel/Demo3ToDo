package menu;

import entity.Task;


import java.util.*;

public class TasksManager {
    public List<Task> tasks = new ArrayList<>();

    public void menu() {
        System.out.println("Hello, dear User!");
        String input;
        do {
            do {
                input = scanConsoleInput("What do you want to do?(You can input \"h\" for help) ");
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
        tasks = WriterTasks.deserializeTasks();
        tasks.forEach(task -> System.out.println("#" + (tasks.indexOf(task) + 1) +
                "\n" + task));
        System.out.println("show");
    }

    private void removeTask() {
//need validate input data
        String input = scanConsoleInput("Which task's number do you wont to remove");
        int indexRemoveTask = Integer.parseInt(input) - 1;
        tasks.remove(indexRemoveTask);
        WriterTasks.serializeTasks(tasks);

        System.out.println("remove");
    }

    private void createTask() {
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
