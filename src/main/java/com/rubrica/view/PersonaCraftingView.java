package com.rubrica.view;

import javax.swing.*;

import com.rubrica.model.classes.Persona;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonaCraftingView extends JDialog {

    private JTextField campoNome;
    private JTextField campoCognome;
    private JTextField campoIndirizzo;
    private JTextField campoTelefono;
    private JTextField campoEta;

    private JButton btnSalva;
    private JButton btnAnnulla;

    private boolean save = false;

    public PersonaCraftingView(JFrame parent, Persona persona) {

        // Layout Configuration
        super(parent, persona == null ? "Nuova Persona" : "Modifica Persona", true);
        setLocationRelativeTo(parent);
        setSize(300, 200);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Fields
        add(new JLabel("Nome:"));
        campoNome = new JTextField(persona != null ? persona.getNome() : "");
        add(campoNome);

        add(new JLabel("Cognome:"));
        campoCognome = new JTextField(persona != null ? persona.getCognome() : "");
        add(campoCognome);

        add(new JLabel("Indirizzo:"));
        campoIndirizzo = new JTextField(persona != null ? persona.getIndirizzo() : "");
        add(campoIndirizzo);

        add(new JLabel("Telefono:"));
        campoTelefono = new JTextField(persona != null ? persona.getTelefono() : "");
        add(campoTelefono);

        add(new JLabel("Età:"));
        campoEta = new JTextField(persona != null ? String.valueOf(persona.getEta()) : "");
        add(campoEta);

        // Buttons
        btnSalva = new JButton("Salva");
        btnAnnulla = new JButton("Annulla");
        add(btnSalva);
        add(btnAnnulla);

        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEtaValid()) {
                    JOptionPane.showMessageDialog(PersonaCraftingView.this, 
                        "L'età deve essere un numero intero", 
                        "Errore di input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                save = true;
                dispose();
            }
        });

        btnAnnulla.addActionListener(_ -> dispose());

    }

    public boolean isSaved() {
        return save;
    }

    public String getNome() {
        return campoNome.getText();
    }

    public String getCognome() {
        return campoCognome.getText();
    }

    public String getIndirizzo() {
        return campoIndirizzo.getText();
    }

    public String getTelefono() {
        return campoTelefono.getText();
    }

    public Integer getEta() {
        return Integer.parseInt(campoEta.getText());
    }

    private boolean isEtaValid() {
        try {
            Integer.parseInt(campoEta.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}