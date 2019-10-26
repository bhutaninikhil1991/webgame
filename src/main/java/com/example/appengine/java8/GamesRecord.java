package com.example.appengine.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * specific functions related to game scoring
 */
public class GamesRecord {
    private ArrayList<GameInstance> gamesRecord = new ArrayList<>();
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * adds a Game to the GamesRecord
     *
     * @param game
     */
    public void add(GameInstance game) {
        gamesRecord.add(game);
    }

    /**
     * returns the average score for all games added to the record
     *
     * @return
     */
    public float average() {
        int total = 0;
        for (GameInstance game :
                gamesRecord) {
            total += game.getScore();
        }
        return (float) total / (float) gamesRecord.size();
    }

    /**
     * returns a sorted list of the top n scores including player and score. This method should use the Collections class to sort the game instances
     *
     * @param n
     */
    public List<GameInstance> highGameList(int n) {
        if (sortDescending().size() < n)
            return sortDescending().subList(0, sortDescending().size());
        else
            return sortDescending().subList(0, n);
    }

    /**
     * helper function to sort list in descending order
     *
     * @return
     */
    private ArrayList<GameInstance> sortDescending() {
        Collections.sort(this.gamesRecord);
        return this.gamesRecord;
    }

    /**
     * contains scoring method for a single player
     *
     * @param n
     */
    public void getScoringInfoForPlayer(int n) {
        if (gamesRecord.size() == 0) {
            return;
        }

        System.out.println(ANSI_BLUE + "\nAll Game Records: " + ANSI_RESET);
        displayGamesRecordsAsTable(gamesRecord);
        System.out.println(ANSI_BLUE + "\nTop " + n + " scores: " + ANSI_RESET);
        displayGamesRecordsAsTable(highGameList(n));
        System.out.println(ANSI_BLUE + "\nGame Average for all games: " + (float) Math.round(average() * 100) / 100 + ANSI_RESET);
    }

    /**
     * display games records in table format
     */
    private void displayGamesRecordsAsTable(List<GameInstance> gamesRecord) {
        String format = ANSI_BLUE + "|%1$-15s|%2$-10s\n" + ANSI_RESET;
        System.out.format(format, "PlayerName", "PlayerScore");
        System.out.println("------------------------------------------");
        for (GameInstance game :
                gamesRecord) {
            System.out.format(format, game.getPlayerName(), game.getScore());
        }
    }

    /**
     * override toString method of Object class
     *
     * @return
     */
    @Override
    public String toString() {
        return gamesRecord.toString();
    }

    /**
     * override equals method of Object class
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamesRecord)) return false;
        GamesRecord myRecord = (GamesRecord) o;
        if (this.gamesRecord.size() != myRecord.gamesRecord.size())
            return false;
        int n = this.gamesRecord.size() < myRecord.gamesRecord.size() ? this.gamesRecord.size() : myRecord.gamesRecord.size();
        for (int i = 0; i < n; i++) {
            if (this.gamesRecord.get(i) != myRecord.gamesRecord.get(i)) {
                return false;
            }
        }

        return true;
    }
}
