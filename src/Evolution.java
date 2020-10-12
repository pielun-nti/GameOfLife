import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 * Evolution class in java
 *
 * @author Pierre Lundström
 */
public class Evolution extends Canvas implements Runnable {
    int width;
    int height;
    ArrayList<Cell> cells;
    Grid grid;
    boolean running;
    private Thread thread;
    private BufferedImage image;
    private int[] pixels;
    private int scale = 4;
    JFrame frame;

    /**
     * Constructor
     * @param w
     * @param h
     */
    public Evolution(int w, int h, Grid grid) {
        this.width = w;
        this.height = h;
        this.grid = grid;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        Dimension size = new Dimension(scale * w, scale * h);
        frame = new JFrame();
        frame.setPreferredSize(size);
        frame.setTitle("GameOfLife");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    /**
     * Custom resize bufferedimage solution
     * @param image
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }



    /**
     * Draw method
     */
    private void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        java.awt.Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }


    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*
    private void draw() {
        /*BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        java.awt.Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();*/
    //}

    private void update() {
       for (int i = 0; i < grid.getCells().size(); i++){
           grid.getCells().get(i).addNeighbor();
       }
    }


    @Override
    public void run () {

        while (running) {
            long now = System.nanoTime();
            Random rand = new Random();
            Cell cell = new Cell(rand.nextInt(), rand.nextInt());
            Cell neighbor = new Cell(rand.nextInt(), rand.nextInt());
            cell.addNeighbor(neighbor);
            if (Math.random() < 0.5){
                Cell neighbor2 = new Cell(rand.nextInt(), rand.nextInt());
                Cell neighbor3 = new Cell(rand.nextInt(), rand.nextInt());
                cell.addNeighbor(neighbor2);
                cell.addNeighbor(neighbor3);
            }
            grid.getCells().add(cell);
            cells = grid.getCells();

            for (int i = 0; i < cells.size(); i++){
                if (cells.get(i).getNeighbors().size() < 2){
                    if (cells.get(i).getCellAlive()) {
                        cells.get(i).setCellAlive(false);
                        //System.out.println("killed cell because cell < 2" + cells.get(i).toString());
                    }
                } else if (cells.get(i).getNeighbors().size() == 3){
                    if (!cells.get(i).getCellAlive()) {
                        cells.get(i).setCellAlive(true);
                        //System.out.println("cell is alive: " + cells.get(i).toString());
                    }
                }else{
                    if (cells.get(i).getCellAlive()) {
                        cells.get(i).setCellAlive(false);
                        //System.out.println("killed cell because size > 3" + cells.get(i).toString());
                    }
                }
                update();
                draw();
            }

            //stop();
        }
    }

}