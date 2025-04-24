package dataproject;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author veranur
 */
public class Node {
    public int index;
    public String type; // "Empty", "Treasure", "Trap"
    public Node next;
    public JButton button; // Bu hücreye karşılık gelen Swing butonu

    public Node(int index, String type) {
        this.index = index;
        this.type = type;
        this.next = null;
        this.button = new JButton(String.valueOf(index + 1));
        this.button.setSize(80, 80);
        updateButton();
    }
    
    public void updateButton() {
        String label;
        if (index == 0) {
            label = "START";
        } else if (index == 31) {
            label = "FINISH";
        }else{
            label=String.valueOf(index +1);
        }
        switch (type) {
            case "treasure":
                button.setToolTipText("💰 Hazine! ");
                button.setBackground(new java.awt.Color(255, 255, 153)); // sarı
                button.setText(label + " 💰");
                break;
            case "trap":
                button.setToolTipText("☠️ Tuzak!");
                button.setBackground(new java.awt.Color(255, 153, 153)); // kırmızımsı
                 button.setText(label + "☠️");
                break;
            case "empty":
                button.setToolTipText("Boş alan");
                button.setBackground(new java.awt.Color(204, 229, 255)); // mavi
                 button.setText(label);
                break;
        }
    }
}
