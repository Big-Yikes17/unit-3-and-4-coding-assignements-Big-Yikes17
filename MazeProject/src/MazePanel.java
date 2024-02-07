
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 125300
 */
public class MazePanel extends JPanel{
    private BufferedImage image;
    
    public MazePanel() {
        image = new BufferedImage(305, 205, BufferedImage.TYPE_INT_RGB);
        clear();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public final void clear() {
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    public void drawMaze() {
        clear();
        MazeMaker.makeMaze(image);
        repaint();
    }
}
