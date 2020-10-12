import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

public class EvolutionOld extends Canvas implements Runnable {
    JFrame frame;
    BufferedImage image;
    int[] pixels;
    int fps;
    int ups;
    int scale;
    double t = 0;
    boolean running;
    Thread thread;
    ArrayList<Cell> cells;
    Grid grid;
    int width;
    int height;

    public EvolutionOld(Grid grid, int w, int h, int scale){
        this.grid = grid;
        this.width = w;
        this.height = h;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        for (int i = 0 ; i < pixels.length ; i++) {
            //pixels[i] =;
        }
       // initComponents();

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
    }

    private void update() {
       /* if (!firstTime) {
            t += Math.PI / 180;

            for (int i = 0; i < square1.getHeight(); i++) {
                for (int j = 0; j < square1.getWidth(); j++) {
                    try {
                        //if (j <= width*scale & i < (height*mainMenuBar.getSize().getWidth())*scale || j <= width*scale & i <= (-height * scale)) {
                        pixels[(ySquare1 + i) * width + xSquare1 + j] = square1.getPixels()[i * square1.getWidth() + j];
                        //  System.out.println((ySquare1 + i) * width + xSquare1 + j + "= " + square1.getPixels()[i * square1.getWidth() + j]);
                        //}
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                        //System.out.println("IndexOutOfBounds Exception: " + ex.toString());
                    }
                }
            }*/
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
                            System.out.println("killed cell because cell < 2" + cells.get(i).toString());
                        }
                    } else if (cells.get(i).getNeighbors().size() == 3){
                        if (!cells.get(i).getCellAlive()) {
                            cells.get(i).setCellAlive(true);
                            System.out.println("cell is alive: " + cells.get(i).toString());
                        }
                    }else{
                        if (cells.get(i).getCellAlive()) {
                            cells.get(i).setCellAlive(false);
                            System.out.println("killed cell because size > 3" + cells.get(i).toString());
                        }
                    }
                }

            //stop();
        }
    }

}