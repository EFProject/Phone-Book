package com.rubrica.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BorderLayout());

        // Panel input
        JPanel panelInput = new JPanel(new GridLayout(2, 2));
        panelInput.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panelInput.add(txtUsername);
        panelInput.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panelInput.add(txtPassword);
        add(panelInput, BorderLayout.CENTER);

        // Panel button
        JPanel panelButton = new JPanel();
        btnLogin = new JButton("Login");
        panelButton.add(btnLogin);
        add(panelButton, BorderLayout.SOUTH);
    }

    public void setLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}