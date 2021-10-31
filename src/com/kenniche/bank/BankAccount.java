package com.kenniche.bank;

import java.util.Random;

public class BankAccount {
	// Static things
	public static int numberAccounts = 0;
	public static double totalHoldings = 0;
	
	private static String generateId() {
		String mySubString = "";
		Random myRandom = new Random();
		for(int i = 0; i < 10; i++) {
			mySubString += myRandom.nextInt(10);
		}
		return mySubString;
	}

	// Object things
	private static String accountNumber;
	private double checkingBalance;
	private double savingsBalance;

	// Constructor Method
	public BankAccount() {
		BankAccount.numberAccounts += 1;
		this.checkingBalance = 0;
		this.savingsBalance = 0;
		accountNumber = BankAccount.generateId();
	}
	
	public static String getAccountNumber() {
		return accountNumber;
	}
	
	public double getCheckingBalance() {
		return this.checkingBalance;
	}

	public double getSavingsBalance() {
		return this.savingsBalance;
	}

	public void depositMoney(double amount, String typeOperation) {
		if(typeOperation.equals("savings"))
			this.savingsBalance += amount;
		else if(typeOperation.equals("checking"))
			this.checkingBalance += amount;
		BankAccount.totalHoldings += amount;
	}

	public void withdrawMoney(double amount, String typeOperation) {
		boolean successful = false;
		if(typeOperation.equals("savings")) {
			// check if enough in account
			if(this.savingsBalance - amount >= 0) {
				successful = true;
				this.savingsBalance -= amount;
				System.out.println("Whitdraw in savings account applied: " + amount);
			}
			else {
				System.out.println("Whitdraw not permmit, is not enough funds in your Savings account, you tried to Whitdraw: " + amount);
			}
		}
		else if(typeOperation.equals("checking")) {
			// check if enough in account
			if(this.checkingBalance - amount >= 0) {
				successful = true;
				this.checkingBalance -= amount;
				System.out.println("Whitdraw in checkings account applied: " + amount);
			}
			else {
				System.out.println("Whitdraw not permmit, is not enough funds in your checking account, you tried to Whitdraw: " + amount);
			}
		}
		if(successful) {
			BankAccount.totalHoldings -= amount;
		}

	}

	public void displayAccountBalance() {
		// %.2f formats decimal to the 10s place (for money)
		System.out.println(String.format("Savings: %.2f, Checking: %.2f", this.savingsBalance, this.checkingBalance));
	}

	public static void main(String[] args) {

		BankAccount myBankAccount = new BankAccount();
		//Print Account Number
		System.out.print("Account Number: ");
		System.out.println(getAccountNumber());
		myBankAccount.depositMoney(100.00, "checking");
		myBankAccount.depositMoney(5.00, "savings");
		myBankAccount.displayAccountBalance();

		System.out.print("Total Hondings: ");
		System.out.println(BankAccount.totalHoldings);

		myBankAccount.withdrawMoney(40, "checking");	
		myBankAccount.withdrawMoney(10, "savings");		
		myBankAccount.displayAccountBalance();
		
		System.out.print("Total Hondings: ");
		System.out.println(BankAccount.totalHoldings);		
	}
}// End of class BankAccount