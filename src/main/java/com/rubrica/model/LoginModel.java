package com.rubrica.model;

import java.sql.SQLException;

import com.rubrica.model.database.DAO.UtenteDAO;

public class LoginModel {
	private UtenteDAO utenteDAO;

    public LoginModel(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    public boolean verificaCredenziali(String username, String password) {
        try {
            return utenteDAO.verificaCredenziali(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
			return false;
        }
    }
}
