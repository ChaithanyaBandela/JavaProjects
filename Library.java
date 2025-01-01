import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author;
    }
}

public class Library {
    private static ArrayList<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search for a Book");
            System.out.println("4. Delete a Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> viewBooks();
                case 3 -> searchBook(scanner);
                case 4 -> deleteBook(scanner);
                case 5 -> System.out.println("Exiting the system...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        library.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\nBooks in the Library:");
            for (Book book : library) {
                System.out.println(book);
            }
        }
    }

    private static void searchBook(Scanner scanner) {
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();
        boolean found = false;

        for (Book book : library) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println("Book found: " + book);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found!");
        }
    }

    private static void deleteBook(Scanner scanner) {
        System.out.print("Enter book title to delete: ");
        String title = scanner.nextLine();
        boolean removed = library.removeIf(book -> book.title.equalsIgnoreCase(title));

        if (removed) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }
}
