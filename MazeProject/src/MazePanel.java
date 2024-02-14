
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
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
    private int iterationCount;
    
    public MazePanel() {
        image = new BufferedImage(101, 51, BufferedImage.TYPE_INT_RGB);
        clear();
        entrance = null;
        exit = null;
        solver = null;
        timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                doNextSolveStep();
            }
        });
    }
    
    public void setSpeed(int speed) {
        timer.setDelay((int)(256/ Math.pow(2, speed)));
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
        //x = (int)(Math.random() * image.getWidth() / 2 - 1);
        //x = 2 * x + 1;
        //y = (int)(Math.random() * image.getHeight() / 2 - 1);
        //y = 2 * y + 1;
        if ( Math.random() < 0.5) {
            //exit on a vertical edge
            if (Math.random() < 0.5) {
                x = 1;
            } else {
                x = image.getWidth() - 2;
            }
            y = (int)(Math.random() * image.getHeight() / 2 - 1);
            y = 2 * y + 1;
        } else {
            //exit on a horizontal edge
            if (Math.random() < 0.5) {
                y = 1;
            } else {
                y = image.getHeight() - 2;
            }
            x = (int)(Math.random() * image.getWidth() / 2 - 1);
            x = 2 * x + 1;
        }
        exit = new Point (x, y);
        image.setRGB(entrance.x, entrance.y, Color.GREEN.getRGB());
        image.setRGB(exit.x, exit.y, Color.BLUE.getRGB());
        solver = new MazeSolver(entrance, image);
    }
    
    public void doNextSolveStep() {
        iterationCount++;
        Point oldPosition = solver.getPosition();
        image.setRGB(oldPosition.x, oldPosition.y, Color.PINK.getRGB());
        if (algorithm == 0) {
            solver.solveRandom();
        } else if (algorithm == 1) {
            solver.solveWallFollowRight();
        } else if (algorithm == 2) {
            solver.solveWallFollowLeft();
        } else if (algorithm == 3) {
            solver.solvePledge();
        }
        Point newPosition = solver.getPosition();
        int color = image.getRGB(newPosition.x, newPosition.y);
        if(color == Color.BLUE.getRGB()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Maze has been Solved in " + iterationCount + " steps!");
            
        }
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
    public void removeWalls() {
        int n = (int)(image.getWidth() * image.getHeight() * 0.02);
        MazeMaker.removeWalls(image, n);
        repaint();
    }
}
