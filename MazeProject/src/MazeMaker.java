/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author 125300
 */
public class MazeMaker {
    public static void makeMaze(BufferedImage image) {
        ArrayList<Point> cells = new ArrayList<>();
        int x = (int)(Math.random() * (image.getWidth() / 2 - 1));
        x = 2 * x + 1;
        int y = (int)(Math.random() * (image.getHeight() / 2 - 1));
        y = 2 * y + 1;
        Point cell = new Point(x, y);
        cells.add(cell);
        image.setRGB(cell.x, cell.y, Color.WHITE.getRGB());
        while(cells.size() > 0) {
            cell = cells.get(cells.size() - 1) ;
            ArrayList<Point> neighbors = getNeighbors(cell, image);
            if (neighbors.size() == 0) {
                cells.remove(cells.size() - 1);
            } else { 
                int i = (int)(Math.random() * neighbors.size());
                Point nextCell = neighbors.get(i);
                cells.add(nextCell);
                image.setRGB(nextCell.x, nextCell.y, Color.WHITE.getRGB());
                int midX = (nextCell.x + cell.x) / 2;
                int midY = (nextCell.y + cell.y) / 2;
                image.setRGB(midX, midY, Color.WHITE.getRGB());
                cell = nextCell;
            }
        }
    }
    
    private static ArrayList<Point> getNeighbors(Point cell, BufferedImage image) {
        ArrayList<Point> neighbors = new ArrayList<>();
        if (cell.y > 1) {
            int color = image.getRGB(cell.x, cell.y - 2);
            if (color == Color.BLACK.getRGB()) {
                neighbors.add(new Point(cell.x, cell.y - 2));
            }
        }
        if (cell.y < image.getHeight() - 3) {
            int color = image.getRGB(cell.x, cell.y + 2);
            if (color == Color.BLACK.getRGB()) {
                neighbors.add(new Point(cell.x, cell.y + 2));
            }
         }
        if (cell.x > 1) {
            int color = image.getRGB(cell.x - 2, cell.y);
            if (color == Color.BLACK.getRGB()) {
                neighbors.add(new Point(cell.x - 2, cell.y));
            }
        }
        if (cell.x < image.getWidth() - 3) {
            int color = image.getRGB(cell.x + 2 , cell.y);
            if (color == Color.BLACK.getRGB()) {
                neighbors.add(new Point(cell.x + 2, cell.y));
            }
        } 
        return neighbors;
    }
}
