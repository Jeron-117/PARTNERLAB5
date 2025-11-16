/**
 * Scrabble Game 
 * Lab 5 Partner Group 10
 * 
 * This program simulates a simplified Scrabble game where a player can draw tiles,
 * form words, and make points based on the standard Scrabble tile values and
 * distribution. The game continues until the player chooses to quit or runs out
 * of tiles.
 * 
 * @author Jeron Mentecillo
 * @author Kwame Puryear
 * @date November 16, 2025
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Tile> tileBag = new ArrayList<Tile>();
    private static ArrayList<Tile> playerHand = new ArrayList<Tile>();
    private static int totalScore = 0;
    private static Random randomGenerator = new Random();
    
    /**
     * Main method that runs the Scrabble game. Handles game initialization,
     * main game loop, user input processing, and game termination.
     * 
     * @param args command line arguments
     * @throws Exception for any unexpected errors during execution
     */
    public static void main(String[] args) throws Exception {
        Scanner inputScanner = new Scanner(System.in);
        
        System.out.println("Get ready to Scrabble!");
        
        // Initialize tile bag with proper Scrabble distribution
        createAllTiles();
        
        // Draw initial 7 tiles
        drawTiles(7);
        
        boolean keepPlaying = true;
        
        while (keepPlaying) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("Your awesome current tiles:");
            displayHand();
            System.out.println("Total score so far: " + totalScore);
            
            System.out.print("\nEnter your word (or 'quit' to exit): ");
            String userWord = inputScanner.nextLine().toUpperCase().trim();
            
            if (userWord.equals("QUIT")) {
                keepPlaying = false;
                continue;
            }
            
            if (userWord.isEmpty()) {
                System.out.println("Please enter a word!");
                continue;
            }
            
            // Validate and play the word
            if (isValidWord(userWord)) {
                int wordScore = calculateScore(userWord);
                totalScore += wordScore;
                
                System.out.println("\n✓ Great word pal! '" + userWord + "' scored " + wordScore + " points!");
                
                // Remove used tiles and draw new ones
                removeUsedTiles(userWord);
                int tilesToDraw = Math.min(userWord.length(), tileBag.size());
                drawTiles(tilesToDraw);
                
                if (tileBag.isEmpty() && playerHand.size() < 2) {
                    System.out.println("\nNo more tiles left! Game over!");
                    keepPlaying = false;
                }
            } else {
                System.out.println("\n✗ Invalid word pal! You don't have all the required tiles.");
            }
        }
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("Thanks for playing! Final score: " + totalScore);
        inputScanner.close();
    }
    
    /**
     * Creates and populates the tile bag with all tiles according to the standard
     * Scrabble distribution. Each letter has a specific count and point value.
     * 
     * @param none
     * @return void
     */
    public static void createAllTiles() {
        // Letter counts and values based on standard Scrabble
        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
                         'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int[] letterCounts = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        int[] letterValues = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letterCounts[i]; j++) {
                tileBag.add(new Tile(letters[i], letterValues[i]));
            }
        }
    }
    
    /**
     * Draws the specified number of tiles (7) randomly from the tile bag and adds
     * them to the player's hand. Stops drawing if the tile bag becomes empty.
     * 
     * @param numberOfTiles 
     * @return void
     */
    public static void drawTiles(int numberOfTiles) {
        for (int i = 0; i < numberOfTiles && !tileBag.isEmpty(); i++) {
            int randomIndex = randomGenerator.nextInt(tileBag.size());
            Tile drawnTile = tileBag.remove(randomIndex);
            playerHand.add(drawnTile);
        }
    }
    
    /**
     * Displays all tiles currently in the player's hand to the console.
     * All tiles are printed with a space, followed by a newline.
     * 
     * @param none
     * @return void
     */
    public static void displayHand() {
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.print(playerHand.get(i) + " ");
        }
        System.out.println();
    }
    
    /**
     * Validates whether the player can form the given word using tiles from
     * their current hand. Checks if all required letters are available,
     * including handling duplicate letters correctly.
     * 
     * @param wordToCheck 
     * @return boolean true if the word can be formed, otherwise false 
     */
    public static boolean isValidWord(String wordToCheck) {
        ArrayList<Tile> temporaryHand = new ArrayList<Tile>();
        
        // Create a copy of the hand to test with
        for (Tile currentTile : playerHand) {
            temporaryHand.add(new Tile(currentTile));
        }
        
        // Check each letter in the word
        for (int i = 0; i < wordToCheck.length(); i++) {
            char currentLetter = wordToCheck.charAt(i);
            boolean letterFound = false;
            
            // Look for this letter in the temp hand
            for (int j = 0; j < temporaryHand.size(); j++) {
                if (temporaryHand.get(j).getLetter() == currentLetter) {
                    temporaryHand.remove(j);
                    letterFound = true;
                    break;
                }
            }
            
            if (!letterFound) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Calculates the total point value for a given word by summing up the
     * point values of each letter tile used from the player's hand.
     * 
     * @param wordToScore
     * @return int (The total point value of the word)
     */
    public static int calculateScore(String wordToScore) {
        int calculatedScore = 0;
        
        for (int i = 0; i < wordToScore.length(); i++) {
            char currentLetter = wordToScore.charAt(i);
            
            // Find the tile value for this letter
            for (Tile currentTile : playerHand) {
                if (currentTile.getLetter() == currentLetter) {
                    calculatedScore += currentTile.getValue();
                    break;
                }
            }
        }
        
        return calculatedScore;
    }
    
    /**
     * Removes the tiles used to form the word from the player's hand.
     * For each letter in the word, removes the first matching tile found in
     * the player's hand to handle duplicate letters correctly.
     * 
     * @param playedWord
     * @return void
     */
    public static void removeUsedTiles(String playedWord) {
        for (int i = 0; i < playedWord.length(); i++) {
            char currentLetter = playedWord.charAt(i);
            
            // Find and remove this tile from the hand
            for (int j = 0; j < playerHand.size(); j++) {
                if (playerHand.get(j).getLetter() == currentLetter) {
                    playerHand.remove(j);
                    break;
                }
            }
        }
    }

}
