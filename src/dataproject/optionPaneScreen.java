/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataproject;

/**
 *
 * @author veranur
 */
import dataproject.BinarySearchTree;
import dataproject.score;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class optionPaneScreen extends JFrame {

    private JTextField usernameField;
    private menu menuScreen;

    public optionPaneScreen(menu menuScreen, int i) {
        this.menuScreen = menuScreen;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        if (i == 0) {
            JPanel panel = new JPanel() {
                Image background = new ImageIcon("assets/game_entry.png").getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            };
            panel.setLayout(null);
            add(panel);

            usernameField = new JTextField();
            usernameField.setBounds(114, 218, 271, 47);
            usernameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            usernameField.setOpaque(false);
            usernameField.setBackground(new Color(0, 0, 0, 0));
            usernameField.setForeground(Color.BLACK);
            usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            panel.add(usernameField);

            JButton cancelButton = creaButton(106, 279, 129, 43);
            JButton startButton = creaButton(267, 279, 129, 43);
            panel.add(cancelButton);
            panel.add(startButton);

            startButton.addActionListener(e -> {
                String username = usernameField.getText();
                if (username == null || username.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lütfen bir kullanıcı adı girin!");
                } 
                new game(username, 1).setVisible(true);
                this.dispose();
            });

            cancelButton.addActionListener(e -> {
                menuScreen.setVisible(true);
                dispose();
            });
        }
        if (i == 1) {
            JPanel panel = new JPanel() {
                Image background = new ImageIcon("assets/score_entry.png").getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            };
            panel.setLayout(null);
            add(panel);

            usernameField = new JTextField();
            usernameField.setBounds(114, 218, 271, 47);
            usernameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            usernameField.setOpaque(false);
            usernameField.setBackground(new Color(0, 0, 0, 0));
            usernameField.setForeground(Color.BLACK);
            usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            panel.add(usernameField);

            JButton cancelButton = creaButton(106, 279, 129, 43);
            JButton oktButton = creaButton(267, 279, 129, 43);
            panel.add(cancelButton);
            panel.add(oktButton);

            oktButton.addActionListener(e -> {
                String username = usernameField.getText();

                BinarySearchTree bst = new BinarySearchTree();
                boolean userFound = false;

                try (BufferedReader br = new BufferedReader(new FileReader("score.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(", ");
                        if (parts.length >= 3 && parts[0].equals(username)) {
                            userFound = true;
                            try {
                                int level = Integer.parseInt(parts[1].replaceAll("\\D+", ""));
                                int score = Integer.parseInt(parts[2]);
                                bst.insert(username, level, score);
                            } catch (NumberFormatException ex) {
                                System.err.println("Error parsing score data for user: " + username);
                            }
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Error reading the score file: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!userFound) {
                    JOptionPane.showMessageDialog(this,
                            "No score found for user: " + username,
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                new score(username, bst.getBestScore(), bst.getWorstScore(), bst.getAllScores()).setVisible(true);
                this.dispose();
            });

            cancelButton.addActionListener(e -> {
                menuScreen.setVisible(true);
                dispose();
            });
        }

    }

    private JButton creaButton(int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setText("");
        return button;
    }
}
