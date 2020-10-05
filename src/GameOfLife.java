public class GameOfLife {
    public static void main(String[]args) {
        Evolution evolutionManager = new Evolution();
        evolutionManager.start();
        //generate cells
        //kill if cell has less than 2 neighbors
        //live if cell has exactly 3 neighbors
    }
}
