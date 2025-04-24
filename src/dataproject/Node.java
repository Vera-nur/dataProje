package dataproject;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author veranur
 */
public class Node {
    public int index;
    public String type;
    public Node next;
    public JButton button;
    public static ImageIcon iconTreasure= new ImageIcon("assets/treasure.png");
    public static ImageIcon iconTrap = new ImageIcon("assets/trap.png");

    public Node(int index, String type) {
        this.index = index;
        this.type = type;
        this.next = null;
        this.button = new JButton(String.valueOf(index + 1));
        this.button.setSize(80, 80);
        //burada her node oluşturulduğunda imageler çalışıyor her seferinde çalışmasına gerek yok buraya bi çözüm bul 
        Image img1 = iconTreasure.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconTreasure = new ImageIcon(img1);

        Image img2 = iconTrap.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        iconTrap = new ImageIcon(img2);
        updateButton();
    }
    
    public void updateButton() {
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
        }else{
            label=String.valueOf(index );
        }
        switch (type) {
            case "treasure":
                button.setBackground(new java.awt.Color(255, 255, 153));
                button.setText(label);
                button.setIcon(iconTreasure);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                break;
            case "trap":
                button.setBackground(new java.awt.Color(255, 153, 153));
                button.setText(label);
                button.setIcon(iconTrap);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                break;
            case "empty":
                button.setBackground(new java.awt.Color(204, 229, 255));
                button.setText(label);
                button.setIcon(null);
                break;
        }
    }
}
