public class TestCell {
    public static void main(String[] args){
        //Old
        Cell cell1 = new Cell(2, 4);
        System.out.println(cell1.toString());
        Cell cell2 = new Cell();
        cell2.setxPos(10);
        cell2.setyPos(30);
        cell2.setCellAlive(true);
        cell1.addNeighbor(cell2);
        System.out.println(cell2.toString());
        System.out.println("Cell 1 neighbours: " + cell1.getNeighbors());
    }
}
