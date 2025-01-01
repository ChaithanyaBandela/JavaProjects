import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

class BankAccount {
    private String accountHolderName;
    private int accountNumber;
    private double balance;

    public BankAccount(String accountHolderName, int accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolderName + ", Balance: $" + balance;
    }
}

public class BankManagement extends Frame {
    private HashMap<Integer, BankAccount> accounts = new HashMap<>();
    private int accountNumberCounter = 1001;

    private TextField nameField = new TextField();
    private TextField accountField = new TextField();
    private TextField amountField = new TextField();
    private TextArea outputArea = new TextArea();

    public BankManagement() {
        setTitle("Bank Account Management System");
        setSize(600, 400);
        setLayout(null);

        // Labels
        Label nameLabel = new Label("Account Holder Name:");
        nameLabel.setBounds(20, 50, 150, 20);
        add(nameLabel);

        Label accountLabel = new Label("Account Number:");
        accountLabel.setBounds(20, 90, 150, 20);
        add(accountLabel);

        Label amountLabel = new Label("Amount:");
        amountLabel.setBounds(20, 130, 150, 20);
        add(amountLabel);

        // Text Fields
        nameField.setBounds(180, 50, 150, 20);
        add(nameField);

        accountField.setBounds(180, 90, 150, 20);
        add(accountField);

        amountField.setBounds(180, 130, 150, 20);
        add(amountField);

        // Buttons
        Button createButton = new Button("Create Account");
        createButton.setBounds(20, 170, 120, 30);
        add(createButton);

        Button depositButton = new Button("Deposit");
        depositButton.setBounds(150, 170, 80, 30);
        add(depositButton);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setBounds(240, 170, 80, 30);
        add(withdrawButton);

        Button balanceButton = new Button("Check Balance");
        balanceButton.setBounds(330, 170, 120, 30);
        add(balanceButton);

        // Output Area
        outputArea.setBounds(20, 220, 540, 150);
        outputArea.setEditable(false);
        add(outputArea);

        // Event Handling
        createButton.addActionListener(e -> createAccount());
        depositButton.addActionListener(e -> depositMoney());
        withdrawButton.addActionListener(e -> withdrawMoney());
        balanceButton.addActionListener(e -> checkBalance());

        // Window Closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void createAccount() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            outputArea.setText("Error: Please enter an account holder name.");
            return;
        }

        int accountNumber = accountNumberCounter++;
        BankAccount account = new BankAccount(name, accountNumber);
        accounts.put(accountNumber, account);

        nameField.setText("");
        outputArea.setText("Account created successfully!\nAccount Number: " + accountNumber);
    }

    private void depositMoney() {
        try {
            int accountNumber = Integer.parseInt(accountField.getText().trim());
            double amount = Double.parseDouble(amountField.getText().trim());

            if (amount <= 0) {
                outputArea.setText("Error: Deposit amount must be greater than 0.");
                return;
            }

            BankAccount account = accounts.get(accountNumber);
            if (account != null) {
                account.deposit(amount);
                outputArea.setText("Deposited $" + amount + " to account " + accountNumber);
                accountField.setText("");
                amountField.setText("");
            } else {
                outputArea.setText("Error: Account not found!");
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Please enter valid account number and amount.");
        }
    }

    private void withdrawMoney() {
        try {
            int accountNumber = Integer.parseInt(accountField.getText().trim());
            double amount = Double.parseDouble(amountField.getText().trim());

            if (amount <= 0) {
                outputArea.setText("Error: Withdrawal amount must be greater than 0.");
                return;
            }

            BankAccount account = accounts.get(accountNumber);
            if (account != null) {
                if (amount > account.getBalance()) {
                    outputArea.setText("Error: Insufficient balance!");
                } else {
                    account.withdraw(amount);
                    outputArea.setText("Withdrew $" + amount + " from account " + accountNumber);
                }
                accountField.setText("");
                amountField.setText("");
            } else {
                outputArea.setText("Error: Account not found!");
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Please enter valid account number and amount.");
        }
    }

    private void checkBalance() {
        try {
            int accountNumber = Integer.parseInt(accountField.getText().trim());
            BankAccount account = accounts.get(accountNumber);

            if (account != null) {
                outputArea.setText(account.toString());
                accountField.setText("");
            } else {
                outputArea.setText("Error: Account not found!");
            }
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Please enter a valid account number.");
        }
    }

    public static void main(String[] args) {
        new BankManagement();
    }
}
