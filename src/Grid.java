import java.util.ArrayList;

public class Grid {
    int width;
    int height;
    ArrayList<Cell> cells = new ArrayList<Cell>();
    public Grid(int width, int height, ArrayList<Cell> cells){
        this.width = width;
        this.height = height;
        this.cells = cells;
    }

    public Grid(int width, int height){
        this.width = width;
        this.height = height;
    }

    public Grid(){

    }

    public ArrayList<Cell> getCells(){
        return this.cells;
    }

    public void addCell(Cell cell){
        cells.add(cell);
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
