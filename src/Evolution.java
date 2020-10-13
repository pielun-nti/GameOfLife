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
        generate();
        for (int i = 0; i < pixels.length; i++){
            pixels[i] = 0xFF00FF;
        }
    }





    /**
     * Draw method
     */
    private void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(1);
            return;
        }

        updatePixels();

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

    private void updateNeighbours() {
       for (int i = 0; i < grid.getCells().length; i++){
           for (int i2 = 0; i2 < grid.getCells().length; i2++) {
               if (grid.getCells()[i2] != grid.getCells()[i]) {
                   grid.getCells()[i].addNeighbor(grid.getCells()[i2]);
               }
           }
       }
    }


    private void updatePixels(){
    /*    for (int i = 0; i < grid.getWidth(); i++){
            for (int i2 = 0; i < grid.getHeight(); i2++){
                pixels[i2*grid.getWidth()+i] = ((grid.getCellAliveAt(i2,i)?0x000000:0xFFFFFF));

                System.out.println("pixel of cell is: " + grid.getCellAliveAt(i2, i));
            }
        }*/

    }

    public void generate(){
        for (int i = 0; i < grid.getCells().length; i++) {
            Random rand = new Random();
            Cell cell = new Cell(rand.nextInt(), rand.nextInt(), Math.random() > 0.5);
            grid.getCells()[i] = cell;
            //System.out.println("Added new cell: " + cell.toString());
        }
        System.out.println("Generated cells");
    }


    @Override
    public void run () {
        long startTime = System.nanoTime();
        while (running) {
            long lastUpdate = ((startTime - System.nanoTime()) / 1000000000) * (-1);
                if (lastUpdate > 1) {
                    //draw();
                    long timenow = ((startTime - System.nanoTime()) / 1000000000) * (-1);
                    frame.setTitle("GameOfLife " + timenow + " seconds running");
                    //updateNeighbours();
                    System.out.println("last update");
            }
            }

            //stop();
        }
    }

