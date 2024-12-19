package com.rubrica.model;
import java.sql.SQLException;
import java.util.Vector;

import com.rubrica.model.database.DAO.PersonaDAO;

public class RubricaModel {

    private PersonaDAO personaDAO;

    public RubricaModel(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public void aggiungiPersona(Persona persona) {
        try {
            personaDAO.aggiungiPersona(persona);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificaPersona(int id, Persona persona) {
        try {
            personaDAO.modificaPersona(id, persona);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    public void eliminaPersona(int id) {
        try {
            personaDAO.eliminaPersona(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Persona> getContatti() {
        try {
            return personaDAO.getContatti();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}