import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankManagementSystem extends JFrame {
    private Bank bank;

    public BankManagementSystem() {
        bank = new Bank();
        setTitle("Bank Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Components
        JPanel panel = new JPanel();
        JButton addAccountButton = new JButton("Add Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton balanceButton = new JButton("Check Balance");
        JTextField accountNumberField = new JTextField(10);
        JTextField amountField = new JTextField(10);
        JTextArea displayArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        panel.add(new JLabel("Account Number: "));
        panel.add(accountNumberField);
        panel.add(new JLabel("Amount: "));
        panel.add(amountField);
        panel.add(addAccountButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(balanceButton);
        panel.add(scrollPane);

        add(panel);

        // Add account button action listener
        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assume details are entered and create a new account
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                Customer customer = new Customer("John Doe", "123 Main St", 30, "123-456-7890");
                Account account = new Account(accountNumber, customer, 0); // Initial balance is 0
                bank.addAccount(account);
                displayArea.append("Account added: " + accountNumber + "\n");
            }
        });

        // Deposit button action listener
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                double amount = Double.parseDouble(amountField.getText());
                for (Account acc : bank.getAllAccounts()) {
                    if (acc.getAccountNumber() == accountNumber) {
                        acc.deposit(amount);
                        displayArea.append("Deposited " + amount + " to account " + accountNumber + "\n");
                        break;
                    }
                }
            }
        });

        // Withdraw button action listener
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                double amount = Double.parseDouble(amountField.getText());
                for (Account acc : bank.getAllAccounts()) {
                    if (acc.getAccountNumber() == accountNumber) {
                        acc.withdraw(amount);
                        displayArea.append("Withdrawn " + amount + " from account " + accountNumber + "\n");
                        break;
                    }
                }
            }
        });

        // Check balance button action listener
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                for (Account acc : bank.getAllAccounts()) {
                    if (acc.getAccountNumber() == accountNumber) {
                        displayArea.append("Balance of account " + accountNumber + ": " + acc.getBalance() + "\n");
                        break;
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BankManagementSystem();
    }
}

class Customer {
    private String name;
    private String address;
    private int age;
    private String phoneNumber;

    public Customer(String name, String address, int age, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

class Account {
    private int accountNumber;
    private Customer customer;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(int accountNumber, Customer customer, double balance) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}

class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public double checkBalance(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc.getBalance();
            }
        }
        return -1; // Account not found
    }

    public ArrayList<Account> getAllAccounts() {
        return accounts;
    }
}

class Transaction {
    private String type; // "Deposit" or "Withdrawal"
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
