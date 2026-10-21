package com.scc.ui;

import com.scc.dao.ScenarioDao;
import com.scc.model.ScenarioEntity;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final ScenarioDao scenarioDao = new ScenarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            printHeader("GYM MEMBERSHIP MANAGEMENT");
            System.out.println("[1] Add New Member");
            System.out.println("[2] View All Members");
            System.out.println("[3] Exit System");
            printDivider();
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter choice (1-3): ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewMember();
                    break;
                case 2:
                    viewAllMembers();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select between 1 and 3.");
            }
        } while (choice != 3);
    }

    private void addNewMember() {
        printHeader("ADD NEW MEMBER");

        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine().trim();

        System.out.print("Enter Tier (BASIC / PREMIUM / VIP): ");
        String tier = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter Monthly Fee: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid amount. Enter Monthly Fee: ");
            scanner.next();
        }
        double monthlyFee = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Status (ACTIVE / INACTIVE): ");
        String status = scanner.nextLine().trim().toUpperCase();

        ScenarioEntity newMember = new ScenarioEntity(fullName, tier, monthlyFee, status);
        scenarioDao.addMember(newMember);

        System.out.println("\nSUCCESS: Member record saved to database!");
    }

    private void viewAllMembers() {
        List<ScenarioEntity> members = scenarioDao.getAllMembers();

        printHeader("ALL GYM MEMBERS");

        System.out.println("+-----+--------------------+-----------+--------------+------------+");
        System.out.printf("| %-3s | %-18s | %-9s | %-12s | %-10s |\n", "ID", "FULL NAME", "TIER", "MONTHLY FEE", "STATUS");
        System.out.println("+-----+--------------------+-----------+--------------+------------+");

        for (ScenarioEntity m : members) {
            System.out.printf("| %-3d | %-18s | %-9s | %-12.2f | %-10s |\n",
                    m.getId(), m.getFullName(), m.getTier(), m.getMonthlyFee(), m.getStatus());
        }

        System.out.println("+-----+--------------------+-----------+--------------+------------+");
        System.out.println("Total Records Displayed: " + members.size());
        printDivider();
    }

    private void printHeader(String title) {
        System.out.println("\n" + "=".repeat(65));
        System.out.printf("%38s\n", title);
        System.out.println("=".repeat(65));
    }

    private void printDivider() {
        System.out.println("=".repeat(65));
    }
}