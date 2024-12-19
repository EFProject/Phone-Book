package com.rubrica.model.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO {
    private Connection connection;

    public UtenteDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean verificaCredenziali(String username, String password) throws SQLException {
        String query = "SELECT * FROM utenti WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // True se trova un risultato
            }
        }
    }
}
