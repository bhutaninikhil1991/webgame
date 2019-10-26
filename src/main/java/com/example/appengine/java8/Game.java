package com.example.appengine.java8;

/**
 * loop through a set of games and recording the results
 */
public abstract class Game {

    /**
     * plays a set of games and records (and returns) a GamesRecord object for the set
     *
     * @return GamesRecord
     */
    public GamesRecord playAll() {
        GamesRecord record = new GamesRecord();
        while (this.playNext()) {
            record.add(this.play());
        }
        return record;
    }

    /**
     * plays a game and returns a GameInstance
     *
     * @return GameInstance
     */
    public abstract GameInstance play();

    /**
     * asks if the next game should be played (this will be called even to check if the first game should be played).
     *
     * @return boolean
     */
    public abstract boolean playNext();

}
