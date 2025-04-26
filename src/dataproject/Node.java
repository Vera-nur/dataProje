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
    public int moveStep= 0;
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
        this.button = new JButton(String.valueOf(index + 1));
        this.button.setSize(80, 80);
        updateButton();
    }
    private static void loadIcons(){
        if(iconTreasure==null || iconTrap==null || iconForward==null ||iconBackward==null ){
            iconTreasure=new ImageIcon("assets/treasure.png");
            iconTrap=new ImageIcon("assets/trap.png");
            iconBackward=new ImageIcon("assets/backward.png");
            iconForward=new ImageIcon("assets/forward.png");
            Image img1 = iconTreasure.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconTreasure = new ImageIcon(img1);
            Image img2 = iconTrap.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            iconTrap = new ImageIcon(img2);
            Image img3 = iconBackward.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconBackward = new ImageIcon(img3);
            Image img4 = iconForward.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconForward = new ImageIcon(img4);
        }
    }
    
    public void updateButton() {
        Random random = new  Random();
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
                if (moveStep==0)
                    moveStep = random.nextInt(6)+1;
                button.setBackground(new java.awt.Color(204, 255, 204));
                button.setText("<html><div style='text-align:center;'>" + moveStep + " ileri<br><b>" + label + "</b></div></html>");//ikon ekle
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.TOP);
                button.setIcon(iconForward);
                break;
            case "backward":
                if (moveStep==0)
                    moveStep = random.nextInt(6)+1;
                button.setBackground(new java.awt.Color(255, 204, 229));
                button.setText("<html><div style='text-align:center;'> " + moveStep + " geri<br><b>" + label + "</b></div></html>");
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.TOP);
                button.setIcon(iconBackward);//ikon ekle
                break;    
        }
    }
}