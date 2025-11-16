/**
 * Tile.java
 * Lab 5 Partner Group 10
 * 
 * This class represents a tile in a word game (like Scrabble) with a letter
 * and its point value. The class has the ability to create, modify, and compare 
 * tiles while ensuring letters are always stored in uppercase.
 * 
 * @author Jeron Mentecillo
 * @author Kwame Puryear
 * @date November 16, 2025
*/

import java.util.Objects;

/**
 * Represents a single tile containing a letter and its point value.
 * Letters are converted to uppercase for consistency.
 */
public class Tile {
    private char letter;
    private int value;

    /**
     * Constructs a new Tile with the specified letter and value.
     * The letter is converted to uppercase.
     * 
     * @param letter
     * @param value 
     */
    public Tile(char letter, int value) {
        this.letter = Character.toUpperCase(letter);
        this.value = value;
    }

    /**
     * Copy constructor that creates a new Tile identical to the given Tile object.
     * 
     * @param obj the Tile object to copy
     */
    public Tile(Tile obj) {
        this.letter = obj.letter;
        this.value = obj.value;
    }

    /**
     * Setter for the letter for this tile.
     * The letter is automatically converted to uppercase.
     * 
     * @param letter the new letter for this tile
     */
    public void setLetter(char letter) {
        this.letter = Character.toUpperCase(letter);
    }

    /**
     * Setter for the point value for this tile.
     * 
     * @param value the new point value for this tile
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns the letter of this tile. (always uppercase)
     * 
     * @return 
     */
    public char getLetter() {
        return this.letter;
    }

    /**
     * Returns the point value of this tile.
     * 
     * @return 
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Compares this tile with another object for equality.
     * Two tiles are considered equal if they have the same letter,
     * regardless of their point values.
     * 
     * @param obj the object to compare with this tile
     * @return true if the objects are equal, otherwise false 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null || getClass() != obj.getClass()) 
            return false;
        Tile tile = (Tile) obj;
        return letter == tile.letter;
    }

    /**
     * Returns a hash code value for this tile.
     * The hash code is based solely on the letter.
     * 
     * @return a hash code value for this tile
     */
    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }

    /**
     * Returns a string representation of this tile.
     * Shows the letter in the format "LETTER(value)"
     * 
     * @return 
     */
    @Override
    public String toString() {
        return String.format("%c(%d)", letter, value);
    }
}
