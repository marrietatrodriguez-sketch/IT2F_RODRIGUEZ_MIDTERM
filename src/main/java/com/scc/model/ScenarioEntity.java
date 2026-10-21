package com.scc.model;

public class ScenarioEntity {
    private int id;
    private String fullName;
    private String tier;
    private double monthlyFee;
    private String status;

    // Constructor for fetching from DB (with ID)
    public ScenarioEntity(int id, String fullName, String tier, double monthlyFee, String status) {
        this.id = id;
        this.fullName = fullName;
        this.tier = tier;
        this.monthlyFee = monthlyFee;
        this.status = status;
    }

    // Constructor for creating new records (auto-generated ID)
    public ScenarioEntity(String fullName, String tier, double monthlyFee, String status) {
        this.fullName = fullName;
        this.tier = tier;
        this.monthlyFee = monthlyFee;
        this.status = status;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getTier() { return tier; }
    public double getMonthlyFee() { return monthlyFee; }
    public String getStatus() { return status; }
}