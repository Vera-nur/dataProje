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
            optionPaneScreen ops=new optionPaneScreen(this,0);
            ops.setVisible(true);
            this.dispose();
        });
        
        //Score ekranına geçiş
        scoresButton.addActionListener(e -> {
            optionPaneScreen ops=new optionPaneScreen(this, 1);
            ops.setVisible(true);
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
