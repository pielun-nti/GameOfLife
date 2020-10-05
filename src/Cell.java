import java.util.ArrayList;

public class Cell implements Rules{
    int yPos;
    int xPos;
    boolean isCellAlive;
    ArrayList neighbors = new ArrayList<Cell>();

    public Cell(){

    }
    public Cell(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Cell(int xPos, int yPos, boolean isCellAlive) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isCellAlive = isCellAlive;
    }



    public void addNeighbor(Cell cell){
        neighbors.add(cell);
    }

    public void removeNeighbor(Cell cell){
        neighbors.remove(cell);
    }

    public ArrayList getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList neighbors) {
        this.neighbors = neighbors;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }


    public boolean getCellAlive(){
        return this.isCellAlive;
    }

    public void setCellAlive(boolean isCellAlive){
        this.isCellAlive = isCellAlive;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "yPos=" + yPos +
                ", xPos=" + xPos +
                ", isCellAlive=" + isCellAlive +
                '}';
    }

    @Override
    public boolean isolation(){
        return true;
    }

    @Override
    public boolean bornwhenthreeneighbors() {
        return true;
    }


}
