import java.awt.*;
import java.awt.event.*;

public class Calci extends Frame implements ActionListener {
    // Components
    TextField display;
    Panel buttonPanel;
    String operator = "";
    double num1 = 0, num2 = 0;

    public Calci() {
        // Frame title
        setTitle("Calculator");

        // Display field
        display = new TextField();
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Button panel
        buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(5,5,5,5));

        // Buttons
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : buttons) {
            Button button = new Button(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // Frame settings
        setSize(400, 500);
        setVisible(true);

        // Close the frame on window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            // Append number or decimal to display
            display.setText(display.getText() + command);
        } else if ("/*-+".contains(command)) {
            // Save the first number and operator
            operator = command;
            num1 = Integer.parseInt(display.getText());
            display.setText("");
        } else if ("=".equals(command)) {
            // Perform calculation
            num2 = Integer.parseInt(display.getText());
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error: Division by zero");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new Calci();
    }
}
