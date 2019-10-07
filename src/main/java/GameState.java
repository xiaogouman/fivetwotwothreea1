import javafx.util.Pair;

import java.io.Serializable;
import java.util.Map;

public class GameState implements Serializable {
    private int[][] maze;
    private Map<String, Pair<Integer, Integer>> locations;
    private Map<String, Integer> scores;

    public int[][] getMaze(){
        return this.maze;
    }

    public Map<String, Pair<Integer, Integer>> getLocations() {
        return this.locations;
    }

    public Map<String, Integer> getScores() {
        return this.scores;
    }

}
