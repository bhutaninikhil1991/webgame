package com.example.appengine.java8;

/**
 * keeps track of the score (integer) and player (String) for a single play of a game
 */
public class GameInstance implements Comparable<GameInstance> {

    private int score;
    private Player player;

    /**
     * constructor for GameInstance
     *
     * @return
     */
    public GameInstance(int score, Player player) {
        this.score = score;
        this.player = player;
    }

    /**
     * getter for playerId
     *
     * @return
     */
    public int getPlayerId() {
        return player.getPlayerId();
    }

    /**
     * getter for playerName
     */
    public String getPlayerName() {
        return player.getPlayerName();
    }

    /**
     * getter for score
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * implement Comparable and provide a default implementation of compareTo which compares scores
     *
     * @param gameInstance
     * @return
     */
    @Override
    public int compareTo(GameInstance gameInstance) {
        if (this.score > gameInstance.score)
            return 1;
        else if (this.score < gameInstance.score)
            return -1;
        else
            return 0;
    }

    /**
     * override toString method of object class
     *
     * @return
     */
    @Override
    public String toString() {
        return player.getPlayerName().toString() + " " + this.score;
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
        if (!(o instanceof GameInstance)) return false;
        GameInstance myGame = (GameInstance) o;
        return myGame.score == this.score && myGame.player == this.player;
    }
}
