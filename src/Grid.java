import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grid {
    int width;
    int height;
    Cell[] cells;
    public Grid(int width, int height, Cell[] cells){
        this.width = width;
        this.height = height;
        this.cells = cells;
    }

    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        this.cells = new Cell[width*height];
    }

    public boolean getCellAliveAt(int xPos, int yPos){
        return cells[yPos*width+xPos].getCellAlive();
    }

    public Grid(){

    }

    public Cell[] getCells(){
        return cells;
    }

    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
