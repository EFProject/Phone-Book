package com.rubrica;

import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.rubrica.controller.LoginController;
import com.rubrica.model.LoginModel;
import com.rubrica.model.database.DatabaseConnection;
import com.rubrica.model.database.DAO.UtenteDAO;
import com.rubrica.view.LoginView;


public class MainApp {
    public static void main(String[] args) {
        // Event Dispatch Thread invocation
        SwingUtilities.invokeLater(() -> {
            try {
                // Configurazione connessione al database
                Connection connection = DatabaseConnection.getConnection();
                
                // Creazione del DAO e del modello Utente
                UtenteDAO utenteDAO = new UtenteDAO(connection);
                LoginModel loginModel = new LoginModel(utenteDAO);
                
                // Creazione della view e del controller Utente
                LoginView loginView = new LoginView();
                new LoginController(loginModel, loginView);
                
                // Mostra la view
                loginView.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Errore nella connessione al database: " + e.getMessage(), 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}