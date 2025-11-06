package com.example;

import java.sql.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import io.github.cdimascio.dotenv.Dotenv;

public class LocalizationService {
    static Dotenv dotenv = Dotenv.configure()
            .filename(".env")
            .ignoreIfMissing()
            .load();

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/bmi_localization";
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    public static Map<String, String> getLocalizedStrings(Locale locale) {
        Map<String, String> strings = new HashMap<>();
        String lang = locale.getLanguage();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT `key`, value FROM localization_strings WHERE language = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, lang);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    strings.put(rs.getString("key"), rs.getString("value"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }
}