package com.scc.dao;

import com.scc.model.ScenarioEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public void addMember(ScenarioEntity member) {
        String sql = "INSERT INTO members (full_name, tier, monthly_fee, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getFullName());
            pstmt.setString(2, member.getTier());
            pstmt.setDouble(3, member.getMonthlyFee());
            pstmt.setString(4, member.getStatus());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting record: " + e.getMessage());
        }
    }

    public List<ScenarioEntity> getAllMembers() {
        List<ScenarioEntity> members = new ArrayList<>();
        String sql = "SELECT id, full_name, tier, monthly_fee, status FROM members";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                members.add(new ScenarioEntity(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("tier"),
                        rs.getDouble("monthly_fee"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching records: " + e.getMessage());
        }

        return members;
    }
}