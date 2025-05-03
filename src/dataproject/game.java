package dataproject;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class game extends JFrame {

    static String username;
    static int level;
    Node head;
    final int cellCount = 32;
    Node currentPlayer;
    int score = 0;

    private JPanel jPanel1;
    private JButton back;
    private JButton dice;
    private JLabel labelUsername;
    private JLabel labelInfo;
    private JLabel labelTop;

    public static ImageIcon iconPlayer;
    public static ImageIcon iconVictory;

    static {
        loadIcons();
    }
    private static final Point[] positions = {
        new Point(550, 30), new Point(660, 30), new Point(770, 30),
        new Point(500, 145), new Point(610, 145), new Point(720, 145), new Point(830, 145),
        new Point(420, 260), new Point(530, 260), new Point(640, 260), new Point(750, 260),
        new Point(100, 375), new Point(210, 375), new Point(320, 375), new Point(430, 375),
        new Point(540, 375), new Point(650, 375), new Point(760, 375), new Point(870, 375),
        new Point(30, 490), new Point(140, 490), new Point(250, 490), new Point(360, 490),
        new Point(470, 490), new Point(580, 490), new Point(690, 490), new Point(800, 490),
        new Point(12, 605), new Point(122, 605), new Point(232, 605), new Point(610, 605), new Point(720, 605), new Point(830, 605)

    };

    public game(String username, int level) {
        jPanel1 = new Background("assets/background.jpg");
        jPanel1.setLayout(null);

        back = Button.createButtonDesign("Back", new Color(228, 219, 162), 20, 20);
        back.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        back.setBounds(20, 720, 80, 40);
        back.addActionListener(e -> {
            new menu().setVisible(true);
            dispose();
        });

        dice = Button.createButtonDesign("Roll Dice", new Color(228, 219, 162), 20, 20);
        dice.setForeground(Color.BLACK);
        dice.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        dice.setBounds(950, 690, 165, 60);
        dice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        labelUsername = new JLabel();
        labelUsername.setBounds(970, 5, 220, 80);
        labelUsername.setForeground(Color.BLACK);
        labelUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        labelUsername.setOpaque(true);
        labelUsername.setBackground(new Color(228, 219, 162));
        labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
        labelUsername.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        labelInfo = new JLabel();
        labelInfo.setBounds(970, 95, 220, 50);
        labelInfo.setForeground(Color.BLACK);
        labelInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        labelInfo.setText("<html>Treasure +10 Points  <br>Trap -5 Points</html>");
        labelInfo.setOpaque(true);
        labelInfo.setHorizontalAlignment(SwingConstants.LEFT);
        labelInfo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        labelInfo.setBackground(new Color(228, 219, 162));

        ArrayList<TreeNode> allscores = allscoreArrayList(level);
        ArrayList<TreeNode> top3;
        if (allscores.size() >= 3) {
            top3 = new ArrayList<>(allscores.subList(allscores.size() - 3, allscores.size()));
            Collections.reverse(top3);
        } else {
            top3 = new ArrayList<>(allscores);
            Collections.reverse(top3);
        }

        StringBuilder top3Text = new StringBuilder("<html><b>TOP 3 PLAYERS</b><br>");
        for (int i = 0; i < top3.size(); i++) {
            TreeNode player = top3.get(i);
            top3Text.append(String.format("%d. %s: %d<br>", i + 1, player.username, player.score));
        }
        top3Text.append("</html>");

        labelTop = new JLabel();
        labelTop.setText(top3Text.toString());
        labelTop.setBounds(970, 500, 220, 150);
        labelTop.setForeground(Color.BLACK);
        labelTop.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        labelTop.setOpaque(true);
        labelTop.setBackground(new Color(228, 219, 162));
        labelTop.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        labelTop.setHorizontalAlignment(SwingConstants.LEFT);

        jPanel1.add(back);
        jPanel1.add(dice);
        jPanel1.add(labelUsername);
        jPanel1.add(labelInfo);
        jPanel1.add(labelTop);

        setContentPane(jPanel1);
        setLayout(null);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.username = username;
        this.level = level;
        updateLabel();

        randomCreateLinkedList(jPanel1);
    }

    private void updateLabel() {
        labelUsername.setText("<html>Username: " + username + "<br>Level: " + level + "<br>Score: " + score + "</html>");
    }

    private static void loadIcons() {
        if (iconPlayer == null || iconVictory == null) {
            iconPlayer = new ImageIcon("assets/player.png");
            iconVictory = new ImageIcon("assets/victory.png");
            Image img1 = iconPlayer.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            iconPlayer = new ImageIcon(img1);
            Image img2 = iconVictory.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            iconVictory = new ImageIcon(img2);
        }
    }

    private void randomCreateLinkedList(JPanel panel) {
        Random random = new Random();
        head = new Node(0, "empty");
        panel.add(head.button);
        head.button.setBounds(positions[0].x, positions[0].y, 90, 90);

        Node current = head;
        for (int i = 1; i < cellCount; i++) {
            Node newNode;
            if (i == cellCount - 1) {
                newNode = new Node(i, "empty");
            } else {
                newNode = new Node(i, randomType(random));
            }
            if (level == 1) {
                current.next = newNode;
                current = newNode;
            } else if (level == 2) {
                current.next = newNode;
                newNode.prev = current;
                current = newNode;
            }
            panel.add(current.button);
            current.button.setBounds(positions[i].x, positions[i].y, 90, 90);
        }
        currentPlayer = head;
        showCurrentPlayer(currentPlayer);
    }

    private String randomType(Random random) {

        if (level == 1) {
            int r = random.nextInt(100);
            if (r < 40) {
                return "treasure";
            } else if (r < 65) {
                return "trap";
            } else {
                return "empty";
            }
        } else {
            int r = random.nextInt(100);
            if (r < 40) {
                return "treasure";
            } else if (r < 60) {
                return "trap";
            } else if (r < 70) {
                return "forward";
            } else if (r < 85) {
                return "backward";
            } else {
                return "empty";
            }
        }
    }
    
    private void rollDice() {
        Random random = new Random();
        int rolls = random.nextInt(6) + 1;

        String message = "🎲 Dice Roll: " + rolls + "\n";
        int startIndex = currentPlayer.index + 1;
        dontShowCurrentPlayer(currentPlayer);

        for (int i = 0; i < rolls; i++) {
            if (currentPlayer.next != null) {
                currentPlayer = currentPlayer.next;
            } else {
                break;
            }
        }
        String start = getCellLabel(startIndex - 1);
        String end = getCellLabel(currentPlayer.index);
        message += "📍 Moved From: " + start + " to " + end + "\n";
        message += getLabelMessage(currentPlayer.index, currentPlayer.type);

        int oldScore = score;
        if (currentPlayer.type.equals("forward")) {
            int step = currentPlayer.moveStep;
            message += "\n⏩ Move forward " + step + " steps!\n";
            movePlayer(step);
            String newPosition = getCellLabel(currentPlayer.index);
            message += "📍 New Position: " + newPosition + "\n";
            message += getLabelMessage(currentPlayer.index, currentPlayer.type);
        } else if (currentPlayer.type.equals("backward")) {
            int step = currentPlayer.moveStep;
            message += "\n⏪ Move backward " + step + " steps!\n";
            movePlayer(-step);
            String newPosition = getCellLabel(currentPlayer.index);
            message += "📍 New Position: " + newPosition + "\n";
            message += getLabelMessage(currentPlayer.index, currentPlayer.type);
        } else {
            updateScore(currentPlayer.type);
        }
        int changeScore = score - oldScore;
        message += getScoreChange(changeScore);

        JOptionPane.showMessageDialog(this, message, "🎲 Turn Result", JOptionPane.PLAIN_MESSAGE, iconVictory);

        showCurrentPlayer(currentPlayer);
        if (currentPlayer.next == null) {
            endGame();
        }
    }

    private void updateScore(String type) {
        switch (type) {
            case "treasure":
                score += 10;
                break;
            case "trap":
                score -= 5;
                break;
            case "forward":
                movePlayer(currentPlayer.moveStep);
                break;
            case "backward":
                movePlayer(-currentPlayer.moveStep);
                break;
            case "empty":
                break;
        }
        updateLabel();
        showCurrentPlayer(currentPlayer);
    }

    private void movePlayer(int step) {
        dontShowCurrentPlayer(currentPlayer);
        if (step > 0) {
            int count = step;
            while (count > 0 && currentPlayer.next != null) {
                currentPlayer = currentPlayer.next;
                count--;
            }
        } else {
            int count = -step;
            while (count > 0 && currentPlayer.prev != null) {
                currentPlayer = currentPlayer.prev;
                count--;
            }
        }
        updateScore(currentPlayer.type);

    }

    private void showCurrentPlayer(Node node) {
        node.button.setText(null);
        node.button.setIcon(iconPlayer);
    }

    private void dontShowCurrentPlayer(Node node) {
        if (node != null) {
            node.updateButton();
        }
    }

    private void endGame() {
        try {
            FileWriter write = new FileWriter("score.txt", true);
            write.write(username + ", level " + level + ", " + score + "\n");
            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "An error occurred while saving the score!\nPlease try again.",
                    "File Write Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (level == 1) {
            int option = JOptionPane.showConfirmDialog(this, "Congratulations \nYour Score: " + score + "\n Would you like to continue to the next level?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconVictory);
            if (option == JOptionPane.YES_NO_OPTION && level == 1) {
                this.dispose();
                new game(username, 2).setVisible(true);
            } else {
                this.dispose();
                new menu().setVisible(true);
            }
        } else if (level == 2) {
            JOptionPane.showMessageDialog(this, " 🎉 Congratulations! \nYou've completed the game! \nYour Final Score: " + score, "\n Game Completed", JOptionPane.PLAIN_MESSAGE, iconVictory);
            this.dispose();
            new menu().setVisible(true);
        }
    }

    private String getCellLabel(int index) {
        if (index == 0) {
            return "START";
        }
        if (index == 31) {
            return "FINISH";
        }
        return String.valueOf(index);
    }

    private String getLabelMessage(int index, String type) {
        if (index == 31) {
            return "🏁 You have reached the FINISH!\n";
        }
        return "⛳️ Landed on: " + type.toUpperCase() + "\n";
    }

    private String getScoreChange(int change) {
        if (change > 0) {
            return "\n✨ You gained +" + change + " points!";
        } else if (change < 0) {
            return "\n💀 You lost " + (-change) + " points.";
        } else {
            return "\n➖ No points gained or lost.";
        }
    }

    public static ArrayList<TreeNode> allscoreArrayList(int level) {
        BinarySearchTree bst = new BinarySearchTree();

        try (BufferedReader br = new BufferedReader(new FileReader("score.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",\\s*");
                if (parts.length == 3) {
                    try {
                        String levelPart = parts[1].trim();
                        if (levelPart.startsWith("level ")) {
                            int currentLevel = Integer.parseInt(levelPart.substring(6).trim());
                            if (currentLevel == level) {
                                String username = parts[0].trim();
                                int score = Integer.parseInt(parts[2].trim());
                                bst.insert(username, currentLevel, score);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("Error parsing score data for line: " + line);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error reading the score file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return bst.getAllScores();
    }
}
