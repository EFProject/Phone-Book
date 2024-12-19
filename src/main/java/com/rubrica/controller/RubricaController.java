package com.rubrica.controller;

import java.util.Vector;

import com.rubrica.model.Persona;
import com.rubrica.model.RubricaModel;
import com.rubrica.view.RubricaView;

public class RubricaController {

    private RubricaModel rubrica;
    private RubricaView view;

    public RubricaController(RubricaModel rubrica, RubricaView view) {
        this.rubrica = rubrica;
        this.view = view;

        view.setController(this);
        view.updateTable(rubrica.getContatti());

        System.out.println("Applicazione correttamente avviata");
    }

    public Vector<Persona> getContatti(){
        return rubrica.getContatti();
    }

    public void aggiungiContatto(String nome, String cognome, String indirizzo, String telefono, Integer eta) {
        Persona nuovaPersona = new Persona(0, nome, cognome, indirizzo, telefono, eta);
        rubrica.aggiungiPersona(nuovaPersona);
        view.updateTable(rubrica.getContatti());
        System.out.println("Contatto " + nome + " " + cognome + " aggiunto alla rubrica con successo.");
    }

    public void modificaContatto(int id, String nome, String cognome, String indirizzo, String telefono, Integer eta) {
        Persona editPersona = new Persona(id, nome, cognome, indirizzo, telefono, eta);
        rubrica.modificaPersona(id, editPersona);
        view.updateTable(rubrica.getContatti());
        System.out.println("Contatto " + nome + " " + cognome + " modificato con successo.");
    }

    public void eliminaContatto(int id) {
        rubrica.eliminaPersona(id);
        view.updateTable(rubrica.getContatti());
        System.out.println("Contatto con id " + id + " eliminato con successo.");
    }
}
