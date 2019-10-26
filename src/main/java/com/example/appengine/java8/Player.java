package com.example.appengine.java8;

/**
 * gets player info i.e. player Id and Name
 */
public class Player {
    private int playerId;
    private String playerName;
    private static int nextPLayerId = 1;

    /**
     * getter for playerId
     *
     * @return
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * getter for playerName
     *
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * constructor for player
     *
     * @param playername
     */
    public Player(String playername) {
        this.playerName = playername;
        this.playerId = nextPLayerId;
        nextPLayerId++;
    }

    /**
     * override toString method of object class
     *
     * @return
     */
    @Override
    public String toString() {
        return this.playerId + " " + this.playerName;
    }

    /**
     * override equals method of object class
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player myPlayer = (Player) o;
        return myPlayer.playerId == this.playerId && myPlayer.playerName == this.playerName;
    }

}
