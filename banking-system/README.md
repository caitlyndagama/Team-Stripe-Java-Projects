# Banking System

## Overview

The Banking System is a Java application developed by Team Stripe for the Java Industry Prep 2026 semester. The application simulates a basic banking environment where users can log in, manage accounts, and perform transactions. It demonstrates object-oriented programming principles and structured application design.

This project includes multiple account types, user authentication, and transaction handling to simulate real-world banking behaviour.

---

## How to Compile

To compile the application, run the following command:

javac -cp src src/Main.java -d out

---

## How to Run

After compiling, run the application using:

java -cp out Main

---

## Test Credentials

The following hardcoded users can be used to test the application:

| Username | Password | Role |
|----------|----------|------|
| admin    | admin123 | Administrator |
| user1    | user123  | Customer |
| user2    | user123  | Customer |

(Note: These credentials are defined in the source code for testing purposes.)

---

## Account Types

The system supports three account types:

### 1. Savings Account
- Enforces a minimum balance requirement
- Prevents withdrawals that would drop below the minimum balance

### 2. Current Account
- Allows overdraft up to a specified limit
- Used for frequent transactions

### 3. Fixed Deposit Account
- Funds cannot be withdrawn before maturity
- Designed for long-term savings

Each account type enforces its own rules to simulate real-world banking constraints.

---

## Features

- User login authentication
- Multiple account types
- Deposit and withdrawal functionality
- Transaction history tracking
- Balance checking
- Role-based user access

---

## Technologies Used

- Java
- Object-Oriented Programming
- ArrayLists
- Abstract Classes
- VS Code
- GitHub

---

## Team Members

- Tam
- Caitlyn
- Michael
- Husna
- Similo

---

## Project Structure

src/
- Main.java
- BankingAccount.java
- SavingsAccount.java
- CurrentAccount.java
- FixedDepositAccount.java

---

## Year

2026

---

## Notes

This project was developed as part of the Java Industry Prep coursework and demonstrates core Java programming concepts including abstraction, encapsulation, and class inheritance.
