package model.dungeon;

import model.Utility;
import model.dungeon.tile.Cell;
import model.dungeon.tile.room.Room;

public class Dungeon {

    /** The dungeon/maze made of Rooms. */
    private final Cell[][] myMaze;

    private int myHeroRow;
    private int myHeroCol;

    /**
     * Constructor for the dungeon that creates a new dungeon
     * and fills it using private methods.
     * @param theSize The dimension of the dungeon.
     */
    public Dungeon(int theSize) {

        // Initialize the Dungeon
        myMaze = new DungeonGenerator(theSize, theSize).generate();

//        // Generate spaces in each room to signify an empty room.
//        generateEmptyDungeon();
//
//        // Generate an entrance and an exit for the Dungeon.
//        generateEntranceAndExit();
//
//        // Generate the 4 Pillars of OO.
//        generatePillars();
//
//        // Place the character at the start.
//        placeCharacter();

    }

//    /**
//     * The row the Hero is currently in.
//     * @return Returns an integer of the row.
//     */
//    public int getHeroRow() {
//        return myHeroRow;
//    }
//
//    /**
//     * The column the Hero is currently in.
//     * @return Returns an integer of the row.
//     */
//    public int getHeroCol() {
//        return myHeroCol;
//    }
//
//    /**
//     * Temporary solution for placing the character in the dungeon.
//     * There is definitely a better way of doing this, so this is
//     * just a band-aid fix for now.
//     */
//    private void placeCharacter() {
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze.length; col++) {
//                if (myMaze[row][col].getContents() != 'i') continue;
//
//                myHeroRow = row;
//                myHeroCol = col;
//                break;
//
//            }
//        }
//    }
//
//    /**
//     * Fills the initial dungeon with empty rooms.
//     */
//    private void generateEmptyDungeon() {
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze.length; col++) {
//                myMaze[row][col] = new Room(' ');
//            }
//        }
//    }
//
//    /**
//     * Calls a helper method to place an entrance (i) and exit (O).
//     */
//    private void generateEntranceAndExit() {
//        addEdge('i');
//        addEdge('O');
//    }
//
//    /**
//     * Generates edge rooms for a given symbol.
//     * @param theSymbol The symbol used to denote a specific room.
//     */
//    private void addEdge(final char theSymbol) {
//        int borderNESW = Utility.RANDOM.nextInt(4);
//        int roomTile = Utility.RANDOM.nextInt(myMaze.length);
//
//        // Re-rolls room tile and border to make sure i's and O's can't
//        // spawn on top of each other.
//        while (myMaze[0][roomTile].getContents() != ' '
//                || myMaze[roomTile][0].getContents() != ' '
//                || myMaze[roomTile][myMaze.length - 1].getContents() != ' '
//                || myMaze[myMaze.length - 1][roomTile].getContents() != ' ') {
//            roomTile = Utility.RANDOM.nextInt(myMaze.length);
//            borderNESW = Utility.RANDOM.nextInt(4);
//        }
//        // Door spawns on the top border.
//        if (borderNESW == 0) {
//            myMaze[0][roomTile] = new Room(theSymbol);
//        }
//        // Door spawns on the bottom border.
//        else if (borderNESW == 1) {
//            myMaze[myMaze.length - 1][roomTile] = new Room(theSymbol);
//        }
//        // Door spawns on the left border.
//        else if (borderNESW == 2) {
//            myMaze[roomTile][0] = new Room(theSymbol);
//        }
//        // Door spawns on the right border
//        else {
//            myMaze[roomTile][myMaze.length - 1] = new Room(theSymbol);
//        }
//    }
//
//    /**
//     * Generate pillars for Abstraction, Encapsulation,
//     * Inheritance, and Polymorphism in random, non-occupied
//     * spaces.
//     */
//    private void generatePillars() {
//        // ENUMS don't make sense with the current setup. Maybe if the
//        // maze was more generic with just "Object" but it just doesn't
//        // fit with what's trying to be achieved.
//        final char[] pillars = {'A', 'E', 'I', 'P'};
//        int count = 0;
//        int row, col;
//
//        while (count < 4) {
//            row = Utility.RANDOM.nextInt(myMaze.length);
//            col = Utility.RANDOM.nextInt(myMaze.length);
//
//            if (myMaze[row][col].getContents() == ' ') {
//                myMaze[row][col] = new Room(pillars[count]);
//                count++;
//            }
//        }
//    }
//
//    /**
//     * Prints out all the enemy spawns.
//     * Used for debugging.
//     * @return Returns of all enemy spawns.
//     */
//    public String enemySpawns() {
//        StringBuilder sr = new StringBuilder();
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze.length; col++) {
//                sr.append(" | ").append(myMaze[row][col].getEnemySpawns());
//            }
//            sr.append(" |\n");
//        }
//        return sr.toString();
//    }
//
//    /**
//     * Prints out the entire dungeon with all trap spawns.
//     * Used for debugging.
//     * @return Returns of a String with all trap spawns.
//     */
//    public String trapSpawns() {
//        StringBuilder sr = new StringBuilder();
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze.length; col++) {
//                sr.append(" | ").append(myMaze[row][col].getTrapSpawns());
//            }
//            sr.append(" |\n");
//        }
//        return sr.toString();
//    }
//
//    /**
//     * Prints out the entire dungeon with potion spawns.
//     * Used for debugging.
//     * @return Returns of a String with potion spawns.
//     */
//    public String potionSpawns() {
//        StringBuilder sr = new StringBuilder();
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze.length; col++) {
//                sr.append(" | ").append(myMaze[row][col].getPotionSpawns());
//            }
//            sr.append(" |\n");
//        }
//        return sr.toString();
//    }
//
//    /**
//     * Prints out the entire dungeon with all hints.
//     * @return Returns of a String with all hints.
//     */
//    @Override
//    public String toString() {
//        StringBuilder sr = new StringBuilder();
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze.length; col++) {
//                sr.append(" | ").append(myMaze[row][col].getContents());
//            }
//            sr.append(" |\n");
//        }
//        return sr.toString();
//    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        final String repeat = "##".repeat(Math.max(0, myMaze.length + 2)) + "\n";
        sb.append(repeat);

        for (Cell[] cells : myMaze) {
            sb.append("##");
            for (int j = 0; j < myMaze[0].length; j++) {
                sb.append(cells[j] instanceof Room ? "  " : "##");
            }
            sb.append("##\n");
        }

        sb.append(repeat);
        return sb.append('\n').toString();

    }

}
