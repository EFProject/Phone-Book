package com.rubrica.view;

import javax.swing.*;

import com.rubrica.model.classes.Persona;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PersonaCraftingView extends JDialog {

    private JTextField campoNome;
    private JTextField campoCognome;
    private JTextField campoIndirizzo;
    private JTextField campoTelefono;
    private JTextField campoEta;

    private JButton btnSave;
    private JButton btnCancel;

    private boolean save = false;

    public PersonaCraftingView(JFrame parent, Persona persona) {

        // Layout Configuration
        super(parent, persona == null ? "Nuova Persona" : "Modifica Persona", true);
        setLocationRelativeTo(parent);
        setSize(400, 300);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // Panel
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelInput.add(new JLabel("Nome:"));
        campoNome = new JTextField(persona != null ? persona.getNome() : "");
        panelInput.add(campoNome);

        panelInput.add(new JLabel("Cognome:"));
        campoCognome = new JTextField(persona != null ? persona.getCognome() : "");
        panelInput.add(campoCognome);

        panelInput.add(new JLabel("Indirizzo:"));
        campoIndirizzo = new JTextField(persona != null ? persona.getIndirizzo() : "");
        panelInput.add(campoIndirizzo);

        panelInput.add(new JLabel("Telefono:"));
        campoTelefono = new JTextField(persona != null ? persona.getTelefono() : "");
        panelInput.add(campoTelefono);

        panelInput.add(new JLabel("Età:"));
        campoEta = new JTextField(persona != null ? String.valueOf(persona.getEta()) : "");
        panelInput.add(campoEta);

        add(panelInput, BorderLayout.CENTER);

        // ToolBar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ImageIcon saveIcon = resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/save.png"))));
        ImageIcon cancelIcon = resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cancel.png"))));

        btnSave = new JButton("Salva", saveIcon);
        btnCancel = new JButton("Annulla", cancelIcon);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(btnSave);
        toolBar.add(btnCancel);
        add(toolBar, BorderLayout.SOUTH);

        btnSave.addActionListener(new ActionListener() {
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

        btnCancel.addActionListener(_ -> dispose());

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

    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}