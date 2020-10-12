public class GameOfLifeOld {

    public static void main(String[]args) {
        Grid grid = new Grid(200, 200);
            //Evolution evolutionManager = new Evolution(400, 250, 4);
            //evolutionManager.start();

        /*Cell cell1 = new Cell(4, 4, true);
        Cell cell2 = new Cell(10, 10, false);
        Cell cell3 = new Cell(12, 12, false);
        Cell cell4 = new Cell(13, 13, false);
        Cell cell5 = new Cell(14, 14, false);
        Cell cell6 = new Cell(15, 15, false);
        Cell cell7 = new Cell(16,16, false);
        Cell cell8 = new Cell(17, 17, false);
        cell1.addNeighbor(cell2);
        cell2.addNeighbor(cell1);
        cell2.addNeighbor(cell3);
        cell2.addNeighbor(cell4);
        System.out.println(cell2.getNeighbors().toString());*/
        /*Grid grid = new Grid(10, 10);
        grid.cells.add(cell1);
        grid.cells.add(cell2);
        grid.cells.add(cell3);
        grid.cells.add(cell4);
        grid.cells.add(cell5);
        grid.cells.add(cell6);
        grid.cells.add(cell7);
        grid.cells.add(cell8);*/
        //generate cells
        //kill if cell has less than 2 neighbors
        //live if cell has exactly 3 neighbors
    }
}
