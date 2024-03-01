import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

public class Main {
    private static List<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("To-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View Tasks");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Set to an invalid choice
            }

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    viewTasks();
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);
    }

    private static void addTask() {
        System.out.print("Enter the task description: ");
        String description = scanner.nextLine();
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    private static void editTask() {
        viewTasks();
        System.out.print("Enter the task number to edit: ");
        int taskNumber = scanner.nextInt();

        if (isValidTaskNumber(taskNumber)) {
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter the new task description: ");
            String newDescription = scanner.nextLine();
            tasks.get(taskNumber - 1).setDescription(newDescription);
            System.out.println("Task edited successfully.");
        } else {
            System.out.println("Invalid task number. Please enter a valid task number.");
        }
    }

    private static void deleteTask() {
        viewTasks();
        System.out.print("Enter the task number to delete: ");
        int taskNumber = scanner.nextInt();

        if (isValidTaskNumber(taskNumber)) {
            tasks.remove(taskNumber - 1);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Invalid task number. Please enter a valid task number.");
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
            }
        }
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }
}
