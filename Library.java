import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

public class Library extends Frame {
    private ArrayList<Book> library = new ArrayList<>();
    private List bookList = new List();
    private TextField titleField = new TextField();
    private TextField authorField = new TextField();
    private TextField searchField = new TextField();

    public Library() {
        setTitle("Library Management System");
        setSize(600, 400);
        setLayout(null);

        // Labels
        Label titleLabel = new Label("Book Title:");
        titleLabel.setBounds(20, 50, 80, 20);
        add(titleLabel);

        Label authorLabel = new Label("Book Author:");
        authorLabel.setBounds(20, 90, 80, 20);
        add(authorLabel);

        Label searchLabel = new Label("Search Title:");
        searchLabel.setBounds(20, 250, 80, 20);
        add(searchLabel);

        // Text Fields
        titleField.setBounds(120, 50, 200, 20);
        add(titleField);

        authorField.setBounds(120, 90, 200, 20);
        add(authorField);

        searchField.setBounds(120, 250, 200, 20);
        add(searchField);

        // Buttons
        Button addButton = new Button("Add Book");
        addButton.setBounds(120, 130, 100, 30);
        add(addButton);

        Button deleteButton = new Button("Delete Book");
        deleteButton.setBounds(120, 290, 100, 30);
        add(deleteButton);

        Button searchButton = new Button("Search");
        searchButton.setBounds(330, 250, 80, 30);
        add(searchButton);

        // Book List
        bookList.setBounds(350, 50, 200, 200);
        add(bookList);

        // Event Handling
        addButton.addActionListener(e -> addBook());
        deleteButton.addActionListener(e -> deleteBook());
        searchButton.addActionListener(e -> searchBook());

        // Window Closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();

        if (!title.isEmpty() && !author.isEmpty()) {
            library.add(new Book(title, author));
            bookList.add(title + " by " + author);
            titleField.setText("");
            authorField.setText("");
            showMessage("Book added successfully!");
        } else {
            showMessage("Please fill in both title and author.");
        }
    }

    private void deleteBook() {
        String title = searchField.getText();
        boolean removed = library.removeIf(book -> book.title.equalsIgnoreCase(title));

        if (removed) {
            bookList.removeAll();
            for (Book book : library) {
                bookList.add(book.toString());
            }
            searchField.setText("");
            showMessage("Book deleted successfully!");
        } else {
            showMessage("Book not found!");
        }
    }

    private void searchBook() {
        String title = searchField.getText();
        boolean found = false;

        for (Book book : library) {
            if (book.title.equalsIgnoreCase(title)) {
                showMessage("Book found: " + book);
                found = true;
                break;
            }
        }

        if (!found) {
            showMessage("Book not found!");
        }
    }

    private void showMessage(String message) {
        Dialog dialog = new Dialog(this, "Message", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 100);

        Label msgLabel = new Label(message);
        dialog.add(msgLabel);

        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.dispose());
        dialog.add(okButton);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new Library();
    }
}
