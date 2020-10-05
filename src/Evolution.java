import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Evolution extends Canvas implements Runnable {
    boolean running;
    Thread thread;
    ArrayList<Cell> cells = new ArrayList<Cell>();

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
            double frameUpdateinteval = 1000000000.0 / fps;
            double stateUpdateinteval = 1000000000.0 / ups;
            double deltaFrame = 0;
            double deltaUpdate = 0;
            long lastTime = System.nanoTime();

            while (running) {
                long now = System.nanoTime();
                Cell cell = new Cell();
                deltaFrame += (now - lastTime) / frameUpdateinteval;
                deltaUpdate += (now - lastTime) / stateUpdateinteval;
                lastTime = now;

                while (deltaUpdate >= 1) {
                    update();
                    deltaUpdate--;
                }

                while (deltaFrame >= 1) {
                    draw();
                    deltaFrame--;
                }
            }
            //stop();
        }
    }

}