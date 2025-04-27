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
    private static ImageIcon iconEmpty;
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
        this.button = new JButton(String.valueOf(index + 1));
        this.button.setSize(120, 120);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        updateButton();
    }

    private static void loadIcons() {
        if (iconTreasure == null || iconTrap == null || iconForward == null || iconBackward == null || iconEmpty == null) {
            iconTreasure = new ImageIcon("assets/treasure.png");
            iconTrap = new ImageIcon("assets/trap.png");
            iconBackward = new ImageIcon("assets/backward.png");
            iconForward = new ImageIcon("assets/forward.png");
            iconEmpty = new ImageIcon("assets/empty.png");
            Image img1 = iconTreasure.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            iconTreasure = new ImageIcon(img1);
            Image img2 = iconTrap.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            iconTrap = new ImageIcon(img2);
            Image img3 = iconBackward.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            iconBackward = new ImageIcon(img3);
            Image img4 = iconForward.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            iconForward = new ImageIcon(img4);
            Image img5 = iconEmpty.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            iconEmpty = new ImageIcon(img5);
        }
    }

    public void updateButton() {
        Random random = new Random();
        String label;
        if (index == 0) {
            label = "START";
            button.setText(label);
            button.setIcon(null);
            return;
        } else if (index == 31) {
            label = "FINISH";
            button.setText(label);
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
                button.setIcon(null);//icon ekle
                button.setFont(new Font("Comic Sans MS", Font.BOLD, 20)); // daha büyük ve eğlenceli
                break;
            case "forward":
                if (moveStep == 0) {
                    moveStep = random.nextInt(6) + 1;
                }
                button.setBackground(new Color(255, 239, 209));
                button.setText("<html><div style='text-align:center; font-family:'Comic Sans MS'; font-size:16px; font-weight:bold;'>"
                        + moveStep + " ileri<br><b></b></div></html>");
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.TOP);
                button.setIcon(iconForward);
                break;
            case "backward":
                if (moveStep == 0) {
                    moveStep = random.nextInt(6) + 1;
                }
                button.setBackground(new Color(255, 239, 209));
                button.setText("<html><div style='text-align:center; font-family:'Comic Sans MS'; font-size:16px; font-weight:bold;'>"
                        + moveStep + " geri<br><b></b></div></html>");
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.TOP);
                button.setIcon(iconBackward);//ikon ekle
                break;
        }
    }
}
