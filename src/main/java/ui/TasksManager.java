package ui;

import entity.Task;


import commands.TaskManagerCommands;
import java.time.LocalDateTime;
import java.util.*;

public class TasksManager {
    private static LocalDateTime localDateTime = LocalDateTime.now();
    private static String fileName;
    public List<Task> tasks = new ArrayList<>();
    TaskManagerCommands command = new TaskManagerCommands();

    public void menu() {
        System.out.println("Hello, dear User!");
        String input;
        do {
            do {
                System.out.println("What do you want to do?(You can input \"h\" for help) ");
                input = TaskManagerCommands.scanConsoleInput();
            } while (input.length() == 0);

            chooseAction(input.charAt(0));
        } while (input.charAt(0) != 'q');
    }

    private void chooseAction(char input) {
        switch (input) {
            case ('c'):
                command.createTask();
                break;
            case ('r'):
                command.removeTask();
                break;
            case ('h'):
                System.out.println("Operations available to you: \n" +
                        "c - create new entity.Task;\n" +
                        "s - show exist task;\n" +
                        "r - remove some task;\n" +
                        "q - quit;");
                break;
            case ('s'):
                command.showTask();
                break;
            case ('q'):
                System.exit(0);
                break;
            default:
                System.out.println("Wrong command! ");
        }
    }


}
