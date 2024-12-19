package com.rubrica.controller;

import com.rubrica.model.LoginModel;
import com.rubrica.model.RubricaModel;
import com.rubrica.model.database.DatabaseConnection;
import com.rubrica.model.database.DAO.PersonaDAO;
import com.rubrica.view.LoginView;
import com.rubrica.view.RubricaView;

import java.sql.Connection;

import javax.swing.*;

public class LoginController {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginController(LoginModel loginModel, LoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;

        loginView.setLoginListener(_ -> verificaLogin());
    }

    private void verificaLogin() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();

        if (loginModel.verificaCredenziali(username, password)) {
            loginView.dispose(); 
            mostraFinestraPrincipale();
        } else {
            loginView.showError("Username o password non corretti.");
        }
    }

    private void mostraFinestraPrincipale() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Configurazione connessione al database
                Connection connection = DatabaseConnection.getConnection();
                
                // Creazione del DAO e del modello Rubrica
                PersonaDAO personaDAO = new PersonaDAO(connection);
                RubricaModel model = new RubricaModel(personaDAO);
                
                // Creazione della view e del controller Rubrica
                RubricaView mainView = new RubricaView();
                new RubricaController(model, mainView);
                
                // Mostra la view
                mainView.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Errore nella connessione al database: " + e.getMessage(), 
                    "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}