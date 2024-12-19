package com.rubrica.model.database.DAO;

import java.sql.*;
import java.util.Vector;

import com.rubrica.model.Persona;

public class PersonaDAO {

	private Connection connection;

    public PersonaDAO(Connection connection) {
        this.connection = connection;
    }

    public void aggiungiPersona(Persona persona) throws SQLException {
        String sql = "INSERT INTO contatti (nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, persona.getNome());
            stmt.setString(2, persona.getCognome());
            stmt.setString(3, persona.getIndirizzo());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getEta());
            stmt.executeUpdate();
        }
    }

	public Vector<Persona> getContatti() throws SQLException {
        String sql = "SELECT * FROM contatti";
        Vector<Persona> contatti = new Vector<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona persona = new Persona(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("indirizzo"),
                    rs.getString("telefono"),
                    rs.getInt("eta")
                );
                contatti.add(persona);
            }
        }
        return contatti;
    }

    public void modificaPersona(int id, Persona persona) throws SQLException {
        String sql = "UPDATE contatti SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, persona.getNome());
            stmt.setString(2, persona.getCognome());
            stmt.setString(3, persona.getIndirizzo());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getEta());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
    }    

    public void eliminaPersona(int id) throws SQLException {
        String sql = "DELETE FROM contatti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}