
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 125300
 */
public class MazeSolver {
    private Point position;
    private Point direction;
    private BufferedImage maze;
    
    public MazeSolver(Point startPos, BufferedImage maze) {
        position = startPos;
        this.maze = maze;
        direction = new Point(1, 0);        
    }
    
    private boolean canMoveForward() {
        Point forward = new Point(position.x + direction.x, position.y + direction.y);
        int color = maze.getRGB(forward.x, forward. y);
        return color != Color.BLACK.getRGB() ;
    }
    
    private void moveForward() {
        if (canMoveForward()) {
            position.translate(direction.x, direction.y);
        }
    }
        
    private void turnRight() {
            direction.move(direction .y, -1 * direction.x);
    }
        
    private void turnLeft() {
        direction.move(-1 * direction.y, direction.x);
    }
    
    private boolean canMoveRight() {
        turnRight();
        boolean canMove = canMoveForward();
        turnLeft();
        return canMove;
    }
    
    private boolean canMoveLeft() {
        turnLeft();
        boolean canMove = canMoveForward();
        turnRight();
        return canMove;
    }
    
    
}