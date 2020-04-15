package menu;

import org.apache.logging.log4j.*;

import static menu.TaskManagerCommands.scanConsoleInput;

public class TasksManager {
    private static final Logger logger = LogManager.getRootLogger();
    private TaskManagerCommands tasksManager = new TaskManagerCommands();

    public void menu() {
        logger.info("Hello, dear User!");
        String input;
        do {
            logger.info("Please enter symbol of action. (You can input \"h\" for help)");
            input = scanConsoleInput();

            if (!input.matches("[a-zA-Z]")) {
                logger.info("Please enter latin letter!");
            } else {
                chooseAction(input);
            }
        } while (!input.equals("q"));
    }

    private void chooseAction(String input) {
        switch (input) {
            case ("c"):
                tasksManager.createTask();
                break;
            case ("r"):
                tasksManager.removeTask();
                break;
            case ("h"):
                logger.info("Operations available to you: \n" +
                        "c - create new task;\n" +
                        "s - show exist task;\n" +
                        "r - remove some task;\n" +
                        "h - help;\n" +
                        "q - quit;");
                break;
            case ("s"):
                tasksManager.showTask();
                break;
            case ("q"):
                System.exit(0);
                break;
            default:
                logger.info("It's not the action command! Please try again.(You can input \"h\" for help)");
        }
    }
}
