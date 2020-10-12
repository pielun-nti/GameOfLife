import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GameOfLife {

    private BufferedImage image;
    private int[] pixels;
    private int scale = 4;

    private Evolution evolution;



    public static void main(String[] args) {
        Grid grid = new Grid(200, 200);
        Evolution evolutionManager = new Evolution(400, 250, grid);
        evolutionManager.start();
    }
}