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
    public Node prev;
    public JButton button;
    private static ImageIcon iconTreasure;
    private static ImageIcon iconTrap;
    static {
        loadIcons();
    }

    public Node(int index, String type) {
        this.index = index;
        this.type = type;
        this.next = null;
        this.prev = null;
        this.button = new JButton(String.valueOf(index + 1));
        this.button.setSize(80, 80);
        updateButton();
    }
    private static void loadIcons(){
        if(iconTreasure==null || iconTrap==null){
            iconTreasure=new ImageIcon("assets/treasure.png");
            iconTrap=new ImageIcon("assets/trap.png");
            Image img1 = iconTreasure.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconTreasure = new ImageIcon(img1);
            Image img2 = iconTrap.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconTrap = new ImageIcon(img2);
        }
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
            label=String.valueOf(index);
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
            case "forward":
                button.setBackground(new java.awt.Color(204, 255, 204));
                button.setText(label);//ikon ekle
                button.setIcon(null);
                break;
            case "backward":
                button.setBackground(new java.awt.Color(255, 204, 229));
                button.setText(label);
                button.setIcon(null);//ikon ekle
                break;    
        }
    }
}
