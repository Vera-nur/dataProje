package dataproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class score extends JFrame {

    private JLabel username_label;
    private JLabel best_score_label;
    private JLabel worst_score_label;
    private JLabel all_scores_label;
    private JButton backButton;

    public score(String username, TreeNode BestScore, TreeNode WorstScore, ArrayList AllScores) {
        setTitle("Player Info");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(538, 806);
        setLocationRelativeTo(null);
        setResizable(false);

        Background background = new Background("assets/scores.png");
        background.setLayout(null);

        Font labelFont = new Font("Comic Sans MS", Font.PLAIN, 15);

        username_label = new JLabel(username);
        username_label.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        username_label.setHorizontalAlignment(SwingConstants.CENTER); // Metni yatayda ortala

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        centerPanel.setBounds(0, 250, 538, 60);

        centerPanel.add(username_label);
        background.add(centerPanel);

        best_score_label = new JLabel();
        best_score_label.setFont(labelFont);
        best_score_label.setBounds(165, 310, 400, 30);
        background.add(best_score_label);

        worst_score_label = new JLabel();
        worst_score_label.setFont(labelFont);
        worst_score_label.setBounds(165, 350, 400, 30);
        background.add(worst_score_label);

        all_scores_label = new JLabel();
        all_scores_label.setFont(labelFont);
        all_scores_label.setBounds(165, 390, 248, 250);
        all_scores_label.setVerticalAlignment(SwingConstants.TOP);
        background.add(all_scores_label);

        
        JButton backButton = Button.createButtonDesign("BACK", new Color(255, 204, 0), 30, 30);
        createButton(backButton, 200, 600, 150, 50, Color.RED, new Font("Comic Sans MS", Font.BOLD, 24));
        background.add(backButton);

        username_label.setText(username);
        best_score_label.setText(String.format("Best Score: %d (Level %d)", BestScore.score, BestScore.level));
        worst_score_label.setText(String.format("Worst Score: %d (Level %d)", WorstScore.score, WorstScore.level));
        StringBuilder text = new StringBuilder("All Scores: ");
        for (Object Score : AllScores) {
            TreeNode node = (TreeNode) Score;
            text.append(node.score).append(" (Level ").append(node.level).append("), ");
        }
        if (text.length() > 0) {
            text.setLength(text.length() - 2);
        }

        all_scores_label.setText("<html>" + text.toString() + "</html>"); // çok uzun olursa alt satıra geçsin diye html kullandım

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu().setVisible(true);
                dispose();
            }
        });

        setContentPane(background);
    }

    private void createButton(JButton button, int x, int y, int width, int height, Color fgColor, Font font) {
        
        button.setBounds(x, y, width, height);
        button.setFont(font);
        button.setForeground(fgColor);
    }
}
