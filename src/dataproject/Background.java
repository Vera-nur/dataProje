package dataproject;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author veranur
 */
public class Background extends JPanel{
    private Image backgroundImage;

    public Background(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
