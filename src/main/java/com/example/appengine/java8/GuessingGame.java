package com.example.appengine.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * methods and data members used by any guessing game i.e. Mastermind or Hangman
 */
public abstract class GuessingGame extends Game {
    private String phrase = "";
    private StringBuilder hiddenPhrase = new StringBuilder();
    private List<String> previousGuesses;
    private int noOfWrongGuessesMade;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * getter for phrase
     *
     * @return String
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * getter for hidden phrase
     *
     * @return StringBuilder
     */
    public StringBuilder getHiddenPhrase() {
        return hiddenPhrase;
    }

    /**
     * getter for previous guesses
     *
     * @return List<Character />
     */
    public List<String> getPreviousGuesses() {
        return previousGuesses;
    }

    /**
     * setter for previous guesses
     *
     * @param previousGuesses
     */
    public void setPreviousGuesses(List<String> previousGuesses) {
        this.previousGuesses = previousGuesses;
    }

    /**
     * setter for set phrase
     *
     * @param phrase
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    /**
     * ask if the player wants to play a game
     *
     * @return boolean
     */
    @Override
    public boolean playNext() {
        boolean nextGame = false;
        System.out.println("Do you want to play a game? Y/N");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Character YesNo = str.toUpperCase().charAt(0);
        switch (YesNo) {
            case 'Y':
                nextGame = true;
                break;
            case 'N':
                break;
            default:
                System.out.println(ANSI_RED + "error: Please select either Y/N." + ANSI_RESET);
                nextGame = this.playNext();
                break;
        }
        return nextGame;
    }

    /**
     * returns the hidden phrase
     */
    public void generateHiddenPhrase() {
        for (Character ch :
                phrase.toCharArray()) {
            if (Character.isLetterOrDigit(ch))
                ch = '*';

            hiddenPhrase.append(ch);
        }
    }

    /**
     * check whether the character is previously guessed or not
     *
     * @param guess
     * @return
     */
    public boolean IsCharacterAlreadyGuessed(String guess) {
        if (previousGuesses.indexOf(guess) == -1) {
            previousGuesses.add(guess);
            return false;
        } else {
            System.out.println(ANSI_RED + "This input has already been entered before. Please refer to previous guesses." + ANSI_RESET);
            return true;
        }
    }

    /**
     * calculate score i.e return no. of wrong guesses
     *
     * @return int
     */
    public int calculateScore() {
        return noOfWrongGuessesMade;
    }

    /**
     * to print the message if user has won the game
     */
    public void GameOver() {
        System.out.println(ANSI_GREEN + "\nCongratulations!!! you won the game" + ANSI_RESET);
        System.out.println("Your Score is : " + calculateScore());
        System.out.println("The hidden phrase was : " + phrase.toString());
    }

    /**
     * to reset game variables
     */
    public void resetGameVariables() {
        //initialize phrase
        phrase = "";
        //initialize hidden phrase
        hiddenPhrase = new StringBuilder();
        //initialize previous guesses
        previousGuesses = new ArrayList<>();
        //initialize no. of wrong guesses
        noOfWrongGuessesMade = 0;
    }

    /**
     * increment wrong guess count
     */
    public void incrementWrongGuess() {
        noOfWrongGuessesMade++;
    }

    /**
     * get user input
     *
     * @return
     */
    abstract String getGuess();

    /**
     * to validate the user guess
     *
     * @param str
     * @return
     */
    abstract boolean validateGuess(String str);

    /**
     * to process the user guess
     *
     * @param guess
     */
    abstract void processGuess(String guess);

    /**
     * list out game rules
     */
    abstract void getImpInfoForGame();

    /**
     * override toString method of object class
     *
     * @return String
     */
    @Override
    public String toString() {
        return previousGuesses.toString();
    }

    /**
     * override equals method of object class
     *
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuessingGame)) return false;
        GuessingGame myGuessingGame = (GuessingGame) o;
        if (this.previousGuesses.size() != myGuessingGame.previousGuesses.size())
            return false;
        int n = this.previousGuesses.size() < myGuessingGame.previousGuesses.size() ? this.previousGuesses.size() : myGuessingGame.previousGuesses.size();
        for (int i = 0; i < n; i++) {
            if (this.previousGuesses.get(i) != myGuessingGame.previousGuesses.get(i)) {
                return false;
            }
        }

        return true;
    }
}
