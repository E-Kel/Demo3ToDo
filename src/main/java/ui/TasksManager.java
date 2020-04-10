package ui;


import java.util.Scanner;

public class TasksManager {
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
                createTask();
                break;
            case ('r'):
                removeTask();
                break;
            case ('h'):
                System.out.println("Operations available to you: \n" +
                        "c - create new Task;\n" +
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
        System.out.println("show");
    }

    private void removeTask() {
        System.out.println("remove");
    }

    private void createTask() {
        System.out.println("create");

    }

    public static String scanConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
