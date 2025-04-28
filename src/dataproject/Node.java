package dataproject;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author veranur
 */
public class Node {

    public int index;
    public String type;
    public Node next;
    public Node prev;
    public JButton button;
    public int moveStep = 0;
    private static ImageIcon iconTreasure;
    private static ImageIcon iconTrap;
    private static ImageIcon iconForward;
    private static ImageIcon iconBackward;

    static {
        loadIcons();
    }

    public Node(int index, String type) {
        this.index = index;
        this.type = type;
        this.next = null;
        this.prev = null;
        this.button = new JButton(String.valueOf(index + 1)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isArmed()) {
                    g2.setColor(getBackground().darker());
                } else {
                    g2.setColor(getBackground());
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();

                super.paintComponent(g);
            }
        };
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        updateButton();
    }

    private static void loadIcons() {
        if (iconTreasure == null || iconTrap == null || iconForward == null || iconBackward == null) {
            iconTreasure = new ImageIcon("assets/treasure.png");
            iconTrap = new ImageIcon("assets/trap.png");
            iconBackward = new ImageIcon("assets/backward.png");
            iconForward = new ImageIcon("assets/forward.png");
            Image img1 = iconTreasure.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            iconTreasure = new ImageIcon(img1);
            Image img2 = iconTrap.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            iconTrap = new ImageIcon(img2);
            Image img3 = iconBackward.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            iconBackward = new ImageIcon(img3);
            Image img4 = iconForward.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            iconForward = new ImageIcon(img4);
        }
    }

    public void updateButton() {
        Random random = new Random();
        String label;
        if (index == 0) {
            label = "START";
            button.setText(label);
            button.setIcon(null);
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
            button.setBackground(new Color(135, 79, 29));
            return;
        }else if (index == 31) {
            label = "FINISH";
            button.setText(label);
            button.setIcon(null);
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
            button.setBackground(new Color(135, 79, 29));
            return;
        } else {
            label = String.valueOf(index);
        }
        switch (type) {
            case "treasure":
                button.setBackground(new Color(253, 206, 76));
                button.setIcon(iconTreasure);
                button.setText("");
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                break;
            case "trap":
                button.setBackground(new Color(228, 117, 62));
                button.setIcon(iconTrap);
                button.setText("");
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                break;
            case "empty":
                button.setBackground(new Color(73, 168, 188));
                button.setText(label);
                button.setIcon(null);
                button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                break;
            case "forward":
                if (moveStep == 0) {
                    moveStep = random.nextInt(6) + 1;
                }
                button.setBackground(new Color(255, 239, 209));
                button.setText(moveStep + " ileri");
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
                button.setIcon(iconForward);
                break;
            case "backward":
                if (moveStep == 0) {
                    moveStep = random.nextInt(6) + 1;
                }
                button.setBackground(new Color(255, 239, 209));
                button.setText(moveStep + " geri");
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
                button.setIcon(iconBackward);
                break;
        }
    }
}
