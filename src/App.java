import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Tile> tiles = new ArrayList<Tile>();
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("get ready to scrab");
        System.out.println("Your awesome tiles: ");
        tiles = new ArrayList<Tile>();

        createAllTiles(tiles);
        ArrayList<Tile> hand = get7Tiles(tiles);

        for (int i=0; i<hand.size(); i++) {
            System.out.println(hand.get(i));
        }

        System.out.print("Your word: ");
        String makeshiftWord = in.nextLine().toUpperCase();

        for (int i=0; i<makeshiftWord.length(); i++) {
            char letter = makeshiftWord.charAt(i);
            boolean wordFinder = false;

            for (int j=0; j<hand.size(); j++) {
                if (letter == hand.get(j).getLetter()) {
                    wordFinder = true;
                    hand.remove(j);
                    break;
                }   
            }

            if (wordFinder == false) {
                System.out.println("You're exceeding your limits pal.");
            }
            else if (wordFinder == true && i == makeshiftWord.length() - 1) {
                System.out.println("That totally works!");
            }
        }

        int totalScore = 0;

        for (int k=0; k<makeshiftWord.length(); k++) {
            char letterScore = makeshiftWord.charAt(k);
            for (int l=0; l<tiles.size(); l++) {
                if (letterScore == tiles.get(l).getLetter()) {
                    totalScore += tiles.get(l).getValue();
                }
            }
        }
        System.out.println("Your current score is: " + totalScore);

        in.close();
    }
    

    public static ArrayList<Tile> createAllTiles(ArrayList<Tile> allTiles) {
        allTiles.add(new Tile('A', 1));
        allTiles.add(new Tile('B', 3));
        allTiles.add(new Tile('C', 3));
        allTiles.add(new Tile('D', 2));
        allTiles.add(new Tile('E', 1));
        allTiles.add(new Tile('F', 4));
        allTiles.add(new Tile('G', 2));
        allTiles.add(new Tile('H', 4));
        allTiles.add(new Tile('I', 1));
        allTiles.add(new Tile('J', 8));
        allTiles.add(new Tile('K', 5));
        allTiles.add(new Tile('L', 1));
        allTiles.add(new Tile('M', 3));
        allTiles.add(new Tile('N', 1));
        allTiles.add(new Tile('O', 1));
        allTiles.add(new Tile('P', 3));
        allTiles.add(new Tile('Q', 10));
        allTiles.add(new Tile('R', 1));
        allTiles.add(new Tile('S', 1));
        allTiles.add(new Tile('T', 1));
        allTiles.add(new Tile('U', 1));
        allTiles.add(new Tile('V', 4));
        allTiles.add(new Tile('W', 4));
        allTiles.add(new Tile('X', 8));
        allTiles.add(new Tile('Y', 4));
        allTiles.add(new Tile('Z', 10));

        return allTiles;
    }

    public static ArrayList<Tile> get7Tiles(ArrayList<Tile> allTiles) {
        ArrayList<Tile> setOf7Tiles = new ArrayList<Tile>();
        Random rand = new Random();

        for (int i=0; i<7; i++) {
            int randomTileNumber = rand.nextInt(26);
            setOf7Tiles.add(new Tile(allTiles.get(randomTileNumber)));
        }

        return setOf7Tiles;
    }
}
