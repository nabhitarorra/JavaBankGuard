/*
Pseudocode:

1. Define OverdraftException and LargeDepositException for handling specific banking errors.

2. BankAccount class:
   - Include a balance to track the account's money.
   - Create a method to withdraw money:
     * Check if there's enough balance; if not, trigger OverdraftException.
     * Subtract the withdrawal amount from the balance.
   - Create a method to deposit money:
     * If the deposit is too large, trigger LargeDepositException.
     * Add the deposit amount to the balance.
   - Include a method to get the current balance.

3. main method:
   - Instantiate a BankAccount with a starting balance.
   - Try withdrawing more than available to see OverdraftException in action.
   - Try depositing a large amount to trigger LargeDepositException.
   - Show normal deposit and withdrawal operations.
*/


// Defining an exception for situations where an account overdraft is tried.
class OverdraftException extends Exception {
    // Constructor for OverdraftException
    public OverdraftException(String message) {
        super(message);
    }
}

// Defining an exception for situations where a large deposit is made.
class LargeDepositException extends Exception {
    // Constructor for LargeDepositException
    public LargeDepositException(String message) {
        super(message);
    }
}

// BankAccount class
class BankAccount {
    private double balance;  // Balance of the bank account.

    // Constructor that initializes a new BankAccount
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to withdraw
    // Throws OverdraftException.
    public void withdraw(double amount) throws OverdraftException {
        if (amount > balance) {
            throw new OverdraftException("Operation denied: Attempting to overdraft.");
        } else {
            balance -= amount;  // Deduct the amount from the balance if no exception is thrown.
        }
    }

    // Method to deposit amount.
    // Throws LargeDepositException if amount exceeds $10,000.
    public void deposit(double amount) throws LargeDepositException {
        if (amount > 10000) {
            throw new LargeDepositException("Operation denied: Deposit exceeds $10,000. Please see a bank employee.");
        } else {
            balance += amount;
        }
    }

    // Getter for account balance.
    public double getBalance() {
        return balance;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);  // Create a new bank account

        // Attempt to withdraw more than the account
        try {
            account.withdraw(1100.0);
        } catch (OverdraftException e) {
            System.out.println(e.getMessage());  // Print message from OverdraftException.
        }

        // Attempt to deposit more than $10,000
        try {
            account.deposit(15000.0);
        } catch (LargeDepositException e) {
            System.out.println(e.getMessage());  // Print message from LargeDepositException.
        }

        // Demonstrate successful deposit and withdrawal operations.
        try {
            account.deposit(500.0);
            System.out.println("Deposit successful. Current balance: $" + account.getBalance());
            account.withdraw(200.0);
            System.out.println("Withdrawal successful. Current balance: $" + account.getBalance());
        } catch (OverdraftException | LargeDepositException e) {
            // block for handling any exceptions during deposit or withdrawal.
            System.out.println(e.getMessage());
        }
    }
}

/*
Operation denied: Attempting to overdraft.
Operation denied: Deposit exceeds $10,000. Please see a bank employee.
Deposit successful. Current balance: $1500.0
Withdrawal successful. Current balance: $1300.0

Process finished with exit code 0 */
