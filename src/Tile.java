public class Tile {
    private char letter;
    private int value;

    public Tile(char letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    public Tile(Tile obj) {
        this.letter = obj.letter;
        this.value = obj.value;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setValue(int value){
        this.value = value;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getValue() {
        return this.value;
    }

    public boolean equals(Tile other) {
        return this.letter == other.letter;
    }

    public String toString() {
        return String.format("%c", letter);
    }
    
}
