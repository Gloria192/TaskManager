import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class InvalidTaskException extends IllegalArgumentException {
    public InvalidTaskException(String message) {
        super(message);
    }
}

public class TaskManager {

    public void addTask(List<String> tasks, String task) {
        if (task == null || task.trim().isEmpty()) {
            throw new InvalidTaskException("Invalid task: Task cannot be null or empty.");
        }
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    public void sortTasks(List<String> tasks) {
        Collections.sort(tasks);
        System.out.println("Tasks sorted: " + tasks);
    }

    public int searchTask(List<String> tasks, String target) {
        int index = tasks.indexOf(target);
        if (index == -1) {
            System.out.println("Task not found: " + target);
        } else {
            System.out.println("Task found at index: " + index);
        }
        return index;
    }

    public void removeTask(List<String> tasks, String task) {
        if (!tasks.remove(task)) {
            throw new InvalidTaskException("Task not found: " + task);
        }
        System.out.println("Task removed: " + task);
    }

    public String findLongestTask(List<String> tasks) {
        if (tasks.isEmpty()) {
            throw new InvalidTaskException("No tasks available to find the longest task.");
        }
        String longest = "";
        for (String task : tasks) {
            if (task.length() > longest.length()) {
                longest = task;
            }
        }
        System.out.println("Longest task: " + longest);
        return longest;
    }

    public String getTask(List<String> tasks, int index) {
        try {
            String task = tasks.get(index);
            System.out.println("Task at index " + index + ": " + task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index out of bounds - " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        List<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Task Manager! Enter your choice:");

        try {
            while (true) {
                System.out.println("\nOptions:\n1. Add Task\n2. Sort Tasks\n3. Search Task\n4. Remove Task\n5. Find Longest Task\n6. Get Task by Index\n7. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter task to add: ");
                        String task = scanner.nextLine();
                        taskManager.addTask(tasks, task);
                        break;
                    case 2:
                        taskManager.sortTasks(tasks);
                        break;
                    case 3:
                        System.out.print("Enter task to search: ");
                        String target = scanner.nextLine();
                        taskManager.searchTask(tasks, target);
                        break;
                    case 4:
                        System.out.print("Enter task to remove: ");
                        String removeTask = scanner.nextLine();
                        taskManager.removeTask(tasks, removeTask);
                        break;
                    case 5:
                        taskManager.findLongestTask(tasks);
                        break;
                    case 6:
                        System.out.print("Enter index to get task: ");
                        int index = scanner.nextInt();
                        taskManager.getTask(tasks, index);
                        break;
                    case 7:
                        System.out.println("Exiting Task Manager. Goodbye!");
                        return; // Exit the program
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (InvalidTaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            System.out.println("Operation logged.");
            scanner.close();
        }
    }
}







