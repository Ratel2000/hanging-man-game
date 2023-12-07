import java.util.Scanner;

public class Game {
    private static final int ALPHABET_SIZE = 26;
    private static final String YES = "y";
    private static final String NO = "n";
    private static char[] latters = new char[ALPHABET_SIZE];// Array to store available letters
    private static int counterOfTrys = 0;
    private static TheChosenWord word = new TheChosenWord();

    private static void initialize() {
        word = new TheChosenWord();// Reset the chosen word
        latters = new char[ALPHABET_SIZE];// Reset available letters
        counterOfTrys = 0;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            latters[i] = (char) ('a' + i);// Fill the array with letters from 'a' to 'z'
        }
    }

    // Start the game
    public static void start() {
        initialize();
        Scanner scan = new Scanner(System.in);
        boolean play = true;
        while (play) {
            displayGameState();
            String chosenLetter = scan.next();
            counterOfTrys++;
            // Check if the input is a valid single letter
            if (isValidInput(chosenLetter)) {
                // Remove the chosen letter from the available letters
                removeChar(chosenLetter.charAt(0));
                if (word.getWord().contains(chosenLetter)) {
                    modifyUnderscore(word, chosenLetter.charAt(0));
                    // Check if the player has won
                    if (winCheck(word)) {
                        System.out.println(word.getUnderscores());
                        System.out.println("Congratulations! You guessed the word " + word.getWord() + " with " + counterOfTrys + " trys \nwanna play again y/n");
                        play = playAgain(scan);
                    } else {
                        System.out.println("Correct guess! Keep it up.");
                    }
                } else {
                    System.out.print("Incorrect guess. Try again.");
                }
            } else {
                System.out.print("your input is not acceptable enter character form character list!");
            }
        }
    }

    private static void displayGameState() {
        System.out.println();
        System.out.println("Please enter a letter from the available letters, then press enter:");
        for (char latter : latters) System.out.print(latter + " ");
        System.out.println();
        System.out.println(word.getUnderscores());
    }

    private static boolean isValidInput(String chosenLetter) {
        return chosenLetter.length() == 1 && new String(latters).contains(chosenLetter);
    }

    private static boolean playAgain(Scanner scan) {
        String goAgain;
        do {
            System.out.print("Do you want to play again? (y/n): ");
            goAgain = scan.next().toLowerCase();

            if (goAgain.equals(NO)) {
                return false;
            } else if (goAgain.equals(YES)) {
                // Start a new game
                initialize();
                return true;
            } else {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        } while (true); // Loop until a valid input is provided
    }

    private static boolean winCheck(TheChosenWord word) {
        int points = 0;
        for (int i = 0; i < word.getWord().length(); i++) {

            char[] underscoreChars = word.getUnderscores().toCharArray();
            if (underscoreChars[i] != '_') {
                points++;
            }
            if (points == word.getWord().length())
                return true;
        }

        return false;
    }

    // Modify the underscore representation based on the guessed letter
    private static void modifyUnderscore(TheChosenWord word, char newChar) {

        char[] wordCharArray = word.getWord().toCharArray();
        char[] underscoreChars = word.getUnderscores().toCharArray();

        for (int i = 0; i < word.getWord().length(); i++) {

            if (wordCharArray[i] == newChar && underscoreChars[i] != newChar) {
                underscoreChars[i] = newChar;
            }
        }
        word.setUnderscore(new String(underscoreChars));
    }

    // Remove a character from the available letters
    private static void removeChar(char charToRemove) {
        int newSize = 0;

        for (char c : latters) {
            if (c != charToRemove) {
                newSize++;
            }
        }

        if (newSize != latters.length) {
            char[] newArray = new char[newSize];
            int newIndex = 0;

            for (char c : latters) {
                if (c != charToRemove) {
                    newArray[newIndex++] = c;
                }
            }
            latters = newArray;
        }
    }

    public static void main(String[] args) {
        start();
    }
}