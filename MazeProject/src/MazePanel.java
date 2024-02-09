
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

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
    private Point entrance;
    private Point exit;
    private MazeSolver solver;
    private Timer timer;
    private int algorithm;
    
    public MazePanel() {
        image = new BufferedImage(201, 101, BufferedImage.TYPE_INT_RGB);
        clear();
        entrance = null;
        exit = null;
        solver = null;
        timer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                doNextSolveStep();
            }
        });
    }
    
    public void startSolving(int algorithm) {
        this.algorithm = algorithm;
        timer.start();
    }
    
    public void setEntranceAndExit() {
        int x = (int)(Math.random() * image.getWidth() / 2 - 1);
        x = 2 * x + 1;
        int y = (int)(Math.random() * image.getHeight() / 2 - 1);
        y = 2 * y + 1;
        entrance = new Point(x, y);
        x = (int)(Math.random() * image.getWidth() / 2 - 1);
        x = 2 * x + 1;
        y = (int)(Math.random() * image.getHeight() / 2 - 1);
        y = 2 * y + 1;
        exit = new Point (x, y);
        image.setRGB(entrance.x, entrance.y, Color.GREEN.getRGB());
        image.setRGB(exit.x, exit.y, Color.BLUE.getRGB());
        solver = new MazeSolver(entrance, image);
    }
    
    public void doNextSolveStep() {
        Point oldPosition = solver.getPosition();
        image.setRGB(oldPosition.x, oldPosition.y, Color.PINK.getRGB());
        if (algorithm == 0) {
            solver.solveRandom();
        }
        Point newPosition = solver.getPosition();
        image.setRGB(oldPosition.x, oldPosition.y, Color.RED.getRGB());
        repaint();
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
        setEntranceAndExit();
        repaint();
    }
}
