package dataproject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class menu extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new menu().setVisible(true);
        });
    }

    public menu() {
        setTitle("Pirate Adventure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(768, 768);
        setLocationRelativeTo(null);
        setResizable(false);

        Background background = new Background("assets/menu.png");
        background.setLayout(null);

        Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 24);

        JButton startButton =Button.createButtonDesign("START", new Color(255, 204, 0), 30, 30);
        createButton(startButton, 280, 80, 200, 60, Color.RED, buttonFont);
        background.add(startButton);

        JButton scoresButton = Button.createButtonDesign("SCORES", new Color(255, 204, 0), 30, 30);
        createButton(scoresButton, 280, 580, 200, 60, Color.RED, buttonFont);
        background.add(scoresButton);

        JButton exitButton = Button.createButtonDesign("EXIT", new Color(255, 102, 0), 30, 30);
        createButton(exitButton, 50, 580, 120, 50, Color.RED, buttonFont);
        background.add(exitButton);
        
        //Game ekranına geçiş
        startButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Sign in with a username to play:");
            if (username == null || username.trim().isEmpty()) {
                return;
            }

            new game(username, 1).setVisible(true);
            this.dispose();
        });
        
        //Score ekranına geçiş
        scoresButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "To check the scores, enter your username first:");
            if (username == null || username.trim().isEmpty()) {
                return;
            }

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
                            bst.insert(username, level, score);        // her bir oyunu node olarak ekliyorum ağaca
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
        

        exitButton.addActionListener(e -> System.exit(0));

        setContentPane(background);
    }

    private void createButton(JButton button, int x, int y, int width, int height, Color fgColor, Font font) {
        
        button.setBounds(x, y, width, height);
        button.setFont(font);
        button.setForeground(fgColor);
    }
}
