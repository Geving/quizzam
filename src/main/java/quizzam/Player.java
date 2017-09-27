package quizzam;

public class Player {
    private String ID;
    private String nick;
    private String currentGameID;
    private int score;
    private int totalGames;
    private int totalWins;

    public Player(){

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCurrentGameID() {
        return currentGameID;
    }

    public void setCurrentGameID(String currentGameID) {
        this.currentGameID = currentGameID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }
}
