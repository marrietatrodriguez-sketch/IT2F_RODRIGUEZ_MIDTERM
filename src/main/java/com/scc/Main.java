package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.ui.ScenarioMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize SQLite Database and Table
        DatabaseConnection.initializeDatabase();

        // Launch Console UI Menu Loop
        ScenarioMenu menu = new ScenarioMenu();
        menu.start();
    }
}