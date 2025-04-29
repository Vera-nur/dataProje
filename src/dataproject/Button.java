package dataproject;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author veranur
 */
public class Button extends JButton{
    
    public static JButton createButtonDesign(String text, Color backgroundColor, int arcWidth, int arcHeight) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isArmed()) {
                    g2.setColor(getBackground().darker());
                } else {
                    g2.setColor(getBackground());
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

                super.paintComponent(g);
                g2.dispose();
            }
        };
        button.setBackground(backgroundColor);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
    
}
