package com.rubrica.view;

import com.rubrica.controller.RubricaController;
import com.rubrica.model.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RubricaView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnNuovo, btnModifica, btnElimina;

    public RubricaView() {
        setTitle("Rubrica Telefonica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Telefono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel buttons
        JPanel panel = new JPanel();
        btnNuovo = new JButton("Nuovo");
        btnModifica = new JButton("Modifica");
        btnElimina = new JButton("Elimina");
        panel.add(btnNuovo);
        panel.add(btnModifica);
        panel.add(btnElimina);
        add(panel, BorderLayout.SOUTH);
    }

    public void setController(RubricaController controller) {

        btnNuovo.addActionListener(_ -> {

            PersonaCraftingView editor = new PersonaCraftingView(this, null);
            editor.setVisible(true); // Block execution untile closure
            if (editor.isSaved()) {
                controller.aggiungiContatto(editor.getNome(), editor.getCognome(), editor.getIndirizzo(), editor.getTelefono(), editor.getEta());
            }

        });

        btnModifica.addActionListener(_ -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow >= 0) {
                Persona personaSelezionata = controller.getContatti().get(selectedRow);
                PersonaCraftingView editor = new PersonaCraftingView(this, personaSelezionata);
                editor.setVisible(true); // Block execution untile closure
                if (editor.isSaved()) {
                    controller.modificaContatto(personaSelezionata.getId(), editor.getNome(), editor.getCognome(), editor.getIndirizzo(), editor.getTelefono(), editor.getEta());
                }
            }  else {
                JOptionPane.showMessageDialog(this, 
                    "Seleziona una persona da modificare!", 
                    "Errore", JOptionPane.WARNING_MESSAGE);
            }

        });

        btnElimina.addActionListener(_ -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Persona personaSelezionata = controller.getContatti().get(selectedRow);
                String nomeCompleto = personaSelezionata.getNome() + " " + personaSelezionata.getCognome();
                
                // Modale di conferma
                int risposta = JOptionPane.showConfirmDialog(
                    this,
                    "Sei sicuro di voler eliminare la persona: " + nomeCompleto + "?",
                    "Conferma Eliminazione",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (risposta == JOptionPane.OK_OPTION) {
                    controller.eliminaContatto(personaSelezionata.getId());
                }

            }  else {
                JOptionPane.showMessageDialog(this, 
                    "Seleziona una persona da eliminare!", 
                    "Errore", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public void updateTable(List<Persona> contatti) {
        tableModel.setRowCount(0); // Clear Table
        for (Persona persona : contatti) {
            tableModel.addRow(new Object[]{persona.getNome(), persona.getCognome(), persona.getTelefono()});
        }
    }
}
