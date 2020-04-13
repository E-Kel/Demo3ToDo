package menu;

import static menu.TaskManagerCommands.scanConsoleInput;

public class TasksManager {
    private TaskManagerCommands tasksManager = new TaskManagerCommands();


    public void menu() {
        System.out.println("Hello, dear User!");
        String input;
        do {
            do {
                System.out.println("What do you want to do?(You can input \"h\" for help) ");
                input = scanConsoleInput();
            } while (input.length() == 0);

            chooseAction(input.charAt(0));
        } while (input.charAt(0) != 'q');
    }

    private void chooseAction(char input) {
        switch (input) {
            case ('c'):
                tasksManager.createTask();
                break;
            case ('r'):
                tasksManager.removeTask();
                break;
            case ('h'):
                System.out.println("Operations available to you: \n" +
                        "c - create new entity.Task;\n" +
                        "s - show exist task;\n" +
                        "r - remove some task;\n" +
                        "q - quit;");
                break;
            case ('s'):
                tasksManager.showTask();
                break;
            case ('q'):
                System.exit(0);
                break;
            default:
                System.out.println("Wrong command! ");
        }
    }
}
