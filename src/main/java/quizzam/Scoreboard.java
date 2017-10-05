package quizzam;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private String ID;

    //private ArrayList<Player> players;

    private String[][] scores;

    public Scoreboard() {
        this.ID=java.util.UUID.randomUUID().toString();
        this.scores = new String[4][2];
        //ScoreboardService sbs = new ScoreboardService();
        //sbs.addScoreboard(this);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void resizeScoreboard(int numberOfPlayers){
        this.scores = new String[numberOfPlayers][2];
    }

    public String[][] getScores() {
        return scores;
    }

    public void setScores(String[][] scores) {
        this.scores = scores;
    }

    public void setSingleScore(String PlayerID, int Score) {
        for(int i = 0; i < this.scores.length;i++){
            System.out.println("Checking scoreboard player " + i + " for player " + PlayerID);
            if(this.scores[i][0]==null){
                this.scores[i][0]=PlayerID;
                this.scores[i][1]= "" + Score;
                return;
            }
            if(this.scores[i][0].equalsIgnoreCase(PlayerID)){
                int currentScore = Integer.parseInt(this.scores[i][1]);
                currentScore += Score;
                this.scores[i][1]=currentScore + "";
                return;
            }
        }
    }
}
