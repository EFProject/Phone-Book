package com.rubrica;

import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.rubrica.controller.RubricaController;
import com.rubrica.model.RubricaModel;
import com.rubrica.model.database.DatabaseConnection;
import com.rubrica.model.database.DAO.PersonaDAO;
import com.rubrica.view.RubricaView;


public class MainApp {
    public static void main(String[] args) {
        // Event Dispatch Thread invocation
        SwingUtilities.invokeLater(() -> {
            try {
                // Configurazione connessione al database
                Connection connection = DatabaseConnection.getConnection();
                
                // Creazione del DAO e del modello
                PersonaDAO personaDAO = new PersonaDAO(connection);
                RubricaModel model = new RubricaModel(personaDAO);
                
                // Creazione della view e del controller
                RubricaView view = new RubricaView();
                new RubricaController(model, view);
                
                // Mostra la view
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Errore nella connessione al database: " + e.getMessage(), 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}