import java.util.ArrayList;

public class TestGrid {
    public static void main(String[]args){
        Cell cell1 = new Cell(2, 4, true);
        System.out.println(cell1.toString());
        Cell cell2 = new Cell();
        cell2.setxPos(10);
        cell2.setyPos(30);
        cell2.setCellAlive(true);
        System.out.println(cell2.toString());
        Grid grid1 = new Grid(100, 100);
        grid1.addCell(cell1);
        grid1.addCell(cell2);
        ArrayList<Cell> arr = grid1.getCells();
        for (int i = 0; i < arr.size(); i++){
            System.out.println("Cells in grid: " +arr.get(i));
        }
    }
}
