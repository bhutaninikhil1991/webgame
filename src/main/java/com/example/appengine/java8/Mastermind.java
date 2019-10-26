package com.example.appengine.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * class contains methods for MasterMind game
 */
public class Mastermind extends GuessingGame {
    private Player player;
    private int codeLength = 4;
    private boolean IsDuplicateAllowed = false;
    private String guess = null;
    private StringBuilder previousGuessesTable = new StringBuilder();
    public static final ArrayList<Character> coloursList = new ArrayList<Character>() {{
        add('R');
        add('G');
        add('Y');
        add('B');
        add('O');
        add('P');
    }};

    /**
     * constructor for MasterMind
     */
    public Mastermind() {
        player = new Player("MasterMindUser");
    }

    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
        GamesRecord record = mastermind.playAll();
        record.getScoringInfoForPlayer(2);
    }

    /**
     * to get code length from the user. Default code length is 4.
     */
    private void getCodeLength() {
        System.out.println("\nPlease select length of the phrase between 3-6:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (!str.matches("^[3-6]*$")) {
            System.out.println(ANSI_RED + "error: please select length of the phrase between 3-6. Try again!!" + ANSI_RESET);
            getCodeLength();
        } else {
            codeLength = Integer.valueOf(str);
        }
    }

    /**
     * to ask user whether he/she wants to allow duplicates or not
     */
    private void AllowDuplicates() {
        System.out.println("\nDo you want to allow duplicates? Y/N:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Character YesNo = str.toUpperCase().charAt(0);
        switch (YesNo) {
            case 'Y':
                IsDuplicateAllowed = true;
                break;
            case 'N':
                IsDuplicateAllowed = false;
                break;
            default:
                System.out.println(ANSI_RED + "error: Please select either Y/N." + ANSI_RESET);
                AllowDuplicates();
                break;
        }
    }

    /**
     * get random phrase
     */
    private void generateRandomPhrase() {
        String temp = "";
        //extract list of colours from colour list
        ArrayList<Character> tempColoursList = new ArrayList<>(coloursList);
        for (int i = 0; i < codeLength; i++) {
            int index = new Random().nextInt(tempColoursList.size());
            Character ch = tempColoursList.get(index);
            //if duplicates are not allowed then remove the colour from the colours
            if (!IsDuplicateAllowed)
                tempColoursList.remove(ch);
            temp += ch;
        }
        //set phrase
        setPhrase(temp);
    }

    /**
     * get guess from user
     */
    @Override
    public String getGuess() {
        System.out.println("\nEnter your guess of length " + codeLength + ", as combination of colours R, G, Y, B, O, P and "
                + (!IsDuplicateAllowed ? "NO " : "") + "duplicates are allowed:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str.toUpperCase();
    }

    /**
     * validate user input
     *
     * @param str
     * @return boolean
     */
    @Override
    public boolean validateGuess(String str) {
        boolean IsValid = false;

        if (str.length() != codeLength) {
            System.out.println(ANSI_RED + "error: Length of the input string should be " + codeLength + ANSI_RESET);
        } else if (!str.matches("^[RGBYOP]*$")) {
            System.out.println(ANSI_RED + "error: Possible colours are R, G, Y, B, O, P" + ANSI_RESET);
        } else if (!IsDuplicateAllowed && !uniqueCharacters(str)) {
            System.out.println(ANSI_RED + "error: Duplicates are not allowed" + ANSI_RESET);
        } else
            IsValid = true;

        return IsValid;
    }

    /**
     * helper function to check for unique characters
     *
     * @param str
     * @return boolean
     */
    private boolean uniqueCharacters(String str) {
        char[] chArray = str.toCharArray();
        //sort array
        Arrays.sort(chArray);

        for (int i = 0; i < chArray.length - 1; i++) {
            if (chArray[i] != chArray[i + 1])
                continue;
            else
                return false;
        }
        return true;
    }

    /**
     * process the guess
     *
     * @param guess
     */
    @Override
    void processGuess(String guess) {
        //if character is already
        if (!IsCharacterAlreadyGuessed(guess)) {
            //process the guess
            StringBuilder secretSB = new StringBuilder(getPhrase());
            StringBuilder guessSB = new StringBuilder(guess);
            int exacts = checkExacts(secretSB, guessSB);
            int partials = checkPartials(secretSB, guessSB);

            if (exacts != codeLength) {
                incrementWrongGuess();
                // display previous guess
                displayPreviousGuess(guess, exacts, partials);
            } else
                GameOver();
        } else {
            //display previous guesses
            System.out.println(previousGuessesTable.toString());
        }
    }

    /**
     * check if each color in your guess is the correct color but is NOT in the correct position in the code sequence
     *
     * @param secretSB
     * @param guessSB
     * @return int
     */
    private int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i = 0;

        int partials = 0;
        while (i < codeLength) {
            int j = 0;
            while (j < codeLength) {
                if (secretSB.charAt(i) == guessSB.charAt(j) && i != j) {
                    partials = partials + 1;
                    secretSB.setCharAt(i, '-');
                    guessSB.setCharAt(j, '*');
                }
                j++;
            }
            i++;
        }
        return partials;
    }

    /**
     * checks if each color in your guess is the correct color and is in the correct position in the code sequence
     *
     * @param secretSB
     * @param guessSB
     * @return
     */
    private int checkExacts(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i = 0;

        int exacts = 0;
        while (i < codeLength) {
            int j = 0;
            while (j < codeLength) {
                if (secretSB.charAt(i) == guessSB.charAt(j) && i == j) {
                    exacts = exacts + 1;
                    secretSB.setCharAt(i, '-');
                    guessSB.setCharAt(j, '*');
                }
                j++;
            }
            i++;
        }

        return exacts;
    }

    /**
     * to play a game
     *
     * @return
     */
    @Override
    public GameInstance play() {
        //reset game variables for new game
        resetGameVariables();
        //get game info
        getImpInfoForGame();
        //get code length from the user
        getCodeLength();
        //check whether user wants to allow duplicates
        AllowDuplicates();
        //computer picks a sequence of colors
        generateRandomPhrase();
        //print the phrase
        //System.out.println(getPhrase());
        //generate hidden phrase
        generateHiddenPhrase();
        //print hidden phrase
        System.out.println("\nHidden Phrase: " + getHiddenPhrase());
        while (!getPhrase().equals(guess)) {
            guess = getGuess();
            if (validateGuess(guess))
                processGuess(guess);
        }
        return new GameInstance(calculateScore(), player);
    }

    /**
     * display previous guesses
     *
     * @param guess
     * @param exacts
     * @param partials
     */
    private void displayPreviousGuess(String guess, int exacts, int partials) {
        String format = "\n%1$-20s|%2$-10s|%3$-10s";
        if (previousGuessesTable.toString().isEmpty()) {
            previousGuessesTable.append(String.format(format, "PreviousGuess", "Exacts", "Partials"));
            previousGuessesTable.append("\n--------------------------------------------------------");
        }
        previousGuessesTable.append(String.format(format, guess, exacts, partials));
        //display previous guesses
        System.out.println(previousGuessesTable.toString());
    }

    /**
     * reset game variables
     */
    @Override
    public void resetGameVariables() {
        super.resetGameVariables();
        previousGuessesTable = new StringBuilder();
    }

    /**
     * get game info
     */
    @Override
    public void getImpInfoForGame() {
        System.out.println("\nWelcome to MasterMind Guessing Game:");
        System.out.println("Game Instructions:");
        System.out.println("1. The computer picks a sequence of colors. The number of colors is the code length." +
                "The default code length is 4 but it can be changed when starting a new game.");
        System.out.println("2. Your guess should be a combination of colours R(red), G(Green), Y(yellow), B(blue), O(orange) or P(Purple)");
        System.out.println("3. The guess is not case sensitive i.e 'R' or 'r' means the same");
        System.out.println("4. By default, a color can be used only once in a code sequence. If you start a new game with the 'Allow duplicates' checked, " +
                "then any color can be used any number of times in the code sequence.");
        System.out.println("5. For each color in your guess that is in the correct color and correct position in the code sequence, " +
                "the computer displays its count as 'exacts' under previous Guesses table");
        System.out.println("6. For each color in your guess that is in the correct color but is NOT in the correct position in the code sequence, " +
                "the computer display its count as 'partials' under previous Guesses table");
        System.out.println("7. You win the game when you manage to guess all the colors in the code sequence and when they all in the right position");
        System.out.println("8. The score is calculated based on the number of wrong guesses made");
    }

    /**
     * override toString() method of object class
     *
     * @return String
     */
    @Override
    public String toString() {
        return player.toString();
    }

    /**
     * override equals method of object class
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mastermind)) return false;
        Mastermind myMasterMindGame = (Mastermind) o;
        return myMasterMindGame.player.getPlayerId() == this.player.getPlayerId()
                && myMasterMindGame.player.getPlayerName() == this.player.getPlayerName();
    }
}
