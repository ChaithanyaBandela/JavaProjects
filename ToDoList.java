import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoList extends JFrame {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(taskListModel);

    public ToDoList() {
        setTitle("Interactive To-Do List");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Task input field
        JTextField taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Buttons
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton completeButton = new JButton("Mark as Complete");
        JButton clearButton = new JButton("Clear Completed Tasks");

        // Task list display
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Scroll Pane for Task List
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Top panel for task input and add button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Bottom panel for action buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(deleteButton);
        bottomPanel.add(completeButton);
        bottomPanel.add(clearButton);

        // Add components to the frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(e -> {
            String taskText = taskField.getText().trim();
            if (!taskText.isEmpty()) {
                Task task = new Task(taskText);
                tasks.add(task);
                updateTaskList();
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Task cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                tasks.remove(selectedIndex);
                updateTaskList();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        completeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                Task task = tasks.get(selectedIndex);
                task.setCompleted(true);
                updateTaskList();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to mark as complete!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            tasks.removeIf(Task::isCompleted);
            updateTaskList();
        });

        setVisible(true);
    }

    // Updates the JList with the current task list
    private void updateTaskList() {
        taskListModel.clear();
        for (Task task : tasks) {
            taskListModel.addElement(task.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoList::new);
    }

    // Task class to represent individual tasks
    static class Task {
        private String description;
        private boolean completed;

        public Task(String description) {
            this.description = description;
            this.completed = false;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public boolean isCompleted() {
            return completed;
        }

        @Override
        public String toString() {
            return description + (completed ? " (Completed)" : "");
        }
    }
}
