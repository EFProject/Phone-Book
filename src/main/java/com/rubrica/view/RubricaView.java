package com.rubrica.view;

import com.rubrica.controller.RubricaController;
import com.rubrica.model.classes.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class RubricaView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnUpdate, btnDelete;

    public RubricaView() {
        setTitle("Rubrica Telefonica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Centra la finestra sullo schermo

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Telefono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ToolBar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        ImageIcon addIcon = resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/add.png"))));
        ImageIcon editIcon = resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/edit.png"))));
        ImageIcon deleteIcon = resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/delete.png"))));

        btnAdd = new JButton("Nuovo", addIcon);
        toolBar.add(btnAdd);
        btnUpdate = new JButton("Modifica", editIcon);
        toolBar.add(btnUpdate);
        btnDelete = new JButton("Elimina", deleteIcon);
        toolBar.add(btnDelete);

        add(toolBar, BorderLayout.NORTH);

    }

    public void setController(RubricaController controller) {

        btnAdd.addActionListener(_ -> {

            PersonaCraftingView editor = new PersonaCraftingView(this, null);
            editor.setVisible(true); // Block execution untile closure
            if (editor.isSaved()) {
                controller.aggiungiContatto(editor.getNome(), editor.getCognome(), editor.getIndirizzo(), editor.getTelefono(), editor.getEta());
            }

        });

        btnUpdate.addActionListener(_ -> {

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

        btnDelete.addActionListener(_ -> {
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

    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
