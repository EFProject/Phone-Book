package com.rubrica.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // Panel input
        JPanel panelInput = new JPanel(new GridLayout(2, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelInput.add(new JLabel("Username:"));
        txtUsername = new JTextField(20);
        panelInput.add(txtUsername);
        panelInput.add(new JLabel("Password:"));
        txtPassword = new JPasswordField(20);
        panelInput.add(txtPassword);
        add(panelInput, BorderLayout.CENTER);

        // ToolBar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ImageIcon loginIcon = resizeIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/login.png"))));

        btnLogin = new JButton("Login", loginIcon);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(btnLogin);
        add(toolBar, BorderLayout.SOUTH);

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

    private ImageIcon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}